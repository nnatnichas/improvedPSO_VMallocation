.. java:import:: org.cloudbus.cloudsim.cloudlets Cloudlet

.. java:import:: org.cloudbus.cloudsim.datacenters Datacenter

.. java:import:: org.cloudbus.cloudsim.vms Vm

.. java:import:: org.cloudbus.cloudsim.core CloudSim

DatacenterBrokerSimple
======================

.. java:package:: org.cloudbus.cloudsim.brokers
   :noindex:

.. java:type:: public class DatacenterBrokerSimple extends DatacenterBrokerAbstract

   A simple implementation of \ :java:ref:`DatacenterBroker`\  that try to host customer's VMs at the first Datacenter found. If there isn't capacity in that one, it will try the other ones.

   The selection of VMs for each cloudlet is based on a Round-Robin policy, cyclically selecting the next VM from the broker VM list for each requesting cloudlet.

   :author: Rodrigo N. Calheiros, Anton Beloglazov, Manoel Campos da Silva Filho

Constructors
------------
DatacenterBrokerSimple
^^^^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public DatacenterBrokerSimple(CloudSim simulation)
   :outertype: DatacenterBrokerSimple

   Creates a new DatacenterBroker object.

   :param simulation: name to be associated with this entity

Methods
-------
selectDatacenterForWaitingVms
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected Datacenter selectDatacenterForWaitingVms()
   :outertype: DatacenterBrokerSimple

   Defines the policy to select a Datacenter to Host a VM. It always selects the first Datacenter from the Datacenter list.

   :return: the Datacenter selected to request the creating of waiting VMs or \ :java:ref:`Datacenter.NULL`\  if no suitable Datacenter was found

selectFallbackDatacenterForWaitingVms
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected Datacenter selectFallbackDatacenterForWaitingVms()
   :outertype: DatacenterBrokerSimple

   Defines the policy to select a fallback Datacenter to Host a VM when a previous selected Datacenter failed to create the requested VMs.

   It gets the first Datacenter that has not been tried yet.

   :return: the Datacenter selected to try creating the remaining VMs or \ :java:ref:`Datacenter.NULL`\  if no suitable Datacenter was found

selectVmForWaitingCloudlet
^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected Vm selectVmForWaitingCloudlet(Cloudlet cloudlet)
   :outertype: DatacenterBrokerSimple

   Defines the policy used to select a Vm to host a Cloudlet that is waiting to be created. It applies a Round-Robin policy to cyclically select the next Vm from the list of waiting VMs.

   :param cloudlet: the cloudlet that needs a VM to be placed into
   :return: the selected Vm for the cloudlet or \ :java:ref:`Vm.NULL`\  if no suitable VM was found

