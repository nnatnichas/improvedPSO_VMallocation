/*
 * CloudSim Plus: A modern, highly-extensible and easier-to-use Framework for
 * Modeling and Simulation of Cloud Computing Infrastructures and Services.
 * http://cloudsimplus.org
 *
 *     Copyright (C) 2015-2016  Universidade da Beira Interior (UBI, Portugal) and
 *     the Instituto Federal de Educação Ciência e Tecnologia do Tocantins (IFTO, Brazil).
 *
 *     This file is part of CloudSim Plus.
 *
 *     CloudSim Plus is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     CloudSim Plus is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with CloudSim Plus. If not, see <http://www.gnu.org/licenses/>.
 */
package org.cloudsimplus.builders;

import java.util.List;
import java.util.Objects;

import org.cloudbus.cloudsim.brokers.DatacenterBroker;
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple;

/**
 * <p>A class that implements the Decorator Design Pattern in order to
 * include functionalities in a existing class.
 * It is used to ensure that specific methods are called only after
 * a given method is called.</p>
 *
 * For instance, the methods {@link #getVmBuilder()} and
 * {@link #getCloudletBuilder()} can only be called after
 * some {@link DatacenterBrokerSimple} was created by calling
 * the method {@link #createBroker()}.<br>
 * By this way, after the method is called, it returns
 * an instance of this decorator that allow
 * chained call to the specific decorator methods
 * as the following example:
 * <ul><li>{@link #createBroker() createBroker()}.{@link #getVmBuilder() getVmBuilder()}</li></ul>
 *
 * @author Manoel Campos da Silva Filho
 * @since CloudSim Plus 1.0
 */
public class BrokerBuilderDecorator implements BrokerBuilderInterface {
    private final BrokerBuilder builder;
    private final VmBuilder vmBuilder;
    private final CloudletBuilder cloudletBuilder;
    private final DatacenterBroker broker;

    public BrokerBuilderDecorator(final BrokerBuilder builder, final DatacenterBrokerSimple broker) {
        if(Objects.isNull(builder)) {
            throw new IllegalArgumentException("The builder parameter cannot be null.");
        }
        if(Objects.isNull(broker)) {
            throw new IllegalArgumentException("The broker parameter cannot be null.");
        }
        this.builder = builder;
        this.broker = broker;

        this.vmBuilder = new VmBuilder(broker);
        this.cloudletBuilder = new CloudletBuilder(this, broker);
    }

    @Override
    public BrokerBuilderDecorator createBroker() {
        return builder.createBroker();
    }

    @Override
    public DatacenterBroker findBroker(int id) {
        return builder.findBroker(id);
    }

    @Override
    public List<DatacenterBroker> getBrokers() {
        return builder.getBrokers();
    }

    @Override
    public DatacenterBroker get(int index) {
       return builder.get(index);
    }

    /**
     * @return the VmBuilder in charge of creating VMs
     * to the latest DatacenterBroker created by this BrokerBuilder
     */
    public VmBuilder getVmBuilder() {
        return vmBuilder;
    }

    /**
     * @return the CloudletBuilder in charge of creating Cloudlets
     * to the latest DatacenterBroker created by this BrokerBuilder
     */
    public CloudletBuilder getCloudletBuilder() {
        return cloudletBuilder;
    }

    /**
     * @return the latest created broker
     */
    public DatacenterBroker getBroker() {
        return broker;
    }

}
