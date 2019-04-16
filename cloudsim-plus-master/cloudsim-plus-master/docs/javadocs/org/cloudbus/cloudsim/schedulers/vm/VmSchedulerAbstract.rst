.. java:import:: java.util.stream LongStream

.. java:import:: org.cloudbus.cloudsim.hosts Host

.. java:import:: org.cloudbus.cloudsim.provisioners PeProvisioner

.. java:import:: org.cloudbus.cloudsim.resources Pe

.. java:import:: org.cloudbus.cloudsim.vms Vm

.. java:import:: java.util.stream IntStream

VmSchedulerAbstract
===================

.. java:package:: org.cloudbus.cloudsim.schedulers.vm
   :noindex:

.. java:type:: public abstract class VmSchedulerAbstract implements VmScheduler

   An abstract class for implementation of \ :java:ref:`VmScheduler`\ s.

   :author: Rodrigo N. Calheiros, Anton Beloglazov

Fields
------
DEFAULT_VM_MIGRATION_CPU_OVERHEAD
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:field:: public static final double DEFAULT_VM_MIGRATION_CPU_OVERHEAD
   :outertype: VmSchedulerAbstract

   The default percentage to define the CPU overhead of VM migration if one is not explicitly set.

   **See also:** :java:ref:`.getVmMigrationCpuOverhead()`

Constructors
------------
VmSchedulerAbstract
^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public VmSchedulerAbstract()
   :outertype: VmSchedulerAbstract

   Creates a VmScheduler.

VmSchedulerAbstract
^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public VmSchedulerAbstract(double vmMigrationCpuOverhead)
   :outertype: VmSchedulerAbstract

   Creates a VmScheduler, defining a CPU overhead for VM migration.

   :param vmMigrationCpuOverhead: the percentage of Host's CPU usage increase when a VM is migrating in or out of the Host. The value is in scale from 0 to 1 (where 1 is 100%).

Methods
-------
allocatePesForVm
^^^^^^^^^^^^^^^^

.. java:method:: @Override public final boolean allocatePesForVm(Vm vm)
   :outertype: VmSchedulerAbstract

allocatePesForVm
^^^^^^^^^^^^^^^^

.. java:method:: @Override public final boolean allocatePesForVm(Vm vm, List<Double> mipsShareRequested)
   :outertype: VmSchedulerAbstract

allocatePesForVmInternal
^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected abstract boolean allocatePesForVmInternal(Vm vm, List<Double> mipsShareRequested)
   :outertype: VmSchedulerAbstract

deallocatePesForAllVms
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void deallocatePesForAllVms()
   :outertype: VmSchedulerAbstract

deallocatePesFromVm
^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void deallocatePesFromVm(Vm vm)
   :outertype: VmSchedulerAbstract

deallocatePesFromVm
^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void deallocatePesFromVm(Vm vm, int pesToRemove)
   :outertype: VmSchedulerAbstract

deallocatePesFromVmInternal
^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected abstract void deallocatePesFromVmInternal(Vm vm, int pesToRemove)
   :outertype: VmSchedulerAbstract

getAllocatedMips
^^^^^^^^^^^^^^^^

.. java:method:: @Override public List<Double> getAllocatedMips(Vm vm)
   :outertype: VmSchedulerAbstract

getAvailableMips
^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getAvailableMips()
   :outertype: VmSchedulerAbstract

getHost
^^^^^^^

.. java:method:: @Override public Host getHost()
   :outertype: VmSchedulerAbstract

getMaxAvailableMips
^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getMaxAvailableMips()
   :outertype: VmSchedulerAbstract

getMaxCpuUsagePercentDuringOutMigration
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getMaxCpuUsagePercentDuringOutMigration()
   :outertype: VmSchedulerAbstract

getMipsMapAllocated
^^^^^^^^^^^^^^^^^^^

.. java:method:: protected Map<Vm, List<Double>> getMipsMapAllocated()
   :outertype: VmSchedulerAbstract

   Gets the map of VMs to MIPS, were each key is a VM and each value is the currently allocated MIPS from the respective PE to that VM. The PEs where the MIPS capacity is get are defined in the \ :java:ref:`peMap`\ .

   :return: the mips map

   **See also:** :java:ref:`.getAllocatedMips(Vm)`

getMipsMapRequested
^^^^^^^^^^^^^^^^^^^

.. java:method:: protected Map<Vm, List<Double>> getMipsMapRequested()
   :outertype: VmSchedulerAbstract

   Gets a map of MIPS requested by each VM, where each key is a VM and each value is a list of MIPS requested by that VM.

getMipsShareRequestedReduced
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected List<Double> getMipsShareRequestedReduced(Vm vm, List<Double> mipsShareRequested)
   :outertype: VmSchedulerAbstract

   Gets an adjusted List of MIPS requested by a VM, reducing every MIPS which is higher than the \ :java:ref:`capacity of each physical PE <getPeCapacity()>`\  to that value.

   :param vm: the VM to get the MIPS requested
   :param mipsShareRequested: the VM requested MIPS List
   :return: the VM requested MIPS List without MIPS higher than the PE capacity.

