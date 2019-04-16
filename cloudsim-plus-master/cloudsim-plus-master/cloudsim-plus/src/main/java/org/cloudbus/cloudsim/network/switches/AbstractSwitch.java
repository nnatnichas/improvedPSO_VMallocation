/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */
package org.cloudbus.cloudsim.network.switches;

import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.core.CloudSimEntity;
import org.cloudbus.cloudsim.core.CloudSimTags;
import org.cloudbus.cloudsim.core.events.SimEvent;
import org.cloudbus.cloudsim.core.predicates.PredicateType;
import org.cloudbus.cloudsim.datacenters.network.NetworkDatacenter;
import org.cloudbus.cloudsim.hosts.network.NetworkHost;
import org.cloudbus.cloudsim.lists.VmList;
import org.cloudbus.cloudsim.network.HostPacket;
import org.cloudbus.cloudsim.util.Conversion;
import org.cloudbus.cloudsim.util.Log;
import org.cloudbus.cloudsim.vms.Vm;

import java.util.*;

/**
 * An base class for implementing Network Switch.
 *
 * @author Saurabh Kumar Garg
 * @author Manoel Campos da Silva Filho
 */
public abstract class AbstractSwitch extends CloudSimEntity implements Switch {

    /**
     * Map of packets sent to Datacenter on the uplink, where each key is a switch
     * and the corresponding value is the list of packets to sent to that switch.
     */
    private final Map<Switch, List<HostPacket>> uplinkSwitchPacketMap;

    /**
     * Map of packets sent to Datacenter on the downlink, where each key is a
     * switch and the corresponding value is the list of packets to sent to that switch.
     */
    private final Map<Switch, List<HostPacket>> downlinkSwitchPacketMap;

    /**
     * List of hosts connected to the switch.
     */
    private final List<NetworkHost> hostList;

    /**
     * List of uplink Datacenter.
     */
    private final List<Switch> uplinkSwitches;

    /**
     * List of downlink Datacenter.
     */
    private final List<Switch> downlinkSwitches;

    /**
     * Map of packets sent to hosts connected in the switch, where each key is a
     * host and the corresponding value is the list of packets to sent to that host.
     */
    private final Map<NetworkHost, List<HostPacket>> packetToHostMap;

    /**
     * @see #getUplinkBandwidth()
     */
    private double uplinkBandwidth;

    /**
     * @see #getDownlinkBandwidth()
     */
    private double downlinkBandwidth;

    /**
     * @see #getPorts()
     */
    private int ports;

    /**
     * @see #getDatacenter()
     */
    private NetworkDatacenter datacenter;

    private final List<HostPacket> packetList;

    /**
     * @see #getSwitchingDelay()
     */
    private double switchingDelay;

    public AbstractSwitch(CloudSim simulation, NetworkDatacenter dc) {
        super(simulation);
        this.packetList = new ArrayList<>();
        this.hostList = new ArrayList<>();
        this.packetToHostMap = new HashMap<>();
        this.uplinkSwitchPacketMap = new HashMap<>();
        this.downlinkSwitchPacketMap = new HashMap<>();
        this.downlinkSwitches = new ArrayList<>();
        this.uplinkSwitches = new ArrayList<>();
        this.datacenter = dc;
    }

    @Override
    protected void startEntity() {
        Log.printConcatLine(getName(), " is starting...");
        schedule(getId(), 0, CloudSimTags.DATACENTER_LIST_REQUEST);
    }

    @Override
    public void processEvent(SimEvent ev) {
        switch (ev.getTag()) {
            case CloudSimTags.NETWORK_EVENT_UP:
                // process the packet from down switch or host
                processPacketUp(ev);
            break;
            case CloudSimTags.NETWORK_EVENT_DOWN:
                // process the packet from uplink
                processPacketDown(ev);
            break;
            case CloudSimTags.NETWORK_EVENT_SEND:
                processPacketForward(ev);
            break;
            case CloudSimTags.NETWORK_EVENT_HOST:
                processHostPacket(ev);
            break;
            case CloudSimTags.NETWORK_HOST_REGISTER:
                registerHost(ev);
            break;
            default:
                processOtherEvent(ev);
            break;
        }
    }

    /**
     * Process a packet sent to a host.
     *
     * @param ev The packet sent.
     */
    protected void processHostPacket(SimEvent ev) {
        final HostPacket pkt = (HostPacket) ev.getData();
        final NetworkHost host = pkt.getDestination();
        host.addReceivedNetworkPacket(pkt);
    }

