package com.comp2601.youtubeplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoItem {
    @SerializedName("snippet")
    @Expose
    private SnippetVideo snippet;

    public VideoItem() {
    }

    public VideoItem(VideoID videoId, SnippetVideo snippet) {
        this.snippet = snippet;
    }

    public SnippetVideo getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetVideo snippet) {
        this.snippet = snippet;
    }

}
