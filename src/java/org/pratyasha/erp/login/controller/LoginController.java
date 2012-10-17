/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.pratyasha.erp.exception.SessionExpiryException;
import org.pratyasha.erp.login.LoginDetails;
import org.pratyasha.erp.login.dao.LoginDao;
import org.pratyasha.erp.user.dao.UserDao;
import org.pratyasha.erp.user.exception.UnauthorizedAccessException;
import org.pratyasha.erp.util.PratyashaUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Login processing controller like login, change password, logout
 * @author Debasis Jana
 */
public class LoginController implements Controller {

    private LoginDao dao = null;
    private UserDao userDao = null;

    public void setDao(LoginDao dao) {
        this.dao = dao;
    }

    public void setUserDao(UserDao dao) {
        this.userDao = dao;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String a = request.getParameter("a");
        if(a.equals("v")) { //login validation (in case of new user)
            HttpSession session = request.getSession(true);

            String code = request.getParameter("c");
            //String pass = request.getParameter("p"); //its fake (its used to get login details)
            String validation_text = request.getParameter("v");
            if(dao.validate(code, validation_text)) { //valid user
                LoginDetails login = dao.exists(code, null);
                session.setAttribute("loginDetails", login);
            } else throw new UnauthorizedAccessException("Unauthorize access");

            request.setAttribute("user", userDao.select(code));
            request.setAttribute("title", "User Profile");
            return new ModelAndView("user/profile");
        } else if(a.equals("l")) { //plain login

            String code = request.getParameter("user");
            String pass = request.getParameter("pass");
            LoginDetails login = dao.exists(code, pass);

            HttpSession session = request.getSession(true);
            if(login != null) {
                session.setAttribute("loginDetails", login);
            }
            //else request.setAttribute("lem", "error!!");
            return new ModelAndView("../../jsp/home"); //home page
        } else if(a.equals("lo")) { //log off

            HttpSession session = request.getSession();
            if(session != null){
                session.invalidate();
                request.setAttribute("lom", "Successfully log out"); //log off message
            } //invalidates session
            return new ModelAndView("../../jsp/home"); //home page

        } else if(a.equals("cp")) { //change password

            HttpSession session = request.getSession();
            if(session != null) {
                String old_pass = request.getParameter("o_pass"); //old pass
                String pass = request.getParameter("pass"); //new pass
                boolean status = true;
                String message = null;
                String code = PratyashaUtil.login(session).getUserID();
                if(dao.exists(code, old_pass) != null) { //password exists
                    status = dao.changePassword(code, pass);
                    message = status ? "Error in password change" : "Successfuly changed !!";
                } else { //password does not exist
                    status = false;
                    message = "Password does not match";
                }

                PratyashaUtil.writeIntoResponse(response, "{s: " + status + ", m: '" + message + "'}", "text/plain");
            } else throw new SessionExpiryException("session has expired");

        } else if(a.equals("rp")) { //request for password

            String code = request.getParameter("c"); //user code
            String email = dao.email(code); //email where password to be sent
            String message = null;
            if(email == null){
                //throw new UnauthorizedAccessException("Invalid user code");
                message = "Unauthorized access";
            } else message = "Check your mail...";
            String pass = dao.password(code);
            /*mailing logic (send password to mail) *****/
            PratyashaUtil.writeIntoResponse(response, message, "text/plain");

        }
        return null;
    }

}