/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.sql;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Database utilities
 * @author Debasis Jana
 * @version 0.1
 */
public class DBUtil {

    private static Context ctx = null;

    static {
        try{
            ctx = new InitialContext();
        } catch(NamingException e) {
            throw new IllegalStateException("Context can't be initialized", e);
        }
    }

    /**
     * Gets bound datasource (in context.xml) by datasource name
     * @param dsName Datasource name, ex: acad-datasource if bound name is jdbc/acad-datasource
     * @return Returns datasource ref
     */
    public static DataSource getDataSource(String dsName) {
        DataSource ds = null;
        try {
            ds = (DataSource)DBUtil.ctx.lookup("java:/comp/env/jdbc/" + dsName);
        } catch(NamingException e) {
            throw new IllegalStateException(e);
        }
        return ds;
    }

    /**
     * Gets connection by datasource name bound in context.xml
     * @param dsName Datasource name, ex: acad-datasource if bound name is jdbc/acad-datasource
     * @return Returns Connection object
     */
    public static Connection getConnection(String dsName) {
        Connection con = null;
        try {
            con = DBUtil.getDataSource(dsName).getConnection();
        } catch(SQLException e) {
            throw new IllegalStateException(e);
        }
        return con;
    }

    /**
     * Gets JDBC template object by datasource name bound in context.xml
     * @param dsName Datasource name, ex: acad-datasource if bound name is jdbc/acad-datasource
     * @return Returns JDBC template object
     */
    public static JdbcTemplate getJdbc(String dsName) {
        return new JdbcTemplate(DBUtil.getDataSource(dsName));
    }
}