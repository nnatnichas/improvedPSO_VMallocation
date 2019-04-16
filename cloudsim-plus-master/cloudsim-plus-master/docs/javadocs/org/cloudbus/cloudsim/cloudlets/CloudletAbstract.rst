.. java:import:: org.cloudbus.cloudsim.brokers DatacenterBroker

.. java:import:: org.cloudbus.cloudsim.core Simulation

.. java:import:: org.cloudbus.cloudsim.core UniquelyIdentificable

.. java:import:: org.cloudbus.cloudsim.datacenters Datacenter

.. java:import:: org.cloudbus.cloudsim.utilizationmodels UtilizationModel

.. java:import:: org.cloudbus.cloudsim.vms Vm

.. java:import:: org.cloudsimplus.listeners CloudletVmEventInfo

.. java:import:: org.cloudsimplus.listeners EventListener

.. java:import:: java.text DecimalFormat

CloudletAbstract
================

.. java:package:: org.cloudbus.cloudsim.cloudlets
   :noindex:

.. java:type:: public abstract class CloudletAbstract implements Cloudlet

   A base class for \ :java:ref:`Cloudlet`\  implementations.

   :author: Rodrigo N. Calheiros, Anton Beloglazov, Manoel Campos da Silva Filho

Constructors
------------
CloudletAbstract
^^^^^^^^^^^^^^^^

.. java:constructor:: public CloudletAbstract(int cloudletId, long length, long pesNumber)
   :outertype: CloudletAbstract

   Creates a Cloudlet with no priority and file size and output size equal to 1.

   :param cloudletId: id of the Cloudlet
   :param length: the length or size (in MI) of this cloudlet to be executed in a VM
   :param pesNumber: number of PEs that Cloudlet will require

CloudletAbstract
^^^^^^^^^^^^^^^^

.. java:constructor:: public CloudletAbstract(long length, int pesNumber)
   :outertype: CloudletAbstract

   Creates a Cloudlet with no priority or id. The id is defined when the Cloudlet is submitted to a \ :java:ref:`DatacenterBroker`\ . The file size and output size is defined as 1.

   :param length: the length or size (in MI) of this cloudlet to be executed in a VM
   :param pesNumber: number of PEs that Cloudlet will require

Methods
-------
addOnFinishListener
^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Cloudlet addOnFinishListener(EventListener<CloudletVmEventInfo> listener)
   :outertype: CloudletAbstract

addOnUpdateProcessingListener
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Cloudlet addOnUpdateProcessingListener(EventListener<CloudletVmEventInfo> listener)
   :outertype: CloudletAbstract

addRequiredFile
^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean addRequiredFile(String fileName)
   :outertype: CloudletAbstract

addRequiredFiles
^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean addRequiredFiles(List<String> fileNames)
   :outertype: CloudletAbstract

assignToDatacenter
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void assignToDatacenter(Datacenter datacenter)
   :outertype: CloudletAbstract

deleteRequiredFile
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean deleteRequiredFile(String filename)
   :outertype: CloudletAbstract

equals
^^^^^^

.. java:method:: @Override public boolean equals(Object o)
   :outertype: CloudletAbstract

getAccumulatedBwCost
^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getAccumulatedBwCost()
   :outertype: CloudletAbstract

getActualCpuTime
^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getActualCpuTime(Datacenter datacenter)
   :outertype: CloudletAbstract

getActualCpuTime
^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getActualCpuTime()
   :outertype: CloudletAbstract

getArrivalTime
^^^^^^^^^^^^^^

.. java:method:: @Override public double getArrivalTime(Datacenter datacenter)
   :outertype: CloudletAbstract

getBroker
^^^^^^^^^

.. java:method:: @Override public DatacenterBroker getBroker()
   :outertype: CloudletAbstract

getCostPerBw
^^^^^^^^^^^^

.. java:method:: @Override public double getCostPerBw()
   :outertype: CloudletAbstract

getCostPerSec
^^^^^^^^^^^^^

.. java:method:: @Override public double getCostPerSec()
   :outertype: CloudletAbstract

getCostPerSec
^^^^^^^^^^^^^

.. java:method:: @Override public double getCostPerSec(Datacenter datacenter)
   :outertype: CloudletAbstract

getExecStartTime
^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getExecStartTime()
   :outertype: CloudletAbstract

getFileSize
^^^^^^^^^^^

.. java:method:: @Override public long getFileSize()
   :outertype: CloudletAbstract

getFinishTime
^^^^^^^^^^^^^

.. java:method:: @Override public double getFinishTime()
   :outertype: CloudletAbstract

getFinishedLengthSoFar
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public long getFinishedLengthSoFar(Datacenter datacenter)
   :outertype: CloudletAbstract

getFinishedLengthSoFar
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public long getFinishedLengthSoFar()
   :outertype: CloudletAbstract

getHistory
^^^^^^^^^^

.. java:method:: @Override public String getHistory()
   :outertype: CloudletAbstract

