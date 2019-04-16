.. java:import:: java.util.function Function

.. java:import:: java.util.function Supplier

.. java:import:: org.cloudbus.cloudsim.cloudlets Cloudlet

.. java:import:: org.cloudbus.cloudsim.core.events SimEvent

.. java:import:: org.cloudbus.cloudsim.datacenters Datacenter

.. java:import:: org.cloudbus.cloudsim.utilizationmodels UtilizationModel

.. java:import:: org.cloudbus.cloudsim.vms Vm

.. java:import:: org.cloudsimplus.autoscaling VerticalVmScaling

.. java:import:: org.cloudsimplus.listeners EventListener

DatacenterBrokerAbstract
========================

.. java:package:: org.cloudbus.cloudsim.brokers
   :noindex:

.. java:type:: public abstract class DatacenterBrokerAbstract extends CloudSimEntity implements DatacenterBroker

   An abstract class to be used as base for implementing a \ :java:ref:`DatacenterBroker`\ .

   :author: Rodrigo N. Calheiros, Anton Beloglazov, Manoel Campos da Silva Filho

Constructors
------------
DatacenterBrokerAbstract
^^^^^^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public DatacenterBrokerAbstract(CloudSim simulation)
   :outertype: DatacenterBrokerAbstract

   Creates a DatacenterBroker object.

   :param simulation: the CloudSim instance that represents the simulation the Entity is related to

Methods
-------
addOnVmsCreatedListener
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public DatacenterBroker addOnVmsCreatedListener(EventListener<DatacenterBrokerEventInfo> listener)
   :outertype: DatacenterBrokerAbstract

addOneTimeOnCreationOfWaitingVmsFinishListener
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: public DatacenterBroker addOneTimeOnCreationOfWaitingVmsFinishListener(EventListener<DatacenterBrokerEventInfo> listener, Boolean oneTimeListener)
   :outertype: DatacenterBrokerAbstract

addOneTimeOnVmsCreatedListener
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public DatacenterBroker addOneTimeOnVmsCreatedListener(EventListener<DatacenterBrokerEventInfo> listener)
   :outertype: DatacenterBrokerAbstract

bindCloudletToVm
^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean bindCloudletToVm(Cloudlet cloudlet, Vm vm)
   :outertype: DatacenterBrokerAbstract

destroyVms
^^^^^^^^^^

.. java:method:: protected void destroyVms(Function<Vm, Double> vmDestructionDelayFunction)
   :outertype: DatacenterBrokerAbstract

   Try to destroy all created broker's VMs at the time defined by a delay \ :java:ref:`Function`\ .

   :param vmDestructionDelayFunction: a \ :java:ref:`Function`\  which indicates to time the VM will wait before being destructed

   **See also:** :java:ref:`.getVmDestructionDelayFunction()`

getCloudletsCreatedList
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Set<Cloudlet> getCloudletsCreatedList()
   :outertype: DatacenterBrokerAbstract

getCloudletsFinishedList
^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public <T extends Cloudlet> List<T> getCloudletsFinishedList()
   :outertype: DatacenterBrokerAbstract

getCloudletsWaitingList
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public <T extends Cloudlet> List<T> getCloudletsWaitingList()
   :outertype: DatacenterBrokerAbstract

getDatacenterList
^^^^^^^^^^^^^^^^^

.. java:method:: protected List<Datacenter> getDatacenterList()
   :outertype: DatacenterBrokerAbstract

   Gets the list of available datacenters.

   :return: the dc list

getDatacenterRequestedList
^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected Set<Datacenter> getDatacenterRequestedList()
   :outertype: DatacenterBrokerAbstract

   Gets the list of datacenters where was requested to place VMs.

getLastSelectedVm
^^^^^^^^^^^^^^^^^

.. java:method:: protected Vm getLastSelectedVm()
   :outertype: DatacenterBrokerAbstract

   :return: latest VM selected to run a cloudlet.

getVmCreationAcks
^^^^^^^^^^^^^^^^^

.. java:method:: protected int getVmCreationAcks()
   :outertype: DatacenterBrokerAbstract

   Gets the number of acknowledges (ACKs) received from Datacenters in response to requests to create VMs. The number of acks doesn't mean the number of created VMs, once Datacenters can respond informing that a Vm could not be created.

   :return: the number vm creation acks

getVmCreationRequests
^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected int getVmCreationRequests()
   :outertype: DatacenterBrokerAbstract

   Gets the number of VM creation requests.

   :return: the number of VM creation requests

getVmDatacenter
^^^^^^^^^^^^^^^

