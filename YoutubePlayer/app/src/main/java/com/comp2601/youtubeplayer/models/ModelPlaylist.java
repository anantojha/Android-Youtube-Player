package com.comp2601.youtubeplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelPlaylist {
    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;

    @SerializedName("items")
    @Expose
    private List<PlayListItems> items;

    public ModelPlaylist() {
    }

    public ModelPlaylist(String nextPageToken, List<PlayListItems> items) {
        this.nextPageToken = nextPageToken;
        this.items = items;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<PlayListItems> getItems() {
        return items;
    }

    public void setItems(List<PlayListItems> items) {
        this.items = items;
    }
}
