/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.client.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.pratyasha.erp.client.dao.ClientDao;
import org.pratyasha.erp.client.entity.Client;
import org.pratyasha.erp.util.PratyashaUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Client related service controller
 * @author Debasis Jana
 * @version 0.1
 */
public class ClientController implements Controller {

    private ClientDao dao = null;

    /**
     * Sets required dao
     * @param dao Dao
     */
    public void setDao(ClientDao dao) {
        this.dao = dao;
    }

    //request processor
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String a = request.getParameter("a");
        if(a.equals("l")) { //logo download
            String c = request.getParameter("c"); //client code
            Client client = dao.cient(c);

            //downloads logo
            PratyashaUtil.writeIntoResponse(response, IOUtils.toByteArray(new FileInputStream(client.getLogo())), "image/jpeg");
        }
        return null;
    }

}