.. java:import:: org.cloudbus.cloudsim.core.events SimEvent

SimEntityNull
=============

.. java:package:: org.cloudbus.cloudsim.core
   :noindex:

.. java:type:: final class SimEntityNull implements SimEntity

   A class that implements the Null Object Design Pattern for \ :java:ref:`SimEntity`\  class.

   :author: Manoel Campos da Silva Filho

   **See also:** :java:ref:`SimEntity.NULL`

Methods
-------
compareTo
^^^^^^^^^

.. java:method:: @Override public int compareTo(SimEntity o)
   :outertype: SimEntityNull

getId
^^^^^

.. java:method:: @Override public int getId()
   :outertype: SimEntityNull

getName
^^^^^^^

.. java:method:: @Override public String getName()
   :outertype: SimEntityNull

getSimulation
^^^^^^^^^^^^^

.. java:method:: @Override public Simulation getSimulation()
   :outertype: SimEntityNull

isStarted
^^^^^^^^^

.. java:method:: @Override public boolean isStarted()
   :outertype: SimEntityNull

println
^^^^^^^

.. java:method:: @Override public void println(String msg)
   :outertype: SimEntityNull

processEvent
^^^^^^^^^^^^

.. java:method:: @Override public void processEvent(SimEvent ev)
   :outertype: SimEntityNull

run
^^^

.. java:method:: @Override public void run()
   :outertype: SimEntityNull

schedule
^^^^^^^^

.. java:method:: @Override public void schedule(int dest, double delay, int tag)
   :outertype: SimEntityNull

setLog
^^^^^^

.. java:method:: @Override public void setLog(boolean log)
   :outertype: SimEntityNull

setName
^^^^^^^

.. java:method:: @Override public SimEntity setName(String newName) throws IllegalArgumentException
   :outertype: SimEntityNull

setSimulation
^^^^^^^^^^^^^

.. java:method:: @Override public SimEntity setSimulation(Simulation simulation)
   :outertype: SimEntityNull

shutdownEntity
^^^^^^^^^^^^^^

.. java:method:: @Override public void shutdownEntity()
   :outertype: SimEntityNull

start
^^^^^

.. java:method:: @Override public void start()
   :outertype: SimEntityNull

