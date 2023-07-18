package com.instagram.service.impl;

import com.instagram.model.Post;
import com.instagram.model.User;
import com.instagram.service.PostService;
import com.instagram.view.UserView;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * Implements the service of the post related operation
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class PostServiceImpl implements PostService {

    private final List<Post> POSTS = new ArrayList<>();
    private static PostServiceImpl postServiceImpl = null;

    private PostServiceImpl() {}

    /**
     * <p>
     * Gets the object of the class
     * </p>
     *
     * @return The post service implementation object
     */
    public static PostServiceImpl getInstance() {
        return null == postServiceImpl ? postServiceImpl = new PostServiceImpl() : postServiceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param post Represents {@link Post} details of the user
     * @return True if post is created, false otherwise
     */
    @Override
    public boolean create(final Post post) {
        final UserView userView = UserView.getInstance();
        final User user = userView.getUserById(post.getUserId());
        final List<Post> posts = user.getPosts();

        posts.add(post);
        user.setPosts(posts);

        return this.POSTS.add(post);
    }

    /**
     * {@inheritDoc}
     *
     * @return The collection of post
     */
    @Override
    public Collection<Post> getAllPost() {
        return POSTS;
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents post id
     * @return Represents {@link Post} details
     */
    @Override
    public Post getPost(final Long id) {
        for (final Post post : POSTS) {

            if (post.getId().equals(id)) {
                return post;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents post id
     * @return True if post is removed, false otherwise
     */
    @Override
    public boolean delete(final Long id) {
        final Post post = getPost(id);

        if (POSTS.contains(post)) {
            final UserView userView = UserView.getInstance();
            final User user = userView.getUserById(post.getUserId());
            final List<Post> posts = user.getPosts();

            posts.remove(post);

            return this.POSTS.remove(post);
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param updatedPost Represents {@link Post} update details
     * @return True if post is updated, false otherwise
     */
    @Override
    public boolean update(final Post updatedPost) {
        for (int index = 0; index < POSTS.size(); index++) {

            if (POSTS.get(index).getId().equals(updatedPost.getId())) {
                final UserView userView = UserView.getInstance();
                final User user = userView.getUserById(updatedPost.getUserId());
                final List<Post> posts = user.getPosts();

                posts.set(index, updatedPost);
                this.POSTS.set(index, updatedPost);

                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param userId Represents id of the user
     * @param id Represents post id
     * @return The post details of the user
     */
    @Override
    public Post getPost(final Long id, final Long userId) {
        for (final Post existingPost : POSTS) {

            if (id.equals(existingPost.getId()) && userId.equals(existingPost.getUserId())) {
                return existingPost;
            }
        }

        return null;
    }
}