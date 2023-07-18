package com.instagram.service.impl;

import com.instagram.model.Like;
import com.instagram.model.User;
import com.instagram.service.LikeService;
import com.instagram.view.UserView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * Implements the service of the like related operation
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class LikeServiceImpl implements LikeService {

    private final List<Like> LIKES = new ArrayList<>();;
    private static LikeServiceImpl likeServiceImpl;

    private LikeServiceImpl() {}

    /**
     * <p>
     * Gets the object of the class
     * </p>
     *
     * @return The like service implementation object
     */
    private static LikeServiceImpl getInstance() {
        return null == likeServiceImpl ? likeServiceImpl = new LikeServiceImpl() : likeServiceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param like Represents {@link Like} details
     */
    @Override
    public void likePost(final Like like) {
        LIKES.add(like);
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents like id
     * @return True if like is removed, false otherwise
     */
    @Override
    public boolean unlikePost(final Long id) {
        for (final Like existingLike : LIKES) {

            if (id.equals(existingLike.getId())) {
                return LIKES.remove(existingLike);
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param postId Represents post id
     * @return The collection of user
     */
    @Override
    public Collection<User> getLikeUser(final Long postId) {
        final Collection<User> users = new ArrayList<>();

        for (final Like existingLike : LIKES) {

            if (postId.equals(existingLike.getPostId())) {
                final UserView userView = UserView.getInstance();
                final User user = userView.getUserById(existingLike.getUserId());

                users.add(user);
            }
        }

        return users;
    }

    /**
     * {@inheritDoc}
     *
     * @param postId Represents post id
     * @return The count of the like
     */
    @Override
    public Long getLikeCount(final Long postId) {
        Long count = 0L;

        for (final Like existingLike : LIKES) {

            if (postId.equals(existingLike.getPostId())) {
                count++;
            }
        }

        return count;
    }
}
