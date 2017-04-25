package com.rgand.x_prt.lastfmhits.model.album;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by x_prt on 21.04.2017
 */

public class AlbumModel {

    private String name;
    private String playcount;
    private String mbid;
    private String url;
    @SerializedName("image")
    private List<AlbumImageModel> imageModelList;

    public String getName() {
        return name;
    }

    public String getPlaycount() {
        return playcount;
    }

    public String getUrl() {
        return url;
    }

    public List<AlbumImageModel> getImageModelList() {
        return imageModelList;
    }
}
