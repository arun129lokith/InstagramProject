package com.instagram.customexception;

/**
 * <p>
 * Throws an data access exception
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class DataAccessException extends RuntimeException {

    public DataAccessException(final String message) {
        super(message);
    }
}
