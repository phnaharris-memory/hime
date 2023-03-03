package com.example.hime_droid.helpers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageHttpRequest extends AsyncTask<String, Integer, BitmapDrawable> {
    public BitmapDrawable doInBackground(String... endpoint) {

        HttpURLConnection urlConnection = null;
        String res = "";

        try {
            URL url = new URL(endpoint[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            Bitmap x;

            urlConnection.connect();
            InputStream input = urlConnection.getInputStream();

            x = BitmapFactory.decodeStream(input);
            return new BitmapDrawable(Resources.getSystem(), x);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }
}
