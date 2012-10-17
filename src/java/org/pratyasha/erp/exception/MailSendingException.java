/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.exception;

/**
 * Exception thrown when mail related error occurs
 * @author Debasis Jana
 * @version 0.1
 */
public class MailSendingException extends RuntimeException {

    public MailSendingException(String message) {
        super(message);
    }

    public MailSendingException(String message, Throwable e) {
        super(message, e);
    }
}