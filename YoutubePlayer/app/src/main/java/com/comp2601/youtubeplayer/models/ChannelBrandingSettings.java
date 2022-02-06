package com.comp2601.youtubeplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChannelBrandingSettings {

    @SerializedName("image")
    @Expose
    private ImageBranding imageBranding;

    public ChannelBrandingSettings() {
    }

    public ChannelBrandingSettings(ImageBranding imageBranding) {
        this.imageBranding = imageBranding;
    }

    public ImageBranding getImageBranding() {
        return imageBranding;
    }

    public void setImageBranding(ImageBranding imageBranding) {
        this.imageBranding = imageBranding;
    }
}
