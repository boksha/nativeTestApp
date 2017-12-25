package com.example.miodragmilosevic.nativetest.imagedownloader;

import android.app.DownloadManager;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by miodrag.milosevic on 12/8/2017.
 */

public class DownloadManagerImageDownloader implements LifecycleObserver, ImageDownloader {

    private static final String TAG = "Miki";
    private Context mContext;
    private ArrayList<Long> mDownloadIds = new ArrayList<>();
    private IntentFilter mDownloadIntentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
    private WeakReference<Callback> mDownloadFinishedCallback;

    public DownloadManagerImageDownloader(Context context) {
        mContext = context;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void registerDownloadReceiver() {
        Log.i("Miki", "registerDownloadReceiver: ");
        mContext.registerReceiver(downloadReceiver, mDownloadIntentFilter);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void unregisterDownloadReceiver() {
        Log.i("Miki", "unregisterDownloadReceiver: ");
        mContext.unregisterReceiver(downloadReceiver);
    }

    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {

            // get the refid from the download manager
            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            Log.i(TAG, "onReceive: refId = " + referenceId);
            if (mDownloadIds.contains(referenceId)) {
                mDownloadIds.remove(referenceId);
                if (mDownloadIds.isEmpty()) {
                    if (mDownloadFinishedCallback.get() != null)
                        mDownloadFinishedCallback.get().onAllImagesDownloaded();
                }
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(referenceId);
                Cursor cursor = ((DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE)).query(query);
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    int status = cursor.getInt(columnIndex);
                    int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
                    int reason = cursor.getInt(columnReason);

                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        Uri fileUri = Uri.parse(cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)));
                        Log.i(TAG, "onReceive: succesful " + fileUri);
                        if (mDownloadFinishedCallback.get() != null){
                            mDownloadFinishedCallback.get().onImageDownloaded(fileUri);
                        }
                    } else if (status == DownloadManager.STATUS_FAILED) {
                        Log.i(TAG, "failed");
                        if (mDownloadFinishedCallback.get() != null){
                            mDownloadFinishedCallback.get().onImageDownloadFailed("Download Failed");
                        }
                    }
                }
                cursor.close();
            }

        }
    };

    @Override
    public void downloadImage(String url, Callback resultCallback) {
        DownloadManager mgr = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        String imageName = extractNameFromPath(url);
        Uri downloadUri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle("Downloading " + imageName)
                .setDescription("Something useful. No, really.")
                .setDestinationInExternalPublicDir("/NativeTest", imageName)
                .allowScanningByMediaScanner();

        long refid = mgr.enqueue(request);
        Log.i(TAG, "downloadFile: " + refid + " downloadUri" + downloadUri);
        mDownloadIds.add(refid);
        mDownloadFinishedCallback = new WeakReference<>(resultCallback);
    }


    private String extractNameFromPath(String path) {
        return path.substring(path.lastIndexOf("/") + 1);
    }

}
