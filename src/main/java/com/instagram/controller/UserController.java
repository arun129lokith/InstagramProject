package com.instagram.controller;

import com.instagram.model.User;
import com.instagram.service.UserService;
import com.instagram.service.impl.UserServiceImpl;

/**
 * <p>
 * Handles the user related operation and responsible for receiving user input and processing it
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class UserController {

    private final UserService userService;
    private static UserController userController;

    private UserController() {
        userService =  UserServiceImpl.getInstance();
    }

    /**
     * <p>
     * Gets the object of the user controller class
     * </p>
     *
     * @return The user controller object
     */
    public static UserController getInstance() {
        return null == userController ? userController = new UserController() : userController;
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
        return userService.signUp(user);
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
        return userService.signIn(user);
    }

    /**
     * <p>
     * Checks the username is exists
     * </p>
     *
     * @param name Represents user name
     * @return True if username is exists, false otherwise
     */
    public boolean isNameExist(final String name) {
        return userService.isNameExist(name);
    }

    /**
     * <p>
     * Checks the email is exists
     * </p>
     *
     * @param email Represents user email
     * @return True if email is exists, false otherwise
     */
    public boolean isEmailExist(final String email) {
        return userService.isEmailExist(email);
    }

    /**
     * <p>
     * Checks the mobile number is exists
     * </p>
     *
     * @param mobileNumber Represents user mobile number
     * @return True if mobile number is exists, false otherwise
     */
    public boolean isMobileNumberExist(final String mobileNumber) {
        return userService.isMobileNumberExist(mobileNumber);
    }

    /**
     * <p>
     * Updates the user details
     * </p>
     *
     * @param user Represent {@link User} details
     * @return True if user details is update, false otherwise
     */
    public boolean update(final User user) {
        return userService.update(user);
    }

    /**
     * <p>
     * Deletes the user account details
     * </p>
     *
     * @param id Represents user id
     * @return True if account is deleted, false otherwise
     */
    public boolean delete(final Long id) {
        return userService.delete(id);
    }

    /**
     * <p>
     * Gets the user details of the user
     * </p>
     *
     * @param id Represents user id
     * @return The user details
     */
    public User getUser(final Long id) {
        return userService.getUser(id);
    }
}
