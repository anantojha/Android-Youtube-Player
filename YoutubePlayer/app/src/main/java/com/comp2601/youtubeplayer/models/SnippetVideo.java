package com.comp2601.youtubeplayer.models;

import com.google.api.services.youtube.model.ResourceId;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SnippetVideo {
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("thumbnails")
    @Expose
    private ThumbnailYT thumbnails;

    @SerializedName("resourceId")
    @Expose
    private ResourceId resourceId;

    public SnippetVideo() {
    }

    public SnippetVideo(String publishedAt, String title, String description, ThumbnailYT thumbnails, ResourceId resourceId) {
        this.publishedAt = publishedAt;
        this.title = title;
        this.description = description;
        this.thumbnails = thumbnails;
        this.resourceId = resourceId;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ThumbnailYT getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(ThumbnailYT thumbnails) {
        this.thumbnails = thumbnails;
    }

    public ResourceId getResourceId() {
        return resourceId;
    }

    public void setResourceId(ResourceId resourceId) {
        this.resourceId = resourceId;
    }
}
