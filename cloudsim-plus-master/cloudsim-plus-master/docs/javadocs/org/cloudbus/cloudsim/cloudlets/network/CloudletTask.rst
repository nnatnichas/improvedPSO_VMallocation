.. java:import:: org.cloudbus.cloudsim.cloudlets CloudletExecutionInfo

.. java:import:: org.cloudbus.cloudsim.cloudlets Cloudlet

.. java:import:: org.cloudbus.cloudsim.core Identificable

CloudletTask
============

.. java:package:: org.cloudbus.cloudsim.cloudlets.network
   :noindex:

.. java:type:: public abstract class CloudletTask implements Identificable

   Represents one of many tasks that can be executed by a \ :java:ref:`NetworkCloudlet`\ .

   Please refer to following publication for more details:

   ..

   * \ `Saurabh Kumar Garg and Rajkumar Buyya, NetworkCloudSim: Modelling Parallel Applications in Cloud Simulations, Proceedings of the 4th IEEE/ACM International Conference on Utility and Cloud Computing (UCC 2011, IEEE CS Press, USA), Melbourne, Australia, December 5-7, 2011. <http://dx.doi.org/10.1109/UCC.2011.24>`_\

   :author: Saurabh Kumar Garg

Constructors
------------
CloudletTask
^^^^^^^^^^^^

.. java:constructor:: public CloudletTask(int id)
   :outertype: CloudletTask

   Creates a new task.

   :param id: task id

Methods
-------
getCloudlet
^^^^^^^^^^^

.. java:method:: public NetworkCloudlet getCloudlet()
   :outertype: CloudletTask

   Gets the NetworkCloudlet that the task belongs to.

getExecutionTime
^^^^^^^^^^^^^^^^

.. java:method:: public double getExecutionTime()
   :outertype: CloudletTask

   :return: the time the task spent executing, or -1 if not finished yet

getFinishTime
^^^^^^^^^^^^^

.. java:method:: public double getFinishTime()
   :outertype: CloudletTask

   :return: the time the task finished or -1 if not finished yet.

getId
^^^^^

.. java:method:: @Override public int getId()
   :outertype: CloudletTask

   Gets the id of the CloudletTask.

getMemory
^^^^^^^^^

.. java:method:: public long getMemory()
   :outertype: CloudletTask

   Gets the memory amount used by the task.

getStartTime
^^^^^^^^^^^^

.. java:method:: public double getStartTime()
   :outertype: CloudletTask

   :return: the time the task started executing, or -1 if not started yet.

isFinished
^^^^^^^^^^

.. java:method:: public boolean isFinished()
   :outertype: CloudletTask

   Indicates if the task is finished or not.

   :return: true if the task has finished

setCloudlet
^^^^^^^^^^^

.. java:method:: public void setCloudlet(NetworkCloudlet cloudlet)
   :outertype: CloudletTask

setFinished
^^^^^^^^^^^

.. java:method:: protected void setFinished(boolean finished)
   :outertype: CloudletTask

   Sets the task as finished or not

   :param finished: true to set the task as finished, false otherwise
   :throws RuntimeException: when the task is already finished and you try to set it as unfinished

setId
^^^^^

.. java:method:: public void setId(int id)
   :outertype: CloudletTask

   Sets the id of the CloudletTask.

   :param id:

setMemory
^^^^^^^^^

.. java:method:: public void setMemory(long memory)
   :outertype: CloudletTask

   Sets the memory amount used by the task.

   :param memory: the memory amount to set

setStartTime
^^^^^^^^^^^^

.. java:method:: public void setStartTime(double startTime)
   :outertype: CloudletTask

   Sets the time the task started executing.

   :param startTime: the start time to set

