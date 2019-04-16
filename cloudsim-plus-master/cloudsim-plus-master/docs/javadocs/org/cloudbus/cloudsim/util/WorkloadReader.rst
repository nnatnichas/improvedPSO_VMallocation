.. java:import:: java.io IOException

.. java:import:: java.util List

.. java:import:: org.cloudbus.cloudsim.cloudlets Cloudlet

WorkloadReader
==============

.. java:package:: org.cloudbus.cloudsim.util
   :noindex:

.. java:type:: public interface WorkloadReader

   Provides methods to be implemented by classes that generate a list of (\ :java:ref:`Cloudlets <Cloudlet>`\ ) (jobs) to be submitted to a DatacenterBroker for execution inside some VMs. Such Cloudlets can be generated from different sources such as XML or CSV files containing Cloudlets configurations or from different formats of Datacenter trace files containing execution logs of real applications that can be used to mimic the behaviour of these application in a simulation environment.

   :author: Marcos Dias de Assuncao

   **See also:** :java:ref:`WorkloadFileReader`

Methods
-------
generateWorkload
^^^^^^^^^^^^^^^^

.. java:method::  List<Cloudlet> generateWorkload() throws IOException
   :outertype: WorkloadReader

   Generates a list of jobs (\ :java:ref:`Cloudlets <Cloudlet>`\ ) to be executed.

   :return: a generated Cloudlet list

