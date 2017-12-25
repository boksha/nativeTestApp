package com.example.miodragmilosevic.nativetest.imagegallery.mvp;

import com.example.miodragmilosevic.nativetest.imagedownloader.ImageDownloader;
import com.example.miodragmilosevic.nativetest.imagegallery.ImageModel;

import java.util.List;

/**
 * Created by miodrag.milosevic on 12/6/2017.
 */

final class Contract {

    public interface View {

        void showReadRationale();

        void showWriteRationale();

        void showImages(List<ImageModel> images);

        void addImages(List<ImageModel> images);

        void showPermissionDeniedToast();

        void showFullImage(String url);
    }

    public interface Presenter {

        void onViewAttached();

        void onViewDetached();

        void onActivityCreated();

        void onActivityDestroyed();

        void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults);

        void onReadRationaleButtonClick();

        void onWriteRationaleButtonClick();

        void onImageClicked(ImageModel image);

        void omButtonDownloadImageClick(String downloadUrl);
    }


    public interface Repository {

        List<String> getImagePaths();

        void downLoadImage(String url, ImageDownloader.Callback callback);
    }
}
