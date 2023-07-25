package com.instagram.service;

import com.instagram.model.User;

/**
 * <p>
 * Provides the services for the user
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public interface UserService {

    /**
     * <p>
     * Signs up a new user with user details of user class
     * </p>
     *
     * @param user Represents {@link User} details
     * @return True if sign-up is successful, false otherwise
     */
    boolean signUp(final User user);

    /**
     * <p>
     * Signs in a new user with user details of user class
     * </p>
     *
     * @param user Represents {@link User} details
     * @return True if sign-in is successful, false otherwise
     */
    boolean signIn(final User user);

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
     * Gets the user details of the user
     * </p>
     *
     * @param id Represents user id
     * @return The user details
     */
    User getUser(final Long id);
}

