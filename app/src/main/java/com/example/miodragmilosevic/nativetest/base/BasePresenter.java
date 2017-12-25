package com.example.miodragmilosevic.nativetest.base;

/**
 * Created by miodrag.milosevic on 12/13/2017.
 */

public interface BasePresenter<T> {
    void onViewAttached(T view);

    void onViewDetached();

    void onActivityCreated();

    void onActivityDestroyed();
}
