.. java:import:: java.io Serializable

.. java:import:: java.util List

.. java:import:: java.util Set

.. java:import:: org.cloudbus.cloudsim.brokers DatacenterBroker

.. java:import:: org.cloudbus.cloudsim.cloudlets Cloudlet

.. java:import:: org.cloudbus.cloudsim.cloudlets CloudletExecutionInfo

.. java:import:: org.cloudbus.cloudsim.network VmPacket

.. java:import:: org.cloudbus.cloudsim.schedulers.cloudlet.network PacketScheduler

.. java:import:: org.cloudbus.cloudsim.vms Vm

.. java:import:: org.cloudbus.cloudsim.core CloudSimTags

.. java:import:: org.cloudbus.cloudsim.datacenters DatacenterSimple

.. java:import:: org.cloudbus.cloudsim.resources Pe

CloudletScheduler
=================

.. java:package:: org.cloudbus.cloudsim.schedulers.cloudlet
   :noindex:

.. java:type:: public interface CloudletScheduler extends Serializable

   An interface to be implemented by each class that provides a policy of scheduling performed by a virtual machine to run its \ :java:ref:`Cloudlets <Cloudlet>`\ . Each VM has to have its own instance of a CloudletScheduler.

   It also implements the Null Object Design Pattern in order to start avoiding \ :java:ref:`NullPointerException`\  when using the \ :java:ref:`CloudletScheduler.NULL`\  object instead of attributing \ ``null``\  to \ :java:ref:`CloudletScheduler`\  variables.

   :author: Rodrigo N. Calheiros, Anton Beloglazov, Manoel Campos da Silva Filho

Fields
------
NULL
^^^^

.. java:field::  CloudletScheduler NULL
   :outertype: CloudletScheduler

   An attribute that implements the Null Object Design Pattern for \ :java:ref:`CloudletScheduler`\  objects.

Methods
-------
addCloudletToReturnedList
^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method::  void addCloudletToReturnedList(Cloudlet cloudlet)
   :outertype: CloudletScheduler

   Adds a Cloudlet to the list of finished Cloudlets that have been returned to its \ :java:ref:`DatacenterBroker`\ .

   :param cloudlet: the Cloudlet to be added

canAddCloudletToExecutionList
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method::  boolean canAddCloudletToExecutionList(CloudletExecutionInfo cloudlet)
   :outertype: CloudletScheduler

   Checks if a Cloudlet can be added to the execution list or not. Each CloudletScheduler can define a different policy to indicate if a Cloudlet can be added to the execution list or not at the moment this method is called.

   For instance, time-shared implementations can put all Cloudlets in the execution list, once it uses a preemptive policy that shares the CPU time between all running Cloudlets, even there are more Cloudlets than the number of CPUs. That is, it might always add new Cloudlets to the execution list.

   On the other hand, space-shared schedulers do not share the same CPUs between different Cloudlets. In this type of scheduler, a CPU is only allocated to a Cloudlet when the previous Cloudlet finished its entire execution. That is, it might not always add new Cloudlets to the execution list.

   :param cloudlet: Cloudlet to check if it can be added to the execution list
   :return: true if the Cloudlet can be added to the execution list, false otherwise

cloudletCancel
^^^^^^^^^^^^^^

.. java:method::  Cloudlet cloudletCancel(int cloudletId)
   :outertype: CloudletScheduler

   Cancels execution of a cloudlet.

   :param cloudletId: ID of the cloudlet being canceled
   :return: the canceled cloudlet or \ :java:ref:`Cloudlet.NULL`\  if not found

cloudletFinish
^^^^^^^^^^^^^^

.. java:method::  void cloudletFinish(CloudletExecutionInfo rcl)
   :outertype: CloudletScheduler

   Processes a finished cloudlet.

   :param rcl: finished cloudlet

cloudletPause
^^^^^^^^^^^^^

.. java:method::  boolean cloudletPause(int cloudletId)
   :outertype: CloudletScheduler

   Pauses execution of a cloudlet.

   :param cloudletId: ID of the cloudlet being paused
   :return: $true if cloudlet paused, $false otherwise

cloudletResume
^^^^^^^^^^^^^^

.. java:method::  double cloudletResume(int cloudletId)
   :outertype: CloudletScheduler

   Resumes execution of a paused cloudlet.

   :param cloudletId: ID of the cloudlet being resumed
   :return: expected finish time of the cloudlet, 0.0 if queued or not found in the paused list

cloudletSubmit
^^^^^^^^^^^^^^

.. java:method::  double cloudletSubmit(Cloudlet cl, double fileTransferTime)
   :outertype: CloudletScheduler

   Receives an cloudlet to be executed in the VM managed by this scheduler.

   :param cl: the submitted cloudlet
   :param fileTransferTime: time required to move the required files from the SAN to the VM
   :return: expected finish time of this cloudlet (considering the time to transfer required files from the Datacenter to the Vm), or 0 if it is in a waiting queue

