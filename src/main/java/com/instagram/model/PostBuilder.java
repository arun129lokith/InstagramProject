package com.instagram.model;

import java.sql.Timestamp;

import com.instagram.model.Post.Format;

/**
 * <p>
 * Builds the post object of the user
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class PostBuilder {

    private final Post post;
    private static PostBuilder postBuilder;

    private PostBuilder() {
        this.post = new Post();
    }

    /**
     * <p>
     * Gets the object of the post builder class
     * </p>
     *
     * @return The post builder object
     */
    public static PostBuilder getInstance() {
        return null == postBuilder ? postBuilder = new PostBuilder() : postBuilder;
    }

    /**
     * <p>
     * Builds the post with id
     * </p>
     *
     * @param id Represents post id
     */
    public void withId(final Long id) {
        post.setId(id);
    }

    /**
     * <p>
     * Builds the post with userId
     * </p>
     *
     * @param userId Represents user id
     */
    public void withUserId(final Long userId) {
        post.setUserId(userId);
    }

    /**
     * <p>
     * Builds the post with location
     * </p>
     *
     * @param location Represents post location
     */
    public void withLocation(final String location) {
        post.setLocation(location);
    }

    /**
     * <p>
     * Builds the post with caption
     * </p>
     *
     * @param caption Represents post caption
     */
    public void withCaption(final String caption) {
        post.setCaption(caption);
    }

    /**
     * <p>
     * Builds the post with uploaded time
     * </p>
     *
     * @param uploadedTime Represents post uploaded time
     */
    public void withUploadedTime(final Timestamp uploadedTime) {
        post.setUploadedTime(uploadedTime);
    }

    /**
     * <p>
     * Builds the post with format
     * </p>
     *
     * @param format Represents post format
     */
    public void withFormat(final Format format) {
        post.setFormat(format);
    }

    /**
     * <p>
     * Builds the post object
     * </p>
     *
     * @return The post object
     */
    public Post build() {
        return this.post;
    }
}
