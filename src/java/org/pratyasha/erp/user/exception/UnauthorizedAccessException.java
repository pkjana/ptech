/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.user.exception;

/**
 * Thrown when unauthorized access found
 * @author Debasis Jana
 */
public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException(String message) {
        super(message);
    }

    public UnauthorizedAccessException(String message, Throwable e) {
        super(message, e);
    }
}