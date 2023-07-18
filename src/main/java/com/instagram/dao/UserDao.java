package com.instagram.dao;

import com.instagram.model.User;

import java.util.Collection;

/**
 * <p>
 * Provides data base service for the user
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public interface UserDao {

    /**
     * <p>
     * Gets the user details of the user
     * </p>
     *
     * @param id Represents user id
     * @return The user details
     */
    User getUser(final Long id);

    /**
     * <p>
     * Collects the all user information
     * </p>
     *
     * @return The collection of user details
     */
    Collection<User> getAllUsers();

    /**
     * <p>
     * Updates the user details
     * </p>
     *
     * @param user Represents {@link User} details
     * @return True if user details is update, false otherwise
     */
    boolean update(final User user);

    /**
     * <p>
     * Deletes the user account details
     * </p>
     *
     * @param id Represents user id
     * @return True if account is deleted, false otherwise
     */
    boolean delete(final Long id);

    /**
     * <p>
     * Gets the user id of the user
     * </p>
     *
     * @param user Represents {@link User} detail
     * @return The user id of the user
     */
    Long getId(final User user);

    /**
     * <p>
     * Checks the username is exists
     * </p>
     *
     * @param name Represents user name
     * @return True if username is exists, false otherwise
     */
    boolean isNameExist(final String name);

    /**
     * <p>
     * Checks the email is exists
     * </p>
     *
     * @param email Represents user email
     * @return True if email is exists, false otherwise
     */
    boolean isEmailExist(final String email);

    /**
     * <p>
     * Checks the mobile number is exists
     * </p>
     *
     * @param mobileNumber Represents user mobile number
     * @return True if mobile number is exists, false otherwise
     */
    boolean isMobileNumberExist(final String mobileNumber);
}
