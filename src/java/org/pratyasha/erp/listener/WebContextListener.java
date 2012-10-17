/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.listener;

import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.pratyasha.erp.exception.ConfigurationFileNotFoundException;
import org.pratyasha.erp.sql.DBUtil;
import org.pratyasha.erp.util.PratyashaUtil;

/**
 * Web application lifecycle listener.
 * @author Administrator
 */

public class WebContextListener implements ServletContextListener {

    //application initializer
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        String menu_query = "select menu_id, display_name as display, images, url, coalesce(tooltip,'') as tooltip from top_menu_details where delete_flag = 'N' order by \"order\"";
        ctx.setAttribute("MENU_DETAILS", DBUtil.getJdbc("ptech").queryForList(menu_query));

        try{
            //application configuration loading
            PratyashaUtil.APP_CONFIGURATION.load(new FileReader(ctx.getRealPath("/WEB-INF/classes/configuration.properties")));
        } catch(IOException e) {
            Logger.getLogger(WebContextListener.class).error(e.getMessage());
            //throw new IllegalStateException(e.getMessage(), e);
            throw new ConfigurationFileNotFoundException("Application configuration file not found", e);
        }
    }

    //destruction method
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}