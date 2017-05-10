package com.rgand.x_prt.lastfmhits.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.rgand.x_prt.lastfmhits.model.album.AlbumModel;

import java.util.ArrayList;
import java.util.List;

public class DataHandler {

    private static final String DATA_BASE_NAME = "lfm_database";
    private static final String ALBUMS_TABLE_NAME = "albums_table";
    private static final String ARTISTS_TABLE_NAME = "artists_table";
    private static final int DATABASE_VERSION = 1;

    private static final String ARTIST_NAME = "artist_name";

    private static final String ALBUM_NAME = "name";
    private static final String ALBUM_PLAYCOUNT = "playcount";
    private static final String ALBUM_URL = "url";
    private static final String ALBUM_LARGE_PHOTO = "largeImageUrl";
    private static final String ALBUM_PHOTO_PATH = "album_photo_path";

    private static final String ALBUM_SQL = "CREATE TABLE " +
            ALBUMS_TABLE_NAME +
            "(" +
            ARTIST_NAME + " TEXT, " +
            ALBUM_NAME + " TEXT, " +
            ALBUM_PLAYCOUNT + " TEXT, " +
            ALBUM_URL + " TEXT, " +
            ALBUM_LARGE_PHOTO + " TEXT, " +
            ALBUM_PHOTO_PATH + " TEXT" +
            ");";

    private SQLiteDatabase sqLiteDB;
    private DataBaseHelper dbHelper;

    public DataHandler(Context context) {
        dbHelper = new DataBaseHelper(context.getApplicationContext());
    }

    private static class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context context) {
            super(context, DATA_BASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ALBUM_SQL);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i2) {
            db.execSQL("DROP TABLE IF EXIST " + ALBUMS_TABLE_NAME);

            onCreate(db);
        }
    }

    public DataHandler open() {
        sqLiteDB = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void saveAlbumToDB(AlbumModel albumModel, String artistName) {
        sqLiteDB.insert(ALBUMS_TABLE_NAME, null, getAlbumContentValues(albumModel, artistName));

    }

    public void saveAlbumList(List<AlbumModel> albumModelArrayList, String artistName) {
        removeAllAlbums(artistName);
        for (AlbumModel album :
                albumModelArrayList) {
            saveAlbumToDB(album, artistName);
        }
    }

    @NonNull
    private ContentValues getAlbumContentValues(AlbumModel albumModel, String artistName) {
        ContentValues content = new ContentValues();
        content.put(ARTIST_NAME, artistName);
        content.put(ALBUM_NAME, albumModel.getName());
        content.put(ALBUM_PLAYCOUNT, albumModel.getPlaycount());
        content.put(ALBUM_URL, albumModel.getUrl());
        content.put(ALBUM_LARGE_PHOTO, albumModel.getLargeImageUrl());
        content.put(ALBUM_PHOTO_PATH, albumModel.getPhotoFilePath());
        return content;
    }

    private void removeAllAlbums(String artistName) {
        String whereClause = ARTIST_NAME + " = ?";
        String[] whereArgs = new String[]{artistName};
        sqLiteDB.delete(ALBUMS_TABLE_NAME, whereClause, whereArgs);
    }

    public ArrayList<AlbumModel> getAlbumList(String artistName) {
        String whereClause = ARTIST_NAME + " = ?";
        String[] whereArgs = new String[]{artistName};
        Cursor cursor = sqLiteDB.query(ALBUMS_TABLE_NAME, null, whereClause, whereArgs, null, null, null);

        ArrayList<AlbumModel> models = new ArrayList<AlbumModel>();
        while (cursor.moveToNext()) {
            models.add(createAlbum(cursor));
        }
        cursor.close();
        return models;
    }

    private AlbumModel createAlbum(Cursor cursor) {
        AlbumModel albumModel = new AlbumModel();

        albumModel.setName(cursor.getString(cursor.getColumnIndex(ALBUM_NAME)));
        albumModel.setPlaycount(cursor.getString(cursor.getColumnIndex(ALBUM_PLAYCOUNT)));
        albumModel.setUrl(cursor.getString(cursor.getColumnIndex(ALBUM_URL)));
        albumModel.setLargeImageUrl(cursor.getString(cursor.getColumnIndex(ALBUM_LARGE_PHOTO)));
        albumModel.setPhotoFilePath(cursor.getString(cursor.getColumnIndex(ALBUM_PHOTO_PATH)));

        return albumModel;
    }
}

