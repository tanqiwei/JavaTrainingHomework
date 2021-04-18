package com.study.qw.jdbcmysqlstudydemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author tqw
 */

@Component
@Slf4j
public class MysqlConfig {
    @Autowired
    private DataSourceProperties dataSourceProperties;


    public Connection createConnection() {
        Connection con;
        try {
            con = DriverManager.getConnection(
                    dataSourceProperties.getUrl(),
                    dataSourceProperties.getUsername(),
                    dataSourceProperties.getPassword());
        } catch (SQLException e) {
            log.error("error to init connection", e);
            throw new RuntimeException("get connection is error.please check");
        }
        return con;
    }

}
