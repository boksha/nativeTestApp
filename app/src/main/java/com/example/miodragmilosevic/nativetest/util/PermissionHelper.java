package com.example.miodragmilosevic.nativetest.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by miodrag.milosevic on 12/6/2017.
 */

public class PermissionHelper {

    public static final int PERMISSIONS_REQUEST_WRITE_STORAGE = 245;
    public static final int PERMISSIONS_REQUEST_READ_STORAGE = 123;
    public static String PERMISSION_READ_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static String PERMISSION_WRITE_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    private final Activity mContext;
    private Map<String, PermissionListener> mPermissionListeners = new HashMap<>();
    private static SparseArray<String> sPermissionList = new SparseArray<>();

    static {
        sPermissionList.put(PERMISSIONS_REQUEST_READ_STORAGE,PERMISSION_READ_STORAGE);
        sPermissionList.put(PERMISSIONS_REQUEST_WRITE_STORAGE,PERMISSION_WRITE_STORAGE);
    }

    public PermissionHelper(Activity context ) {
        mContext  = context;
    }

    public void checkPermission(int permissionRequestCode, PermissionListener listener) {
        String permission = sPermissionList.get(permissionRequestCode);
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (permission != null) {
                if (ContextCompat.checkSelfPermission(mContext,
                        permission) != PackageManager.PERMISSION_GRANTED) {
                    mPermissionListeners.put(permission, listener);
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            mContext,
                            permission)) {
                        listener.onShowRationale(permission);
                    } else {
                        askSystemForPermission(permissionRequestCode);
                    }
                } else {
                    listener.onPermissionGranted(true,permission);
                }
            } else {
                throw new IllegalArgumentException("Permission code undefined");
            }
        } else {
            listener.onPermissionGranted(true, permission);
        }
    }

    public void removeListener(PermissionListener listener){
        boolean result = mPermissionListeners.values().remove(listener);
        Log.i("Miki", "removeListener: " + result);
    }

    public void askSystemForPermission(int permissionRequestCode) {
        String permission = sPermissionList.get(permissionRequestCode);
        ActivityCompat.requestPermissions(mContext, new String[]{permission}, permissionRequestCode);
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        if (sPermissionList.get(requestCode)!= null){
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int result = grantResults[i];
                    onPermissionResponse(requestCode,permission, result);
                }
            }
        }

    private void onPermissionResponse(int requestCode, String permission, int result) {
        PermissionListener listener = mPermissionListeners.get(permission);
        if (listener != null) {
            if (result == PackageManager.PERMISSION_GRANTED) {
                listener.onPermissionGranted(true, permission);
            } else {
                listener.onPermissionGranted(false, permission);
                }
            mPermissionListeners.remove(permission);
        }
    }

    public interface PermissionListener {
        void onPermissionGranted(boolean isGranted, String permission);
        void onShowRationale(String permission);
    }
}
