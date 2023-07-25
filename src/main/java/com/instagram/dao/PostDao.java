package com.instagram.dao;

import com.instagram.model.Post;

/**
 * <p>
 * Provides post data base service for the user
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public interface PostDao {

    /**
     * <p>
     * Creates the user post
     * </p>
     *
     * @param post Represents {@link Post} details of the user
     */
    boolean create(final Post post);

    /**
     * <p>
     * Deletes the user post
     * </p>
     *
     * @param id Represents post id
     * @return True if post is deleted, false otherwise
     */
    boolean delete(final Long id);

    /**
     * <p>
     * Gets the post detail of the user
     * </p>
     *
     * @param id Represents post id
     * @return The post details of the user
     */
    Post getPost(final Long id);

    /**
     * <p>
     * Updates the user post details
     * </p>
     *
     * @param updatedPost Represents {@link Post} update details
     * @return True if post is updated, false otherwise
     */
    boolean update(final Post updatedPost);
}
