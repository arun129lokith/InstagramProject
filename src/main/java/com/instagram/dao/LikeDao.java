package com.instagram.dao;

import com.instagram.model.Like;

/**
 * <p>
 * Provides like data base service for the post
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public interface LikeDao {

    /**
     * <p>
     * Creates the like for the post
     * </p>
     *
     * @param like Represents {@link Like} details
     */
    void likePost(final Like like);

    /**
     * <p>
     * Removes the like for the post provided by the user
     * </p>
     *
     * @param id Represents like id
     * @return True if like is removed, false otherwise
     */
    boolean unlikePost(final Long id);
}
