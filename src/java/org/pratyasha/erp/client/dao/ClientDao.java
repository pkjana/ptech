/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.client.dao;

import java.util.List;
import javax.sql.DataSource;
import org.pratyasha.erp.client.entity.Client;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Client related DAO services
 * @author Debasis Jana
 * @version 0.1
 */
public class ClientDao {

    private DataSource ds = null;

    public ClientDao() {}

    public ClientDao(DataSource ds) {
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
     * Gets whole client list
     * @return Returns active client list
     */
    public List<Client> clients() {
        String query = "select code, description, from_date as date, logo_path as logo from clients where delete_flag = 'N' order by from_date";
        return new JdbcTemplate(ds).query(query, new BeanPropertyRowMapper(Client.class));
    }

    /**
     * Gets client details by client code
     * @param code Given code
     * @return Returns client details, null in case no data found
     */
    public Client cient(String code) {
        String query = "select code, description, from_date as date, logo_path as logo from clients where code = '" + code + "' and delete_flag = 'N'";
        try {
            return (Client)new JdbcTemplate(ds).queryForObject(query, new BeanPropertyRowMapper(Client.class));
        } catch(IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

}