package com.instagram.service.impl;

import com.instagram.dao.PostDao;
import com.instagram.dao.impl.PostDaoImpl;
import com.instagram.model.Post;
import com.instagram.service.PostService;

/**
 * <p>
 * Implements the service of the post related operation
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class PostServiceImpl implements PostService {

    private final PostDao postDao;
    private static PostServiceImpl postServiceImpl;

    private PostServiceImpl() {
        postDao = PostDaoImpl.getInstance();
    }

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
        return postDao.create(post);
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents post id
     * @return True if post is removed, false otherwise
     */
    @Override
    public boolean delete(final Long id) {
        return postDao.delete(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents post id
     * @return Represents {@link Post} details
     */
    @Override
    public Post getPost(final Long id) {
        return postDao.getPost(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param updatedPost Represents {@link Post} update details
     * @return True if post is updated, false otherwise
     */
    @Override
    public boolean update(final Post updatedPost) {
        return postDao.update(updatedPost);
    }
}
