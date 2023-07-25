package com.instagram.controller;

import com.instagram.model.User;
import com.instagram.service.impl.LikeServiceImpl;
import com.instagram.model.Like;
import com.instagram.service.LikeService;

import java.util.Collection;

/**
 * <p>
 * Handles the like related operation of the post and responsible for receiving user input and processing it
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class LikeController {

    private static LikeController likeController;
    private final LikeService likeService;

    private LikeController() {
        likeService = LikeServiceImpl.getInstance();
    }

    /**
     * <p>
     * Gets the object of the class
     * </p>
     *
     * @return The like controller object
     */
    public static LikeController getInstance() {
        return null == likeController ? likeController = new LikeController() : likeController;
    }

    /**
     * <p>
     * Creates the like for the post
     * </p>
     *
     * @param like Represents {@link Like} details
     */
    public void likePost(final Like like) {
        likeService.likePost(like);
    }

    /**
     * <p>
     * Removes the like for the post provided by the user
     * </p>
     *
     * @param id Represents like id
     * @return True if like is removed, false otherwise
     */
    public boolean unlikePost(final Long id) {
        return likeService.unlikePost(id);
    }

    /**
     * <p>
     * Gets the collection of user who react for the post
     * </p>
     *
     * @param postId Represents post id
     * @return The collection of user
     */
    public Collection<User> getLikeUser(final Long postId) {
        return likeService.getLikeUser(postId);
    }
}
