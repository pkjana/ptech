/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.util.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Abstract class for Downloading web resources. Developer needs to put logic of getting stream and response MIME type.
 * @author Debasis Jana
 * @version 0.1
 */
public abstract class DownloadWebResources implements Controller {

    private String mime = null;

    /**
     * User defined MIME type
     * @param mime MIME type
     */
    public void setMime(String mime) {
        this.mime = mime;
    }

    //request processor
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        return null;
    }

    /**
     * Gets web contents according to user requested parameters
     * @param request User requested parameter
     * @return Returns resource contents
     */
    public abstract byte[] contents(HttpServletRequest request);

}