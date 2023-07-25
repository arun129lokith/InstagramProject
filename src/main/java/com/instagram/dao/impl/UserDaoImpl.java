package com.instagram.dao.impl;

import com.instagram.customexception.DataAccessException;
import com.instagram.dao.UserDao;
import com.instagram.database.DataSourceConnection;
import com.instagram.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Implements the data base service of the user related operation
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class UserDaoImpl implements UserDao {

    private static UserDaoImpl userDaoImpl;
    private final DataSourceConnection connectionPool;
    private Connection connection = null;

    private UserDaoImpl() {
        connectionPool = DataSourceConnection.getInstance();
    }

    /**
     * <p>
     * Gets the object of the class
     * </p>
     *
     * @return The user database service implementation object
     */
    public static UserDaoImpl getInstance() {
        return null == userDaoImpl ? userDaoImpl = new UserDaoImpl() : userDaoImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details
     * @return True if sign-up is successful, false otherwise
     */
    @Override
    public boolean signUp(final User user) {
        final String query = "INSERT INTO USERS (NAME, MOBILE_NUMBER, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)";

        try {
            connection = connectionPool.getDataSource().getConnection();

            connection.setAutoCommit(false);

            try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getMobileNumber());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getPassword());

                preparedStatement.executeUpdate();
                connection.commit();

                return true;
            } catch (SQLException message) {
                connection.rollback();
            }
        } catch (SQLException message) {
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
    public boolean isMobileNumberExist(User user) {
        final String query = "SELECT * FROM USERS WHERE MOBILE_NUMBER = ? AND PASSWORD = ?";

        try {
            connection = connectionPool.getDataSource().getConnection();

            connection.setAutoCommit(false);

            try (final PreparedStatement checkStatement = connection.prepareStatement(query)) {

                checkStatement.setString(1, user.getMobileNumber());
                checkStatement.setString(2, user.getPassword());
                final ResultSet resultSet = checkStatement.executeQuery();

                connection.commit();

                return resultSet.next();
            } catch (SQLException message) {
                connection.rollback();
            }
        } catch (SQLException message) {
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
    public boolean isEmailExist(User user) {
        final String query = "SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";

        try {
            connection = connectionPool.getDataSource().getConnection();

            connection.setAutoCommit(false);

            try (final PreparedStatement checkStatement = connection.prepareStatement(query)) {

                checkStatement.setString(1, user.getEmail());
                checkStatement.setString(2, user.getPassword());
                final ResultSet resultSet = checkStatement.executeQuery();

                connection.commit();

                return resultSet.next();
            } catch (SQLException message) {
                connection.rollback();
            }
        } catch (SQLException message) {
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
     * @param name Represents user name
     * @return True if name is exists, false otherwise
     */
    @Override
    public boolean isNameExist(final String name) {
        final String query = "SELECT * FROM USERS WHERE NAME = ?";

        try {
            connection = connectionPool.getDataSource().getConnection();

            connection.setAutoCommit(false);

            try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, name);
                final ResultSet resultSet = preparedStatement.executeQuery();

                connection.commit();

                return resultSet.next();
            } catch (SQLException message) {
                connection.rollback();
            }
        } catch (SQLException message) {
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

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param email Represents user email
     * @return True if email is exists, false otherwise
     */
    @Override
    public boolean isEmailExist(final String email) {
        final String query = "SELECT * FROM USERS WHERE EMAIL = ?";

        try {
            connection = connectionPool.getDataSource().getConnection();

            connection.setAutoCommit(false);

            try (final PreparedStatement checkStatement = connection.prepareStatement(query)) {

                checkStatement.setString(1, email);
                final ResultSet resultSet = checkStatement.executeQuery();

                connection.commit();

                return resultSet.next();
            } catch (SQLException message) {
                connection.rollback();
            }
        } catch (SQLException message) {
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

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param mobileNumber Represents user mobile number
     * @return True if mobile number is exists, false otherwise
     */
    @Override
    public boolean isMobileNumberExist(final String mobileNumber) {
        final String query = "SELECT * FROM USERS WHERE MOBILE_NUMBER = ?";

        try {
            connection = connectionPool.getDataSource().getConnection();

            connection.setAutoCommit(false);

            try (final PreparedStatement checkStatement = connection.prepareStatement(query)) {

                checkStatement.setString(1, mobileNumber);
                final ResultSet resultSet = checkStatement.executeQuery();

                connection.commit();

                return resultSet.next();
            } catch (SQLException message) {
                connection.rollback();
            }
        } catch (SQLException message) {
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

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details
     * @return True if update is successful, false otherwise
     */
    @Override
    public boolean update(final User user) {
        final String query = "UPDATE USERS SET NAME = ?, MOBILE_NUMBER = ?, EMAIL = ?, PASSWORD = ? WHERE ID = ?";

        try {
            connection = connectionPool.getDataSource().getConnection();

            connection.setAutoCommit(false);

            try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getMobileNumber());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setLong(5, user.getId());
                int rowUpdated = preparedStatement.executeUpdate();

                connection.commit();

                if (0 < rowUpdated) {
                    return true;
                }
            } catch (SQLException message) {
                connection.rollback();
            }
        } catch (SQLException message) {
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
     * @param id Represents user id
     * @return True if account is deleted, false otherwise
     */
    @Override
    public boolean delete(final Long id) {
        final String query = "DELETE FROM USERS WHERE ID = ?";

        try {
            connection = connectionPool.getDataSource().getConnection();

            connection.setAutoCommit(false);

            try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setLong(1, id);
                int rowDeleted = preparedStatement.executeUpdate();

                connection.commit();

                if (0 < rowDeleted) {
                    return true;
                }
            } catch (SQLException message) {
                connection.rollback();
            }
        } catch (SQLException message) {
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
     * @param id Represents user id
     * @return The user details
     */
    @Override
    public User getUser(final Long id) {
        final String query = "SELECT * FROM USERS WHERE ID = ?";

        try {
            connection = connectionPool.getDataSource().getConnection();

            connection.setAutoCommit(false);

            try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                final ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    final User user = new User();

                    user.setId(resultSet.getLong("ID"));
                    user.setName(resultSet.getString("NAME"));
                    user.setEmail(resultSet.getString("EMAIL"));
                    user.setMobileNumber(resultSet.getString("MOBILE_NUMBER"));
                    user.setPassword(resultSet.getString("PASSWORD"));

                    connection.commit();

                    return user;
                }
            } catch (SQLException message) {
                connection.rollback();
            }
        } catch (SQLException message) {
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

        return null;
    }
}
