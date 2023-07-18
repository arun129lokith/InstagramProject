package com.instagram;

import com.instagram.view.AuthenticationView;

/**
 * <p>
 * Activates the features of the instagram application
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class InstagramActivator {

    /**
     * <p>
     * Starts the execution of the application
     * </p>
     *
     * @param args Represents command line arguments
     */
    public static void main(String[] args) {
        AuthenticationView.getInstance().menu();
    }
}