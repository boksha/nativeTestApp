package com.example.miodragmilosevic.nativetest.imagedownloader;

import android.net.Uri;

/**
 * Created by miodrag.milosevic on 12/8/2017.
 */

public interface ImageDownloader {

    void downloadImage(String url, Callback callback);

    interface Callback{
        void onImageDownloaded(Uri uri);
        void onAllImagesDownloaded();
        void onImageDownloadFailed(String errorMsg);
    }
}
