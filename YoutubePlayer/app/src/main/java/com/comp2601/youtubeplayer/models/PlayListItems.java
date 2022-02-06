package com.comp2601.youtubeplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayListItems {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("snippet")
    @Expose
    private PlaylistSnippet snippet;

    @SerializedName("contentDetails")
    @Expose
    private PlaylistDetail contentDetails;

    public PlayListItems() {
    }

    public PlayListItems(String id, PlaylistSnippet snippet) {
        this.id = id;
        this.snippet = snippet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PlaylistSnippet getSnippet() {
        return snippet;
    }

    public void setSnippet(PlaylistSnippet snippet) {
        this.snippet = snippet;
    }

    public PlaylistDetail getContentDetails() {
        return contentDetails;
    }

    public void setContentDetails(PlaylistDetail contentDetails) {
        this.contentDetails = contentDetails;
    }
}