.. java:method:: protected Datacenter getVmDatacenter(Vm vm)
   :outertype: DatacenterBrokerAbstract

   Gets the Datacenter where a VM is placed.

   :param vm: the VM to get its Datacenter

getVmDestructionDelayFunction
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Function<Vm, Double> getVmDestructionDelayFunction()
   :outertype: DatacenterBrokerAbstract

getVmFromCreatedList
^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected Vm getVmFromCreatedList(int vmIndex)
   :outertype: DatacenterBrokerAbstract

   Gets a Vm at a given index from the \ :java:ref:`list of created VMs <getVmsCreatedList()>`\ .

   :param vmIndex: the index where a VM has to be got from the created VM list
   :return: the VM at the given index or \ :java:ref:`Vm.NULL`\  if the index is invalid

getVmsCreatedList
^^^^^^^^^^^^^^^^^

.. java:method:: @Override public <T extends Vm> List<T> getVmsCreatedList()
   :outertype: DatacenterBrokerAbstract

getVmsWaitingList
^^^^^^^^^^^^^^^^^

.. java:method:: @Override public <T extends Vm> List<T> getVmsWaitingList()
   :outertype: DatacenterBrokerAbstract

getWaitingVm
^^^^^^^^^^^^

.. java:method:: @Override public Vm getWaitingVm(int index)
   :outertype: DatacenterBrokerAbstract

isThereWaitingCloudlets
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean isThereWaitingCloudlets()
   :outertype: DatacenterBrokerAbstract

processCloudletReturn
^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected void processCloudletReturn(SimEvent ev)
   :outertype: DatacenterBrokerAbstract

   Processes the end of execution of a given cloudlet inside a Vm.

   :param ev: the cloudlet that has just finished to execute and was returned to the broker

processDatacenterListRequest
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected void processDatacenterListRequest(SimEvent ev)
   :outertype: DatacenterBrokerAbstract

   Process a request to get the list of all Datacenters registered in the Cloud Information Service (CIS) of the \ :java:ref:`simulation <getSimulation()>`\ .

   :param ev: a CloudSimEvent object

processEvent
^^^^^^^^^^^^

.. java:method:: @Override public void processEvent(SimEvent ev)
   :outertype: DatacenterBrokerAbstract

processFailedVmCreationInDatacenter
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected void processFailedVmCreationInDatacenter(Vm vm, Datacenter datacenter)
   :outertype: DatacenterBrokerAbstract

   Process a response from a Datacenter informing that it was NOT able to create the VM requested by the broker.

   :param vm: id of the Vm that failed to be created inside the Datacenter
   :param datacenter: id of the Datacenter where the request to create

processSuccessVmCreationInDatacenter
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected void processSuccessVmCreationInDatacenter(Vm vm, Datacenter datacenter)
   :outertype: DatacenterBrokerAbstract

   Process a response from a Datacenter informing that it was able to create the VM requested by the broker.

   :param vm: id of the Vm that succeeded to be created inside the Datacenter
   :param datacenter: id of the Datacenter where the request to create the Vm succeeded

processVmCreateResponseFromDatacenter
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected boolean processVmCreateResponseFromDatacenter(SimEvent ev)
   :outertype: DatacenterBrokerAbstract

   Process the ack received from a Datacenter to a broker's request for creation of a Vm in that Datacenter.

   :param ev: a CloudSimEvent object
   :return: true if the VM was created successfully, false otherwise

requestCreationOfWaitingVmsToFallbackDatacenter
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected void requestCreationOfWaitingVmsToFallbackDatacenter()
   :outertype: DatacenterBrokerAbstract

   After the response (ack) of all VM creation request were received but not all VMs could be created (what means some acks informed about Vm creation failures), try to find another Datacenter to request the creation of the VMs in the waiting list.

requestDatacenterToCreateWaitingVms
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected void requestDatacenterToCreateWaitingVms()
   :outertype: DatacenterBrokerAbstract

   Request the creation of VMs in the \ :java:ref:`VM waiting list <getVmsWaitingList()>`\  inside some Datacenter.

   **See also:** :java:ref:`.submitVmList(java.util.List)`

requestDatacenterToCreateWaitingVms
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected void requestDatacenterToCreateWaitingVms(Datacenter datacenter)
   :outertype: DatacenterBrokerAbstract

   Request a specific Datacenter to create the VM in the \ :java:ref:`VM waiting list <getVmsWaitingList()>`\ .

   :param datacenter: id of the Datacenter to request the VMs creation

   **See also:** :java:ref:`.submitVmList(java.util.List)`

