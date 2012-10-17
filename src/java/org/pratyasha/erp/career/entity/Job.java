/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.career.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Reprents a Job in an organization
 * @author Debasis Jana
 * @version 0.1
 */
public class Job implements Serializable {
    private String id = null; //job ID
    private String post = null; //job post
    private String details = null; //job details
    private Date date = null; //creation date
    private String user = null; //created by
    private char delete = 'N'; //delete flag

    /**
     * Empty constructor
     */
    public Job() {
    }

    /**
     * Constructor
     * @param id Job ID
     * @param post Job post
     */
    public Job(String id, String post) {
        this.id = id;
    }

    /**
     * Gets job ID
     * @return the job ID
     */
    public String getId() {
        return id;
    }

    /**
     * Sets job ID
     * @param id JOb ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets job post
     * @return the job post
     */
    public String getPost() {
        return post;
    }

    /**
     * Sets job post
     * @param post Job post
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * Gets job details
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets job details
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Gets job posting date
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param Sets job posting date
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets delete flag
     * @return the delete
     */
    public char getDelete() {
        return delete;
    }

    /**
     * Sets user (created by)
     * @param user User (created by)
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets user (created by)
     * @return Returns user (created by)
     */
    public String getUser() {
        return user;
    }

}