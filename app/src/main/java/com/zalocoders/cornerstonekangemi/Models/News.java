package com.zalocoders.cornerstonekangemi.Models;

import android.os.Parcel;
import android.os.Parcelable;


public class News {
    private String description;
    private String imageUrls;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }
}
