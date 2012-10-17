/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.blog.dao;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.pratyasha.erp.blog.entity.Blog;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Blog master
 * @author Debasis Jana
 */
public class BlogDao {

    private DataSource ds = null;

    /**
     * Empty constructor
     */
    public BlogDao() {
    }

    /**
     * COnstructor
     * @param ds Required datasource
     */
    public BlogDao(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Sets required datasource
     * @param ds Required datasource
     */
    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Gets blogs for a given user and given range
     * @param user Given user code, if null then all blogs listed
     * @param offset Start index, starts from 0
     * @param limit Limit
     * @return Returns list of blogs
     */
    public List<Blog> blogs(String user, int offset, int limit) {
        String query = "select id, subject, details as body, coalesce(b.code, 'Unknown') as user, creation_time as time from blog_master a left outer join siteusers_details b on a.created_by = b.code where a.delete_flag = 'N' " + (user == null ? "" : (" and a.created_by '" + user + "'") ) + " order by creation_time offset " + offset + " limit " + limit;
        return new JdbcTemplate(ds).query(query, new BeanPropertyRowMapper(Blog.class));
    }

    /**
     * Gets all blogs for a given range
     * @param offset Start index, starts from 0
     * @param limit Limit
     * @return Returns list of blogs
     */
    public List<Blog> blogs(int offset, int limit) {
        return blogs(null, offset, limit);
    }

    /**
     * Gets 10 blogs fora given given start index
     * @param offset Start index, starts from 0
     * @return Returns list of blogs
     */
    public List<Blog> blogs(int offset) {
        return blogs(null, offset, 10);
    }

    /**
     * Gets 10 blogs for a given user and given start index
     * @param user Given user code
     * @param offset Start index, starts from 0
     * @return Returns list of blogs
     */
    public List<Blog> blogs(String user, int offset) {
        return blogs(user, offset, 10);
    }

    /**
     * Gets first 10 blogs for a given user
     * @param user Given user
     * @return Returns list of blogs
     */
    public List<Blog> blogs(String user) {
        return blogs(user, 0, 10);
    }

    /**
     * Gets first 10 blogs
     * @return Returns list of blogs
     */
    public List<Blog> blogs() {
        return blogs(null, 0, 10);
    }

    /**
     * Gets blog details for a given blog id and given range
     * @param id Blog ID
     * @param offset Start index, starts from 0
     * @param limit Limit
     * @return Returns mapped blog details. Mapped keys are: <b>message-&gt</b>, <b>user</b>, <b>time</b>. Keys are self explainatory
     */
    public List<Map<String, Object>> blogDetails(int id, int offset, int limit) {
        String query = "select message, coalesce(b.code,'Unknown') as user, to_char(creation_time, 'yyyy/mm/dd HH12: MI AM') as time from blog_details a left outer join siteusers_details b on a.created_by = b.code where a.blog_id = " + id + " and a.delete_flag = 'N' order by creation_time offset " + offset + " limit " + limit;
        return new JdbcTemplate(ds).queryForList(query);
    }

    /**
     * Gets 10 blog details for a given blog id
     * @param id Blog ID
     * @param offset Start index, starts from 0
     * @return Returns blog details. Mapped keys are: <b>message-&gt</b>, <b>user</b>, <b>time</b>. Keys are self explainatory
     */
    public List<Map<String, Object>> blogDetails(int id, int offset) {
        return blogDetails(id, offset, 10);
    }

    /**
     * Gets first 10 blog details for a given blog id
     * @param id Given blog ID
     * @return Returns mapped blog details. Mapped keys are: <b>message-&gt</b>, <b>user</b>, <b>time</b>. Keys are self explainatory
     */
    public List<Map<String, Object>> blogDetails(int id) {
        return blogDetails(id, 0, 10);
    }

}