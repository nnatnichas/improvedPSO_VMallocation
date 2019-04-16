/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */
package org.cloudbus.cloudsim.resources;

/**
 * A class that represents simple resources such as RAM, CPU, Bandwidth or Pe,
 * storing, for instance, the resource capacity and amount of free available resource.
 *
 * <p>The class is abstract just to ensure there will be an specific subclass
 * for each kind of resource, allowing to differentiate, for instance,
 * a RAM resource instance from a BW resource instance.
 * The VM class also relies on this differentiation for generically getting a
 * required resource.</p>
 *
 * @author Uros Cibej
 * @author Anthony Sulistio
 * @author Manoel Campos da Silva Filho
 * @since CloudSim Plus 1.0
 */
public abstract class ResourceManageableAbstract extends ResourceAbstract implements ResourceManageable {

    /** @see #getAvailableResource() */
    private long availableResource;

    public ResourceManageableAbstract(final long capacity) {
        super(capacity);
        this.availableResource = capacity;
    }

    @Override
    public boolean setCapacity(long newCapacity){
        if(newCapacity < 0 || getAllocatedResource() > newCapacity) {
            return false;
        }

        final long oldCapacity = this.capacity;
        this.capacity = newCapacity;
        sumAvailableResource(newCapacity - oldCapacity);
        return true;
    }

    @Override
    public boolean addCapacity(long capacityToAdd) {
        if(capacityToAdd < 0){
            throw new IllegalArgumentException("The number of PEs to add cannot be negative.");
        }

        return setCapacity(getCapacity()+capacityToAdd);
    }

    @Override
    public boolean removeCapacity(long capacityToRemove) {
        if(capacityToRemove < 0){
            throw new IllegalArgumentException("The number of PEs to remove cannot be negative.");
        }
        if(capacityToRemove > this.getCapacity()){
            throw new IllegalArgumentException(
                "The number of PEs to remove cannot be higher than the number of existing PEs. "+
                "Requested to remove: " + capacityToRemove + " PEs. Existing: " + this.getCapacity() + " PEs.");
        }
        return setCapacity(getCapacity()-capacityToRemove);
    }

    /**
     * Sum a given amount (negative or positive) of available (free) resource to the total
     * available resource.
     * @param amountToSum the amount to sum in the current total
     * available resource. If given a positive number, increases the total available
     * resource; otherwise, decreases the total available resource.
     * @return true if the total available resource was changed; false otherwise
     */
    protected boolean sumAvailableResource(final long amountToSum){
        final long newTotalAvailableResource = getAvailableResource() + amountToSum;
        return setAvailableResource(newTotalAvailableResource);
    }

    /**
     * Sets the given amount as available resource.
     *
     * @param newAvailableResource the new amount of available resource to set
     * @return true if {@code availableResource > 0 and availableResource <= capacity}, false otherwise
     */
    protected final boolean setAvailableResource(final long newAvailableResource) {
        if(newAvailableResource < 0 || newAvailableResource > getCapacity()) {
            return false;
        }

        this.availableResource = newAvailableResource;
        return true;
    }

    @Override
    public long getAvailableResource() {
        return availableResource;
    }

    @Override
    public boolean allocateResource(final long amountToAllocate) {
        if(amountToAllocate <= 0 || !isResourceAmountAvailable(amountToAllocate)) {
            return false;
        }

        final long newAvailableResource = getAvailableResource() - amountToAllocate;

        return setAvailableResource(newAvailableResource);
    }

    @Override
    public boolean setAllocatedResource(final long newTotalAllocatedResource) {
        if(newTotalAllocatedResource < 0 || !isSuitable(newTotalAllocatedResource)) {
            return false;
        }

        deallocateAllResources();
        return allocateResource(newTotalAllocatedResource);
    }

    @Override
    public boolean deallocateAndRemoveResource(long amountToDeallocate) {
        final long amountToRemoveFromSize = amountToDeallocate;
        //cannot deallocate more than it's allocated
        amountToDeallocate = Math.min(amountToDeallocate, this.getAllocatedResource());
        if(amountToDeallocate != 0 && !deallocateResource(amountToDeallocate)){
            return false;
        }

        return removeCapacity(amountToRemoveFromSize);

    }

    @Override
    public boolean deallocateResource(final long amountToDeallocate) {
        if(amountToDeallocate <= 0 || !isResourceAmountBeingUsed(amountToDeallocate)) {
            return false;
        }

        final long newAvailableResource = getAvailableResource() + amountToDeallocate;
        return setAvailableResource(newAvailableResource);
    }

    @Override
    public long deallocateAllResources() {
        final long previousAllocated = getAllocatedResource();
        setAvailableResource(getCapacity());
        return previousAllocated;
    }

    @Override
    public String toString() {
        return String.format("%s: used %d of %d", getClass().getSimpleName(), getAllocatedResource(), getCapacity());
    }
}
