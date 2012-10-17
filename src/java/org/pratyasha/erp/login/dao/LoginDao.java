/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.login.dao;

import javax.sql.DataSource;
import org.pratyasha.erp.login.LoginDetails;
import org.pratyasha.erp.util.PratyashaUtil;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Login related DAO services like password change, password retrieval
 * @author Debasis Jana
 */
public class LoginDao {

    private DataSource ds = null;

    public LoginDao() {
    }

    public LoginDao(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Sets required datasource
     * @param ds Datasource
     */
    public void setDs(DataSource ds) {
        this.ds = ds;
    }


    /**
     * Changes user password
     * @param code User code
     * @param password New password
     */
    public boolean changePassword(String code, String password) {
        String query = "update site_users set password = '" + PratyashaUtil.SimplePasswordUtil.encrypt(password) + "' where id = '" + code + "'";
        return new JdbcTemplate(ds).update(query) == 1;
    }

    /**
     * Checks whether user exists for a given code and password
     * @param code User code
     * @param password User password, null in case of details by code only
     * @return Returns Login Details, null if not found
     */
    public LoginDetails exists(String code, String password) {
        String query = "select code as userID, password, name as userName, a.type as userType from siteusers_details a, site_users b where id = code and id = '" + code + "' " + (password == null ? "" : " and validated = 'Y' ") + " and delete_flag = 'N'";
        //System.out.println("Q: " + query);
        try {
            LoginDetails login = (LoginDetails)new JdbcTemplate(ds).queryForObject(query,new BeanPropertyRowMapper(LoginDetails.class));
            if(password != null) {
                String pass = PratyashaUtil.SimplePasswordUtil.decrypt(login.getPassword()); //original password
                return pass.equals(password) ? login : null;
            }
            return login;
        } catch(IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    /**
     * Gets passwowrd for a given user
     * @param code Given user code
     * @return Returns password, null if not found
     */
    public String password(String code) {
        String query = "select password from site_users where id = '" + code + "' and delete_flag = 'N'";
        try {
            return PratyashaUtil.SimplePasswordUtil.decrypt((String)new JdbcTemplate(ds).queryForObject(query, String.class));
        } catch(IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    /**
     * Gets email for a given user id
     * @param code User id
     * @return Returns email, null if invalid code found
     */
    public String email(String code) {
        String query = "select email from siteusers_details where code = '" + code + "'";
        try{
            return (String)new JdbcTemplate(ds).queryForObject(query, String.class);
        } catch(IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    /**
     * Validates user code when user clicks the link which sent to mail id
     * @param code Code to be validated
     * @param validation_text Validation text
     * @return Returns true if sucess otherwise false
     */
    public boolean validate(String code, String validation_text) {
        JdbcTemplate jdbc = new JdbcTemplate(ds);
        String query = "select count(*) from site_users where id = '" + code + "' and validation_text = '" + validation_text+ "' and delete_flag = 'N'";
        if(jdbc.queryForInt(query) == 1) { //valid user
            query = "update site_users set validated = 'Y' where id = '" + code + "'";
            jdbc.update(query);
            return true;
        } else return false; //unauthorize access
    }

    /**
     * Updates last access time for a given user code
     * @param code Given user code
     * @return Returns true, if succes otherwise false
     */
    public boolean updateAccessTime(String code) {
        String query = "update site_users set last_access_time = current_timestamp where id = '" + code + "'";
        return new JdbcTemplate(ds).update(query) == 1;
    }

    public static void main(String a[]) throws Exception{
        DriverManagerDataSource ds = new DriverManagerDataSource("org.postgresql.Driver", "jdbc:postgresql://10.57.4.68:5432/ptech", "erp", "db4dev@68");
        //LoginDao dao = new LoginDao(ds);
        //dao.exists("abc@gmail.com", "abd12345678");
        //JdbcTemplate jdbc = new JdbcTemplate(ds);
        //String d = "debasis jana";
        //final String e_d = new String(PratyashaUtil.PasswordUtil.encrypt(d));
        /*final byte[] e_d = PratyashaUtil.PasswordUtil.encrypt(d);
        String q = "insert into test values(?)";
        jdbc.update(q, new PreparedStatementSetter() {

            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setBytes(1, e_d);
            }
        });*/
        //jdbc.update(q);
        //String q = "select data from test";
        //byte[] e_d = (byte[])jdbc.queryForObject(q, byte[].class);
        //System.out.println("Dycrypt password: " + PratyashaUtil.PasswordUtil.decrypt(e_d));
    }

}