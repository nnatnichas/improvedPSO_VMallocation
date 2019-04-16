/*
 * Title: CloudSim Toolkit Description: CloudSim (Cloud Simulation) Toolkit for Modeling and
 * Simulation of Clouds Licence: GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.cloudbus.cloudsim.schedulers.vm;

import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.hosts.HostSimple;
import org.cloudbus.cloudsim.provisioners.ResourceProvisionerSimple;
import org.cloudbus.cloudsim.resources.Pe;
import org.cloudbus.cloudsim.resources.PeSimple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.vms.VmSimpleTest;

import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Anton Beloglazov
 * @since CloudSim Toolkit 2.0
 */
public class VmSchedulerTimeSharedTest {

    private static final double MIPS = 1000;
    private VmScheduler vmScheduler;
    private Vm vm0;
    private Vm vm1;

    @Before
    public void setUp() throws Exception {
        vmScheduler = createVmScheduler(MIPS, 2);
        vm0 = VmSimpleTest.createVm(0, MIPS / 4, 2);
        vm1 = VmSimpleTest.createVm(1, MIPS / 2, 2);
    }

    private VmScheduler createVmScheduler(double mips, int pesNumber) {
        VmSchedulerTimeShared scheduler = new VmSchedulerTimeShared();
        final List<Pe> peList = new ArrayList<>(pesNumber);
        LongStream.range(0, pesNumber).forEach(i -> peList.add(new PeSimple(mips, new PeProvisionerSimple())));
        final Host host = new HostSimple(2048, 20000, 20000, peList);
        host
            .setRamProvisioner(new ResourceProvisionerSimple())
            .setBwProvisioner(new ResourceProvisionerSimple())
            .setVmScheduler(scheduler)
            .setId(0);
        return scheduler;
    }

    @Test
    public void testIsSuitableForVm0() {
        final Vm vm0 = VmSimpleTest.createVm(0, MIPS / 4, 2);
        vm0.setCreated(false);
        assertTrue(vmScheduler.isSuitableForVm(vm0));
    }

    @Test
    public void testIsSuitableForVm1() {
        final Vm vm1 = VmSimpleTest.createVm(1, MIPS / 2, 2);
        vm1.setCreated(false);
        assertTrue(vmScheduler.isSuitableForVm(vm1));
    }

    @Test
    public void testIsSuitableForVm2() {
        final Vm vm2 = VmSimpleTest.createVm(2, MIPS * 2, 2);
        vm2.setCreated(false);
        assertFalse(vmScheduler.isSuitableForVm(vm2));
    }

    @Test
    public void testInit() {
        final List<Pe> peList = vmScheduler.getHost().getWorkingPeList();
        assertEquals(peList, vmScheduler.getWorkingPeList());
        assertEquals(2000, vmScheduler.getAvailableMips(), 0);
        assertEquals(1000, vmScheduler.getMaxAvailableMips(), 0);
        assertEquals(0, vmScheduler.getTotalAllocatedMipsForVm(vm0), 0);
    }

    @Test
    public void testAllocatePesForVm() {
        final List<Double> mipsShare1 = new ArrayList<>(1);
        mipsShare1.add(250.0);

        assertTrue(vmScheduler.allocatePesForVm(vm0, mipsShare1));
        final List<Pe> peList = vmScheduler.getHost().getPeList();
        assertEquals(1750, vmScheduler.getAvailableMips(), 0);
        assertEquals(1000, vmScheduler.getMaxAvailableMips(), 0);
        assertEquals(MIPS / 4, vmScheduler.getTotalAllocatedMipsForVm(vm0), 0);

        final List<Double> mipsShare2 = new ArrayList<>(2);
        mipsShare2.add(500.0);
        mipsShare2.add(125.0);

        assertTrue(vmScheduler.allocatePesForVm(vm1, mipsShare2));

        assertEquals(1125, vmScheduler.getAvailableMips(),0);
        assertEquals(875, vmScheduler.getMaxAvailableMips(),0);
        assertEquals(625, vmScheduler.getTotalAllocatedMipsForVm(vm1), 0);

        vmScheduler.deallocatePesForAllVms();

        assertEquals(2000, vmScheduler.getAvailableMips(), 0);
        assertEquals(1000, vmScheduler.getMaxAvailableMips(), 0);
        assertEquals(0, vmScheduler.getTotalAllocatedMipsForVm(vm1), 0);
    }

    @Test
    public void testAllocatePes_forVmMigrationIn() {
        vm0.setInMigration(true);

        vmScheduler.getHost().addMigratingInVm(vm0);
        final List<Pe> peList = vmScheduler.getHost().getPeList();
        assertEquals(1500, vmScheduler.getAvailableMips(), 0);
        /*While the VM is being migrated, just 10% of its requested MIPS is allocated,
        * representing the CPU migration overhead.*/
        assertEquals(50, vmScheduler.getTotalAllocatedMipsForVm(vm0), 0);

        vmScheduler.deallocatePesForAllVms();

        assertEquals(2000, vmScheduler.getAvailableMips(), 0);
        assertEquals(1000, vmScheduler.getMaxAvailableMips(), 0);
        assertEquals(0, vmScheduler.getTotalAllocatedMipsForVm(vm1), 0);
    }

    @Test
    public void testAllocatePes_forVmMigrationOut() {
        vmScheduler = createVmScheduler(MIPS, 2);
        final double vmMips = MIPS / 4;
        final Vm vm0 = VmSimpleTest.createVm(0, vmMips, 2);
        vmScheduler.getHost().addVmMigratingOut(vm0);

        final List<Double> mipsShare = new ArrayList<>(1);
        mipsShare.add(vmMips);

        vmScheduler.allocatePesForVm(vm0, mipsShare);
        assertTrue(vmScheduler.getHost().getVmsMigratingOut().isEmpty());
    }
}
