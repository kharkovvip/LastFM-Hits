package com.rgand.x_prt.lastfmhits.model.artist;

import com.google.gson.annotations.SerializedName;
import com.rgand.x_prt.lastfmhits.util.AppConstants;

import java.util.List;

/**
 * Created by x_prt on 21.04.2017
 */

public class ArtistModel {

    private String name;
    private String listeners;
    @SerializedName("image")
    private List<ArtistImageModel> imageModelList;
    private String largeImageUrl = AppConstants.IMAGE_MODEL_IS_EMPTY_KEY;
    private String largePhotoFilePath = AppConstants.IMAGE_MODEL_IS_EMPTY_KEY;
    private String megaImageUrl = AppConstants.IMAGE_MODEL_IS_EMPTY_KEY;
    private String megaPhotoFilePath = AppConstants.IMAGE_MODEL_IS_EMPTY_KEY;

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

    public List<ArtistImageModel> getImageModelList() {
        return imageModelList;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    public String getMegaImageUrl() {
        return megaImageUrl;
    }

    public void setMegaImageUrl(String megaImageUrl) {
        this.megaImageUrl = megaImageUrl;
    }

    public String getLargePhotoFilePath() {
        return largePhotoFilePath;
    }

    public void setLargePhotoFilePath(String largePhotoFilePath) {
        this.largePhotoFilePath = largePhotoFilePath;
    }

    public String getMegaPhotoFilePath() {
        return megaPhotoFilePath;
    }

    public void setMegaPhotoFilePath(String megaPhotoFilePath) {
        this.megaPhotoFilePath = megaPhotoFilePath;
    }
}
