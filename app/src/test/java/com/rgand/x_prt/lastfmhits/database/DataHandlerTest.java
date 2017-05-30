package com.rgand.x_prt.lastfmhits.database;

import android.content.Context;

import com.rgand.x_prt.lastfmhits.R;
import com.rgand.x_prt.lastfmhits.model.artist.ArtistModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by x_prt on 29.05.2017
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 24, manifest = Config.NONE)
public class DataHandlerTest {

    private DataHandler dataHandler;

    private String[] countries;
    private List<ArtistModel> mockArtistList;

    @Before
    public void setUp() throws Exception {
        Context context = RuntimeEnvironment.application.getApplicationContext();

        dataHandler = new DataHandler(context);
        dataHandler.open();

        countries = context.getResources().getStringArray(R.array.countries_array);
    }

    @Test
    public void saveArtistList() throws Exception {
        mockArtistList = Arrays.asList();
        dataHandler.saveArtistList(mockArtistList, "Ukraine");
        ArrayList<ArtistModel> list = dataHandler.getArtistList("Ukraine");
        assertThat(list, is(mockArtistList));
    }

    @Test
    public void removeAllArtists() {
        String a = countries[0];
        assertThat(a, is("Ukraine"));
    }
}