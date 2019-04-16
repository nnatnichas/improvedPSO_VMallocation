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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.cloudbus.cloudsim.brokers.DatacenterBroker;
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple;

/**
 * A Builder class to createBroker {@link DatacenterBrokerSimple} objects.
 *
 * @author Manoel Campos da Silva Filho
 * @since CloudSim Plus 1.0
 */
public class BrokerBuilder extends Builder implements BrokerBuilderInterface {
    private static final String BROKER_NAME_FORMAT = "Broker%d";
    private final List<DatacenterBroker> brokers;
    private final SimulationScenarioBuilder scenario;

    public BrokerBuilder(SimulationScenarioBuilder scenario) {
        super();
        this.scenario = scenario;
        this.brokers = new ArrayList<>();
    }

    @Override
    public BrokerBuilderDecorator createBroker() {
        final DatacenterBrokerSimple broker = new DatacenterBrokerSimple(scenario.getSimulation());
        brokers.add(broker);
        return new BrokerBuilderDecorator(this, broker);
    }

    @Override
    public List<DatacenterBroker> getBrokers() {
        return brokers;
    }

    @Override
    public DatacenterBroker get(final int index) {
        return brokers.get(index);
    }

    @Override
    public DatacenterBroker findBroker(final int id) {
        return brokers.stream()
            .filter(b -> b.getId() == id)
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException(String.format("There isn't a broker with id %d", id)));
    }
}

