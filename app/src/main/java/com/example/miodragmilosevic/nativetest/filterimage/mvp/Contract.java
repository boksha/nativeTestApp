package com.example.miodragmilosevic.nativetest.filterimage.mvp;

import android.graphics.Bitmap;

import com.example.miodragmilosevic.nativetest.base.BasePresenter;
import com.example.miodragmilosevic.nativetest.filterimage.FilterItem;
import com.example.miodragmilosevic.nativetest.filters.ImageProcessor;

import io.reactivex.Single;

/**
 * Created by miodrag.milosevic on 12/6/2017.
 */

public final class Contract {

    public interface View {

        void showImage(Bitmap image);

        void showHistogram(String imagePath);

    }

    public interface Presenter extends BasePresenter<View> {

        void onFilterItemClicked(Bitmap originalBitmap, FilterItem item);

        void onFullScreenImageClicked(String imagePath);
    }


    public interface Repository {

        Single<Bitmap> applyFilter(Bitmap originalBitmap, @ImageProcessor.Type int type);

    }
}
