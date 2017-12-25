package com.example.miodragmilosevic.nativetest.imagegallery.mvp;

import android.net.Uri;

import com.example.miodragmilosevic.nativetest.imagedownloader.ImageDownloader;
import com.example.miodragmilosevic.nativetest.imagegallery.ImageModel;
import com.example.miodragmilosevic.nativetest.util.PermissionHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miodrag.milosevic on 12/6/2017.
 */

public class ImagePresenter implements Contract.Presenter {

    private String mDownLoadUrl;
    private PermissionHelper mPermissionHelper;
    private Contract.Repository mImageRepository;

    private Contract.View mView;
    private ImageDownloader.Callback mDownloadFinishedCallback = new ImageDownloader.Callback() {
        @Override
        public void onImageDownloaded(Uri uri) {
            String path = uri.getPath();
            ImageModel image = new ImageModel(path ,extractNameFromPath(path));
            ArrayList<ImageModel> list = new ArrayList<>();
            list.add(image);
            mView.addImages(list);
        }

        @Override
        public void onAllImagesDownloaded() {

        }

        @Override
        public void onImageDownloadFailed(String errorMsg) {

        }
    };
    private PermissionHelper.PermissionListener mPermissionListener = new PermissionHelper.PermissionListener() {

        @Override
        public void onPermissionGranted(boolean isGranted, String permission) {
            if (permission == PermissionHelper.PERMISSION_READ_STORAGE) {
                if (isGranted) {
                    // do your stuff
                    OnLoadImageModel();
                } else {
                    mView.showPermissionDeniedToast();
                }
            } else if(permission == PermissionHelper.PERMISSION_WRITE_STORAGE){
                if (isGranted){
                    mImageRepository.downLoadImage(mDownLoadUrl, mDownloadFinishedCallback);
                } else {
                    mView.showPermissionDeniedToast();
                }
            }
        }

        @Override
        public void onShowRationale(String permission) {
            if (permission == PermissionHelper.PERMISSION_READ_STORAGE) {
                mView.showReadRationale();
            } else if(permission == PermissionHelper.PERMISSION_WRITE_STORAGE){
                mView.showWriteRationale();
            }
        }
    };


    public ImagePresenter(Contract.View view, ImageRepository imageRepository, PermissionHelper permissionHelper) {
        mPermissionHelper = permissionHelper;
        mImageRepository = imageRepository;
        mView = view;
    }

    @Override
    public void onViewAttached() {

    }

    @Override
    public void onViewDetached() {
    }

    @Override
    public void onActivityDestroyed() {
        mPermissionHelper.removeListener(mPermissionListener);

    }

    @Override
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        mPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onReadRationaleButtonClick() {
        mPermissionHelper.askSystemForPermission(PermissionHelper.PERMISSIONS_REQUEST_READ_STORAGE);
    }

    @Override
    public void onWriteRationaleButtonClick() {
        mPermissionHelper.askSystemForPermission(PermissionHelper.PERMISSIONS_REQUEST_WRITE_STORAGE);
    }

    @Override
    public void onImageClicked(ImageModel image) {
        mView.showFullImage(image.getUrl());
    }

    @Override
    public void omButtonDownloadImageClick(String downloadUrl) {
        mDownLoadUrl = downloadUrl;
        mPermissionHelper.checkPermission(PermissionHelper.PERMISSIONS_REQUEST_WRITE_STORAGE, mPermissionListener);
    }

    @Override
    public void onActivityCreated() {
        mPermissionHelper.checkPermission(PermissionHelper.PERMISSIONS_REQUEST_READ_STORAGE, mPermissionListener);
    }

    private void OnLoadImageModel() {
        List<String> paths = mImageRepository.getImagePaths();
        List<ImageModel> images = createImageModel(paths);
        mView.showImages(images);
    }

    private List<ImageModel> createImageModel(List<String> paths) {
        List<ImageModel> result = new ArrayList<>();
        for (String path : paths){
            ImageModel model = new ImageModel(path,extractNameFromPath(path));
            result.add(model);
        }
        return result;
    }

    private String extractNameFromPath(String path){
        return path.substring(path.lastIndexOf("/")+1);
    }

}
