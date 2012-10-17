/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.user.command;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents work experience details
 * @author Administrator
 */
public class WorkExperience implements Serializable {

    private String orgnization = null;
    private int serial = 0;
    private Date  from = null;
    private Date to = null;
    private String role = null;

    /**
     * @return the orgnization
     */
    public String getOrgnization() {
        return orgnization;
    }

    /**
     * @param orgnization the orgnization to set
     */
    public void setOrgnization(String orgnization) {
        this.orgnization = orgnization;
    }

    /**
     * @return the serial
     */
    public int getSerial() {
        return serial;
    }

    /**
     * @param serial the serial to set
     */
    public void setSerial(int serial) {
        this.serial = serial;
    }

    /**
     * @return the from
     */
    public Date getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(Date from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public Date getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(Date to) {
        this.to = to;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object we) {
        if(we == null) return false;
        else if(we == this) return true;
        else if(we instanceof WorkExperience) {
            WorkExperience _we = (WorkExperience)we;
            return _we.orgnization.equals(this.orgnization) && _we.serial == this.serial;
        } else return false;
    }

    @Override
    public int hashCode() {
        int hash = this.orgnization.hashCode();
        return hash =+ hash* this.serial * 31;
    }

}