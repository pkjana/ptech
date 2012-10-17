/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.pratyasha.erp.login.LoginDetails;
import org.pratyasha.erp.user.command.User;
import org.pratyasha.erp.user.dao.UserDao;
import org.pratyasha.erp.user.exception.UnauthorizedAccessException;
import org.pratyasha.erp.util.PratyashaUtil;
import org.pratyasha.erp.util.PratyashaUtil.DatePropertyEditor;
import org.springframework.beans.propertyeditors.ByteArrayPropertyEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * User controller to provide service registered users
 * @author Debasis Jana
 */
public class UserController extends SimpleFormController {

    private UserDao dao = null;
    private String profileView = null; //profile view

    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    public void setProfileView(String view) {
        this.profileView = view;
    }

    public UserController() {
        setCommandClass(User.class);
    }

    //GET request processing
    /*@Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
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
                return new ModelAndView(profileView);
            }
            return null;
        } else return new ModelAndView(getFormView());
    }*/

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        //bind multipart
        binder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
        binder.registerCustomEditor(Date.class, new DatePropertyEditor());
    }

    @Override
    protected void onBind(HttpServletRequest request, Object user) throws Exception {
        HttpSession session = request.getSession();
        if(session != null) {
            LoginDetails login = PratyashaUtil.login(request);
            User u = (User)user;
            if(login != null) {
                u.setEmail(login.getUserID()); //sets user code (email) from session
                u.setMode('U');
                //profile update
                setFormView(profileView);
            } else setFormView("../../jsp/home"); //new user sign up
        } else setFormView("../../jsp/home");
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object user, BindException errors) throws Exception {
        User u = (User)user;
        if(u.getMode() == 'I' && dao.exists(u.getEmail())) {
            errors.rejectValue("email", "email.exists", "email already");
        }
    }

    @Override
    protected ModelAndView onSubmit(Object user) throws Exception {
        User u = (User)user;
        char m = u.getMode();
        boolean insert = m == 'I';
        dao.save(u);

        Map<String, Object> model = new HashMap();
        model.put("user", user);
        model.put("message", insert ? "Check your mail and click the link to complete registration" : "Profile successfully updated");
        return new ModelAndView(insert ? "thanks4interest" : profileView, model);
    }
}