getId
^^^^^

.. java:method:: @Override public int getId()
   :outertype: CloudletAbstract

getLastDatacenter
^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Datacenter getLastDatacenter()
   :outertype: CloudletAbstract

getLastDatacenterArrivalTime
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getLastDatacenterArrivalTime()
   :outertype: CloudletAbstract

getLastExecutedDatacenterIdx
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected int getLastExecutedDatacenterIdx()
   :outertype: CloudletAbstract

getLength
^^^^^^^^^

.. java:method:: @Override public long getLength()
   :outertype: CloudletAbstract

getNetServiceLevel
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public int getNetServiceLevel()
   :outertype: CloudletAbstract

getNumberOfPes
^^^^^^^^^^^^^^

.. java:method:: @Override public long getNumberOfPes()
   :outertype: CloudletAbstract

getOutputSize
^^^^^^^^^^^^^

.. java:method:: @Override public long getOutputSize()
   :outertype: CloudletAbstract

getPriority
^^^^^^^^^^^

.. java:method:: @Override public int getPriority()
   :outertype: CloudletAbstract

getRequiredFiles
^^^^^^^^^^^^^^^^

.. java:method:: @Override public List<String> getRequiredFiles()
   :outertype: CloudletAbstract

getSimulation
^^^^^^^^^^^^^

.. java:method:: @Override public Simulation getSimulation()
   :outertype: CloudletAbstract

getStatus
^^^^^^^^^

.. java:method:: @Override public Status getStatus()
   :outertype: CloudletAbstract

getSubmissionDelay
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getSubmissionDelay()
   :outertype: CloudletAbstract

getTotalCost
^^^^^^^^^^^^

.. java:method:: @Override public double getTotalCost()
   :outertype: CloudletAbstract

getTotalLength
^^^^^^^^^^^^^^

.. java:method:: @Override public long getTotalLength()
   :outertype: CloudletAbstract

getUid
^^^^^^

.. java:method:: @Override public String getUid()
   :outertype: CloudletAbstract

getUtilizationModelBw
^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public UtilizationModel getUtilizationModelBw()
   :outertype: CloudletAbstract

getUtilizationModelCpu
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public UtilizationModel getUtilizationModelCpu()
   :outertype: CloudletAbstract

getUtilizationModelRam
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public UtilizationModel getUtilizationModelRam()
   :outertype: CloudletAbstract

getUtilizationOfBw
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getUtilizationOfBw()
   :outertype: CloudletAbstract

getUtilizationOfBw
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getUtilizationOfBw(double time)
   :outertype: CloudletAbstract

getUtilizationOfCpu
^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getUtilizationOfCpu()
   :outertype: CloudletAbstract

getUtilizationOfCpu
^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getUtilizationOfCpu(double time)
   :outertype: CloudletAbstract

getUtilizationOfRam
^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getUtilizationOfRam()
   :outertype: CloudletAbstract

getUtilizationOfRam
^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getUtilizationOfRam(double time)
   :outertype: CloudletAbstract

getVm
^^^^^

.. java:method:: @Override public Vm getVm()
   :outertype: CloudletAbstract

getWaitingTime
^^^^^^^^^^^^^^

.. java:method:: @Override public double getWaitingTime()
   :outertype: CloudletAbstract

getWallClockTime
^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getWallClockTime(Datacenter datacenter)
   :outertype: CloudletAbstract

getWallClockTimeInLastExecutedDatacenter
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getWallClockTimeInLastExecutedDatacenter()
   :outertype: CloudletAbstract

hashCode
^^^^^^^^

.. java:method:: @Override public int hashCode()
   :outertype: CloudletAbstract

isAssignedToDatacenter
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean isAssignedToDatacenter()
   :outertype: CloudletAbstract

isBindToVm
^^^^^^^^^^

.. java:method:: @Override public boolean isBindToVm()
   :outertype: CloudletAbstract

isFinished
^^^^^^^^^^

.. java:method:: @Override public boolean isFinished()
   :outertype: CloudletAbstract

isRecordTransactionHistory
^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: public boolean isRecordTransactionHistory()
   :outertype: CloudletAbstract

   Indicates if Cloudlet transaction history is to be recorded or not.

   **See also:** :java:ref:`.getHistory()`

notifyOnUpdateProcessingListeners
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void notifyOnUpdateProcessingListeners(double time)
   :outertype: CloudletAbstract

registerArrivalInDatacenter
^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double registerArrivalInDatacenter()
   :outertype: CloudletAbstract

removeOnFinishListener
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean removeOnFinishListener(EventListener<CloudletVmEventInfo> listener)
   :outertype: CloudletAbstract

removeOnUpdateProcessingListener
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean removeOnUpdateProcessingListener(EventListener<CloudletVmEventInfo> listener)
   :outertype: CloudletAbstract

requiresFiles
^^^^^^^^^^^^^

.. java:method:: @Override public boolean requiresFiles()
   :outertype: CloudletAbstract

