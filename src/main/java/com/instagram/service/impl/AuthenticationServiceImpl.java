package com.instagram.service.impl;

import com.instagram.model.User;
import com.instagram.service.AuthenticationService;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Implements the service of the authentication related operation
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    protected  final Map<Long, User> USERS = new HashMap<>();
    private static AuthenticationServiceImpl authenticationServiceImpl;

    protected AuthenticationServiceImpl() {}

    /**
     * <p>
     * Gets the object of the authentication service implementation class
     * </p>
     *
     * @return The authentication service implementation object
     */
    public static AuthenticationServiceImpl getInstance() {
        return null == authenticationServiceImpl ? authenticationServiceImpl = new AuthenticationServiceImpl()
                : authenticationServiceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details
     * @return True if sign-up is successful, false otherwise
     */
    @Override
    public boolean signUp(final User user) {
        USERS.put(user.getId(), user);

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User} details
     * @return True if sign-in is successful, false otherwise
     */
    @Override
    public boolean signIn(final User user) {
        return null != user.getMobileNumber() ? isMobileNumberExist(user) : isEmailExist(user);
    }

    /**
     * Checks the mobile number and password is exists
     *
     * @param user Represents {@link User} details
     * @return True if mobile number is exists, false otherwise
     */
    private boolean isMobileNumberExist(final User user) {
        for (final User existingUser : USERS.values()) {

            if (user.getMobileNumber().equals(existingUser.getMobileNumber())
                    && user.getPassword().equals(existingUser.getPassword())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks the email and password is exists
     *
     * @param user Represents {@link User} details
     * @return True if email is exists, false otherwise
     */
    private boolean isEmailExist(final User user) {
        for (final User existingUser : USERS.values()) {

            if (existingUser.getEmail().equals(user.getEmail())
                    && existingUser.getPassword().equals(user.getPassword())) {
                return true;
            }
        }

        return false;
    }
}
