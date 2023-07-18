package com.instagram.view;

import java.util.Scanner;

/**
 * <p>
 * Creates an object of scanner in single time only
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class ScannerInstance {

    private static Scanner scanner;

    /**
     * Gets the object of the scanner class
     *
     * @return The scanner object
     */
    public static synchronized Scanner getInstance() {
        return null == scanner ? scanner = new Scanner(System.in) : scanner;
    }
}
