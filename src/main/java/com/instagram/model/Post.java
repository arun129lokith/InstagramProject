package com.instagram.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSD")
    private Timestamp uploadedTime;
    private Format format;

    public enum Format {

        IMAGE("Image"), VIDEO("Video");

        private final String value;

        Format(final String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @JsonCreator
        public static Format existingFormat(final String value) {
            for (final Format format : Format.values()) {

                if (format.value.equalsIgnoreCase(value)) {
                    return format;
                }
            }

            throw new IllegalArgumentException("Invalid Post Format");
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
