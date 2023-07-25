package com.instagram.dao;

import com.instagram.model.User;

/**
 * <p>
 * Provides database authentication service for the user
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public interface AuthenticationDao {

    /**
     * <p>
     * Signs up a new user with user details of user class
     * </p>
     *
     * @param user Represents {@link User} details
     * @return True if sign-up is successful, false otherwise
     */
    boolean create(final User user);

    /**
     * <p>
     * Checks the mobile number is exists
     * </p>
     *
     * @param user Represents {@link User} details
     * @return True if mobile number is exists, false otherwise
     */
    boolean isMobileNumberExist(final User user);

    /**
     * <p>
     * Checks the email is exists
     * </p>
     *
     * @param user Represents {@link User} details
     * @return True if email is exists, false otherwise
     */
    boolean isEmailExist(final User user);
}
