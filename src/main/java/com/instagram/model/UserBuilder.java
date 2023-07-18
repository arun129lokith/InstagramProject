package com.instagram.model;

/**
 * <p>
 * Builds the user object for the application
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class UserBuilder {

    private final User user;
    private static UserBuilder userBuilder;

    private UserBuilder() {
        this.user = new User();
    }

    /**
     * <p>
     * Gets the object of the user builder class
     * </p>
     *
     * @return The user builder object
     */
    public static UserBuilder getInstance() {
        return null == userBuilder ? userBuilder = new UserBuilder() : userBuilder;
    }

    /**
     * <p>
     * Builds the user with id
     * </p>
     *
     * @param id Represents id of the user
     */
    public void withId(final Long id) {
        user.setId(id);
    }

    /**
     * <p>
     * Builds the user with mobile number
     * </p>
     *
     * @param mobileNumber Represents mobile number of the user
     */
    public void withMobileNumber(final String mobileNumber) {
        user.setMobileNumber(mobileNumber);
    }

    /**
     * <p>
     * Builds the user with email address
     * </p>
     *
     * @param email Represents email id of the user
     */
    public void withEmail(final String email) {
        user.setEmail(email);
    }

    /**
     * <p>
     * Builds the user with password
     * </p>
     *
     * @param password Represents password of the user
     */
    public void withPassword(final String password) {
        user.setPassword(password);
    }

    /**
     * <p>
     * Builds the user with name
     * </p
     *
     * @param name Represents user name
     */
    public void withName(final String name) {
        user.setName(name);
    }

    /**
     * <p>
     * Builds the user object completely
     * </p>
     *
     * @return The user object
     */
    public User build() {
        return this.user;
    }
}
