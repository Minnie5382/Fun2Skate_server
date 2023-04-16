package com.example.demo.src.email;


import com.example.demo.src.email.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EmailDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){ this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public String retrieveEmailAddress(int InstrIdx) {
        String retrieveEmailAddressQuery = "select email from Instructor where instrIdx=?;";
        int retrieveEmailAddressParams = InstrIdx;
        return this.jdbcTemplate.queryForObject(retrieveEmailAddressQuery, String.class, retrieveEmailAddressParams);
    }



}
