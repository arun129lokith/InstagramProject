package com.instagram.dao.impl;

import com.instagram.customexception.DataAccessException;
import com.instagram.dao.PostDao;
import com.instagram.database.DataSourceConnection;
import com.instagram.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Implements the data base service of the post related operation
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class PostDaoImpl implements PostDao {

    private static PostDaoImpl postDaoImpl;
    private final DataSourceConnection connectionPool;
    private Connection connection = null;

    private PostDaoImpl() {
        connectionPool = DataSourceConnection.getInstance();
    }

    /**
     * <p>
     * Gets the object of the class
     * </p>
     *
     * @return The post database service implementation object
     */
    public static PostDaoImpl getInstance() {
        return null == postDaoImpl ? postDaoImpl = new PostDaoImpl() : postDaoImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param post Represents {@link Post} details of the user
     * @return True if post is created, false otherwise
     */
    @Override
    public boolean create(final Post post) {
        final String query = String.join("","INSERT INTO POST(CAPTION, LOCATION, FORMAT, ",
                "UPLOADED_TIME, USER_ID) VALUES (?, ? , ?::POST_FORMAT, ?, ?)");

        try {
            connection = connectionPool.getDataSource().getConnection();

            connection.setAutoCommit(false);

            try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, post.getCaption());
                preparedStatement.setString(2, post.getLocation());
                preparedStatement.setString(3, post.getFormat().toString());
                preparedStatement.setTimestamp(4, post.getUploadedTime());
                preparedStatement.setLong(5, post.getUserId());

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
     * @param id Represents post id
     * @return True if post is removed, false otherwise
     */
    @Override
    public boolean delete(final Long id) {
        final String query = "DELETE FROM POST WHERE ID = ?";

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
     * @param id Represents post id
     * @return Represents {@link Post} details
     */
    @Override
    public Post getPost(final Long id) {
        final String query = "SELECT * FROM POST WHERE ID = ?";

        try {
            connection = connectionPool.getDataSource().getConnection();

            connection.setAutoCommit(false);

            try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setLong(1, id);
                final ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    final Post post = new Post();

                    post.setId(resultSet.getLong("ID"));
                    post.setCaption(resultSet.getString("CAPTION"));
                    post.setLocation(resultSet.getString("LOCATION"));
                    post.setFormat(Post.Format.valueOf(resultSet.getString("FORMAT")));
                    post.setUploadedTime(resultSet.getTimestamp("UPLOADED_TIME"));
                    post.setUserId(resultSet.getLong("USER_ID"));

                    connection.commit();

                    return post;
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

    /**
     * {@inheritDoc}
     *
     * @param updatedPost Represents {@link Post} update details
     * @return True if post is updated, false otherwise
     */
    @Override
    public boolean update(final Post updatedPost) {
        final String query = String.join("","UPDATE POST SET CAPTION = ?, LOCATION = ?, ",
                "FORMAT = ?::POST_FORMAT, UPLOADED_TIME = ?  WHERE ID = ?");

        try {
            connection = connectionPool.getDataSource().getConnection();

            connection.setAutoCommit(false);

            try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, updatedPost.getCaption());
                preparedStatement.setString(2, updatedPost.getLocation());
                preparedStatement.setString(3, updatedPost.getFormat().toString());
                preparedStatement.setTimestamp(4, updatedPost.getUploadedTime());
                preparedStatement.setLong(5, updatedPost.getId());
                final int rowUpdated = preparedStatement.executeUpdate();

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
}
