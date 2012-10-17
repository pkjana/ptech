/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.util.controller;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.pratyasha.erp.exception.IncompleteInformationException;
import org.pratyasha.erp.exception.MailSendingException;
import org.pratyasha.erp.login.LoginDetails;
import org.pratyasha.erp.util.Mailer;
import org.pratyasha.erp.util.PratyashaUtil;
import org.pratyasha.erp.util.dao.CommonServices;
import org.springframework.mail.MailSendException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Common services, news loading. Users sends a parameter name, <b>action</b>, which in turns decides which action to be performed. The user requested action details given below.<br/>
 * 1. <b>ne-&gt;News and event details (limit is 10)<b/>, it requires start index (starts from 1) and the paramatere name is <b>w->from where<b/><br/>
 * Most of the services returns JSON.
 * @author Debasis Jana
 * @version 0.1
 */
public class CommonServicesController implements Controller{

    private CommonServices dao = null;

    /**
     * Sets common services DAO
     * @param dao Common services dao
     */
    public void setDao(CommonServices dao) {
        this.dao = dao;
    }

    //request processor
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String action = request.getParameter("a"); //action
        if(action.equals("ne")) { //news and events
            int s = new Integer(request.getParameter("w")); //start index, starts from 0
            List<Map<String, Object>> data = dao.getNewsAndEvents(s);
            PratyashaUtil.writeIntoResponse(response, PratyashaUtil.JSONScript(data), "text/plain");
        } else if(action.equals("q")) { //query
            //return new ModelAndView("../../jsp/contact.jsp");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String message = request.getParameter("message");

            if(name.isEmpty() || email.isEmpty() || !PratyashaUtil.validateEmail(email) || message.isEmpty()) throw new IncompleteInformationException("Invalid information provided");
            
            /*Mailer mailer = new Mailer();
            Properties configuration = PratyashaUtil.APP_CONFIGURATION;
            mailer.setUser(configuration.getProperty("contact"));
            mailer.setSubject("Query ....");
            mailer.setBody(message);
            mailer.setTo(phone);
            boolean status = PratyashaUtil.mail(mailer);*/
            boolean status = true; //mailing logic to be put here
            if(status) {
                request.setAttribute("message", "Soon we will get back to you");
                return new ModelAndView("thanks4interest");
            } else throw new MailSendingException("Mail can't be sent");
        } else if(action.equals("ird")) { //image resource download
            String c = request.getParameter("c"); //user code
            String t = request.getParameter("t"); //image type (P/S)

            PratyashaUtil.writeIntoResponse(response, dao.image(c, t.charAt(0)), "image/jpeg");
        } else if(action.equals("urd")) { //user resume download
            String c = request.getParameter("c"); //code

            PratyashaUtil.writeIntoResponse(response, dao.resume(c), "application/msword");
        }
        return null;
    }

}