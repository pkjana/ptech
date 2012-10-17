/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.util;

import java.util.Properties;
import javax.mail.Authenticator;

/**
 * Plain text mailing helper class
 * @author Debasis Jana
 * @version 0.1
 */
public class Mailer {

    private Properties configuration = null;
    private String mime = null; //mime type (plain text allowed only)
    private String user = null; //from user
    private String subject = null;
    private String body = null;
    private String[] to = null;
    private Authenticator authenticator = null;

    public Mailer() {
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @return the configuration
     */
    public Properties getConfiguration() {
        return configuration;
    }

    /**
     * @param configuration the configuration to set
     */
    public void setConfiguration(Properties configuration) {
        this.configuration = configuration;
    }

    /**
     * @return the mime
     */
    public String getMime() {
        return mime;
    }

    /**
     * @param mime the mime to set
     */
    public void setMime(String mime) {
        this.mime = mime;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the to
     */
    public String[] getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String[] to) {
        this.to = to;
    }

    public void setTo(String to) {
        setTo(new String[]{to});
    }

    /**
     * @return the authenticator
     */
    public Authenticator getAuthenticator() {
        return authenticator;
    }

    /**
     * @param authenticator the authenticator to set
     */
    public void setAuthenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

}