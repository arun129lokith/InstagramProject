package com.instagram.view;

import com.instagram.model.UserBuilder;
import com.instagram.model.Post;
import com.instagram.model.User;

import java.math.BigInteger;

import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * Users to interact with application for accessing the features of the application
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class UserView extends CommonView {

    private static final AuthenticationView AUTHENTICATION_VIEW = AuthenticationView.getInstance();
    private static UserView userView;

    private UserView() {}

    /**
     * <p>
     * Gets the object of the user view class
     * </p>
     *
     * @return The user view object
     */
    public static UserView getInstance() {
        return null == userView ? userView = new UserView() : userView;
    }

    /**
     * <p>
     * Gets the username from the user after validating the name
     * </p>
     *
     * @return The valid username of the user
     */
    public String getProcessedUserName() {
        printMessage(String.join("\n", "Enter Your UserName:",
                "[Username Contains Lowercase Letter And Underscore And Digits Without Space]",
                "If You Want To Exit Press '!'"));
        final String name = scanner.nextLine().trim();

        exitMenu(name);

        return validation.validateUserName(name) ? name : getProcessedUserName();
    }

    /**
     * <p>
     * Gets the valid username is not already exists in the application
     * </p>
     *
     * @param name Represents user name
     * @return The valid username
     */
    public String getValidName(final String name) {
        if (validation.isValidName(name)) {
            printMessage("User Name Already Exist. Please Re-enter The Valid User Name");

            return getValidName(getProcessedUserName());
        }

        return name;
    }

    /**
     * <p>
     * Gets the valid email from the user after validation
     * </p>
     *
     * @return The valid email of the user
     */
    public String getProcessedEmail() {
        printMessage(String.join(" ", "Enter Your EmailId:",
                "\n[EmailId Must Contains Lowercase Letter[a-z] Then Contain Digits[0-9] Is Optional One",
                "'@' After Must Contains [5 Or Above] Lowercase Letter And '.' After Must Contains 2 Or 3 ",
                "Characters]\nIF You Want To Exit Press '!'"));
        final String email = scanner.nextLine().trim();

        exitMenu(email);

        return validation.validateEmail(email) ? email : getProcessedEmail();
    }

    /**
     * <p>
     * Gets the email is not already exists in the application
     * </p>
     *
     * @param email Represents user email
     * @return The mobile number of the user
     */
    public String getValidEmail(final String email) {
        if (validation.isValidEmail(email)) {
            printMessage("User Email Is Already Exist. Please Re-enter The Valid User Name");

            return getValidEmail(getProcessedEmail());
        }

        return email;
    }

    /**
     * <p>
     * Gets the password from the user after validating the password
     * </p>
     *
     * @return The valid password of the user
     */
    public String getProcessedPassword() {
        printMessage(String.join(" ", "Enter Your Password:", "\n[Password Must Contain At least",
                "One Uppercase, One Lowercase, Special Character And Digits In The Range 8-20 Characters]",
                "\nIF You Want To Exit Press '!'"));
        final String password = scanner.nextLine().trim();

        exitMenu(password);

        if (validation.validatePassword(password)) {
            final String hashPassword = hashPassword(password);

            if (null != hashPassword) {
                return hashPassword.substring(0,16);
            }
        }

        return getProcessedPassword();
    }

    /**
     * <p>
     * Encrypts the password using hashing techniques
     * </p>
     *
     * @param password Represents the user password
     * @return The encrypted password
     */
    private String hashPassword(final String password) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            final byte[] encodedHash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            final BigInteger number = new BigInteger(encodedHash);
            final StringBuilder hexString = new StringBuilder(number.toString(16));

            while (hexString.length() < 32) {
                hexString.insert(0,'0');
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException message) {
            printMessage("Hashing Algorithm Is Not Found");
        }

        return null;
    }

    /**
     * <p>
     * Gets the valid mobile number from the user after validation
     * </p>
     *
     * @return The mobile number of the user
     */
    public String getProcessedMobileNumber() {
        printMessage(String.join(" ", "Enter Your Mobile Number:", "\n[Mobile Number Must",
                "Contains 10 Digits  And Starts With [6-9]]", "\nIf You Want To Exit Press '!'"));
        final String mobileNumber = scanner.nextLine().trim();

        exitMenu(mobileNumber);

        return validation.validateMobileNumber(mobileNumber) ? mobileNumber : getProcessedMobileNumber();
    }

    /**
     * <p>
     * Gets the mobile number is not already exists in the application
     * </p>
     *
     * @param mobileNumber Represents user mobile number
     * @return The mobile number of the user
     */
    public String getValidMobileNumber(final String mobileNumber) {
        if (validation.isValidMobileNumber(mobileNumber)) {
            printMessage("User Mobile Number Is Already Exist. Please Re-enter The Valid User Name");

            return getValidMobileNumber(getProcessedMobileNumber());
        }

        return mobileNumber;
    }

    /**
     * <p>
     * Gets the user details of the user
     * </p>
     */
    private void getUser() {
        printMessage("Enter Your UserId:");
        final User user = userController.getUser(getUserId());

        System.out.println(null != user ? user : "User Not Found");
    }

    /**
     * <p>
     * Gets the all user details
     * </p>
     */
    private void getAllUsers() {
        System.out.println(userController.getAllUsers());
    }

    /**
     * <p>
     * Prints the features of the application
     * </p>
     *
     * @param id Represents user id
     */
    public void userScreen(final Long id) {
        printMessage(String.join(" ","Click 1 To User Post Menu\nClick 2 To Logout", "\nClick 3",
                "To Get User\nClick 4 To Get All User \nClick 5 To Update User\nClick 6 To Delete User",
                "\nClick 7 To Main Menu\nClick 8 To Display Post Of The User"));
        final PostView postView = PostView.getInstance();

        switch (getFeatures()) {
            case ONE:
                postView.menu(id);
                break;
            case TWO:
                logout();
                break;
            case THREE:
                getUser();
                break;
            case FOUR:
                getAllUsers();
                break;
            case FIVE:
                update(id);
                break;
            case SIX:
                delete();
                break;
            case SEVEN:
                AUTHENTICATION_VIEW.menu();
                break;
            case EIGHT:
                displayPost();
                break;
            default:
                printMessage("Invalid User Choice. Please Try Again\n[Enter The Choice In The Range 1-8]");
                userScreen(id);
        }
        userScreen(id);
    }

    /**
     * <p>
     * Displays the collection of user post
     * </p>
     */
    private void displayPost() {
        printMessage("Enter The User Id To Get Collection Of Post:");
        final User user = getUserById(getUserId());

        if (null != user) {

            if (! user.getPosts().isEmpty()) {

                for (final Post post : user.getPosts()) {

                    if (post.getUserId().equals(user.getId())) {
                        System.out.println(post);
                    }
                }
            } else {
                printMessage("Post Not Created By The User");
            }
        } else {
            printMessage("User Not Found.Please Try Again");
        }
    }

    /**
     * <p>
     * Gets the valid user id
     * </p>
     *
     * @return The user id
     */
    private Long getUserId() {
        try {
            return Long.parseLong(scanner.nextLine().trim());
        } catch (final NumberFormatException message) {
            printMessage("Invalid User Id Format. Please Enter A Number");
        }

        return getUserId();
    }

    /**
     * <p>
     * Users to enter update details of the user information
     * </p>
     *
     * @param id Represents user id
     */
    private void update(final Long id) {
        final UserBuilder user = UserBuilder.getInstance();
        final User existingUser = getUserById(id);

        System.out.println(existingUser);
        user.withId(id);
        user.withName(continueOrExit() ? existingUser.getName() : getValidName(getProcessedUserName()));
        user.withPassword(continueOrExit() ? existingUser.getPassword() : getProcessedPassword());
        user.withEmail(continueOrExit() ? existingUser.getEmail() : getValidEmail(getProcessedEmail()));
        user.withMobileNumber(continueOrExit() ? existingUser.getMobileNumber()
                : getValidMobileNumber(getProcessedMobileNumber()));

        printMessage(userController.update(user.build()) ? "Account Updated Successfully" : "User Not Found");
    }

    /**
     * <p>
     * Gets user information by id of the user
     * </p>
     *
     * @param id Represents user id
     * @return Represents {@link User} information
     */
    public User getUserById(final Long id) {
        return userController.getUser(id);
    }

    /**
     * <p>
     * Users to delete his account
     * </p>
     */
    private void delete() {
        printMessage("Enter Your User Id:");

        if (userController.delete(getUserId())) {
            printMessage("User Account Deleted Successfully");
            AUTHENTICATION_VIEW.menu();
        } else {
            printMessage("User Not Found. Please Try Again");
        }
    }

    /**
     * <p>
     * Users to log out the page
     * </p>
     */
    private void logout() {
        printMessage("Logged Out Successfully");

        if (continueOrExit()) {
            scanner.close();
            System.exit(0);
        }
        AUTHENTICATION_VIEW.menu();
    }

    /**
     * <p>
     * Exits the screen to menu
     * </p>
     *
     * @param userChoice Represents the choice of the user
     */
    private void exitMenu(final String userChoice) {
        if (validation.backToMenu(userChoice)) {
            AUTHENTICATION_VIEW.menu();
        }
    }
}