    /**
     * Sends a packet to Datacenter connected through a downlink port.
     *
     * @param ev Event/packet to process
     */
    protected void processPacketDown(SimEvent ev) {
        // packet coming from up level router
        // has to send downward.
        // check which switch to forward to
        // add packet in the switch list
        // add packet in the host list
        // int src=ev.getSource();
        getSimulation().cancelAll(getId(), new PredicateType(CloudSimTags.NETWORK_EVENT_SEND));
        schedule(getId(), getSwitchingDelay(), CloudSimTags.NETWORK_EVENT_SEND);
    }

    /**
     * Gets the Host where a VM is placed.
     * @param vm the VM to get its Host
     * @return the Host where the VM is placed
     */
    protected NetworkHost getVmHost(Vm vm) {
        return (NetworkHost)vm.getHost();
    }

    /**
     * Sends a packet to Datacenter connected through a uplink port.
     *
     * @param ev Event/packet to process
     */
    protected void processPacketUp(SimEvent ev) {
        // packet coming from down level router has to be sent up.
        // check which switch to forward to and add packet in the switch list
        getSimulation().cancelAll(getId(), new PredicateType(CloudSimTags.NETWORK_EVENT_SEND));
        schedule(getId(), switchingDelay, CloudSimTags.NETWORK_EVENT_SEND);
    }

    /**
     * Register a host that is connected to the switch.
     *
     * @param ev the event containing the host to be registered
     */
    private void registerHost(SimEvent ev) {
        final NetworkHost host = (NetworkHost) ev.getData();
        hostList.add(host);
    }

    /**
     * Process non-default received events that aren't processed by the
     * {@link #processEvent(SimEvent)} method. This
     * method should be overridden by subclasses in other to process new defined
     * events.
     *
     * @param ev the event to be processed
     */
    protected void processOtherEvent(SimEvent ev) {/**/}

    /**
     * Sends a packet to hosts connected to the switch
     *
     * @param ev Event/packet to process
     */
    protected void processPacketForward(SimEvent ev) {
        forwardPacketsToDownlinkSwitches();
        forwardPacketsToUplinkSwitches();
        forwardPacketsToHosts();
    }

    /**
     * Gets the list of packets to be sent to each Downlink AbstractSwitch
     * and forward them.
     *
     * @see #downlinkSwitchPacketMap
     */
    private void forwardPacketsToDownlinkSwitches() {
        for (final Switch destinationSwitch: downlinkSwitchPacketMap.keySet()) {
            final List<HostPacket> netPktList = getDownlinkSwitchPacketList(destinationSwitch);
            for (final HostPacket pkt : netPktList) {
                final double delay = networkDelayForPacketTransmission(pkt, downlinkBandwidth, netPktList);
                this.send(destinationSwitch.getId(), delay, CloudSimTags.NETWORK_EVENT_DOWN, pkt);
            }
            netPktList.clear();
        }
    }

    /**
     * Gets the list of packets to be sent to each Uplink Switch
     * and forward them.
     *
     * @see #uplinkSwitchPacketMap
     */
    private void forwardPacketsToUplinkSwitches() {
        for (final Switch destinationSwitch : uplinkSwitchPacketMap.keySet()) {
            final List<HostPacket> packetList = getUplinkSwitchPacketList(destinationSwitch);
            for(final HostPacket pkt: packetList) {
                final double delay = networkDelayForPacketTransmission(pkt, uplinkBandwidth, packetList);
                this.send(destinationSwitch.getId(), delay, CloudSimTags.NETWORK_EVENT_UP, pkt);
            }
            packetList.clear();
        }
    }

    /**
     * Gets the list of packets to be sent to each Host
     * and forward them.
     *
     * @see #packetToHostMap
     */
    private void forwardPacketsToHosts() {
        for (final NetworkHost host : packetToHostMap.keySet()) {
            final List<HostPacket> packetList = getHostPacketList(host);
            for (final HostPacket pkt: packetList) {
                final double delay = networkDelayForPacketTransmission(pkt, downlinkBandwidth, packetList);
                this.send(getId(), delay, CloudSimTags.NETWORK_EVENT_HOST, pkt);
            }
            packetList.clear();
        }
    }

    /**
     * Computes the network delay to send a packet through the network.
     *
     * @param netPkt     the packet to be sent
     * @param bwCapacity the total bandwidth capacity (in Megabits/s)
     * @param netPktList the list of packets waiting to be sent
     * @return the expected time to transfer the packet through the network (in seconds)
     */
    protected double networkDelayForPacketTransmission(HostPacket netPkt, double bwCapacity, List<HostPacket> netPktList) {
        return Conversion.bytesToMegaBits(netPkt.getVmPacket().getSize()) / getAvailableBwForEachPacket(bwCapacity, netPktList);
    }

