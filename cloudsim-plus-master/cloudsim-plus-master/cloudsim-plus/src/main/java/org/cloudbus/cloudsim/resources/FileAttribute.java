/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */
package org.cloudbus.cloudsim.resources;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.cloudbus.cloudsim.util.Conversion;
import org.cloudbus.cloudsim.util.DataCloudTags;


/**
 * Stores related information regarding to a
 * {@link org.cloudbus.cloudsim.resources.File} entity.
 *
 * @author Uros Cibej
 * @author Anthony Sulistio
 * @since CloudSim Toolkit 1.0
 */
public class FileAttribute {

    /**
     * Owner name of this file.
     */
    private String ownerName;

    /**
     * File ID given by a Replica Catalogue.
     */
    private int id;

    /**
     * File type, for instance raw, reconstructed, etc.
     */
    private int type;

    /**
     * File size in byte.
     */
    private int fileSize;

    /**
     * Check sum.
     */
    private int checksum;

    /**
     * Last updated time (sec) - relative.
     */
    private double lastUpdateTime;

    /**
     * Creation time (ms) - abosulte/relative.
     */
    private long creationTime;

    /**
     * Price of the file.
     */
    private double cost;

    /**
     * Indicates if the file is a master copy or not. If the attribute is false,
     * it means the file is a replica.
     */
    private boolean masterCopy;

    /**
     * The file that this attribute object is related to
     */
    private final File file;

    /**
     * Creates a new FileAttribute object.
     *
     * @param file the file that this attribute object is related to
     * @param fileSize the size for the File
     */
    public FileAttribute(final File file, final int fileSize) {
        this.file = file;

        // set the file creation time. This is absolute time
        final Calendar cal =
                (!Objects.isNull(file.getDatacenter().getSimulation())  ?
                file.getDatacenter().getSimulation().getCalendar() :
                Calendar.getInstance());
        Date date = cal.getTime();
        creationTime = date.getTime();

        ownerName = "";
        id = File.NOT_REGISTERED;
        checksum = 0;
        type = File.TYPE_UNKOWN;
        lastUpdateTime = 0;
        cost = 0;
        masterCopy = true;
        setFileSize(fileSize);
    }

    /**
     * Copy the values of the object into a given FileAttribute instance.
     *
     * @param destinationAttr the destination FileAttribute object to copy the
     * current object to
     * @return <tt>true</tt> if the copy operation is successful, <tt>false</tt>
     * otherwise
     */
    public boolean copyValue(FileAttribute destinationAttr) {
        if (Objects.isNull(destinationAttr)) {
            return false;
        }

        destinationAttr.setFileSize(fileSize);
        destinationAttr.setOwnerName(ownerName);
        destinationAttr.setUpdateTime(lastUpdateTime);
        destinationAttr.setRegistrationId(id);
        destinationAttr.setType(type);
        destinationAttr.setChecksum(checksum);
        destinationAttr.setCost(cost);
        destinationAttr.setMasterCopy(masterCopy);
        destinationAttr.setCreationTime(creationTime);

        return true;
    }

    /**
     * Sets the file creation time (in millisecond).
     *
     * @param creationTime the file creation time (in millisecond)
     * @return <tt>true</tt> if successful, <tt>false</tt> otherwise
     */
    public boolean setCreationTime(long creationTime) {
        if (creationTime <= 0) {
            return false;
        }

        this.creationTime = creationTime;
        return true;
    }

    /**
     * Gets the file creation time (in millisecond).
     *
     * @return the file creation time (in millisecond)
     */
    public long getCreationTime() {
        return creationTime;
    }

    /**
     * Sets the owner name of the file.
     *
     * @param name the owner name
     * @return <tt>true</tt> if successful, <tt>false</tt> otherwise
     */
    public boolean setOwnerName(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            return false;
        }