getPeCapacity
^^^^^^^^^^^^^

.. java:method:: @Override public long getPeCapacity()
   :outertype: VmSchedulerAbstract

getPeMap
^^^^^^^^

.. java:method:: protected Map<Vm, List<Pe>> getPeMap()
   :outertype: VmSchedulerAbstract

   Gets the map of VMs to PEs, where each key is a VM and each value is a list of PEs allocated to that VM.

getPesAllocatedForVm
^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public List<Pe> getPesAllocatedForVm(Vm vm)
   :outertype: VmSchedulerAbstract

getRequestedMips
^^^^^^^^^^^^^^^^

.. java:method:: @Override public List<Double> getRequestedMips(Vm vm)
   :outertype: VmSchedulerAbstract

getTotalAllocatedMipsForVm
^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getTotalAllocatedMipsForVm(Vm vm)
   :outertype: VmSchedulerAbstract

getVmMigrationCpuOverhead
^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public double getVmMigrationCpuOverhead()
   :outertype: VmSchedulerAbstract

getWorkingPeList
^^^^^^^^^^^^^^^^

.. java:method:: @Override public final List<Pe> getWorkingPeList()
   :outertype: VmSchedulerAbstract

isAllowedToAllocateMips
^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean isAllowedToAllocateMips(List<Double> vmRequestedMipsShare)
   :outertype: VmSchedulerAbstract

   Checks if the requested amount of MIPS is available to be allocated to a VM.

   :param vmRequestedMipsShare: a list of MIPS requested by a VM
   :return: true if the requested MIPS List is available, false otherwise

isSuitableForVm
^^^^^^^^^^^^^^^

.. java:method:: @Override public final boolean isSuitableForVm(Vm vm)
   :outertype: VmSchedulerAbstract

percentOfMipsToRequest
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected double percentOfMipsToRequest(Vm vm)
   :outertype: VmSchedulerAbstract

   Gets the percentage of the MIPS requested by a VM that will be in fact requested to the Host, according to the VM migration status:

   ..

   * VM is migrating out of this Host: the MIPS requested by VM will be reduced according to the \ :java:ref:`CPU migration overhead <getVmMigrationCpuOverhead()>`\ . The number of MIPS corresponding to the CPU overhead is used by the Host to perform the migration;
   * VM is migrating into this Host: only a fraction of its requested MIPS will be in fact requested to the Host. This amount is computed by reducing the \ :java:ref:`CPU migration overhead <getVmMigrationCpuOverhead()>`\ ;
   * VM is not in migration: 100% of its requested MIPS will be in fact requested to the Host

   :param vm: the VM that is requesting MIPS from the Host
   :return: the percentage of MIPS requested by the VM that will be in fact requested to the Host (in scale from [0 to 1], where is 100%)

removePesFromMap
^^^^^^^^^^^^^^^^

.. java:method:: protected <T> int removePesFromMap(Vm vm, Map<Vm, List<T>> map, int pesToRemove)
   :outertype: VmSchedulerAbstract

   Remove a given number of PEs from a given \ ``Vm -> List<PE>``\  Map, where each PE in the List associated to each Vm may be an actual \ :java:ref:`Pe`\  object or just its capacity in MIPS (Double).

   In other words, the map can be \ ``Map<Vm, List<Double>>``\  or \ ``Map<Vm, List<Pe>>``\ .

   :param <T>: the type of the elements into the List associated to each map key, which can be a MIPS number (Double) or an actual \ :java:ref:`Pe`\  object.
   :param vm: the VM to remove PEs from
   :param map: the map where the PEs will be removed
   :param pesToRemove: the number of PEs to remove from the List of PEs associated to the Vm
   :return: the number of removed PEs

setHost
^^^^^^^

.. java:method:: @Override public VmScheduler setHost(Host host)
   :outertype: VmSchedulerAbstract

setMipsMapAllocated
^^^^^^^^^^^^^^^^^^^

.. java:method:: protected final void setMipsMapAllocated(Map<Vm, List<Double>> mipsMapAllocated)
   :outertype: VmSchedulerAbstract

   Sets the map of VMs to MIPS, were each key is a VM and each value is the currently allocated MIPS from the respective PE to that VM. The PEs where the MIPS capacity is get are defined in the \ :java:ref:`peMap`\ .

   :param mipsMapAllocated: the mips map

setPeMap
^^^^^^^^

.. java:method:: protected final void setPeMap(Map<Vm, List<Pe>> peMap)
   :outertype: VmSchedulerAbstract

   Sets the map of VMs to PEs, where each key is a VM and each value is a list of PEs allocated to that VM.

   :param peMap: the pe map

