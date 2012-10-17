/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.pratyasha.erp.blog.dao.BlogDao;
import org.pratyasha.erp.util.PratyashaUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Blog related service controller
 * @author Debasis Jana
 * @version 0.1
 */
public class BlogController implements Controller {

    private BlogDao dao = null;

    /**
     * Sets blog dao
     * @param dao Blog dao
     */
    public void setDao(BlogDao dao) {
        this.dao = dao;
    }

    //request processor
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String a = request.getParameter("a");

        if(a.equals("bd")) { //blog details
            String id = request.getParameter("id");
            PratyashaUtil.writeIntoResponse(response, PratyashaUtil.JSONScript(dao.blogDetails(new Integer(id))), "text/plain");
        }
        return null;
    }

}