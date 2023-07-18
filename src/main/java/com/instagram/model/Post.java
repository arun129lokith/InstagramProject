package com.instagram.model;

import java.sql.Timestamp;

/**
 * <p>
 * Represents the post entity of the user with various properties and methods
 * </p>
 *
 * @author Arun
 * @version 1.0
 */
public class Post {

    private Long userId;
    private Long id;
    private String caption;
    private String location;
    private Timestamp uploadedTime;
    private Format format;

    public enum Format {

        IMAGE(1), VIDEO(2);
        private final int choice;

        Format(final int choice) {
            this.choice = choice;
        }

        public static Format existingFormat(final int choice) {
            for (final Format existingFormat : Format.values()) {

                if (existingFormat.choice == choice) {
                    return existingFormat;
                }
            }

            return null;
        }
    }

    public Timestamp getUploadedTime() {
        return uploadedTime;
    }

    public void setUploadedTime(final Timestamp uploadedTime) {
        this.uploadedTime = uploadedTime;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(final Format format) {
        this.format = format;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String toString() {
        return String.format("Id = %d\nCaption = %s\nLocation = %s\nTime And Date = %s\nUser Id = %d\nFormat = %s\n",
                id, caption, location, uploadedTime, userId, format);
    }
}
