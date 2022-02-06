package com.comp2601.youtubeplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelVideos {

    @SerializedName("items")
    @Expose
    private List<VideoItem> items;

    public ModelVideos() {
    }

    public ModelVideos(List<VideoItem> items) {
        this.items = items;
    }

    public List<VideoItem> getItems() {
        return items;
    }

    public void setItems(List<VideoItem> items) {
        this.items = items;
    }
}
