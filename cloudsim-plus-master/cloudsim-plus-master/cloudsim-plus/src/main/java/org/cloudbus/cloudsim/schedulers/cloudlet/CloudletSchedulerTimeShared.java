/*
 * Title: CloudSim Toolkit Description: CloudSim (Cloud Simulation) Toolkit for Modeling and
 * Simulation of Clouds Licence: GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */
package org.cloudbus.cloudsim.schedulers.cloudlet;

import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.cloudlets.CloudletExecutionInfo;
import org.cloudbus.cloudsim.schedulers.vm.VmScheduler;

import java.util.List;

/**
 * CloudletSchedulerTimeShared implements a policy of scheduling performed by a
 * virtual machine to run its {@link Cloudlet Cloudlets}. Cloudlets execute in
 * time-shared manner in VM. Each VM has to have its own instance of a
 * CloudletScheduler. <b>This scheduler does not consider Cloudlets priorities
 * to define execution order. If actual priorities are defined for Cloudlets,
 * they are just ignored by the scheduler.</b>
 *
 * <p>
 * It also does not perform a preemption process in order to move running
 * Cloudlets to the waiting list in order to make room for other already waiting
 * Cloudlets to run. It just imposes there is not waiting Cloudlet,
 * <b>oversimplifying</b> the problem considering that for a given simulation
 * second <tt>t</tt>, the total processing capacity of the processor cores (in
 * MIPS) is equally divided by the applications that are using them.
 * </p>
 *
 * <p>In processors enabled with <a href="https://en.wikipedia.org/wiki/Hyper-threading">Hyper-threading technology (HT)</a>,
 * it is possible to run up to 2 processes at the same physical CPU core.
 * However, usually just the Host operating system scheduler (a {@link VmScheduler} assigned to a Host)
 * has direct knowledge of HT to accordingly schedule up to 2 processes to the same physical CPU core.
 * Further, this scheduler implementation
 * oversimplifies a possible HT for the virtual PEs, allowing that more than 2 processes to run at the same core.</p>
 *
 * <p>Since this CloudletScheduler implementation does not account for the
 * <a href="https://en.wikipedia.org/wiki/Context_switch">context switch</a>
 * overhead, this oversimplification impacts tasks completion by penalizing
 * equally all the Cloudlets that are running on the same CPU core.
 * Other impact is that, if there are
 * Cloudlets of the same length running in the same PEs, they will finish
 * exactly at the same time. On the other hand, on a real time-shared scheduler
 * these Cloudlets will finish almost in the same time.
 * </p>
 *
 * <p>
 * As an example, consider a scheduler that has 1 PE that is able to execute
 * 1000 MI/S (MIPS) and is running Cloudlet 0 and Cloudlet 1, each of having
 * 5000 MI of length. These 2 Cloudlets will spend 5 seconds to finish. Now
 * consider that the time slice allocated to each Cloudlet to execute is 1
 * second. As at every 1 second a different Cloudlet is allowed to run, the
 * execution path will be as follows:<br>
 *
 * Time (second): 00 01 02 03 04 05<br>
 * Cloudlet (id): C0 C1 C0 C1 C0 C1<br>
 *
 * As one can see, in a real time-shared scheduler that does not define priorities
 * for applications, the 2 Cloudlets will in fact finish in different times. In
 * this example, one Cloudlet will finish 1 second after the other.
 * </p>
 *
 *
 * @author Rodrigo N. Calheiros
 * @author Anton Beloglazov
 * @author Manoel Campos da Silva Filho
 * @since CloudSim Toolkit 1.0
 * @see CloudletSchedulerCompletelyFair
 */
public class CloudletSchedulerTimeShared extends CloudletSchedulerAbstract {

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b>For this scheduler, this list is always empty, once the VM PEs
     * are shared across all Cloudlets running inside a VM. Each Cloudlet has
     * the opportunity to use the PEs for a given timeslice.</b></p>
     *
     * @return {@inheritDoc}
     */
    @Override
    public List<CloudletExecutionInfo> getCloudletWaitingList() {
        return super.getCloudletWaitingList();
    }

    /**
     * Moves a Cloudlet that was paused and has just been resumed to the
     * Cloudlet execution list.
     *
     * @param cloudlet the Cloudlet to move from the paused to the exec lit
     * @return the Cloudlet expected finish time
     */
    private double movePausedCloudletToExecListAndGetExpectedFinishTime(CloudletExecutionInfo cloudlet) {
        getCloudletPausedList().remove(cloudlet);
        addCloudletToExecList(cloudlet);
        return getEstimatedFinishTimeOfCloudlet(cloudlet, getVm().getSimulation().clock());
    }

    @Override
    public double cloudletResume(int cloudletId) {
        return getCloudletPausedList().stream()
                .filter(c -> c.getCloudletId() == cloudletId)
                .findFirst()
                .map(this::movePausedCloudletToExecListAndGetExpectedFinishTime)
                .orElse(0.0);
    }

    /**
     * This time-shared scheduler shares the CPU time between all executing
     * cloudlets, giving the same CPU timeslice for each Cloudlet to execute. It
     * always allow any submitted Cloudlets to be immediately added to the
     * execution list. By this way, it doesn't matter what Cloudlet is being
     * submitted, since it will always include it in the execution list.
     *
     * @param cloudlet the Cloudlet that will be added to the execution list.
     * @return always <b>true</b> to indicate that any submitted Cloudlet can be
     * immediately added to the execution list
     */
    @Override
    public boolean canAddCloudletToExecutionList(CloudletExecutionInfo cloudlet) {
        return true;
    }

}
