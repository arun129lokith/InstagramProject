package com.instagram.service.impl;

import com.instagram.model.User;
import com.instagram.service.UserService;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * Implements the service of the user related operation
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class UserServiceImpl extends AuthenticationServiceImpl implements UserService {

    private static UserServiceImpl userServiceImpl = null;

    private UserServiceImpl() {
        super();
    }

    /**
     * <p>
     * Gets the object of the class
     * </p>
     *
     * @return The user service implementation object
     */
    public static UserServiceImpl getInstance() {
        return null == userServiceImpl ? userServiceImpl = new UserServiceImpl() : userServiceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents user id
     * @return The user details
     */
    @Override
    public User getUser(final Long id) {
        return USERS.containsKey(id) ? USERS.get(id) : null;
    }

    /**
     * {@inheritDoc}
     *
     * @return The collection of user details
     */
    @Override
    public Collection<User> getAllUsers() {
        return USERS.values();
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details
     * @return True if update is successful, false otherwise
     */
    @Override
    public boolean update(final User user) {
        USERS.put(user.getId(), user);
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents user id
     * @return True if account is deleted, false otherwise
     */
    @Override
    public boolean delete(final Long id) {
        if (USERS.containsKey(id)) {
            USERS.remove(id);

            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} detail
     * @return Users id
     */
    @Override
    public Long getId(final User user) {
        for (final Map.Entry<Long, User> entry : USERS.entrySet()) {
            final User existingUser = entry.getValue();

            if (existingUser.getEmail().equals(user.getEmail())
                    || existingUser.getMobileNumber().equals(user.getMobileNumber())) {
                return entry.getKey();
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param name Represents user name
     * @return True if name is exists, false otherwise
     */
    @Override
    public boolean isNameExist(final String name) {
        for (final User existingUser : USERS.values()) {

            return existingUser.getName().equals(name);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param email Represents user email
     * @return True if email is exists, false otherwise
     */
    @Override
    public boolean isEmailExist(final String email) {
        for (final User existingUser : USERS.values()) {

            return existingUser.getEmail().equals(email);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param mobileNumber Represents user mobile number
     * @return True if mobile number is exists, false otherwise
     */
    @Override
    public boolean isMobileNumberExist(final String mobileNumber) {
        for (final User existingUser : USERS.values()) {

            return existingUser.getMobileNumber().equals(mobileNumber);
        }

        return false;
    }
}

