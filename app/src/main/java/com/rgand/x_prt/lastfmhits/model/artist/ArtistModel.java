package com.rgand.x_prt.lastfmhits.model.artist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by x_prt on 21.04.2017
 */

public class ArtistModel {

    private String name;
    private String listeners;
    private String mbid;
    private String url;
    private String streamable;
    private boolean isStreameable;
    @SerializedName("image")
    private List<ArtistImageModel> imageModelList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
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

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
        isStreameable = streamable.equals("1");
    }

    public boolean isStreameable() {
        return isStreameable;
    }

    public List<ArtistImageModel> getImageModelList() {
        return imageModelList;
    }

    public void setImageModelList(List<ArtistImageModel> imageModelList) {
        this.imageModelList = imageModelList;
    }

    @Override
    public String toString() {
        return "ArtistModel{" +
                "name='" + name + '\'' +
                ", listeners='" + listeners + '\'' +
                ", mbid='" + mbid + '\'' +
                ", url='" + url + '\'' +
                ", streamable='" + streamable + '\'' +
                ", isStreameable=" + isStreameable +
                ", imageModelList=" + imageModelList +
                '}';
    }
}
