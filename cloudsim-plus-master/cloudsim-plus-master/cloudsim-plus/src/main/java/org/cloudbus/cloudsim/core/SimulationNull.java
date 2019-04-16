package org.cloudbus.cloudsim.core;

import org.cloudbus.cloudsim.core.events.SimEvent;
import org.cloudbus.cloudsim.datacenters.Datacenter;
import org.cloudbus.cloudsim.network.topologies.NetworkTopology;
import org.cloudsimplus.listeners.EventInfo;
import org.cloudsimplus.listeners.EventListener;

import java.util.*;
import java.util.function.Predicate;

/**
 * A class that implements the Null Object Design Pattern for {@link Simulation}
 * class.
 *
 * @author Manoel Campos da Silva Filho
 * @see Simulation#NULL
 */
final class SimulationNull implements Simulation {
    @Override public void abort() {/**/}
    @Override public void addEntity(CloudSimEntity e) {/**/}
    @Override public SimEvent cancel(int src, Predicate<SimEvent> p) {
        return SimEvent.NULL;
    }
    @Override public boolean cancelAll(int src, Predicate<SimEvent> p) {
        return false;
    }
    @Override public double clock() { return 0.0; } 
    @Override public double clockInMinutes() { return 0.0; }    
    @Override public double clockInHours() { return 0.0; }    
    @Override public SimEvent findFirstDeferred(int dest, Predicate<SimEvent> p) {
        return SimEvent.NULL;
    }
    @Override public Calendar getCalendar() {
        return Calendar.getInstance();
    }
    @Override public int getCloudInfoServiceEntityId() {
        return 0;
    }
    @Override public Set<Datacenter> getDatacenterList() {
        return Collections.EMPTY_SET;
    }
    @Override public SimEntity getEntity(int id) {
        return SimEntity.NULL;
    }
    @Override public SimEntity getEntity(String name) {
        return SimEntity.NULL;
    }
    @Override public int getEntityId(String name) {
        return 0;
    }
    @Override public List<SimEntity> getEntityList() {
        return Collections.EMPTY_LIST;
    }
    @Override public String getEntityName(int entityId) {
        return "";
    }
    @Override public double getMinTimeBetweenEvents() {
        return 0;
    }
    @Override public int getNumEntities() {
        return 0;
    }
    @Override public boolean removeOnEventProcessingListener(EventListener<SimEvent> listener) {
        return false;
    }
    @Override public Simulation addOnSimulationPausedListener(EventListener<EventInfo> listener) {
        return this;
    }
    @Override public boolean removeOnSimulationPausedListener(EventListener<EventInfo> listener) {
        return false;
    }
    @Override public void holdEntity(int src, long delay) {/**/}
    @Override public boolean isPaused() {
        return false;
    }
    @Override public void pauseEntity(int src, double delay) {/**/}
    @Override public boolean pause() {
        return false;
    }
    @Override public boolean pause(double time) {
        return false;
    }
    @Override public boolean resume() {
        return false;
    }
    @Override public boolean isRunning() {
        return false;
    }
    @Override public SimEvent select(int dest, Predicate<SimEvent> p) {
        return SimEvent.NULL;
    }
    @Override public void send(int src, int dest, double delay, int tag, Object data) {/**/}
    @Override public void sendFirst(int src, int dest, double delay, int tag, Object data) {/**/}
    @Override public void sendNow(int src, int dest, int tag, Object data) {/**/}
    @Override public Simulation addOnEventProcessingListener(EventListener<SimEvent> listener) {
        return this;
    }
    @Override public Simulation addOnClockTickListener(EventListener<EventInfo> listener) {
        return this;
    }
    @Override public boolean removeOnClockTickListener(EventListener<EventInfo> listener) {
        return false;
    }
    @Override public double start() throws RuntimeException { return 0; }
    @Override public boolean terminate() {
        return false;
    }
    @Override public boolean terminateAt(double time) {
        return false;
    }
    @Override public void wait(CloudSimEntity src, Predicate<SimEvent> p) {/**/}
    @Override public long waiting(int dest, Predicate<SimEvent> p) {
        return 0;
    }
    @Override public NetworkTopology getNetworkTopology() {
        return NetworkTopology.NULL;
    }
    @Override public void setNetworkTopology(NetworkTopology networkTopology) {/**/}
    @Override public Map<String, SimEntity> getEntitiesByName() { return Collections.EMPTY_MAP; }
    @Override public boolean updateEntityName(String oldName) { return false; }
    @Override public long getNumberOfFutureEvents(Predicate<SimEvent> predicate) { return 0; }
}
