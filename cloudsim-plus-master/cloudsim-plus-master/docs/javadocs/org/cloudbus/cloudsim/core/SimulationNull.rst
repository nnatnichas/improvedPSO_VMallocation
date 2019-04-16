.. java:import:: org.cloudbus.cloudsim.core.events SimEvent

.. java:import:: org.cloudbus.cloudsim.datacenters Datacenter

.. java:import:: org.cloudbus.cloudsim.network.topologies NetworkTopology

.. java:import:: org.cloudsimplus.listeners EventInfo

.. java:import:: org.cloudsimplus.listeners EventListener

.. java:import:: java.util.function Predicate

SimulationNull
==============

.. java:package:: org.cloudbus.cloudsim.core
   :noindex:

.. java:type:: final class SimulationNull implements Simulation

   A class that implements the Null Object Design Pattern for \ :java:ref:`Simulation`\  class.

   :author: Manoel Campos da Silva Filho

   **See also:** :java:ref:`Simulation.NULL`

Methods
-------
abort
^^^^^

.. java:method:: @Override public void abort()
   :outertype: SimulationNull

addEntity
^^^^^^^^^

.. java:method:: @Override public void addEntity(CloudSimEntity e)
   :outertype: SimulationNull

addOnClockTickListener
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Simulation addOnClockTickListener(EventListener<EventInfo> listener)
   :outertype: SimulationNull

addOnEventProcessingListener
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Simulation addOnEventProcessingListener(EventListener<SimEvent> listener)
   :outertype: SimulationNull

addOnSimulationPausedListener
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Simulation addOnSimulationPausedListener(EventListener<EventInfo> listener)
   :outertype: SimulationNull

cancel
^^^^^^

.. java:method:: @Override public SimEvent cancel(int src, Predicate<SimEvent> p)
   :outertype: SimulationNull

cancelAll
^^^^^^^^^

.. java:method:: @Override public boolean cancelAll(int src, Predicate<SimEvent> p)
   :outertype: SimulationNull

clock
^^^^^

.. java:method:: @Override public double clock()
   :outertype: SimulationNull

clockInHours
^^^^^^^^^^^^

.. java:method:: @Override public double clockInHours()
   :outertype: SimulationNull

clockInMinutes
^^^^^^^^^^^^^^

.. java:method:: @Override public double clockInMinutes()
   :outertype: SimulationNull

findFirstDeferred
^^^^^^^^^^^^^^^^^

.. java:method:: @Override public SimEvent findFirstDeferred(int dest, Predicate<SimEvent> p)
   :outertype: SimulationNull

getCalendar
^^^^^^^^^^^

.. java:method:: @Override public Calendar getCalendar()
   :outertype: SimulationNull

getCloudInfoServiceEntityId
^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public int getCloudInfoServiceEntityId()
   :outertype: SimulationNull

getDatacenterList
^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Set<Datacenter> getDatacenterList()
   :outertype: SimulationNull

getEntitiesByName
^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Map<String, SimEntity> getEntitiesByName()
   :outertype: SimulationNull

getEntity
^^^^^^^^^

.. java:method:: @Override public SimEntity getEntity(int id)
   :outertype: SimulationNull

getEntity
^^^^^^^^^

.. java:method:: @Override public SimEntity getEntity(String name)
   :outertype: SimulationNull

getEntityId
^^^^^^^^^^^

.. java:method:: @Override public int getEntityId(String name)
   :outertype: SimulationNull

getEntityList
^^^^^^^^^^^^^

.. java:method:: @Override public List<SimEntity> getEntityList()
   :outertype: SimulationNull

getEntityName
^^^^^^^^^^^^^

.. java:method:: @Override public String getEntityName(int entityId)
   :outertype: SimulationNull

getMinTimeBetweenEvents
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getMinTimeBetweenEvents()
   :outertype: SimulationNull

getNetworkTopology
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public NetworkTopology getNetworkTopology()
   :outertype: SimulationNull

getNumEntities
^^^^^^^^^^^^^^

.. java:method:: @Override public int getNumEntities()
   :outertype: SimulationNull

getNumberOfFutureEvents
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public long getNumberOfFutureEvents(Predicate<SimEvent> predicate)
   :outertype: SimulationNull

holdEntity
^^^^^^^^^^

.. java:method:: @Override public void holdEntity(int src, long delay)
   :outertype: SimulationNull

isPaused
^^^^^^^^

.. java:method:: @Override public boolean isPaused()
   :outertype: SimulationNull

isRunning
^^^^^^^^^

.. java:method:: @Override public boolean isRunning()
   :outertype: SimulationNull

pause
^^^^^

.. java:method:: @Override public boolean pause()
   :outertype: SimulationNull

pause
^^^^^

.. java:method:: @Override public boolean pause(double time)
   :outertype: SimulationNull

pauseEntity
^^^^^^^^^^^

.. java:method:: @Override public void pauseEntity(int src, double delay)
   :outertype: SimulationNull

removeOnClockTickListener
^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean removeOnClockTickListener(EventListener<EventInfo> listener)
   :outertype: SimulationNull

removeOnEventProcessingListener
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean removeOnEventProcessingListener(EventListener<SimEvent> listener)
   :outertype: SimulationNull

removeOnSimulationPausedListener
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean removeOnSimulationPausedListener(EventListener<EventInfo> listener)
   :outertype: SimulationNull

resume
^^^^^^

.. java:method:: @Override public boolean resume()
   :outertype: SimulationNull

select
^^^^^^

.. java:method:: @Override public SimEvent select(int dest, Predicate<SimEvent> p)
   :outertype: SimulationNull

send
^^^^

.. java:method:: @Override public void send(int src, int dest, double delay, int tag, Object data)
   :outertype: SimulationNull

sendFirst
^^^^^^^^^

.. java:method:: @Override public void sendFirst(int src, int dest, double delay, int tag, Object data)
   :outertype: SimulationNull

sendNow
^^^^^^^

.. java:method:: @Override public void sendNow(int src, int dest, int tag, Object data)
   :outertype: SimulationNull

setNetworkTopology
^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void setNetworkTopology(NetworkTopology networkTopology)
   :outertype: SimulationNull

start
^^^^^

.. java:method:: @Override public double start() throws RuntimeException
   :outertype: SimulationNull

terminate
^^^^^^^^^

.. java:method:: @Override public boolean terminate()
   :outertype: SimulationNull

terminateAt
^^^^^^^^^^^

.. java:method:: @Override public boolean terminateAt(double time)
   :outertype: SimulationNull

updateEntityName
^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean updateEntityName(String oldName)
   :outertype: SimulationNull

wait
^^^^

.. java:method:: @Override public void wait(CloudSimEntity src, Predicate<SimEvent> p)
   :outertype: SimulationNull

waiting
^^^^^^^

.. java:method:: @Override public long waiting(int dest, Predicate<SimEvent> p)
   :outertype: SimulationNull

