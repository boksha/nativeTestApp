package com.example.miodragmilosevic.nativetest.imagegallery.mvp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miodragmilosevic.nativetest.filterimage.mvp.FilterImageActivity;
import com.example.miodragmilosevic.nativetest.R;
import com.example.miodragmilosevic.nativetest.imagedownloader.DownloadManagerImageDownloader;
import com.example.miodragmilosevic.nativetest.imagedownloader.ImageDownloader;
import com.example.miodragmilosevic.nativetest.imagegallery.ImageGridAdapter;
import com.example.miodragmilosevic.nativetest.imagegallery.ImageModel;
import com.example.miodragmilosevic.nativetest.util.PermissionHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miodrag.milosevic on 12/6/2017.
 */

public class ImageGalleryActivity extends AppCompatActivity implements Contract.View {

    private static final String TAG = "Miki";
    private ImageGridAdapter imageGridAdapter;
    private Contract.Presenter mPresenter;
    private RecyclerView mGridView;
    private ArrayList<ImageModel> mImages;
    private EditText editTextDownloadImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_galery);
        ImageDownloader imageDownloader = new DownloadManagerImageDownloader(this);
        ImageRepository imageRepository = new ImageRepository(getApplicationContext(),imageDownloader);
        getLifecycle().addObserver((DownloadManagerImageDownloader)imageDownloader);
        mPresenter = new ImagePresenter(this, imageRepository,new PermissionHelper(this));
        mGridView = findViewById(R.id.gridView1);
        imageGridAdapter = new ImageGridAdapter(this);
        imageGridAdapter.setOnItemClickListener((image -> {
            mPresenter.onImageClicked(image);
            Log.i(TAG, "onItemClick: filterImage" + image);
        }));
        mGridView.setAdapter(imageGridAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

        mGridView.setLayoutManager(gridLayoutManager);
        editTextDownloadImage = findViewById(R.id.edit_text_download_image);
        final Button buttonDownload = findViewById(R.id.button_download);
        buttonDownload.setOnClickListener(view -> {
            final String downloadUrl = editTextDownloadImage.getText().toString();
            if (!TextUtils.isEmpty(downloadUrl)) {
                mPresenter.omButtonDownloadImageClick(downloadUrl);
            }

        });
        mPresenter.onActivityCreated();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onActivityDestroyed();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        mPresenter.onRequestPermissionResult(requestCode, permissions, grantResults);
    }


    @Override
    public void showReadRationale() {
        showDialog(getString(R.string.read_storage_rationale),(dialog,which) -> mPresenter.onReadRationaleButtonClick());
    }

    @Override
    public void showWriteRationale() {
        showDialog(getString(R.string.write_storage_rationale), (dialog,which) -> mPresenter.onWriteRationaleButtonClick());
    }

    @Override
    public void showImages(List<ImageModel> images) {
        imageGridAdapter.setData(images);
    }

    @Override
    public void addImages(List<ImageModel> images) {
        editTextDownloadImage.setText("");
        imageGridAdapter.addData(images );
    }

    @Override
    public void showPermissionDeniedToast() {
        Toast.makeText(this, "Permission Denied",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFullImage(String url) {
        Intent intent = new Intent(this, FilterImageActivity.class);
        intent.putExtra(FilterImageActivity.EXTRA_IMAGE_URI,url);
        startActivity(intent);
    }

    private void showDialog(final String msg, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
               listener
        );
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }
}
