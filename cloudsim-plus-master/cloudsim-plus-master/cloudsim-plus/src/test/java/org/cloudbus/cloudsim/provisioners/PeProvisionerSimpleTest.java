/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */
package org.cloudbus.cloudsim.provisioners;

import org.cloudbus.cloudsim.resources.PeSimple;
import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.vms.VmSimpleTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author	Anton Beloglazov
 * @since	CloudSim Toolkit 2.0
 */
public class PeProvisionerSimpleTest {

    private static final double MIPS = 1000;
    public static final double THREE_FORTH_MIPS = MIPS * 3 / 4.0;
    private static final long HALF_MIPS = (long)(MIPS / 2.0);
    private static final long ONE_FORTH_MIPS = (long)(MIPS / 4.0);

    private PeProvisionerSimple peProvisioner;

    @Before
    public void setUp() throws Exception {
        peProvisioner = new PeProvisionerSimple();
        new PeSimple(MIPS, peProvisioner);
    }

    @Test
    public void testGetAvailableMips() {
        assertEquals(MIPS, peProvisioner.getAvailableResource(), 0);
    }

    @Test
    public void testGetTotalAllocatedMips() {
        assertEquals(0, peProvisioner.getTotalAllocatedResource(), 0);
    }

    @Test
    public void testGetUtilization() {
        assertEquals(0, peProvisioner.getUtilization(), 0);
    }

    @Test
    public void testAllocateMipsForVm() {
        final Vm vm1 = VmSimpleTest.createVm(0, HALF_MIPS, 1);
        final Vm vm2 = VmSimpleTest.createVm(1, HALF_MIPS, 1);
        final Vm vm3 = VmSimpleTest.createVm(2, HALF_MIPS, 2);

        assertTrue(peProvisioner.allocateResourceForVm(vm1, HALF_MIPS));
        assertEquals(HALF_MIPS, peProvisioner.getAvailableResource(), 0);
        assertEquals(HALF_MIPS, peProvisioner.getTotalAllocatedResource(), 0);
        assertEquals(0.5, peProvisioner.getUtilization(), 0);

        assertTrue(peProvisioner.allocateResourceForVm(vm2, ONE_FORTH_MIPS));
        assertEquals(ONE_FORTH_MIPS, peProvisioner.getAvailableResource(), 0);
        assertEquals(THREE_FORTH_MIPS, peProvisioner.getTotalAllocatedResource(), 0);
        assertEquals(0.75, peProvisioner.getUtilization(), 0);

        assertFalse(peProvisioner.allocateResourceForVm(vm3, HALF_MIPS));
        assertEquals(ONE_FORTH_MIPS, peProvisioner.getAvailableResource(), 0);
        assertEquals(THREE_FORTH_MIPS, peProvisioner.getTotalAllocatedResource(), 0);
        assertEquals(0.75, peProvisioner.getUtilization(), 0);

        peProvisioner.deallocateResourceForVm(vm1);
        peProvisioner.deallocateResourceForVm(vm2);

        assertTrue(peProvisioner.allocateResourceForVm(vm3, ONE_FORTH_MIPS));
        assertEquals(THREE_FORTH_MIPS, peProvisioner.getAvailableResource(), 0);
        assertEquals(ONE_FORTH_MIPS, peProvisioner.getTotalAllocatedResource(), 0);
        assertEquals(0.25, peProvisioner.getUtilization(), 0);

        //Allocating the same amount doesn't change anything
        assertTrue(peProvisioner.allocateResourceForVm(vm3, ONE_FORTH_MIPS));
        assertEquals(THREE_FORTH_MIPS, peProvisioner.getAvailableResource(), 0);
        assertEquals(ONE_FORTH_MIPS, peProvisioner.getTotalAllocatedResource(), 0);
        assertEquals(0.25, peProvisioner.getUtilization(), 0);
    }

    @Test
    public void testDeallocateMipsForVM() {
        Vm vm1 = VmSimpleTest.createVm(0, HALF_MIPS, 1);
        Vm vm2 = VmSimpleTest.createVm(1, HALF_MIPS, 1);

        peProvisioner.allocateResourceForVm(vm1, HALF_MIPS);
        peProvisioner.allocateResourceForVm(vm2, ONE_FORTH_MIPS);

        assertEquals(ONE_FORTH_MIPS, peProvisioner.getAvailableResource(), 0);

        peProvisioner.deallocateResourceForVm(vm1);

        assertEquals(THREE_FORTH_MIPS, peProvisioner.getAvailableResource(), 0);

        peProvisioner.deallocateResourceForVm(vm2);

        assertEquals(MIPS, peProvisioner.getAvailableResource(), 0);
    }

}
