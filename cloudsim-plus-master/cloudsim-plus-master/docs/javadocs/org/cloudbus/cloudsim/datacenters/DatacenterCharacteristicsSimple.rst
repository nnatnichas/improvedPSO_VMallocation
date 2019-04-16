.. java:import:: org.cloudbus.cloudsim.hosts Host

.. java:import:: org.cloudbus.cloudsim.resources Pe

.. java:import:: java.util List

.. java:import:: java.util Objects

.. java:import:: org.cloudbus.cloudsim.lists HostList

.. java:import:: org.cloudbus.cloudsim.lists PeList

DatacenterCharacteristicsSimple
===============================

.. java:package:: org.cloudbus.cloudsim.datacenters
   :noindex:

.. java:type:: public class DatacenterCharacteristicsSimple implements DatacenterCharacteristics

   Represents static properties of a Datacenter such as architecture, Operating System (OS), management policy (time- or space-shared), cost and time zone at which the resource is located along resource configuration. Each \ :java:ref:`Datacenter`\  has to have its own instance of this class, since it stores the Datacenter host list.

   :author: Manzur Murshed, Rajkumar Buyya, Rodrigo N. Calheiros, Anton Beloglazov

Constructors
------------
DatacenterCharacteristicsSimple
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public DatacenterCharacteristicsSimple(List<? extends Host> hostList)
   :outertype: DatacenterCharacteristicsSimple

   Creates a DatacenterCharacteristics with default values for \ :java:ref:`architecture <getArchitecture()>`\ , \ :java:ref:`OS <getOs()>`\ , \ :java:ref:`Time Zone <getTimeZone()>`\  and \ :java:ref:`VMM <getVmm()>`\ . The costs for \ :java:ref:`BW <getCostPerBw()>`\ , \ :java:ref:`getCostPerMem()`\  () RAM} and \ :java:ref:`getCostPerStorage()`\  () Storage} are set to zero.

   :param hostList: list of \ :java:ref:`Host`\  in the Datacenter

DatacenterCharacteristicsSimple
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:constructor:: @Deprecated public DatacenterCharacteristicsSimple(String architecture, String os, String vmm, List<? extends Host> hostList, double timeZone, double costPerSec, double costPerMem, double costPerStorage, double costPerBw)
   :outertype: DatacenterCharacteristicsSimple

   Creates a DatacenterCharacteristics. If the time zone is invalid, then by default, it will be GMT+0.

   :param architecture: the architecture of the Datacenter
   :param os: the operating system used on the Datacenter's PMs
   :param vmm: the virtual machine monitor used
   :param hostList: list of machines in the Datacenter
   :param timeZone: local time zone of a user that owns this reservation. Time zone should be of range [GMT-12 ... GMT+13]
   :param costPerSec: the cost per sec of CPU use in the Datacenter
   :param costPerMem: the cost to use memory in the Datacenter
   :param costPerStorage: the cost to use storage in the Datacenter
   :param costPerBw: the cost of each byte of bandwidth (bw) consumed

Methods
-------
getArchitecture
^^^^^^^^^^^^^^^

.. java:method:: @Override public String getArchitecture()
   :outertype: DatacenterCharacteristicsSimple

getCostPerBw
^^^^^^^^^^^^

.. java:method:: @Override public double getCostPerBw()
   :outertype: DatacenterCharacteristicsSimple

getCostPerMem
^^^^^^^^^^^^^

.. java:method:: @Override public double getCostPerMem()
   :outertype: DatacenterCharacteristicsSimple

getCostPerSecond
^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getCostPerSecond()
   :outertype: DatacenterCharacteristicsSimple

getCostPerStorage
^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getCostPerStorage()
   :outertype: DatacenterCharacteristicsSimple

getDatacenter
^^^^^^^^^^^^^

.. java:method:: @Override public Datacenter getDatacenter()
   :outertype: DatacenterCharacteristicsSimple

getHostList
^^^^^^^^^^^

.. java:method:: @Override public <T extends Host> List<T> getHostList()
   :outertype: DatacenterCharacteristicsSimple

getHostWithFreePe
^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Host getHostWithFreePe()
   :outertype: DatacenterCharacteristicsSimple

getHostWithFreePe
^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Host getHostWithFreePe(int peNumber)
   :outertype: DatacenterCharacteristicsSimple