cloudletSubmit
^^^^^^^^^^^^^^

.. java:method::  double cloudletSubmit(Cloudlet cl)
   :outertype: CloudletScheduler

   Receives an cloudlet to be executed in the VM managed by this scheduler.

   :param cl: the submited cloudlet
   :return: expected finish time of this cloudlet (considering the time to transfer required files from the Datacenter to the Vm), or 0 if it is in a waiting queue

deallocatePesFromVm
^^^^^^^^^^^^^^^^^^^

.. java:method::  void deallocatePesFromVm(Vm vm, int pesToRemove)
   :outertype: CloudletScheduler

   Releases a given number of PEs from a VM.

   :param vm: the vm to deallocate PEs from
   :param pesToRemove: number of PEs to deallocate

getAllocatedMipsForCloudlet
^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method::  double getAllocatedMipsForCloudlet(CloudletExecutionInfo rcl, double time)
   :outertype: CloudletScheduler

   Gets the current allocated MIPS for cloudlet.

   :param rcl: the rcl
   :param time: the time
   :return: the current allocated mips for cloudlet

getCloudletExecList
^^^^^^^^^^^^^^^^^^^

.. java:method::  List<CloudletExecutionInfo> getCloudletExecList()
   :outertype: CloudletScheduler

   Gets a \ **read-only**\  List of cloudlets being executed on the VM.

   :return: the cloudlet execution list

getCloudletFinishedList
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method::  List<CloudletExecutionInfo> getCloudletFinishedList()
   :outertype: CloudletScheduler

   Gets a list of finished cloudlets.

   :return: the cloudlet finished list

getCloudletList
^^^^^^^^^^^^^^^

.. java:method::  List<Cloudlet> getCloudletList()
   :outertype: CloudletScheduler

   Gets a \ **read-only**\  List of all cloudlets which are either \ **waiting**\  or \ **executing**\  on the VM.

   :return: the list of waiting and executing cloudlets

getCloudletReturnedList
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method::  Set<Cloudlet> getCloudletReturnedList()
   :outertype: CloudletScheduler

   Gets a \ **read-only**\  list of Cloudlets that finished executing and were returned the their broker. A Cloudlet is returned to to notify the broker about the end of its execution.

getCloudletStatus
^^^^^^^^^^^^^^^^^

.. java:method::  int getCloudletStatus(int cloudletId)
   :outertype: CloudletScheduler

   Gets the status of a cloudlet with a given id.

   :param cloudletId: ID of the cloudlet to get the status
   :return: status of the cloudlet if it was found, otherwise, returns -1

getCloudletToMigrate
^^^^^^^^^^^^^^^^^^^^

.. java:method::  Cloudlet getCloudletToMigrate()
   :outertype: CloudletScheduler

   Returns one cloudlet to migrate to another Vm. How the migrating cloudlet is select is defined by each class implementing this interface.

   :return: one running cloudlet

getCloudletWaitingList
^^^^^^^^^^^^^^^^^^^^^^

.. java:method::  List<CloudletExecutionInfo> getCloudletWaitingList()
   :outertype: CloudletScheduler

   Gets a \ **read-only**\  List of cloudlet waiting to be executed on the VM.

   :return: the cloudlet waiting list

getCurrentMipsShare
^^^^^^^^^^^^^^^^^^^

.. java:method::  List<Double> getCurrentMipsShare()
   :outertype: CloudletScheduler

   Gets a \ **read-only**\  list of current mips capacity from the VM that will be made available to the scheduler. This mips share will be allocated to Cloudlets as requested.

   :return: the current mips share list, where each item represents the MIPS capacity of a \ :java:ref:`Pe`\ . that is available to the scheduler.

