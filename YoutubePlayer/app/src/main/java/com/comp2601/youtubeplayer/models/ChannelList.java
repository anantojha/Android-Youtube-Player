package com.comp2601.youtubeplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChannelList {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("snippet")
    @Expose
    private SnippetYT snippet;

    @SerializedName("statistics")
    @Expose
    private ChannelStatistics statistics;

    @SerializedName("brandingSettings")
    @Expose
    private ChannelBrandingSettings brandingSettings;

    public ChannelList(String id, SnippetYT snippet, ChannelStatistics statistics, ChannelBrandingSettings brandingSettings) {
        this.id = id;
        this.snippet = snippet;
        this.statistics = statistics;
        this.brandingSettings = brandingSettings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SnippetYT getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetYT snippet) {
        this.snippet = snippet;
    }

    public ChannelStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(ChannelStatistics statistics) {
        this.statistics = statistics;
    }

    public ChannelBrandingSettings getBrandingSettings() {
        return brandingSettings;
    }

    public void setBrandingSettings(ChannelBrandingSettings brandingSettings) {
        this.brandingSettings = brandingSettings;
    }
}
