package com.rgand.x_prt.lastfmhits.model.artist;

import com.google.gson.annotations.SerializedName;

/**
 * Created by x_prt on 21.04.2017
 */

public class ArtistImageModel {

    @SerializedName("#text")
    private String imageUrl;
    private String size;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