getId
^^^^^

.. java:method:: @Override public int getId()
   :outertype: DatacenterCharacteristicsSimple

getMips
^^^^^^^

.. java:method:: @Override public double getMips()
   :outertype: DatacenterCharacteristicsSimple

getMipsOfOnePe
^^^^^^^^^^^^^^

.. java:method:: @Override public long getMipsOfOnePe(int hostId, int peId)
   :outertype: DatacenterCharacteristicsSimple

getNumberOfBusyPes
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public int getNumberOfBusyPes()
   :outertype: DatacenterCharacteristicsSimple

getNumberOfFailedHosts
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public long getNumberOfFailedHosts()
   :outertype: DatacenterCharacteristicsSimple

getNumberOfFreePes
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public int getNumberOfFreePes()
   :outertype: DatacenterCharacteristicsSimple

getNumberOfHosts
^^^^^^^^^^^^^^^^

.. java:method:: @Override public int getNumberOfHosts()
   :outertype: DatacenterCharacteristicsSimple

getNumberOfPes
^^^^^^^^^^^^^^

.. java:method:: @Override public int getNumberOfPes()
   :outertype: DatacenterCharacteristicsSimple

getOs
^^^^^

.. java:method:: @Override public String getOs()
   :outertype: DatacenterCharacteristicsSimple

getResourceName
^^^^^^^^^^^^^^^

.. java:method:: @Override public String getResourceName()
   :outertype: DatacenterCharacteristicsSimple

getTimeZone
^^^^^^^^^^^

.. java:method:: @Override public double getTimeZone()
   :outertype: DatacenterCharacteristicsSimple

getVmm
^^^^^^

.. java:method:: @Override public String getVmm()
   :outertype: DatacenterCharacteristicsSimple

isWorking
^^^^^^^^^

.. java:method:: @Override public boolean isWorking()
   :outertype: DatacenterCharacteristicsSimple

setArchitecture
^^^^^^^^^^^^^^^

.. java:method:: @Override public final DatacenterCharacteristics setArchitecture(String architecture)
   :outertype: DatacenterCharacteristicsSimple

setCostPerBw
^^^^^^^^^^^^

.. java:method:: @Override public final DatacenterCharacteristics setCostPerBw(double costPerBw)
   :outertype: DatacenterCharacteristicsSimple

setCostPerMem
^^^^^^^^^^^^^

.. java:method:: @Override public final DatacenterCharacteristics setCostPerMem(double costPerMem)
   :outertype: DatacenterCharacteristicsSimple

setCostPerSecond
^^^^^^^^^^^^^^^^

.. java:method:: @Override public final DatacenterCharacteristics setCostPerSecond(double costPerSecond)
   :outertype: DatacenterCharacteristicsSimple

setCostPerStorage
^^^^^^^^^^^^^^^^^

.. java:method:: @Override public final DatacenterCharacteristics setCostPerStorage(double costPerStorage)
   :outertype: DatacenterCharacteristicsSimple

setDatacenter
^^^^^^^^^^^^^

.. java:method:: @Override public DatacenterCharacteristics setDatacenter(Datacenter datacenter)
   :outertype: DatacenterCharacteristicsSimple

setHostList
^^^^^^^^^^^

.. java:method:: protected final void setHostList(List<? extends Host> hostList)
   :outertype: DatacenterCharacteristicsSimple

   Sets the host list.

   :param hostList: the new host list

setId
^^^^^

.. java:method:: protected final void setId(int id)
   :outertype: DatacenterCharacteristicsSimple

   Sets the Datacenter id.

   :param id: the new id

setOs
^^^^^

.. java:method:: @Override public final DatacenterCharacteristics setOs(String os)
   :outertype: DatacenterCharacteristicsSimple

setPeStatus
^^^^^^^^^^^

.. java:method:: @Override public boolean setPeStatus(Pe.Status status, int hostId, int peId)
   :outertype: DatacenterCharacteristicsSimple

setTimeZone
^^^^^^^^^^^

.. java:method:: @Override public final DatacenterCharacteristics setTimeZone(double timeZone)
   :outertype: DatacenterCharacteristicsSimple

setVmm
^^^^^^

.. java:method:: @Override public final DatacenterCharacteristics setVmm(String vmm)
   :outertype: DatacenterCharacteristicsSimple

