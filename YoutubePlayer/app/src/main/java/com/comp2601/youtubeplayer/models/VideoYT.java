package com.comp2601.youtubeplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoYT {

    @SerializedName("id")
    @Expose
    private VideoID videoId;

    @SerializedName("snippet")
    @Expose
    private SnippetYT snippet;

    public VideoYT() {
    }

    public VideoYT(VideoID videoId, SnippetYT snippet) {
        this.videoId = videoId;
        this.snippet = snippet;
    }

    public VideoID getVideoId() {
        return videoId;
    }

    public void setVideoId(VideoID videoId) {
        this.videoId = videoId;
    }

    public SnippetYT getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetYT snippet) {
        this.snippet = snippet;
    }
}
