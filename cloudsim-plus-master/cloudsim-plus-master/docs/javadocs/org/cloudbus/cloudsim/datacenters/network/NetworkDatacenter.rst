.. java:import:: org.cloudbus.cloudsim.allocationpolicies VmAllocationPolicy

.. java:import:: org.cloudbus.cloudsim.cloudlets Cloudlet

.. java:import:: org.cloudbus.cloudsim.core CloudSim

.. java:import:: org.cloudbus.cloudsim.core CloudSimTags

.. java:import:: org.cloudbus.cloudsim.core Simulation

.. java:import:: org.cloudbus.cloudsim.core.events SimEvent

.. java:import:: org.cloudbus.cloudsim.datacenters Datacenter

.. java:import:: org.cloudbus.cloudsim.datacenters DatacenterCharacteristics

.. java:import:: org.cloudbus.cloudsim.datacenters DatacenterSimple

.. java:import:: org.cloudbus.cloudsim.network.switches AbstractSwitch

.. java:import:: org.cloudbus.cloudsim.network.switches EdgeSwitch

.. java:import:: org.cloudbus.cloudsim.network.switches Switch

.. java:import:: org.cloudbus.cloudsim.resources FileStorage

.. java:import:: org.cloudbus.cloudsim.schedulers.cloudlet CloudletScheduler

.. java:import:: org.cloudbus.cloudsim.util Log

.. java:import:: org.cloudbus.cloudsim.vms Vm

NetworkDatacenter
=================

.. java:package:: org.cloudbus.cloudsim.datacenters.network
   :noindex:

.. java:type:: public class NetworkDatacenter extends DatacenterSimple

   NetworkDatacenter class is a \ :java:ref:`Datacenter`\  whose hostList are virtualized and networked. It contains all the information about internal network. For example, which VM is connected to what switch, etc.

   Please refer to following publication for more details:

   ..

   * \ `Saurabh Kumar Garg and Rajkumar Buyya, NetworkCloudSim: Modelling Parallel Applications in Cloud Simulations, Proceedings of the 4th IEEE/ACM International Conference on Utility and Cloud Computing (UCC 2011, IEEE CS Press, USA), Melbourne, Australia, December 5-7, 2011. <http://dx.doi.org/10.1109/UCC.2011.24>`_\

   :author: Saurabh Kumar Garg, Manoel Campos da Silva Filho

Constructors
------------
NetworkDatacenter
^^^^^^^^^^^^^^^^^

.. java:constructor:: public NetworkDatacenter(Simulation simulation, DatacenterCharacteristics characteristics, VmAllocationPolicy vmAllocationPolicy)
   :outertype: NetworkDatacenter

   Creates a NetworkDatacenter with the given parameters.

   :param simulation: The CloudSim instance that represents the simulation the Entity is related to
   :param characteristics: the characteristics of the Datacenter to be created
   :param vmAllocationPolicy: the policy to be used to allocate VMs into hosts
   :throws IllegalArgumentException: when this entity has \ ``zero``\  number of PEs (Processing Elements).  No PEs mean the Cloudlets can't be processed. A CloudResource must contain one or more Machines. A Machine must contain one or more PEs.

NetworkDatacenter
^^^^^^^^^^^^^^^^^

.. java:constructor:: @Deprecated public NetworkDatacenter(CloudSim simulation, DatacenterCharacteristics characteristics, VmAllocationPolicy vmAllocationPolicy, List<FileStorage> storageList, double schedulingInterval)
   :outertype: NetworkDatacenter

   Creates a NetworkDatacenter with the given parameters.

   :param simulation: The CloudSim instance that represents the simulation the Entity is related to
   :param characteristics: the characteristics of the Datacenter to be created
   :param vmAllocationPolicy: the policy to be used to allocate VMs into hosts
   :param storageList: a List of storage elements, for data simulation
   :param schedulingInterval: the scheduling delay to process each Datacenter received event
   :throws IllegalArgumentException: when this entity has \ ``zero``\  number of PEs (Processing Elements).  No PEs mean the Cloudlets can't be processed. A CloudResource must contain one or more Machines. A Machine must contain one or more PEs.

Methods
-------
addSwitch
^^^^^^^^^

.. java:method:: public void addSwitch(Switch sw)
   :outertype: NetworkDatacenter

   Adds a \ :java:ref:`AbstractSwitch`\  to the Datacenter.

   :param sw: the AbstractSwitch to be added

getEdgeSwitch
^^^^^^^^^^^^^

.. java:method:: public List<Switch> getEdgeSwitch()
   :outertype: NetworkDatacenter

   Gets a map of all Edge Switches in the Datacenter network, where each key is the switch id and each value is the switch itself. One can design similar functions for other type of Datacenter.

getSwitchMap
^^^^^^^^^^^^

.. java:method:: public List<Switch> getSwitchMap()
   :outertype: NetworkDatacenter

   Gets a \ **read-only**\  list of network Datacenter's Switches.

processCloudletSubmit
^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override protected void processCloudletSubmit(SimEvent ev, boolean ack)
   :outertype: NetworkDatacenter

processVmCreate
^^^^^^^^^^^^^^^

.. java:method:: @Override protected boolean processVmCreate(SimEvent ev, boolean ackRequested)
   :outertype: NetworkDatacenter

