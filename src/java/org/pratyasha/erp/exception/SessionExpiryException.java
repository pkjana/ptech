/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.exception;

/**
 * Thrown when session expired and user tries to access any authenticated page
 * @author Debasis Jana
 */
public class SessionExpiryException extends RuntimeException {

    public SessionExpiryException(String message) {
        super(message);
    }

    public SessionExpiryException(String message, Throwable e) {
        super(message, e);
    }
}