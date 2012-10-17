/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.exception;

/**
 * Thrown when incomplete information found
 * @author Debasis Jana
 * @version 0.1
 */
public class IncompleteInformationException extends RuntimeException {

    public IncompleteInformationException(String message) {
        super(message);
    }

    public IncompleteInformationException(String message, Throwable e) {
        super(message, e);
    }
}
