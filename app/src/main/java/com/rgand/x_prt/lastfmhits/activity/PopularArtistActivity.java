package com.rgand.x_prt.lastfmhits.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rgand.x_prt.lastfmhits.R;
import com.rgand.x_prt.lastfmhits.adapter.TopArtistRVAdapter;
import com.rgand.x_prt.lastfmhits.base.BaseActivity;
import com.rgand.x_prt.lastfmhits.listener.OnArtistItemClickListener;
import com.rgand.x_prt.lastfmhits.model.artist.ArtistModel;
import com.rgand.x_prt.lastfmhits.model.artist.GeoArtistData;
import com.rgand.x_prt.lastfmhits.network.listener.RequestListener;
import com.rgand.x_prt.lastfmhits.network.requests.GetArtistsRequest;
import com.rgand.x_prt.lastfmhits.util.AppConstants;
import com.rgand.x_prt.lastfmhits.util.ArtistByListenersComparator;
import com.rgand.x_prt.lastfmhits.util.ArtistByNameComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PopularArtistActivity extends BaseActivity implements View.OnClickListener,
        OnArtistItemClickListener, DialogInterface.OnDismissListener, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AlertDialog selectLocationDialog;

    private boolean isSwipeRefreshing;
    private boolean isSortingByListeners;
    private String chosenCountry;
    private TopArtistRVAdapter artistRVAdapter;
    private List<ArtistModel> artistModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        overridePendingTransition(R.anim.slide_down_in_animation, R.anim.slide_up_out_animation);

        initViews();
    }

    private void initViews() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_popular_activity));
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                AppConstants.TOP_ARTISTS_RV_COLUMNS, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setAutoMeasureEnabled(true);

        chosenCountry = getResources().getString(R.string.country_ukraine_txt);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbarlayout_popular_activity);
        collapsingToolbarLayout.setTitle(chosenCountry);

        artistRVAdapter = new TopArtistRVAdapter(this);
        RecyclerView rvArtists = (RecyclerView) findViewById(R.id.rv_top_artists);
        rvArtists.setItemAnimator(new DefaultItemAnimator());
        rvArtists.setLayoutManager(layoutManager);
        rvArtists.setAdapter(artistRVAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLocationDialog();
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.white);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.site_red);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTopArtistsRequest();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_artists, menu);
        return true;
    }

    /**
     * "Feel free to take a creative freedom and show your design skills" in task description was written,
     * so I decided to make sorting at that menu and to and to move country's selector to FloatingActionButton
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_names:
                isSortingByListeners = false;
                artistRVAdapter.setList(sortArtistList(artistModels));
                break;
            case R.id.action_sort_listeners:
                isSortingByListeners = true;
                artistRVAdapter.setList(sortArtistList(artistModels));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * request to API for getting top artists of chosen (hardcoded) country
     */
    private void getTopArtistsRequest() {
        if (!isSwipeRefreshing) {
            showProgressBar();
        } else {
            isSwipeRefreshing = false;
        }
        GetArtistsRequest getArtistsRequest = new GetArtistsRequest(chosenCountry);
        getArtistsRequest.setListener(new RequestListener<GeoArtistData>() {
            @Override
            public void onSuccess(GeoArtistData result) {
                artistModels = result.getTopartists().getArtistModelList();
                artistRVAdapter.setList(sortArtistList(artistModels));
                hideProgressBar();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(String errorMessage) {
                hideProgressBar();
                swipeRefreshLayout.setRefreshing(false);
//                checkInternetConnection(PopularArtistActivity.this, PopularArtistActivity.this);
            }
        });
        getArtistsRequest.execute();
    }

    private List<ArtistModel> sortArtistList(List<ArtistModel> artistModelList) {
        Collections.sort(artistModelList, isSortingByListeners
                ? new ArtistByListenersComparator() : new ArtistByNameComparator());
        return artistModelList;
    }

    /**
     * here must be GoogleMaps screen calling or GooglePlaces search tool
     */
    private void showLocationDialog() {
        View view = View.inflate(this, R.layout.dialog_select_location, null);
        if (selectLocationDialog == null) {
            selectLocationDialog = new AlertDialog.Builder(this).create();
            selectLocationDialog.setView(view);
            selectLocationDialog.setCancelable(true);
            if (selectLocationDialog.getWindow() != null) {
                selectLocationDialog.getWindow()
                        .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            view.findViewById(R.id.tv_dialog_location_ukraine).setOnClickListener(this);
            view.findViewById(R.id.tv_dialog_location_georgia).setOnClickListener(this);
            view.findViewById(R.id.tv_dialog_location_switzerland).setOnClickListener(this);
        }
        selectLocationDialog.dismiss();
        selectLocationDialog.show();
    }

    @Override
    public void onClick(View v) {
        selectLocationDialog.dismiss();
        switch (v.getId()) {
            case R.id.tv_dialog_location_ukraine:
                chosenCountry = getResources().getString(R.string.country_ukraine_txt);
                collapsingToolbarLayout.setTitle(chosenCountry);
                getTopArtistsRequest();
                break;
            case R.id.tv_dialog_location_georgia:
                chosenCountry = getResources().getString(R.string.country_georgia_txt);
                collapsingToolbarLayout.setTitle(chosenCountry);
                getTopArtistsRequest();
                break;
            case R.id.tv_dialog_location_switzerland:
                chosenCountry = getResources().getString(R.string.country_switzerland_txt);
                collapsingToolbarLayout.setTitle(chosenCountry);
                getTopArtistsRequest();
                break;
            default:
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_up_in_animation, R.anim.slide_down_out_animation);
    }

    @Override
    public void onArtistItemClicked(String artistName, String artistPhoto) {
        Intent intent = new Intent(PopularArtistActivity.this, ArtistInfoActivity.class);
        intent.putExtra(ArtistInfoActivity.ARTIST_NAME_KEY, artistName);
        intent.putExtra(ArtistInfoActivity.ARTIST_PHOTO_KEY, artistPhoto);
        startActivity(intent);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        getTopArtistsRequest();
    }

    @Override
    public void onRefresh() {
        isSwipeRefreshing = true;
        getTopArtistsRequest();
    }
}
