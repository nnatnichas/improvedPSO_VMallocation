.. java:import:: java.util ArrayList

.. java:import:: java.util Collections

.. java:import:: java.util List

.. java:import:: org.cloudbus.cloudsim.cloudlets CloudletSimple

.. java:import:: org.cloudbus.cloudsim.utilizationmodels UtilizationModel

NetworkCloudlet
===============

.. java:package:: org.cloudbus.cloudsim.cloudlets.network
   :noindex:

.. java:type:: public class NetworkCloudlet extends CloudletSimple

   NetworkCloudlet class extends Cloudlet to support simulation of complex applications. Each NetworkCloudlet represents a task of the application. Each task consists of several tasks.

   Please refer to following publication for more details:

   ..

   * \ `Saurabh Kumar Garg and Rajkumar Buyya, NetworkCloudSim: Modelling Parallel Applications in Cloud Simulations, Proceedings of the 4th IEEE/ACM International Conference on Utility and Cloud Computing (UCC 2011, IEEE CS Press, USA), Melbourne, Australia, December 5-7, 2011. <http://dx.doi.org/10.1109/UCC.2011.24>`_\

   :author: Saurabh Kumar Garg

Constructors
------------
NetworkCloudlet
^^^^^^^^^^^^^^^

.. java:constructor:: public NetworkCloudlet(int id, long cloudletLength, int pesNumber)
   :outertype: NetworkCloudlet

   Creates a NetworkCloudlet with no priority and file size and output size equal to 1.

   :param id: the unique ID of this cloudlet
   :param cloudletLength: the length or size (in MI) of this cloudlet to be executed in a VM
   :param pesNumber: the pes number

NetworkCloudlet
^^^^^^^^^^^^^^^

.. java:constructor:: @Deprecated public NetworkCloudlet(int id, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize, long memory, UtilizationModel utilizationModelCpu, UtilizationModel utilizationModelRam, UtilizationModel utilizationModelBw)
   :outertype: NetworkCloudlet

   Creates a NetworkCloudlet with the given parameters.

   :param id: the unique ID of this cloudlet
   :param cloudletLength: the length or size (in MI) of this cloudlet to be executed in a VM
   :param pesNumber: the pes number
   :param cloudletFileSize: the file size (in bytes) of this cloudlet \ ``BEFORE``\  submitting to a Datacenter
   :param cloudletOutputSize: the file size (in bytes) of this cloudlet \ ``AFTER``\  finish executing by a VM
   :param memory: the amount of memory
   :param utilizationModelCpu: the utilization model of CPU
   :param utilizationModelRam: the utilization model of RAM
   :param utilizationModelBw: the utilization model of BW

Methods
-------
addTask
^^^^^^^

.. java:method:: public NetworkCloudlet addTask(CloudletTask task)
   :outertype: NetworkCloudlet

   Adds a task to the \ :java:ref:`task list <getTasks()>`\  and links the task to the NetworkCloudlet.

   :param task: Task to be added
   :return: the NetworkCloudlet instance

getCurrentTask
^^^^^^^^^^^^^^

.. java:method:: public CloudletTask getCurrentTask()
   :outertype: NetworkCloudlet

   Gets the current task.

getLength
^^^^^^^^^

.. java:method:: @Override public long getLength()
   :outertype: NetworkCloudlet

   {@inheritDoc}

   The length of a NetworkCloudlet is the length sum of all its \ :java:ref:`CloudletExecutionTask`\ 's.

   :return: the length sum of all \ :java:ref:`CloudletExecutionTask`\ 's

getMemory
^^^^^^^^^

.. java:method:: public long getMemory()
   :outertype: NetworkCloudlet

   Gets the Cloudlet's RAM memory.

getNextTaskIfCurrentIfFinished
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected CloudletTask getNextTaskIfCurrentIfFinished()
   :outertype: NetworkCloudlet

   Gets the next task in the list if the current task is finished.

   :return: the next task or null if the current task is already the last one or it is not finished yet.

getNumberOfTasks
^^^^^^^^^^^^^^^^

.. java:method:: public double getNumberOfTasks()
   :outertype: NetworkCloudlet

getTasks
^^^^^^^^

.. java:method:: public List<CloudletTask> getTasks()
   :outertype: NetworkCloudlet

   :return: a read-only list of cloudlet's tasks.

isFinished
^^^^^^^^^^

.. java:method:: @Override public boolean isFinished()
   :outertype: NetworkCloudlet

isTasksStarted
^^^^^^^^^^^^^^

.. java:method:: public boolean isTasksStarted()
   :outertype: NetworkCloudlet

   Checks if the some Cloudlet Task has started yet.

   :return: true if some task has started, false otherwise

numberOfExecutionTasks
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected long numberOfExecutionTasks()
   :outertype: NetworkCloudlet

setFinishedLengthSoFar
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean setFinishedLengthSoFar(long length)
   :outertype: NetworkCloudlet

setMemory
^^^^^^^^^

.. java:method:: public NetworkCloudlet setMemory(long memory)
   :outertype: NetworkCloudlet

   Sets the Cloudlet's RAM memory.

   :param memory: amount of RAM to set

startNextTaskIfCurrentIsFinished
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: public boolean startNextTaskIfCurrentIsFinished(double nextTaskStartTime)
   :outertype: NetworkCloudlet

   Change the current task to the next one in order to start executing it, if the current task is finished.

   :param nextTaskStartTime: the time that the next task will start
   :return: true if the current task finished and the next one was started, false otherwise

