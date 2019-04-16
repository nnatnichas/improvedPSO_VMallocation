.. java:import:: java.util HashMap

.. java:import:: java.util List

.. java:import:: java.util Map

.. java:import:: java.util Objects

.. java:import:: java.util.stream LongStream

.. java:import:: org.cloudbus.cloudsim.datacenters Datacenter

.. java:import:: org.cloudbus.cloudsim.hosts Host

.. java:import:: org.cloudbus.cloudsim.provisioners ResourceProvisioner

.. java:import:: org.cloudbus.cloudsim.util Log

.. java:import:: org.cloudbus.cloudsim.vms Vm

.. java:import:: org.cloudsimplus.autoscaling VerticalVmScaling

VmAllocationPolicyAbstract
==========================

.. java:package:: org.cloudbus.cloudsim.allocationpolicies
   :noindex:

.. java:type:: public abstract class VmAllocationPolicyAbstract implements VmAllocationPolicy

   An abstract class that represents the policy used by a \ :java:ref:`Datacenter`\  to choose a \ :java:ref:`Host`\  to place or migrate a given \ :java:ref:`Vm`\ . It supports two-stage commit of reservation of hosts: first, we reserve the Host and, once committed by the customer, the VM is effectively allocated to that Host.

   Each \ :java:ref:`Datacenter`\  must to have its own instance of a \ :java:ref:`VmAllocationPolicy`\ .

   :author: Rodrigo N. Calheiros, Anton Beloglazov

Constructors
------------
VmAllocationPolicyAbstract
^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public VmAllocationPolicyAbstract()
   :outertype: VmAllocationPolicyAbstract

   Creates a new VmAllocationPolicy object.

Methods
-------
addUsedPes
^^^^^^^^^^

.. java:method:: protected void addUsedPes(Vm vm)
   :outertype: VmAllocationPolicyAbstract

   Adds number used PEs for a Vm to the map between each VM and the number of PEs used.

   :param vm: the VM to add the number of used PEs to the map

getDatacenter
^^^^^^^^^^^^^

.. java:method:: @Override public Datacenter getDatacenter()
   :outertype: VmAllocationPolicyAbstract

getHostFreePesMap
^^^^^^^^^^^^^^^^^

.. java:method:: protected final Map<Host, Long> getHostFreePesMap()
   :outertype: VmAllocationPolicyAbstract

   Gets a map with the number of free PEs for each host from \ :java:ref:`getHostList()`\ .

   :return: a Map where each key is a host and each value is the number of free PEs of that host.

getHostList
^^^^^^^^^^^

.. java:method:: @Override public final <T extends Host> List<T> getHostList()
   :outertype: VmAllocationPolicyAbstract

getUsedPes
^^^^^^^^^^

.. java:method:: protected Map<Vm, Long> getUsedPes()
   :outertype: VmAllocationPolicyAbstract

   Gets the map between each VM and the number of PEs used. The map key is a VM and the value is the number of used Pes for that VM.

   :return: the used PEs map

removeUsedPes
^^^^^^^^^^^^^

.. java:method:: protected long removeUsedPes(Vm vm)
   :outertype: VmAllocationPolicyAbstract

   Removes the used PEs for a Vm from the map between each VM and the number of PEs used.

   :param vm:
   :return: the used PEs number

scaleVmVertically
^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean scaleVmVertically(VerticalVmScaling scaling)
   :outertype: VmAllocationPolicyAbstract

setDatacenter
^^^^^^^^^^^^^

.. java:method:: @Override public final void setDatacenter(Datacenter datacenter)
   :outertype: VmAllocationPolicyAbstract

   Sets the Datacenter associated to the Allocation Policy

   :param datacenter: the Datacenter to set

setHostFreePesMap
^^^^^^^^^^^^^^^^^

.. java:method:: protected final VmAllocationPolicy setHostFreePesMap(Map<Host, Long> hostFreePesMap)
   :outertype: VmAllocationPolicyAbstract

   Sets the Host free PEs Map.

   :param hostFreePesMap: the new Host free PEs map

setUsedPes
^^^^^^^^^^

.. java:method:: protected final void setUsedPes(Map<Vm, Long> usedPes)
   :outertype: VmAllocationPolicyAbstract

   Sets the used pes.

   :param usedPes: the used pes

