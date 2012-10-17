/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.login;

import java.io.Serializable;
import java.util.Set;

/**
 * User credential details, when a user logs in
 * @author Debasis Jana
 * @version 0.1
 */
public class LoginDetails implements Serializable {

    /**
     * User ID
     */
    private String userID = null;

    private String password = null;
    
    /**
     * User type (E->Employee, U->Ordinary registered User, C-> Client, V->Vendor ....)
     */
    private String userType = null;

    /**
     * User name
     */
    private String userName = null;

    /**
     * User designation (valid in case of employee)
     */
    private String designation = null;

    /**
     * User roles (valid in case of employee)
     */
    private Set<String> roles = null;

    /**
     * user email
     */
    private String email = null;

    public LoginDetails(){}

    /**
     * Constructor
     * @param userID User ID
     */
    public LoginDetails(String userID) {
        this.userID = userID;
    }

    /**
     * Constructor
     * @param userID User ID
     * @param userName User name
     */
    public LoginDetails(String userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    /**
     * Constructor
     * @param userID User ID
     * @param userName User name
     * @param userType User type (E->Employee, U->Ordinary registered User, C-> Client, V->Vendor ....)
     */
    public LoginDetails(String userID, String userName, String userType) {
        this.userID = userID;
        this.userName = userName;
        this.userType = userType;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * User ID
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    /**
     * User type (E->Employee, U->Ordinary registered User, C-> Client, V->Vendor ....)
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * User type (E->Employee, U->Ordinary registered User, C-> Client, V->Vendor ....)
     * @param userType the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * User name
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * User name
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * User designation (valid in case of employee)
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * User designation (valid in case of employee)
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * User roles (valid in case of employee)
     * @return the roles
     */
    public Set<String> getRoles() {
        return roles;
    }

    /**
     * User roles (valid in case of employee)
     * @param roles the roles to set
     */
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}