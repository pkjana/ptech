/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.career.dao;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.pratyasha.erp.career.entity.Job;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Career related DAO services, like loading of current openings
 * @author Debasis Jana
 * @version 0.1
 */
public class CareerDao {

    private DataSource ds = null;

    /**
     * Empty constructor
     */
    public CareerDao() {
    }

    /**
     * Constructor
     * @param ds Required datasource
     */
    public CareerDao(DataSource ds) {
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
     * Gets current openings for a given range
     * @param offset Start index, starts from 0
     * @param limit Limit
     * @return Returns Current opening list
     */
    public List<Job> currentOpenings(int offset, int limit) {
        String query = "select a.id as id, \"desc\" as post, details, date, a.created_by as user from job_list a, post_master b where a.delete_flag = 'N' and a.post = b.id order by date offset " + offset + " limit " + limit;
        return new JdbcTemplate(ds).query(query, new BeanPropertyRowMapper(Job.class));
    }

    /**
     * Gets 10 current openings for a given start index
     * @param offset Start index, starts from 0
     * @return Returns Current opening list
     */
    public List<Job> currentOpenings(int offset) {
        return currentOpenings(offset, 10);
    }

    /**
     * Gets first 10 current openings
     * @return Returns Current opening list
     */
    public List<Job> currentOpenings() {
        return currentOpenings(0, 10);
    }

    public static void main(String ...a) {
        CareerDao career = new CareerDao();
        DriverManagerDataSource ds = new DriverManagerDataSource("org.postgresql.Driver", "jdbc:postgresql://10.57.4.68:5432/ptech", "erp", "db4dev@68");
        career.setDs(ds);
        //List<Job> jobs = career.currentOpenings();
        //Job job = jobs.get(0);
        //System.out.println(job.getDate());
    }

}