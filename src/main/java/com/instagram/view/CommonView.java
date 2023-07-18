package com.instagram.view;

import com.instagram.controller.AuthenticationController;
import com.instagram.controller.PostController;
import com.instagram.controller.UserController;
import com.instagram.customexception.FileAccessException;
import com.instagram.database.DataBaseConnectionPool;
import com.instagram.view.validation.CommonValidation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * <p>
 * Represents the commonly used field and methods in the application
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class CommonView {

    protected final Scanner scanner;
    protected final CommonValidation validation;
    protected final AuthenticationController authenticationController;
    protected final PostController postController;
    protected final UserController userController;
    protected final DataBaseConnectionPool connection;

    CommonView() {
        scanner = ScannerInstance.getInstance();
        validation = CommonValidation.getInstance();
        authenticationController = AuthenticationController.getInstance();
        postController = PostController.getInstance();
        userController = UserController.getInstance();
        connection = DataBaseConnectionPool.getInstance();
    }

    /**
     * <p>
     * Gets the valid choice from the user.
     * </p>
     *
     * @return The choice of the user.
     */
    protected int getChoice() {
        printMessage("Enter Your Choice:");

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException message) {
            printMessage("Invalid Choice. Please Enter An Integer");
        }

        return getChoice();
    }

    /**
     * <p>
     * Checks the process to continue or exit.
     * </p>
     *
     * @return True if exit the process, false otherwise.
     */
    protected boolean continueOrExit() {
        printMessage(String.join(" ","Do You Want To Continue The Process Press 'Any Key Or",
                "Word' Else Press 'N Key Or No Word' For Exit The Process\nEnter Your Message For Continue Or Exit:"));

        return validation.continueOrExit(scanner.nextLine());
    }

    /**
     * <p>
     * Prints the information to the user
     * </p>
     *
     * @param message Represents information of the process
     */
    protected void printMessage(final String message) {
        System.out.println(message);
    }

    protected enum Features {

        ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
        SEVEN(7), EIGHT(8);
        private final int choice;

        Features(final int choice) {
            this.choice = choice;
        }

        public static Features existingFeatures(final int choice) {
            for (final Features existingFeatures : Features.values()) {

                if (choice == existingFeatures.choice) {
                    return existingFeatures;
                }
            }

            return null;
        }
    }

    /**
     * <p>
     * Gets the features of the application
     * </p>
     *
     * @return The feature of the application
     */
    protected Features getFeatures() {
        final Features features = Features.existingFeatures(getChoice());

        if (null != features) {
            return features;
        } else {
            printMessage("Invalid Features choice. Please Enter The Choice In The Range[1-8]");

            return getFeatures();
        }
    }
}
