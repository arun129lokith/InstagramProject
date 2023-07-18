package com.instagram.controller;

import com.instagram.model.User;
import com.instagram.service.AuthenticationService;
import com.instagram.service.impl2.AuthenticationServiceImpl;

/**
 * <p>
 * Handles the authentication related operation and responsible for receiving user input and processing it
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private static AuthenticationController authenticationController;

    private AuthenticationController() {
        authenticationService = AuthenticationServiceImpl.getInstance();
    }

    /**
     * <p>
     * Gets the object of the authentication controller class
     * </p>
     *
     * @return The authentication controller object
     */
    public static AuthenticationController getInstance() {
        return null == authenticationController ? authenticationController = new AuthenticationController()
                : authenticationController;
    }

    /**
     * <p>
     * Signs up a new user with user details of authentication class
     * </p>
     *
     * @param user Represents {@link User} details
     * @return True if sign-up is successful, false otherwise
     */
    public boolean signUp(final User user) {
        return authenticationService.signUp(user);
    }

    /**
     * <p>
     * Signs in a new user with user details of authentication class
     * </p>
     *
     * @param user Represents {@link User} details
     * @return True if sign-in is successful, false otherwise
     */
    public boolean signIn(final User user) {
        return authenticationService.signIn(user);
    }
}
