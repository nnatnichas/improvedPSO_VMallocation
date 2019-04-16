/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.cloudbus.cloudsim.utilizationmodels;

import org.cloudbus.cloudsim.core.Simulation;

import java.util.Objects;

/**
 * An abstract implementation of {@link UtilizationModel}.
 * @author Manoel Campos da Silva Filho
 * @since CloudSim Plus 1.2
 */
public abstract class UtilizationModelAbstract implements UtilizationModel {
    /**
     * A constant which indicates that values lower or equal to this value
     * will be considered as zero.
     */
    public static final double ALMOST_ZERO = 0.0000000001;

    private Simulation simulation;
    private Unit unit;

    public UtilizationModelAbstract(){
        this(Unit.PERCENTAGE);
    }

    public UtilizationModelAbstract(Unit unit){
        this.simulation = Simulation.NULL;
        this.setUnit(unit);
    }

    @Override
    public Simulation getSimulation() {
        return simulation;
    }

    @Override
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets the {@link Unit} in which the resource utilization is defined.
     * @param unit {@link Unit} to set
     * @return
     */
    protected final UtilizationModel setUnit(Unit unit) {
        Objects.requireNonNull(unit);
        this.unit = unit;
        return this;
    }

    @Override
    public UtilizationModel setSimulation(Simulation simulation) {
        Objects.requireNonNull(simulation);
        this.simulation = simulation;
        return this;
    }

    @Override
    public double getUtilization() {
        return getUtilization(simulation.clock());
    }

    /**
     * Checks if a given field has a valid value, considering that the minimum value is zero.
     * @param fieldName the name of the field to display at the Exception when the value is invalid
     * @param fieldValue the current value of the field
     */
    protected void validateUtilizationField(String fieldName, double fieldValue) {
        validateUtilizationField(fieldName, fieldValue, 0);
    }

    protected void validateUtilizationField(String fieldName, double fieldValue, double minValue) {
        minValue = (minValue <= ALMOST_ZERO ? 0 : minValue);
        if(fieldValue < minValue) {
            throw new IllegalArgumentException(fieldName + " cannot be lower than " + minValue);
        }
    }

}
