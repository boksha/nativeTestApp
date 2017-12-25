package com.example.miodragmilosevic.nativetest.histogram;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.example.miodragmilosevic.nativetest.R;
import com.example.viewkitlibrary.ImageHistogramView;

/**
 * Created by miodrag.milosevic on 12/21/2017.
 */

public class HistogramActivity extends AppCompatActivity {

    private static final String TAG = "Miki";
    public static final String EXTRA_IMAGE_URI = "com.example.miodragmilosevic.nativetest.histogram.IMAGE_URI";

    ImageHistogramView mHistogramView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histogram);
        mHistogramView = findViewById(R.id.histogramView);
        String imagePath = getIntent().getStringExtra(EXTRA_IMAGE_URI);
        Log.i(TAG, "onCreate: imagePath " + imagePath);
        if (!TextUtils.isEmpty(imagePath)) {
            Bitmap originalBitmap = BitmapFactory.decodeFile((imagePath));
            // Get info about Bitmap
            int width = originalBitmap.getWidth();
            int height = originalBitmap.getHeight();
            int pixels = width * height;

            // Get original pixels
            int[] pixelArray = new int[pixels];
            originalBitmap.getPixels(pixelArray, 0, width, 0, 0, width, height);
            mHistogramView.setBitmapArray(pixelArray);
        }
    }
}
