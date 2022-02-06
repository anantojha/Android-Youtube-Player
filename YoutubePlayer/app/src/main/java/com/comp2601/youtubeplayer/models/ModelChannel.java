package com.comp2601.youtubeplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelChannel {

    @SerializedName("items")
    @Expose
    private List<ChannelList> items;

    public ModelChannel() {
    }

    public ModelChannel(List<ChannelList> items) {
        this.items = items;
    }

    public List<ChannelList> getItems() {
        return items;
    }

    public void setItems(List<ChannelList> items) {
        this.items = items;
    }
}
