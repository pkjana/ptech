/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.user.command;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Registered user details
 * @author Debasis Jana
 */
public class User implements Serializable {

    private String email = null;
    private String name = null;
    private String password = null;
    private String confirm = null; //confirm password
    private String sex = null;
    private Date dob  = null;
    private byte[] resume = null;
    private boolean re = true; //resume exists
    private String phone = null;
    private String address = null;
    private byte[] photo = null;
    private byte[] sign = null;
    private boolean pe = false; //photo exists
    private boolean se = false; //sign exists
    private char mode = 'I'; //insert or update

    //education experince details
    private Set<Education> education = new HashSet();
    private Set<WorkExperience> experience = new HashSet();

    public User() {
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the confirm
     */
    public String getConfirm() {
        return confirm;
    }

    /**
     * @param confirm the confirm to set
     */
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the resume
     */
    public byte[] getResume() {
        return resume;
    }

    /**
     * @param resume the resume to set
     */
    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the photo
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(byte[] photo) {
        if(photo.length > 0) this.pe = true;
        this.photo = photo;
    }

    /**
     * @return the sign
     */
    public byte[] getSign() {
        return sign;
    }

    /**
     * @param sign the sign to set
     */
    public void setSign(byte[] sign) {
        if(sign.length > 0) this.se = true;
        this.sign = sign;
    }

    public void setMode(char mode) {
        this.mode = mode;
    }

    public char getMode() {
        return mode;
    }

    @Override
    public boolean equals(Object user) {
        if(user == null) return false;
        if(user == this) return true;
        if(user instanceof User) {
            User u = (User)user;
            return u.email.equals(this.email);
        } else return false;
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    /**
     * @return the education
     */
    public Set<Education> getEducation() {
        return education;
    }

    /**
     * @param education the education to set
     */
    public void setEducation(Set<Education> education) {
        this.education = education;
    }

    /**
     * @return the experience
     */
    public Set<WorkExperience> getExperience() {
        return experience;
    }

    /**
     * @param experience the experience to set
     */
    public void setExperience(Set<WorkExperience> experience) {
        this.experience = experience;
    }

    /**
     * @return the re
     */
    public boolean isRe() {
        return re;
    }

    /**
     * @param re the re to set
     */
    public void setRe(boolean re) {
        this.re = re;
    }

    /**
     * @return the pe
     */
    public boolean isPe() {
        return pe;
    }

    /**
     * @param pe the pe to set
     */
    public void setPe(boolean pe) {
        this.pe = pe;
    }

    /**
     * @return the se
     */
    public boolean isSe() {
        return se;
    }

    /**
     * @param se the se to set
     */
    public void setSe(boolean se) {
        this.se = se;
    }

}