.. java:import:: org.cloudbus.cloudsim.cloudlets Cloudlet

.. java:import:: org.cloudbus.cloudsim.network VmPacket

.. java:import:: org.cloudbus.cloudsim.schedulers.cloudlet CloudletScheduler

.. java:import:: org.cloudbus.cloudsim.vms Vm

.. java:import:: java.util Collections

.. java:import:: java.util List

PacketSchedulerNull
===================

.. java:package:: org.cloudbus.cloudsim.schedulers.cloudlet.network
   :noindex:

.. java:type:: final class PacketSchedulerNull implements PacketScheduler

   A class that implements the Null Object Design Pattern for \ :java:ref:`PacketScheduler`\  class.

   :author: Manoel Campos da Silva Filho

   **See also:** :java:ref:`PacketScheduler.NULL`

Methods
-------
addPacketToListOfPacketsSentFromVm
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean addPacketToListOfPacketsSentFromVm(VmPacket pkt)
   :outertype: PacketSchedulerNull

clearVmPacketsToSend
^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void clearVmPacketsToSend()
   :outertype: PacketSchedulerNull

getVm
^^^^^

.. java:method:: @Override public Vm getVm()
   :outertype: PacketSchedulerNull

getVmPacketsToSend
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public List<VmPacket> getVmPacketsToSend()
   :outertype: PacketSchedulerNull

isTimeToUpdateCloudletProcessing
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean isTimeToUpdateCloudletProcessing(Cloudlet cloudlet)
   :outertype: PacketSchedulerNull

   {@inheritDoc}

   :param cloudlet: {@inheritDoc}
   :return: always returns true to indicate that if this NULL Object is being used, no network packets will be processed by the \ :java:ref:`CloudletScheduler`\  that this object is assigned to. By this way, the processing of Cloudlets must be always updated because the Cloudlet doesn't have to wait for packets dispatch or reception.

processCloudletPackets
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void processCloudletPackets(Cloudlet cloudlet, double currentTime)
   :outertype: PacketSchedulerNull

setVm
^^^^^

.. java:method:: @Override public void setVm(Vm vm)
   :outertype: PacketSchedulerNull

