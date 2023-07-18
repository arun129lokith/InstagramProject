package com.instagram.model;

import java.util.Date;

public class Reel {

    private Long id;
    private String caption;
    private String videoUrl;
    private Long userId;
    private Date uploadDate;

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setVideoUrl(final String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setUploadDate(final Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String toString() {
        return String.format("Id = %d\nCaption = %s\nVideoUrl = %s\nUploadDate = %s\nUserId = %d\n",
                id, caption, videoUrl, uploadDate, userId);
    }
}
