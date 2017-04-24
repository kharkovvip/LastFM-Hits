package com.rgand.x_prt.lastfmhits.network;

import com.rgand.x_prt.lastfmhits.model.album.ArtistInfoData;
import com.rgand.x_prt.lastfmhits.model.artist.GeoArtistData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by x_prt on 21.04.2017
 */

public interface LastFmApi {

    @GET("2.0/")
    Call<GeoArtistData> getTopArtists(
            @Query("method") String callingMethod,
            @Query("country") String chosenCountry,
            @Query("limit") String artistsPerPage,
            @Query("page") String pageNumber);

    @GET("2.0/")
    Call<ArtistInfoData> getTopAlbums(
            @Query("method") String callingMethod,
            @Query("artist") String artist,
            @Query("limit") String albumsPerPage,
            @Query("page") String pageNumber);
}
