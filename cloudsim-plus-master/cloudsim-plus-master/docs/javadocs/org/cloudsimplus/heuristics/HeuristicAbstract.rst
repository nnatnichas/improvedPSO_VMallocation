.. java:import:: org.cloudbus.cloudsim.distributions ContinuousDistribution

.. java:import:: java.lang.reflect Constructor

.. java:import:: java.lang.reflect InvocationTargetException

.. java:import:: java.util.stream IntStream

HeuristicAbstract
=================

.. java:package:: org.cloudsimplus.heuristics
   :noindex:

.. java:type:: public abstract class HeuristicAbstract<S extends HeuristicSolution<?>> implements Heuristic<S>

   A base class for \ :java:ref:`Heuristic`\  implementations.

   :author: Manoel Campos da Silva Filho
   :param <S>: the class of solutions the heuristic will deal with, starting with a initial solution (usually random, depending on sub-classes implementations) and executing the solution search in order to achieve a satisfying solution (defined by a stop criteria)

Constructors
------------
HeuristicAbstract
^^^^^^^^^^^^^^^^^

.. java:constructor::  HeuristicAbstract(ContinuousDistribution random, Class<S> solutionClass)
   :outertype: HeuristicAbstract

   Creates a heuristic.

   :param random: a random number generator
   :param solutionClass: reference to the generic class that will be used to instantiate heuristic solutions

Methods
-------
getBestSolutionSoFar
^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public S getBestSolutionSoFar()
   :outertype: HeuristicAbstract

getNeighborSolution
^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public S getNeighborSolution()
   :outertype: HeuristicAbstract

getNumberOfNeighborhoodSearchesByIteration
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: public int getNumberOfNeighborhoodSearchesByIteration()
   :outertype: HeuristicAbstract

getRandom
^^^^^^^^^

.. java:method:: protected ContinuousDistribution getRandom()
   :outertype: HeuristicAbstract

   :return: a random number generator

getRandomValue
^^^^^^^^^^^^^^

.. java:method:: @Override public int getRandomValue(int maxValue)
   :outertype: HeuristicAbstract

getSolveTime
^^^^^^^^^^^^

.. java:method:: @Override public double getSolveTime()
   :outertype: HeuristicAbstract

setBestSolutionSoFar
^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected final void setBestSolutionSoFar(S solution)
   :outertype: HeuristicAbstract

   Sets a solution as the current one.

   :param solution: the solution to set as the current one.

setNeighborSolution
^^^^^^^^^^^^^^^^^^^

.. java:method:: protected final void setNeighborSolution(S neighborSolution)
   :outertype: HeuristicAbstract

   Sets a solution as the neighbor one.

   :param neighborSolution: the solution to set as the neighbor one.

setNumberOfNeighborhoodSearchesByIteration
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: public void setNumberOfNeighborhoodSearchesByIteration(int numberOfNeighborhoodSearches)
   :outertype: HeuristicAbstract

setSolveTime
^^^^^^^^^^^^

.. java:method:: protected void setSolveTime(double solveTime)
   :outertype: HeuristicAbstract

   Sets the time taken to solve the heuristic.

   :param solveTime: the time to set (in seconds)

solve
^^^^^

.. java:method:: @Override public S solve()
   :outertype: HeuristicAbstract

updateSystemState
^^^^^^^^^^^^^^^^^

.. java:method:: protected abstract void updateSystemState()
   :outertype: HeuristicAbstract

   Updates the state of the system in order to keep looking for a suboptimal solution.

