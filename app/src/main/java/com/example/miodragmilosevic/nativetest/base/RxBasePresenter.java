package com.example.miodragmilosevic.nativetest.base;

import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by miodrag.milosevic on 12/13/2017.
 */

public class RxBasePresenter<T> implements BasePresenter<T> {

    private static final String TAG = "Miki";

    protected CompositeDisposable disposableList = new CompositeDisposable();

    @Override
    public void onViewAttached(T view) {

    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onActivityDestroyed() {
        if (disposableList != null) {
            Log.i(TAG, "onDestroy: dispose");
            disposableList.clear();
        }
    }
}