getCurrentRequestedBwPercentUtilization
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method::  double getCurrentRequestedBwPercentUtilization()
   :outertype: CloudletScheduler

   /** Gets the current utilization percentage of Bandwidth that the running Cloudlets are requesting (in scale from 0 to 1).

   :return: the BW utilization percentage from 0 to 1 (where 1 is 100%)

getCurrentRequestedRamPercentUtilization
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method::  double getCurrentRequestedRamPercentUtilization()
   :outertype: CloudletScheduler

   Gets the current utilization percentage of RAM that the running Cloudlets are requesting (in scale from 0 to 1).

   :return: the RAM utilization percentage from 0 to 1 (where 1 is 100%)

getFreePes
^^^^^^^^^^

.. java:method::  long getFreePes()
   :outertype: CloudletScheduler

   Gets the number of PEs currently not being used.

getPacketScheduler
^^^^^^^^^^^^^^^^^^

.. java:method::  PacketScheduler getPacketScheduler()
   :outertype: CloudletScheduler

   Gets the \ :java:ref:`PacketScheduler`\  that will be used by this CloudletScheduler to process \ :java:ref:`VmPacket`\ s to be sent or received by the Vm that is assigned to the current CloudletScheduler.

   :return: the PacketScheduler for this CloudletScheduler or \ :java:ref:`PacketScheduler.NULL`\  if this scheduler will not deal with packets transmission.

getPreviousTime
^^^^^^^^^^^^^^^

.. java:method::  double getPreviousTime()
   :outertype: CloudletScheduler

   Gets the previous time when the scheduler updated the processing of cloudlets it is managing.

   :return: the previous time

getRequestedCpuPercentUtilization
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method::  double getRequestedCpuPercentUtilization(double time)
   :outertype: CloudletScheduler

   Gets total CPU utilization percentage of all cloudlets, according to CPU UtilizationModel of each one (in scale from 0 to 1, where 1 is 100%).

   :param time: the time to get the current CPU utilization
   :return: the total CPU utilization percentage

getRequestedMipsForCloudlet
^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method::  double getRequestedMipsForCloudlet(CloudletExecutionInfo rcl, double time)
   :outertype: CloudletScheduler

   Gets the current requested MIPS for a given cloudlet.

   :param rcl: the rcl
   :param time: the time
   :return: the current requested mips for the given cloudlet

getUsedPes
^^^^^^^^^^

.. java:method::  long getUsedPes()
   :outertype: CloudletScheduler

   Gets the number of currently used \ :java:ref:`Pe`\ 's.

getVm
^^^^^

.. java:method::  Vm getVm()
   :outertype: CloudletScheduler

   Gets the Vm that uses the scheduler.

hasFinishedCloudlets
^^^^^^^^^^^^^^^^^^^^

.. java:method::  boolean hasFinishedCloudlets()
   :outertype: CloudletScheduler

   Informs if there is any cloudlet that finished to execute in the VM managed by this scheduler.

   :return: $true if there is at least one finished cloudlet; $false otherwise

isCloudletReturned
^^^^^^^^^^^^^^^^^^

.. java:method::  boolean isCloudletReturned(Cloudlet cloudlet)
   :outertype: CloudletScheduler

   Checks if a Cloudlet has finished and was returned to its \ :java:ref:`DatacenterBroker`\ .

   :param cloudlet: the Cloudlet to be checked
   :return: true if the Cloudlet has finished and was returned to the broker, falser otherwise

isEmpty
^^^^^^^

.. java:method::  boolean isEmpty()
   :outertype: CloudletScheduler

   Checks if there \ **aren't**\  cloudlets \ **waiting**\  or \ **executing**\  inside the Vm.

   :return: true if there aren't \ **waiting**\  or \ **executing**\  Cloudlets, false otherwise.

isTherePacketScheduler
^^^^^^^^^^^^^^^^^^^^^^

.. java:method::  boolean isTherePacketScheduler()
   :outertype: CloudletScheduler

   Checks if there is a packet scheduler assigned to this CloudletScheduler in order to enable dispatching packets from and to the Vm of this CloudletScheduler.

runningCloudletsNumber
^^^^^^^^^^^^^^^^^^^^^^

.. java:method::  int runningCloudletsNumber()
   :outertype: CloudletScheduler

   Returns the number of cloudlets running in the virtual machine.

   :return: number of cloudlets running

setPacketScheduler
^^^^^^^^^^^^^^^^^^

.. java:method::  void setPacketScheduler(PacketScheduler packetScheduler)
   :outertype: CloudletScheduler

   Sets the \ :java:ref:`PacketScheduler`\  that will be used by this CloudletScheduler to process \ :java:ref:`VmPacket`\ s to be sent or received by the Vm that is assigned to the current CloudletScheduler. The Vm from the CloudletScheduler is also set to the PacketScheduler.

   \ **This attribute usually doesn't need to be set manually. See the note at the  interface for more details.**\

   :param packetScheduler: the PacketScheduler to set for this CloudletScheduler or \ :java:ref:`PacketScheduler.NULL`\  if this scheduler will not deal with packets transmission.

setVm
^^^^^

.. java:method::  void setVm(Vm vm)
   :outertype: CloudletScheduler

   Sets the Vm that will use the scheduler. It is not required to manually set a Vm for the scheduler, since a \ :java:ref:`Vm`\  sets itself to the scheduler when the scheduler is assigned to the Vm.

   :param vm: the Vm to set
   :throws IllegalArgumentException: when the scheduler already is assigned to another Vm, since each Vm must have its own scheduler
   :throws NullPointerException: when the vm parameter is null

updateProcessing
^^^^^^^^^^^^^^^^

.. java:method::  double updateProcessing(double currentTime, List<Double> mipsShare)
   :outertype: CloudletScheduler

   Updates the processing of cloudlets inside the Vm running under management of this scheduler.

   :param currentTime: current simulation time
   :param mipsShare: list with MIPS share of each Pe available to the scheduler
   :return: the predicted completion time of the earliest finishing cloudlet (which is a relative delay from the current simulation time), or \ :java:ref:`Double.MAX_VALUE`\  if there is no next Cloudlet to execute

