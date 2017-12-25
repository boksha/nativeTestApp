package com.example.miodragmilosevic.nativetest.filterimage.mvp;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.miodragmilosevic.nativetest.base.RxBasePresenter;
import com.example.miodragmilosevic.nativetest.filterimage.FilterItem;
import com.example.miodragmilosevic.nativetest.filters.ImageProcessor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by miodrag.milosevic on 12/13/2017.
 */

public class FilterImagePresenter extends RxBasePresenter<Contract.View> implements Contract.Presenter {

    public static final String TAG = "Miki";
    private Contract.View mView;
    private Contract.Repository mRepository;

    public FilterImagePresenter(Contract.View view, Contract.Repository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void onFilterItemClicked(Bitmap originalBitmap, FilterItem item) {
        getFilteredBitmap(originalBitmap,item.getType());
    }

    @Override
    public void onFullScreenImageClicked(String imagePath) {
        mView.showHistogram(imagePath);
    }

    private void getFilteredBitmap(Bitmap originalBitmap,@ImageProcessor.Type int filterItemType) {
        disposableList.clear();
        disposableList.add(mRepository.applyFilter(originalBitmap, filterItemType)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Bitmap>() {


                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        Log.i(TAG, "getFilteredBitmap onNext: " + bitmap + " " + Thread.currentThread().getName());
                        mView.showImage(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "getFilteredBitmap onError: " + e.getMessage());
                    }

                }));

    }
}
