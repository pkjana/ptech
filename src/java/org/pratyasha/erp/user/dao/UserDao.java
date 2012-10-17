/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.user.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.sql.DataSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.pratyasha.erp.user.command.Education;
import org.pratyasha.erp.user.command.User;
import org.pratyasha.erp.user.command.WorkExperience;
import org.pratyasha.erp.util.PratyashaUtil;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * Registered user DAO services
 * @author Debasis Jana
 */
public class UserDao {

    private DataSource ds = null;

    /**
     * Sets required datasource
     * @param ds Reuqired datasource
     */
    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Saves user data
     * @param u User data details
     * @return returns Updated user data
     */
    @Transactional
    public User save(User u) {
        char mode = u.getMode();
        String email = u.getEmail();
        String password = u.getPassword();
        String name = u.getName();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
        Date date = u.getDob();
        String dob = sdf.format(date);

        String sex = u.getSex();
        String address = u.getAddress();
        String phone = u.getPhone();
        byte[] resume = u.getResume();
        boolean resume_u = resume != null && resume.length > 0; //resume uploaded
        byte[] photo = u.getPhoto();
        boolean photo_u = photo != null && photo.length > 0; //photo uploaded
        byte[] sign = u.getSign();
        boolean sign_u = sign != null && sign.length > 0; //sign uploaded

        String resume_path = null;
        String photo_path = null;
        String sign_path = null;
        if(resume_u) { //resume uploaded
            resume_path = PratyashaUtil.APP_CONFIGURATION.getProperty("resume_location");
            resume_path += email;
            new File(resume_path).mkdirs();
            resume_path += "/resume.doc";
            try{
                IOUtils.write(resume, new FileOutputStream(resume_path));
            } catch(Exception e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
        if(photo_u) { //photo uploaded
            photo_path = PratyashaUtil.APP_CONFIGURATION.getProperty("photo_location");
            photo_path += email;
            new File(photo_path).mkdirs();
            photo_path += "/photo.jpg";
            try{
                IOUtils.write(photo, new FileOutputStream(photo_path));
            } catch(Exception e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
        if(sign_u) { //sign uploaded
            sign_path = PratyashaUtil.APP_CONFIGURATION.getProperty("sign_location");
            sign_path += email;
            new File(sign_path).mkdirs();
            sign_path += "/sign.jpg";
            try{
                IOUtils.write(sign, new FileOutputStream(sign_path));
            } catch(Exception e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }

        JdbcTemplate jdbc = new JdbcTemplate(ds);
        if(mode == 'I') { //insert
            String query = "INSERT INTO users(email, \"name\", dob, sex, cv, created_by, creation_time) values(" +
                    "'" + email + "', '" + name + "', to_date('" + dob+ "', 'yyyy/mm/dd'), '" + sex + "','" + resume_path + "','" + email + "', current_timestamp)";
            jdbc.update(query);

            String vn = PratyashaUtil.userValidationNumber();
            query = "INSERT INTO site_users(id, \"password\", \"type\", created_by, creation_time, last_access_time, validation_text) values" +
                    "('" + email + "', '" + new String(PratyashaUtil.SimplePasswordUtil.encrypt(password)) + "', 'U', '" + email + "', current_timestamp, current_timestamp, '" + vn + "')";
            jdbc.update(query);

            /****mailing logic*****/
            //localhost should be your host name or IP
            String url = "http://localhost:8084/PratashyaTech/login.htm?a=v&v=" + vn + "&c=" + email;
            System.out.println("Link to be mail: " + url);
            /**********************/
        } else if(mode == 'U') { //update
            String query = "UPDATE users set name = '" + name + "', dob = to_date('" + dob + "', 'yyyy/mm/dd'), address = " + (address != null ? ("'" + address + "'") : "") + ", phone = " + phone + ", updated_by = '" + email + "', updation_time = current_timestamp" +
                    (resume_u ? (", cv = '" + resume_path + "'") : "") + (photo_u ? (", photo = '" + photo_path + "'") : "") + (sign_u ? (", sign = '" + sign_path + "'") : "") + " where email = '" + email + "'";
            
            jdbc.update(query);

            /*Set<Education> education = u.getEducation();
            Set<WorkExperience> experience = u.getExperience();*/

            //query = "delete from "
        } else { /*worst case*/ }
        return u;
    }

   /**
     * Checks whether user exists or not
     * @param code Email code
     * @return Returns true if exists otherwise false
     */
    public boolean exists(String code) {
        String query = "select count(*) from users where email = '" + code + "'";
        return new JdbcTemplate(ds).queryForInt(query) == 1;
    }

    /**
     * Gets user details for a given code
     * @param code Given code
     * @return Returns User details
     */
    public User select(String code) {
        String query = "SELECT email, \"name\", dob, sex, coalesce(address, '') as address, coalesce(phone, 0) as phone, true as re, case when coalesce(photo,'') <> '' then true else false end as pe, case when coalesce(sign,'') <> '' then true else false end as se  FROM users where email = '" + code +  "'";
        return (User)new JdbcTemplate(ds).queryForObject(query, new BeanPropertyRowMapper(User.class));
    }

}