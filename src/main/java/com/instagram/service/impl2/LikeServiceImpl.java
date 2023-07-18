package com.instagram.service.impl2;

import com.instagram.dao.LikeDao;
import com.instagram.dao.impl.LikeDaoImpl;
import com.instagram.model.Like;
import com.instagram.model.User;
import com.instagram.service.LikeService;

import java.util.Collection;

/**
 * <p>
 * Implements the service of like related operation
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class LikeServiceImpl implements LikeService {

    private static LikeServiceImpl likeServiceImpl;
    private final LikeDao likeDao;

    private LikeServiceImpl() {
        likeDao = LikeDaoImpl.getInstance();
    }

    /**
     * <p>
     * Gets the object of the class
     * </p>
     *
     * @return The like service implementation object
     */
    public static LikeServiceImpl getInstance() {
        return null == likeServiceImpl ? likeServiceImpl = new LikeServiceImpl() : likeServiceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param like Represents {@link Like} details
     */
    @Override
    public void likePost(final Like like) {
        likeDao.likePost(like);
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents like id
     * @return True if like is removed, false otherwise
     */
    @Override
    public boolean unlikePost(final Long id) {
        return likeDao.unlikePost(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param postId Represents post id
     * @return The collection of user
     */
    @Override
    public Collection<User> getLikeUser(final Long postId) {
        return likeDao.getLikeUser(postId);
    }

    /**
     * {@inheritDoc}
     *
     * @param postId Represents post id
     * @return The count of the like
     */
    @Override
    public Long getLikeCount(final Long postId) {
        return likeDao.getLikeCount(postId);
    }
}
