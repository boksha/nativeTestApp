package com.example.miodragmilosevic.nativetest.filterimage.mvp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.example.miodragmilosevic.nativetest.R;
import com.example.miodragmilosevic.nativetest.filterimage.FilterAdapter;
import com.example.miodragmilosevic.nativetest.filterimage.FilterItem;
import com.example.miodragmilosevic.nativetest.filters.JavaImageProcessor;
import com.example.miodragmilosevic.nativetest.filters.NativeImageProcessor;
import com.example.miodragmilosevic.nativetest.histogram.HistogramActivity;
import com.squareup.picasso.Picasso;

import java.io.File;


/**
 * Created by miodrag.milosevic on 12/11/2017.
 */

public class FilterImageActivity extends AppCompatActivity implements Contract.View {

    public static final String EXTRA_IMAGE_URI = "com.example.miodragmilosevic.nativetest.IMAGE_URI";
    private static final String TAG = "Miki";
    private ImageView mFullScreenImage;
    private Bitmap mOriginalBitmap;
    private FilterAdapter mFilterAdapter;
    private Contract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_image);
        String imagePath = getIntent().getStringExtra(EXTRA_IMAGE_URI);
        Log.i(TAG, "onCreate: imagePath " + imagePath);
        mFullScreenImage = findViewById(R.id.full_screen_image_view);
        mFullScreenImage.setOnClickListener((view) ->{
            mPresenter.onFullScreenImageClicked(imagePath);
        });

        mOriginalBitmap = BitmapFactory.decodeFile((imagePath));
        mPresenter = new FilterImagePresenter(this, new FilterImageRepository(new NativeImageProcessor()));
//        mFullScreenImage.setImageBitmap(mOriginalBitmap);
        Picasso.with(this).load(new File(imagePath)).fit().centerInside().into(mFullScreenImage);
        initFilterRecyclerView();
    }

    private void initFilterRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mFilterAdapter = new FilterAdapter();
        mFilterAdapter.setOnItemClickListener((FilterItem filterItem) -> {
            Log.i(TAG, "initFilterRecyclerView: " + filterItem.getName());
            mPresenter.onFilterItemClicked(mOriginalBitmap, filterItem);
        });
        RecyclerView reviewsRecyclerView = findViewById(R.id.recycler_filter);
        reviewsRecyclerView.setLayoutManager(layoutManager);
        reviewsRecyclerView.setAdapter(mFilterAdapter);
    }


    @Override
    public void showImage(Bitmap image) {
        mFullScreenImage.setImageBitmap(image);
    }

    @Override
    public void showHistogram(String imagePath) {
        Intent intent = new Intent(FilterImageActivity.this, HistogramActivity.class);
        intent.putExtra(HistogramActivity.EXTRA_IMAGE_URI,imagePath);
        startActivity(intent);
    }
}
