package com.rgand.x_prt.lastfmhits.model.artist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by x_prt on 21.04.2017
 */

public class ArtistModel {

    private String name;
    private String listeners;
    @SerializedName("image")
    private List<ArtistImageModel> imageModelList;

    public String getName() {
        return name;
    }

    public String getListeners() {
        return listeners;
    }

    public List<ArtistImageModel> getImageModelList() {
        return imageModelList;
    }
}
