package com.instagram.dao.impl;

import com.instagram.customexception.DataAccessException;
import com.instagram.dao.LikeDao;
import com.instagram.database.DataSourceConnection;
import com.instagram.model.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>
 * Implements the data base service of like related operation
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class LikeDaoImpl implements LikeDao {

    private static LikeDaoImpl likeDaoImpl;
    private final DataSourceConnection connectionPool;
    private Connection connection = null;

    private LikeDaoImpl() {
        connectionPool = DataSourceConnection.getInstance();
    }

    /**
     * <p>
     * Gets the object of the class
     * </p>
     *
     * @return The like database service implementation object
     */
    public static LikeDaoImpl getInstance() {
        return null == likeDaoImpl ? likeDaoImpl = new LikeDaoImpl() : likeDaoImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param like Represents {@link Like} details
     */
    @Override
    public void likePost(final Like like) {
        final String query = "INSERT INTO LIKES (POST_ID, USER_ID) VALUES (?, ?)";

        try {
            connection = connectionPool.getDataSource().getConnection();

            connection.setAutoCommit(false);

            try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, like.getPostId());
                preparedStatement.setLong(2, like.getUserId());

                preparedStatement.executeUpdate();
                connection.commit();
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
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents like id
     * @return True if like is removed, false otherwise
     */
    @Override
    public boolean unlikePost(final Long id) {
        final String query = "DELETE FROM LIKES WHERE ID = ?";

        try {
            connection = connectionPool.getDataSource().getConnection();

            connection.setAutoCommit(false);

            try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setLong(1, id);

                connection.commit();

                if (0 < preparedStatement.executeUpdate()) {
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
}
