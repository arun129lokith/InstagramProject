package com.instagram.view;

import com.instagram.model.User;
import com.instagram.model.UserBuilder;

/**
 * <p>
 * Gets the authorization window and present it to the user
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class AuthenticationView extends CommonView {

    private static AuthenticationView authenticationView;
    private final UserView userView;

    private AuthenticationView() {
        userView = UserView.getInstance();
    }

    /**
     * <p>
     * Gets the object of the authentication view class
     * </p>
     *
     * @return The authentication view object
     */
    public static AuthenticationView getInstance() {
        return null == authenticationView ? authenticationView = new AuthenticationView() : authenticationView;
    }

    /**
     * <p>
     * Gets the choice for user menu
     * </p>
     */
    public void menu() {
        System.out.println("Click 1 To Sign Up \nClick 2 To Sign In \nClick 3 To Exit");

        switch (getChoice()) {
            case 1:
                signUp();
                break;
            case 2:
                signIn();
                break;
            case 3:
                exit();
                break;
            default:
                System.out.println("Invalid User Choice. Please Try Again\n[Enter the choice in the range 1-3]");
                menu();
                break;
        }
    }

    /**
     * <p>
     * Users to enter details for sign up process
     * </p>
     */
    private void signUp() {
        final UserBuilder user = UserBuilder.getInstance();

        user.withId(idGenerator());
        user.withName(userView.getValidName(userView.getProcessedUserName()));
        user.withEmail(userView.getValidEmail(userView.getProcessedEmail()));
        user.withPassword(userView.getProcessedPassword());
        user.withMobileNumber(userView.getValidMobileNumber(userView.getProcessedMobileNumber()));

        if (authenticationController.signUp(user.build())) {
            printMessage("Sign Up Successfully");

            if (continueOrExit()) {
                menu();
            } else {
                userView.userScreen(userController.getId(user.build()));
            }
        }
        System.out.println("Sign Up Not Successfully");
        menu();
    }

    /**
     * <p>
     * Users to enter details to sign_in process
     * </p>
     */
    private void signIn() {
        final UserBuilder user = UserBuilder.getInstance();

        userChoice(user);
        user.withPassword(userView.getProcessedPassword());

        if (authenticationController.signIn(user.build())) {
            printMessage("Sign in successfully");
            userView.userScreen(userController.getId(user.build()));
        } else {
            printMessage("User Not Found. Please Try Again");
            menu();
        }
    }

    /**
     * <p>
     * Gets the user choice for sign in with email or mobile number
     * </p>
     *
     * @param user Represents {@link User} details
     */
    private void userChoice(final UserBuilder user) {
        System.out.println("Click 1 To Get Email\nClick 2 To Get Mobile Number");

        switch (getChoice()) {
            case 1:
                user.withEmail(userView.getProcessedEmail());
                break;
            case 2:
                user.withMobileNumber(userView.getProcessedMobileNumber());
                break;
            default:
                System.out.println("Invalid User Choice. Please Enter the Choice 1 or 2");
                userChoice(user);
                break;
        }
    }

    /**
     * <p>
     * Generates id for the new user
     * </p>
     *
     * @return The user id
     */
    private long idGenerator() {
        return userController.getAllUsers().size() + 1;
    }

    /**
     * <p>
     * Exits the user from the application
     * </p>
     */
    private void exit() {
        printMessage("Exiting");
        scanner.close();
        connection.closeConnectionPool();
        System.exit(0);
    }
}
