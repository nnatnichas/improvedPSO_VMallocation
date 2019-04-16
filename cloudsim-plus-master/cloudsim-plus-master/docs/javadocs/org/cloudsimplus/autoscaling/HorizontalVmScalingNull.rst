.. java:import:: org.cloudbus.cloudsim.vms Vm

.. java:import:: java.util.function Predicate

.. java:import:: java.util.function Supplier

HorizontalVmScalingNull
=======================

.. java:package:: org.cloudsimplus.autoscaling
   :noindex:

.. java:type:: final class HorizontalVmScalingNull implements HorizontalVmScaling

   A class that implements the Null Object Design Pattern for \ :java:ref:`HorizontalVmScaling`\  class.

   :author: Manoel Campos da Silva Filho

   **See also:** :java:ref:`HorizontalVmScaling.NULL`

Methods
-------
getOverloadPredicate
^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Predicate<Vm> getOverloadPredicate()
   :outertype: HorizontalVmScalingNull

getUnderloadPredicate
^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public Predicate<Vm> getUnderloadPredicate()
   :outertype: HorizontalVmScalingNull

getVm
^^^^^

.. java:method:: @Override public Vm getVm()
   :outertype: HorizontalVmScalingNull

getVmSupplier
^^^^^^^^^^^^^

.. java:method:: @Override public Supplier<Vm> getVmSupplier()
   :outertype: HorizontalVmScalingNull

requestScalingIfPredicateMatch
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public boolean requestScalingIfPredicateMatch(double time)
   :outertype: HorizontalVmScalingNull

setOverloadPredicate
^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public VmScaling setOverloadPredicate(Predicate<Vm> predicate)
   :outertype: HorizontalVmScalingNull

setUnderloadPredicate
^^^^^^^^^^^^^^^^^^^^^

.. java:method:: @Override public VmScaling setUnderloadPredicate(Predicate<Vm> predicate)
   :outertype: HorizontalVmScalingNull

setVm
^^^^^

.. java:method:: @Override public VmScaling setVm(Vm vm)
   :outertype: HorizontalVmScalingNull

setVmSupplier
^^^^^^^^^^^^^

.. java:method:: @Override public HorizontalVmScaling setVmSupplier(Supplier<Vm> supplier)
   :outertype: HorizontalVmScalingNull

