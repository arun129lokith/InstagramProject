package com.instagram.database;

import com.instagram.customexception.FileAccessException;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataSourceConnection {

    private final BasicDataSource dataSource;
    private static DataSourceConnection dataSourceConnection;

    private DataSourceConnection() {
        dataSource = new BasicDataSource();
        final Properties properties = new Properties();

        try {
            final FileInputStream file = new FileInputStream(String.join("", "C:\\Users\\arunl\\InstagramWebApplication\\src\\main\\resources\\DataBase\\db.properties"));

            properties.load(file);
        } catch (IOException message) {
            throw new FileAccessException(message.getMessage());
        }

        dataSource.setDriverClassName(properties.getProperty("db_driver"));
        dataSource.setUrl(properties.getProperty("db_url"));
        dataSource.setUsername(properties.getProperty("db_name"));
        dataSource.setPassword(properties.getProperty("db_password"));
        dataSource.setMinIdle(20);
        dataSource.setMaxIdle(100);
    }

    public static DataSourceConnection getInstance() {
        return null == dataSourceConnection ? dataSourceConnection = new DataSourceConnection() : dataSourceConnection;
    }

    public DataSource getDataSource() {

        return dataSource;
    }
}
