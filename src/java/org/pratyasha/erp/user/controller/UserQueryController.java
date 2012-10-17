/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.pratyasha.erp.login.LoginDetails;
import org.pratyasha.erp.user.dao.UserDao;
import org.pratyasha.erp.user.exception.UnauthorizedAccessException;
import org.pratyasha.erp.util.PratyashaUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * User query processing controller
 * @author Debasis Jana
 */
public class UserQueryController implements Controller {

    private UserDao dao = null; //dao

    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String a = request.getParameter("a");
        //typical query request
        if(a != null) {
            if(a.equals("p")) { //profile
                String c = request.getParameter("c"); //requested user code, if null then current user
                if(c == null) {
                    LoginDetails login = (LoginDetails)PratyashaUtil.login(request);
                    if(login != null) { //login found
                        c = login.getUserID();
                    } else throw new UnauthorizedAccessException("invalid access");
                }
                //user object
                request.setAttribute("user", dao.select(c));
                return new ModelAndView("user/profile");
            }
            return null;
        }
        return null;
    }

}