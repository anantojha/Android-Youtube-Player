package com.comp2601.youtubeplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChannelStatistics {
    @SerializedName("viewCount")
    @Expose
    private String viewCount;

    @SerializedName("subscriberCount")
    @Expose
    private String subscriberCount;

    @SerializedName("videoCount")
    @Expose
    private String videoCount;

    public ChannelStatistics() {
    }

    public ChannelStatistics(String viewCount, String subscriberCount, String videoCount) {
        this.viewCount = viewCount;
        this.subscriberCount = subscriberCount;
        this.videoCount = videoCount;
    }

    public String getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(String videoCount) {
        this.videoCount = videoCount;
    }

    public String getSubscriberCount() {
        return subscriberCount;
    }

    public void setSubscriberCount(String subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }
}
