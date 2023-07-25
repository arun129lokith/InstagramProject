package com.instagram.model;

/**
 * <p>
 * Represents a basic user entity with various properties and methods
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class User {

    private Long id;
    private String mobileNumber;
    private String name;
    private String email;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String toString() {
        return String.format("Id = %s\nName = %s\nMobileNumber = %s\nEmail = %s\n", id, name, mobileNumber, email);
    }
}

