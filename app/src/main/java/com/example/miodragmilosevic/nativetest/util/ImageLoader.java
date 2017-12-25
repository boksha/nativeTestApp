package com.example.miodragmilosevic.nativetest.util;

/**
 * Created by miodrag.milosevic on 12/6/2017.
 */

import android.content.Context;
import android.net.Uri;
import android.support.annotation.StringDef;
import android.widget.ImageView;

import com.example.miodragmilosevic.nativetest.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.StringDef;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by milosevi on 10/9/17.
 */

public class ImageLoader {
    //Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
    private static final String BASE_URL_IMAGES_TMDB = "http://filterImage.tmdb.org/t/p/";

    private static final String W92 = "w92";
    private static final String W154 = "w154";
    private static final String W185 = "w185";
    private static final String W342 = "w342";
    private static final String W500 = "w500";
    private static final String W780 = "w780";
    private static final String ORIGINAL = "original";

    @StringDef({W92, W154,W185,W342,W500,W780,ORIGINAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ImageSize {}

    public static void loadImageFromPathIntoView(Context context, String url, ImageView view){
        if (url == null) {
            Picasso.with(context).load(R.drawable.download).into(view);
            return;
        }
//        url = url.split("/")[1];
//        Log.i("Miki", "loadImageFromPathIntoView: " + url);
//        Uri path = createImageUri(url);
//        Picasso.with(context).setLoggingEnabled(true);
        Picasso.with(context).load(new File(url)).fit().centerCrop().into(view);
    }

    private static Uri createImageUri(String imageUri){
        Uri.Builder builder = Uri.parse(BASE_URL_IMAGES_TMDB).buildUpon();
        Uri result =  builder.appendPath(W154)
                .appendPath(imageUri)
                .build();
//        String result = BASE_URL_IMAGES_TMDB + W154+"/"+imageUri;
//        Log.i("Miki", "createImageUri: " + result);
        return result;
    }

}