setAccumulatedBwCost
^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected final void setAccumulatedBwCost(double accumulatedBwCost)
   :outertype: CloudletAbstract

   Sets the \ :java:ref:`accumulated bw cost <getAccumulatedBwCost()>`\ .

   :param accumulatedBwCost: the accumulated bw cost to set

setBroker
^^^^^^^^^

.. java:method:: @Override public final Cloudlet setBroker(DatacenterBroker broker)
   :outertype: CloudletAbstract

setCostPerBw
^^^^^^^^^^^^

.. java:method:: protected final void setCostPerBw(double costPerBw)
   :outertype: CloudletAbstract

   Sets \ :java:ref:`the cost of each byte of bandwidth (bw) <getCostPerBw()>`\  consumed.

   :param costPerBw: the new cost per bw to set

setExecStartTime
^^^^^^^^^^^^^^^^

.. java:method:: @Override public void setExecStartTime(double clockTime)
   :outertype: CloudletAbstract

setFileSize
^^^^^^^^^^^

.. java:method:: @Override public final Cloudlet setFileSize(long fileSize)
   :outertype: CloudletAbstract

setFinishTime
^^^^^^^^^^^^^

.. java:method:: protected final void setFinishTime(double finishTime)
   :outertype: CloudletAbstract

   Sets the \ :java:ref:`finish time <getFinishTime()>`\  of this cloudlet in the latest Datacenter.

   :param finishTime: the finish time

setFinishedLengthSoFar
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean setFinishedLengthSoFar(long length)
   :outertype: CloudletAbstract

setId
^^^^^

.. java:method:: @Override public void setId(int id)
   :outertype: CloudletAbstract

setLastExecutedDatacenterIdx
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected void setLastExecutedDatacenterIdx(int lastExecutedDatacenterIdx)
   :outertype: CloudletAbstract

setLength
^^^^^^^^^

.. java:method:: @Override public final Cloudlet setLength(long length)
   :outertype: CloudletAbstract

setNetServiceLevel
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean setNetServiceLevel(int netServiceLevel)
   :outertype: CloudletAbstract

setNumberOfPes
^^^^^^^^^^^^^^

.. java:method:: @Override public final Cloudlet setNumberOfPes(long numberOfPes)
   :outertype: CloudletAbstract

setOutputSize
^^^^^^^^^^^^^

.. java:method:: @Override public final Cloudlet setOutputSize(long outputSize)
   :outertype: CloudletAbstract

setPriority
^^^^^^^^^^^

.. java:method:: @Override public void setPriority(int priority)
   :outertype: CloudletAbstract

setRecordTransactionHistory
^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: public void setRecordTransactionHistory(boolean recordTransactionHistory)
   :outertype: CloudletAbstract

   Sets the Cloudlet transaction history writing.

   :param recordTransactionHistory: true enables transaction history writing, false disables.

setRequiredFiles
^^^^^^^^^^^^^^^^

.. java:method:: public final void setRequiredFiles(List<String> requiredFiles)
   :outertype: CloudletAbstract

   Sets the list of \ :java:ref:`required files <getRequiredFiles()>`\ .

   :param requiredFiles: the new list of required files

setStatus
^^^^^^^^^

.. java:method:: @Override public boolean setStatus(Status newStatus)
   :outertype: CloudletAbstract

setSubmissionDelay
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public final void setSubmissionDelay(double submissionDelay)
   :outertype: CloudletAbstract

setUtilizationModel
^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Cloudlet setUtilizationModel(UtilizationModel utilizationModel)
   :outertype: CloudletAbstract

setUtilizationModelBw
^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public final Cloudlet setUtilizationModelBw(UtilizationModel utilizationModelBw)
   :outertype: CloudletAbstract

setUtilizationModelCpu
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public final Cloudlet setUtilizationModelCpu(UtilizationModel utilizationModelCpu)
   :outertype: CloudletAbstract

setUtilizationModelRam
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public final Cloudlet setUtilizationModelRam(UtilizationModel utilizationModelRam)
   :outertype: CloudletAbstract

setVm
^^^^^

.. java:method:: @Override public final Cloudlet setVm(Vm vm)
   :outertype: CloudletAbstract

setWallClockTime
^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean setWallClockTime(double wallTime, double actualCpuTime)
   :outertype: CloudletAbstract

write
^^^^^

.. java:method:: protected void write(String str)
   :outertype: CloudletAbstract

   Writes a particular history transaction of this Cloudlet into a log.

   :param str: a history transaction of this Cloudlet

write
^^^^^

.. java:method:: protected void write(String format, Object... args)
   :outertype: CloudletAbstract

   Writes a formatted particular history transaction of this Cloudlet into a log.

   :param format: the format of the Cloudlet's history transaction, according to the format parameter of \ :java:ref:`String.format(String,Object...)`\
   :param args: The list of values to be shown in the history, that are referenced by the format.

   **See also:** :java:ref:`.write(String)`

