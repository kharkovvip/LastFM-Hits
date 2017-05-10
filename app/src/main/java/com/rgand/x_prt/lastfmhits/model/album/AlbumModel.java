package com.rgand.x_prt.lastfmhits.model.album;

import com.google.gson.annotations.SerializedName;
import com.rgand.x_prt.lastfmhits.util.AppConstants;

import java.util.List;

/**
 * Created by x_prt on 21.04.2017
 */

public class AlbumModel {

    private String name;
    private String playcount;
    private String url;
    @SerializedName("image")
    private List<AlbumImageModel> imageModelList;
    private String largeImageUrl = AppConstants.IMAGE_MODEL_IS_EMPTY_KEY;
    private String photoFilePath = AppConstants.IMAGE_MODEL_IS_EMPTY_KEY;

    public AlbumModel() {
    }

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

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    public String getPhotoFilePath() {
        return photoFilePath;
    }

    public void setPhotoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
    }
}
