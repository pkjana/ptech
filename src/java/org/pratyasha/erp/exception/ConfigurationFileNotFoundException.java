/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.exception;

/**
 * Thrown when any configuration file not found
 * @author Debasis Jana
 */
public class ConfigurationFileNotFoundException extends RuntimeException {

    public ConfigurationFileNotFoundException(String message) {
        super(message);
    }

    public ConfigurationFileNotFoundException(String message, Throwable e) {
        super(message, e);
    }
}