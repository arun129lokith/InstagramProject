package com.instagram.dao.impl;

import com.instagram.customexception.DataAccessException;
import com.instagram.dao.AuthenticationDao;
import com.instagram.database.DataBaseConnectionPool;
import com.instagram.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Implements the database service for the authentication related operation
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class AuthenticationDaoImpl implements AuthenticationDao {

    private static AuthenticationDaoImpl authenticationDaoImpl;
    private final DataBaseConnectionPool connectionPool;
    private Connection connection = null;

    private AuthenticationDaoImpl() {
        connectionPool = DataBaseConnectionPool.getInstance();
    }

    /**
     * <p>
     * Gets the object of the class
     * </p>
     *
     * @return The authentication database service implementation object
     */
    public static AuthenticationDaoImpl getInstance() {
        return null == authenticationDaoImpl ? authenticationDaoImpl = new AuthenticationDaoImpl()
                : authenticationDaoImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details
     * @return True if sign-up is successful, false otherwise
     */
    @Override
    public boolean create(final User user) {
        final String query = "INSERT INTO USERS (NAME, MOBILE_NUMBER, EMAIL, PASSWORD) VALUES (?,?,?,?)";

        try {
            connection = connectionPool.get();

            connection.setAutoCommit(false);

            try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getMobileNumber());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getPassword());

                preparedStatement.executeUpdate();
                connection.commit();
                connectionPool.releaseConnection(connection);

                return true;
            } catch (SQLException message) {
                connection.rollback();
            }
        } catch (SQLException | InterruptedException message) {
            throw new DataAccessException(message.getMessage());
        } finally {
            if (null != connection) {

                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details
     * @return True if mobile number is exists, false otherwise
     */
    @Override
    public boolean isMobileNumberExist(final User user) {
        final String query = "SELECT * FROM USERS WHERE MOBILE_NUMBER = ? AND PASSWORD = ?";

        try {
            connection = connectionPool.get();

            connection.setAutoCommit(false);

            try (final PreparedStatement checkStatement = connection.prepareStatement(query)) {

                checkStatement.setString(1, user.getMobileNumber());
                checkStatement.setString(2, user.getPassword());
                final ResultSet resultSet = checkStatement.executeQuery();

                connection.commit();
                connectionPool.releaseConnection(connection);

                return resultSet.next();
            } catch (SQLException message) {
                connection.rollback();
            }
        } catch (SQLException | InterruptedException message) {
            throw new DataAccessException(message.getMessage());
        } finally {
            if (null != connection) {

                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details
     * @return True if email is exists, false otherwise
     */
    @Override
    public boolean isEmailExist(final User user) {
        final String query = "SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";

        try {
            connection = connectionPool.get();

            connection.setAutoCommit(false);

            try (final PreparedStatement checkStatement = connection.prepareStatement(query)) {

                checkStatement.setString(1, user.getEmail());
                checkStatement.setString(2, user.getPassword());
                final ResultSet resultSet = checkStatement.executeQuery();

                connection.commit();
                connectionPool.releaseConnection(connection);

                return resultSet.next();
            } catch (SQLException message) {
                connection.rollback();
            }
        } catch (SQLException | InterruptedException message) {
            throw new DataAccessException(message.getMessage());
        } finally {
            if (null != connection) {

                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }
}
