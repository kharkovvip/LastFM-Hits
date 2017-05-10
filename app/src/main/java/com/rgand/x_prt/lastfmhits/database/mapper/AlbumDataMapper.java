package com.rgand.x_prt.lastfmhits.database.mapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.rgand.x_prt.lastfmhits.model.album.AlbumImageModel;
import com.rgand.x_prt.lastfmhits.model.album.AlbumModel;
import com.rgand.x_prt.lastfmhits.util.AppConstants;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by x_prt on 09.05.2017
 */

public class AlbumDataMapper {

    private static String filePath = AppConstants.IMAGE_MODEL_IS_EMPTY_KEY;

    private static void saveBitmapUri(Context context,
                                      final String artistName,
                                      final AlbumModel albumModel,
                                      AlbumImageModel imageModel) {

        Picasso.with(context).load(imageModel.getImageUrl()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                try {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    File pickDirectory = new File(Environment.getExternalStorageDirectory().getPath()
                            + File.pathSeparator
                            + "LastFMCash");
                    pickDirectory.mkdirs();

                    filePath = pickDirectory
                            + File.separator
                            + artistName
                            + File.separator
                            + albumModel.getName()
                            + File.separator
                            + "cover.png";

                    FileOutputStream fo = new FileOutputStream(new File(filePath));
                    fo.write(byteArray);
                    fo.flush();
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                albumModel.setLargeImageUrl(filePath);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
}
