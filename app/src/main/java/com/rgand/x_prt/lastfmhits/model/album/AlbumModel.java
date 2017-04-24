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

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<AlbumImageModel> getImageModelList() {
        return imageModelList;
    }

    public void setImageModelList(List<AlbumImageModel> imageModelList) {
        this.imageModelList = imageModelList;
    }
}