requestDatacentersToCreateWaitingCloudlets
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected void requestDatacentersToCreateWaitingCloudlets()
   :outertype: DatacenterBrokerAbstract

   Request Datacenters to create the Cloudlets in the \ :java:ref:`Cloudlets waiting list <getCloudletsWaitingList()>`\ . If there aren't available VMs to host all cloudlets, the creation of some ones will be postponed.

   This method is called after all submitted VMs are created in some Datacenter.

   **See also:** :java:ref:`.submitCloudletList(java.util.List)`

requestShutDown
^^^^^^^^^^^^^^^

.. java:method:: protected void requestShutDown()
   :outertype: DatacenterBrokerAbstract

   Send an internal event to the broker itself, communicating there is not more events to process (no more VMs to create or Cloudlets to execute).

setCloudletComparator
^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void setCloudletComparator(Comparator<Cloudlet> comparator)
   :outertype: DatacenterBrokerAbstract

setDatacenterList
^^^^^^^^^^^^^^^^^

.. java:method:: protected final void setDatacenterList(Set<Datacenter> datacenterList)
   :outertype: DatacenterBrokerAbstract

   Sets the list of available datacenters.

   :param datacenterList: the new dc list

setDatacenterSupplier
^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public final void setDatacenterSupplier(Supplier<Datacenter> datacenterSupplier)
   :outertype: DatacenterBrokerAbstract

setFallbackDatacenterSupplier
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public final void setFallbackDatacenterSupplier(Supplier<Datacenter> fallbackDatacenterSupplier)
   :outertype: DatacenterBrokerAbstract

setVmComparator
^^^^^^^^^^^^^^^

.. java:method:: @Override public void setVmComparator(Comparator<Vm> comparator)
   :outertype: DatacenterBrokerAbstract

setVmDestructionDelayFunction
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public DatacenterBroker setVmDestructionDelayFunction(Function<Vm, Double> function)
   :outertype: DatacenterBrokerAbstract

setVmMapper
^^^^^^^^^^^

.. java:method:: @Override public final void setVmMapper(Function<Cloudlet, Vm> vmMapper)
   :outertype: DatacenterBrokerAbstract

shutdownEntity
^^^^^^^^^^^^^^

.. java:method:: @Override public void shutdownEntity()
   :outertype: DatacenterBrokerAbstract

startEntity
^^^^^^^^^^^

.. java:method:: @Override public void startEntity()
   :outertype: DatacenterBrokerAbstract

submitCloudlet
^^^^^^^^^^^^^^

.. java:method:: @Override public void submitCloudlet(Cloudlet cloudlet)
   :outertype: DatacenterBrokerAbstract

submitCloudletList
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void submitCloudletList(List<? extends Cloudlet> list, double submissionDelay)
   :outertype: DatacenterBrokerAbstract

submitCloudletList
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void submitCloudletList(List<? extends Cloudlet> list, Vm vm)
   :outertype: DatacenterBrokerAbstract

submitCloudletList
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void submitCloudletList(List<? extends Cloudlet> list, Vm vm, double submissionDelay)
   :outertype: DatacenterBrokerAbstract

submitCloudletList
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void submitCloudletList(List<? extends Cloudlet> list)
   :outertype: DatacenterBrokerAbstract

   {@inheritDoc}

   If the entity already started (the simulation is running), the creation of previously submitted Cloudlets already was requested by the \ :java:ref:`start()`\  method that is called just once. By this way, this method will immediately request the creation of these just submitted Cloudlets if all submitted VMs were already created, in order to allow Cloudlet creation after the simulation has started. This avoid the developer to dynamically create brokers just to create VMs or Cloudlets during simulation execution.

   :param list: {@inheritDoc}

   **See also:** :java:ref:`.submitCloudletList(List,double)`

submitVm
^^^^^^^^

.. java:method:: @Override public void submitVm(Vm vm)
   :outertype: DatacenterBrokerAbstract

submitVmList
^^^^^^^^^^^^

.. java:method:: @Override public void submitVmList(List<? extends Vm> list, double submissionDelay)
   :outertype: DatacenterBrokerAbstract

submitVmList
^^^^^^^^^^^^

.. java:method:: @Override public void submitVmList(List<? extends Vm> list)
   :outertype: DatacenterBrokerAbstract

   {@inheritDoc}

   If the entity already started (the simulation is running), the creation of previously submitted VMs already was requested by the \ :java:ref:`start()`\  method that is called just once. By this way, this method will immediately request the creation of these just submitted VMs in order to allow VM creation after the simulation has started. This avoid the developer to dynamically create brokers just to create VMs or Cloudlets during simulation execution.

   :param list: {@inheritDoc}

toString
^^^^^^^^

.. java:method:: @Override public String toString()
   :outertype: DatacenterBrokerAbstract

