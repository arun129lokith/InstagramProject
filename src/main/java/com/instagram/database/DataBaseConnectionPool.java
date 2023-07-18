package com.instagram.database;

import com.instagram.customexception.FileAccessException;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * <p>
 * Connects with database to store user information
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class  DataBaseConnectionPool {

    private final String sqlUrl;
    private final String userName;
    private final String password;
    private final Integer maxPoolSize;
    private static BlockingQueue<Connection> pool;
    private static DataBaseConnectionPool connectionPool;

    private DataBaseConnectionPool() {
        maxPoolSize = 10;
        pool = new ArrayBlockingQueue<>(maxPoolSize);
        final Properties properties = new Properties();

        try {
            final FileInputStream file = new FileInputStream(String.join("","C:\\Users\\arunl\\",
                    "InstagramMaven\\DataBaseResource\\db.properties"));

            properties.load(file);
        } catch (IOException message) {
            throw new FileAccessException(message.getMessage());
        }
        sqlUrl = properties.getProperty("db_url");
        userName = properties.getProperty("db_name");
        password = properties.getProperty("db_password");

        initializePool();
    }

    /**
     * <p>
     * Gets the object of the connection pool class
     * </p>
     *
     * @return The database connection object
     */
    public static DataBaseConnectionPool getInstance() {
            return null == connectionPool ? connectionPool = new DataBaseConnectionPool() : connectionPool;
    }

    /**
     * <p>
     * Gets the maximum collection of connection at a time
     * </p>
     */
    private void initializePool() {
        try {

            for (int i = 0; i < maxPoolSize; i++) {
                final Connection connection = getConnection();

                if (null != connection) {
                    pool.add(connection);
                }
            }
        } catch (SQLException message) {
            throw new FileAccessException(message.getMessage());
        }
    }

    /**
     * <p>
     *  Gets the connection with database
     * </p>
     *
     * @return The connection of database
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(sqlUrl, userName, password);
    }

    /**
     * <p>
     * Gets the connection object from the collection
     * </p>
     *
     * @return The connection object of database
     * @throws InterruptedException
     */
    public Connection get() throws InterruptedException {
        return pool.take();
    }

    /**
     * <p>
     * Releases the connection to the collection
     * </p>
     *
     * @param connection Represents connection object
     */
    public void releaseConnection(final Connection connection) {
        if (null != connection) {
            pool.add(connection);
        }
    }

    /**
     * <p>
     * Closes the connection with database
     * </p>
     *
     * @throws SQLException
     */
    public void closeConnectionPool() {
        for (final Connection connection : pool) {

            try {
                connection.close();
            } catch (SQLException message) {
                System.out.println(message.getMessage());
            }
        }
    }
}
