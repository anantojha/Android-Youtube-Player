package com.comp2601.youtubeplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThumbnailYT {
    @SerializedName("medium")
    @Expose
    private MediumThumb medium;

    public ThumbnailYT() {
    }

    public ThumbnailYT(MediumThumb medium) {
        this.medium = medium;
    }

    public MediumThumb getMedium() {
        return medium;
    }

    public void setMedium(MediumThumb medium) {
        this.medium = medium;
    }

    public class MediumThumb {
        @SerializedName("url")
        @Expose
        private String url;

        private MediumThumb() {
        }

        private MediumThumb(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
