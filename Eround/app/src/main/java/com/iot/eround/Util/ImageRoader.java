package com.iot.eround.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageRoader {

    public ImageRoader() {

        new ThreadPolicy();
    }

    public Bitmap getBitmapImg(String image) {

        Bitmap bitmapImg = null;

        try {
            URL url = new URL(image);

            HttpURLConnection conn = (HttpURLConnection) url
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            bitmapImg = BitmapFactory.decodeStream(is);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return bitmapImg;
    }

}
