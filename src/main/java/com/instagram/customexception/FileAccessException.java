package com.instagram.customexception;

/**
 * <p>
 * Throws an file access exception
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class FileAccessException extends RuntimeException {

    public FileAccessException(final String message) {
        super(message);
    }
}
