package com.instagram.model;

/**
 * <p>
 * Represents the like entity of the post with various properties and methods
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class Like {

    private Long id;
    private Long userId;
    private Long postId;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(final Long postId) {
        this.postId = postId;
    }

    public String toString() {
        return String.format("Id = %d\nUserId = %d\nPostId = %d\n", id, userId, postId);
    }
}
