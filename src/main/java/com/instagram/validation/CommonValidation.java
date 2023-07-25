package com.instagram.validation;

import com.instagram.controller.UserController;

/**
 * <P>
 * Handles the validation of the user details
 *
 * @author Arun
 * @version 1.0
 */
public class CommonValidation {

    private static CommonValidation validation = null;
    private static final String USER_NAME_PATTERN = "^(?!.*[._]{2})[A-Za-z][A-Za-z\\d_.]{0,28}[A-Za-z\\d]$";
    private static final String EMAIL_PATTERN = "^[a-z][a-z\\d._]+@[a-z]{5,}.[a-z]{2,3}$";
    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    private static final String MOBILE_NUMBER_PATTERN = "^[6-9]\\d{9}$";
    private static final String LOCATION_PATTERN = "^[a-zA-z]+$";
    private final UserController userController;

    private CommonValidation() {
        userController = UserController.getInstance();
    }

    /**
     * <p>
     * Gets the object of the class
     * </p>
     *
     * @return The validation object
     */
    public static CommonValidation getInstance() {
        return null == validation ? validation = new CommonValidation() : validation;
    }

    /**
     * <p>
     * Validates the username of the user
     * </p>
     *
     * @param userName The username of the user
     * @return True if username is valid, false otherwise
     */
    public boolean validateUserName(final String userName) {
        return userName.matches(USER_NAME_PATTERN);
    }

    /**
     * <p>
     * Validates the email of the user
     * </p>
     *
     * @param email The email of the user
     * @return True if email is valid, false otherwise
     */
    public boolean validateEmail(final String email) {
        return email.matches(EMAIL_PATTERN);
    }

    /**
     * <p>
     * Validates the password of the user
     * </p>
     *
     * @param password The password of the user
     * @return True if password is valid, false otherwise
     */
    public boolean validatePassword(final String password) {
        return password.matches(PASSWORD_PATTERN);
    }

    /**
     * <p>
     * Validates the mobile number of the user
     * </p>
     *
     * @param mobileNumber The mobile number of yhe user
     * @return True if mobile number is valid, false otherwise
     */
    public boolean validateMobileNumber(final String mobileNumber) {
        return mobileNumber.matches(MOBILE_NUMBER_PATTERN);
    }

    /**
     * <p>
     * Goes to main menu of the application
     * </p>
     *
     * @param userInput The input of the user details
     */
    public boolean backToMenu(final String userInput) {
        return userInput.contains("!");
    }

    /**
     * <p>
     * Validates the user for exit
     * </p>
     *
     * @param exitChoice The exit choice of the user
     * @return True if exit choice condition is satisfied, false otherwise
     */
    public boolean continueOrExit(final String exitChoice) {
        return "No".equalsIgnoreCase(exitChoice) || "N".equalsIgnoreCase(exitChoice);
    }

    /**
     * <p>
     * Validates the location of the post
     * </p>
     *
     * @param location Represents the location of the user
     * @return True if location is valid, false otherwise
     */
    public boolean isValidLocation(final String location) {
        return location.matches(LOCATION_PATTERN);
    }

    /**
     * <p>
     * Checks the user name is already exists or not in the application
     * </p>
     *
     * @param name Represents user name
     * @return True if username is exists, false otherwise
     */
    public boolean isNameExist(final String name) {
        return userController.isNameExist(name);
    }

    /**
     * <p>
     * Checks the email is already exists or not in the application
     * </p>
     *
     * @param email Represents user email
     * @return True if email is exist, false otherwise
     */
    public boolean isValidEmail(final String email) {
        return userController.isEmailExist(email);
    }

    /**
     * <p>
     * Checks the mobile number is not already exists in the application
     * </p>
     *
     * @param mobileNumber Represents user mobile number
     * @return True if mobile number is exist, false otherwise
     */
    public boolean isValidMobileNumber(final String mobileNumber) {
        return userController.isMobileNumberExist(mobileNumber);
    }
}
