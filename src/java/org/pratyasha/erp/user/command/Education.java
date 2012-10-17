/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.user.command;

import java.io.Serializable;

/**
 * Represents education details
 * @author Debasis Jana
 */
public class Education implements Serializable {

    private String code = null;
    private String place = null;
    private String approval = null;
    private int year = 0;
    private double marks = 0;

    public Education() {
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the place
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @return the approval
     */
    public String getApproval() {
        return approval;
    }

    /**
     * @param approval the approval to set
     */
    public void setApproval(String approval) {
        this.approval = approval;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the marks
     */
    public double getMarks() {
        return marks;
    }

    /**
     * @param marks the marks to set
     */
    public void setMarks(double marks) {
        this.marks = marks;
    }


    @Override
    public boolean equals(Object education) {
        if(education == null) return true;
        else if(education == this) return true;
        if(education instanceof Education) {
            Education e = (Education)education;
            return e.code.equals(this.code);
        } else return false;
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }
}