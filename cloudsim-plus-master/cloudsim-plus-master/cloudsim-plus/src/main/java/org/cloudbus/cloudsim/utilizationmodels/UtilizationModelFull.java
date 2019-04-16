/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.cloudbus.cloudsim.utilizationmodels;

/**
 * A {@link UtilizationModel} that according to which, a Cloudlet always utilizes
 * a given allocated resource from its Vm at 100%, all the time.
 *
 * @author Anton Beloglazov
 * @since CloudSim Toolkit 2.0
 */
public class UtilizationModelFull extends UtilizationModelAbstract {
    /**
     * Gets the utilization percentage (in scale from [0 to 1]) of resource at a given simulation time.
     *
     * @param time the time to get the resource usage.
     * @return Always return 1 (100% of utilization), independent of the time.
     */
    @Override
    public double getUtilization(double time) {
        return 1;
    }

    /**
     * Gets the utilization percentage (in scale from [0 to 1]) of resource at the current simulation time.
     *
     * @return Always return 1 (100% of utilization), independent of the time.
     */
    @Override
    public double getUtilization() {
        return super.getUtilization();
    }
}