        ownerName = name;
        return true;
    }

    /**
     * Gets the owner name of the file.
     *
     * @return the owner name or <tt>null</tt> if empty
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Gets the size of the object (in byte). <br/>
     * NOTE: This object size is NOT the actual file size. Moreover, this size
     * is used for transferring this object over a network.
     *
     * @return the object size (in byte)
     */
    public int getAttributeSize() {
        int length = DataCloudTags.PKT_SIZE;
        if (!Objects.isNull(ownerName)) {
            length += ownerName.length();
        }

        length += file.getName().length();

        return length;
    }

    /**
     * Sets the file size (in MBytes).
     *
     * @param fileSize the file size (in MBytes)
     * @return <tt>true</tt> if successful, <tt>false</tt> otherwise
     */
    public final boolean setFileSize(int fileSize) {
        if (fileSize < 0) {
            return false;
        }

        this.fileSize = fileSize;
        return true;
    }

    /**
     * Gets the file size (in MBytes).
     *
     * @return the file size (in MBytes)
     */
    public int getFileSize() {
        return fileSize;
    }

    /**
     * Gets the file size (in bytes).
     *
     * @return the file size (in bytes)
     */
    public int getFileSizeInByte() {
        return fileSize * Conversion.MILLION;   // 1e6
        // return size * 1048576; // 1e6 - more accurate
    }

    /**
     * Sets the last update time of the file (in seconds). <br/>
     * NOTE: This time is relative to the start time. Preferably use
     * {@link org.cloudbus.cloudsim.core.CloudSim#clock()} method.
     *
     * @param time the last update time (in seconds)
     * @return <tt>true</tt> if successful, <tt>false</tt> otherwise
     */
    public boolean setUpdateTime(double time) {
        if (time <= 0 || time < lastUpdateTime) {
            return false;
        }

        lastUpdateTime = time;
        return true;
    }

    /**
     * Gets the last update time (in seconds).
     *
     * @return the last update time (in seconds)
     */
    public double getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * Sets the file registration ID (published by a Replica Catalogue entity).
     *
     * @param id registration ID
     * @return <tt>true</tt> if successful, <tt>false</tt> otherwise
     */
    public boolean setRegistrationId(int id) {
        if (id < 0) {
            return false;
        }

        this.id = id;
        return true;
    }

    /**
     * Gets the file registration ID.
     *
     * @return registration ID
     */
    public int getRegistrationID() {
        return id;
    }

    /**
     * Sets the file type (for instance raw, tag, etc).
     *
     * @param type a file type
     * @return <tt>true</tt> if successful, <tt>false</tt> otherwise
     */
    public boolean setType(int type) {
        if (type < 0) {
            return false;
        }

        this.type = type;
        return true;
    }

    /**
     * Gets the file type.
     *
     * @return file type
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the checksum of the file.
     *
     * @param checksum the checksum of this file
     * @return <tt>true</tt> if successful, <tt>false</tt> otherwise
     */
    public boolean setChecksum(int checksum) {
        if (checksum < 0) {
            return false;
        }

        this.checksum = checksum;
        return true;
    }

    /**
     * Gets the file checksum.
     *
     * @return file checksum
     */
    public int getChecksum() {
        return checksum;
    }

    /**
     * Sets the cost associated with the file.
     *
     * @param cost cost of this file
     * @return <tt>true</tt> if successful, <tt>false</tt> otherwise
     */
    public boolean setCost(double cost) {
        if (cost < 0) {
            return false;
        }

        this.cost = cost;
        return true;
    }

    /**
     * Gets the cost associated with the file.
     *
     * @return the cost of this file
     */
    public double getCost() {
        return cost;
    }

    /**
     * Checks if the file is already registered to a Replica Catalogue.
     *
     * @return <tt>true</tt> if it is registered, <tt>false</tt> otherwise
     */
    public boolean isRegistered() {
        return id != File.NOT_REGISTERED;
    }

    /**
     * Marks the file as a master copy or replica.
     *
     * @param masterCopy a flag denotes <tt>true</tt> for master copy or
     * <tt>false</tt> for a replica
     */
    public void setMasterCopy(boolean masterCopy) {
        this.masterCopy = masterCopy;
    }

    /**
     * Checks whether the file is a master copy or replica.
     *
     * @return <tt>true</tt> if it is a master copy or <tt>false</tt> if it is a
     * replica
     */
    public boolean isMasterCopy() {
        return masterCopy;
    }

    /**
     * Check if the name of a file is valid or not.
     *
     * @param fileName the file name to be checked for validity
     * @return <tt>true</tt> if the file name is valid, <tt>false</tt> otherwise
     */
    public static boolean isValid(final String fileName) {
        return !Objects.isNull(fileName) && !fileName.trim().isEmpty();
    }
}