    /**
     * Considering a list of packets to be sent,
     * gets the amount of available bandwidth for each packet,
     * assuming that the bandwidth is shared equally among
     * all packets, disregarding the packet size.
     *
     * @param bwCapacity the total bandwidth capacity to be shared among
     *                   the packets to be sent (in Megabits/s)
     * @param netPktList list of packets to be sent
     * @return the available bandwidth for each packet in the list of packets to send (in Megabits/s)
     * or the total bandwidth capacity if the packet list has 0 or 1 element
     */
    private double getAvailableBwForEachPacket(double bwCapacity, List<HostPacket> netPktList) {
        return (netPktList.isEmpty() ? bwCapacity : bwCapacity / netPktList.size());
    }

    /**
     * Gets the host of a given VM.
     *
     * @param vmId The id of the VM
     * @return the host of the VM
     */
    protected NetworkHost getHostOfVm(int vmId) {
        for (final NetworkHost host : hostList) {
            final Vm vm = VmList.getById(host.getVmList(), vmId);
            if (vm != Vm.NULL) {
                return host;
            }
        }

        return null;
    }

    @Override
    public void shutdownEntity() {
        Log.printConcatLine(getName(), " is shutting down...");
    }

    @Override
    public double getUplinkBandwidth() {
        return uplinkBandwidth;
    }

    @Override
    public final void setUplinkBandwidth(double uplinkBandwidth) {
        this.uplinkBandwidth = uplinkBandwidth;
    }

    @Override
    public double getDownlinkBandwidth() {
        return downlinkBandwidth;
    }

    @Override
    public final void setDownlinkBandwidth(double downlinkBandwidth) {
        this.downlinkBandwidth = downlinkBandwidth;
    }

    @Override
    public int getPorts() {
        return ports;
    }

    @Override
    public final void setPorts(int ports) {
        this.ports = ports;
    }

    @Override
    public double getSwitchingDelay() {
        return switchingDelay;
    }

    @Override
    public final void setSwitchingDelay(double switchingDelay) {
        this.switchingDelay = switchingDelay;
    }

    @Override
    public List<Switch> getUplinkSwitches() {
        return uplinkSwitches;
    }

    @Override
    public List<NetworkHost> getHostList() {
        return Collections.unmodifiableList(hostList);
    }

    @Override
    public void connectHost(NetworkHost host) {
        hostList.add(host);
    }

    @Override
    public boolean disconnectHost(NetworkHost host) {
        return hostList.remove(host);
    }

    @Override
    public Map<NetworkHost, List<HostPacket>> getPacketToHostMap() {
        return Collections.unmodifiableMap(packetToHostMap);
    }

    @Override
    public List<Switch> getDownlinkSwitches() {
        return downlinkSwitches;
    }

    @Override
    public List<HostPacket> getDownlinkSwitchPacketList(Switch downlinkSwitch) {
        downlinkSwitchPacketMap.putIfAbsent(downlinkSwitch, new ArrayList<>());
        return downlinkSwitchPacketMap.get(downlinkSwitch);
    }

    @Override
    public List<HostPacket> getUplinkSwitchPacketList(Switch uplinkSwitch) {
        uplinkSwitchPacketMap.putIfAbsent(uplinkSwitch, new ArrayList<>());
        return uplinkSwitchPacketMap.get(uplinkSwitch);
    }

    @Override
    public List<HostPacket> getHostPacketList(NetworkHost host) {
        packetToHostMap.putIfAbsent(host, new ArrayList<>());
        return packetToHostMap.get(host);
    }

    @Override
    public Map<Switch, List<HostPacket>> getUplinkSwitchPacketMap() {
        return Collections.unmodifiableMap(uplinkSwitchPacketMap);
    }

    @Override
    public void addPacketToBeSentToDownlinkSwitch(Switch downlinkSwitch, HostPacket packet) {
        getDownlinkSwitchPacketList(downlinkSwitch).add(packet);
    }

    @Override
    public void addPacketToBeSentToUplinkSwitch(Switch uplinkSwitch, HostPacket packet) {
        getUplinkSwitchPacketList(uplinkSwitch).add(packet);
    }

    @Override
    public void addPacketToBeSentToHost(NetworkHost host, HostPacket packet) {
        getHostPacketList(host).add(packet);
    }

    @Override
    public NetworkDatacenter getDatacenter() {
        return datacenter;
    }

    @Override
    public void setDatacenter(NetworkDatacenter datacenter) {
        this.datacenter = datacenter;
    }

    @Override
    public List<HostPacket> getPacketList() {
        return packetList;
    }

    /**
     * Gets the {@link EdgeSwitch} that the Host where the VM is placed is connected to.
     * @param vm the VM to get the Edge Switch
     * @return the connected Edge Switch
     */
    protected EdgeSwitch getVmEdgeSwitch(Vm vm) {
        return ((NetworkHost)vm.getHost()).getEdgeSwitch();
    }
}
