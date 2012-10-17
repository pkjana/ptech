/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.util.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;
import org.pratyasha.erp.util.PratyashaUtil;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Common services dao like news loading.
 * @author Debasis Jana
 */
public class CommonServices {

    private DataSource ds = null;

    /**
     * Sets datasource to fetch data
     * @param ds Required datasource
     */
    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Gets news and events for a given limit
     * @param offset From which index data to be fetched, starts from 0
     * @param limit How many news and events to be fetched
     * @return Returns list of mapped object<br/>
     * Mapped keys: <b>details-&gt;Details about news or event</b> and <b>id-&gt;its for internal usage(to load more details)</b>
     */
    public List<Map<String, Object>> getNewsAndEvents(int offset, int limit) {
        String query = "select id, header, details from events_news where delete_flag = 'N' and current_date between coalesce(from_date, current_date - 1) and coalesce(to_date, current_date + 1) order by from_date offset " + offset + " limit " + limit;
        return new JdbcTemplate(ds).queryForList(query);
    }

    /**
     * Gets news and events for a given limit, (here limit is 10)
     * @param offset From which index data to be fetched, starts from 0
     * @return Returns list of mapped object<br/>
     * Mapped keys: <b>details-&gt;Details about news or event</b> and <b>id-&gt;its for internal usage(to load more details)</b>
     * @see #getNewsAndEvents(int, int)
     */
    public List<Map<String, Object>> getNewsAndEvents(int offset) {
        return getNewsAndEvents(offset, 10);
    }

    /**
     * Gets first 10 news and events details
     * @return Returns list of mapped object<br/>
     * Mapped keys: <b>details-&gt;Details about news or event</b> and <b>id-&gt;its for internal usage(to load more details)</b>
     * @see #getNewsAndEvents(int, int)
     * @see #getNewsAndEvents(int)
     */
    public List<Map<String, Object>> getNewsAndEvents() {
        return getNewsAndEvents(0, 10);
    }

    /**
     * Gets image resource for a given code and image type
     * @param code Given code
     * @param type Given image type (P/S)
     * @return returns image contents, null in case of image not found or invalid user or physical file not found
     */
    public byte[] image(String code, char type) {
        String c = type == 'P' ? "photo" : (type == 'S' ? "Sign" : "");
        if(c.isEmpty()) return null;
        String query = "select " + c + " from siteusers_details where code = '" + code + "'";
        String img_p = (String)new JdbcTemplate(ds).queryForObject(query, String.class);
        if(img_p.isEmpty()) return null;
        try{
            return IOUtils.toByteArray(new FileInputStream(img_p));
        } catch(IOException e) {
            return null;
        }
    }

    /**
     * Gets resume contents for a given code
     * @param code Given user code
     * @return returns resume byte array, null in case of invalid user or physical file not found
     */
    public byte[] resume(String code) {
        String query = "select cv from users where email = '" + code + "' and delete_flag = 'N'";
        try{
            //assumed resume will be found
            String resume = (String)new JdbcTemplate(ds).queryForObject(query, String.class);
            try{
                return IOUtils.toByteArray(new FileInputStream(resume));
            } catch(IOException e) {
                return null;
            }
        } catch(IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    public static void main(String ...a) {
        CommonServices cs = new CommonServices();
        DriverManagerDataSource ds = new DriverManagerDataSource("org.postgresql.Driver", "jdbc:postgresql://10.57.4.68:5432/ptech", "erp", "db4dev@68");
        cs.setDs(ds);
        System.out.println(PratyashaUtil.JSONScript(cs.getNewsAndEvents()));
    }

}