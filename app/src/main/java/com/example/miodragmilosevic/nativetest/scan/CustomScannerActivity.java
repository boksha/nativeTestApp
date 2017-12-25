package com.example.miodragmilosevic.nativetest.scan;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.miodragmilosevic.nativetest.R;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

/**
 * Created by miodrag.milosevic on 11/29/2017.
 */

//public class CustomScannerActivity extends Activity  {
//
//    private CaptureManager capture;
//    private DecoratedBarcodeView barcodeScannerView;
//    private Button switchFlashlightButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_custom_scanner);
//
//        barcodeScannerView = findViewById(R.id.zxing_barcode_scanner_custom);
////        barcodeScannerView.setTorchListener(this);
//
//
//        // if the device does not have flashlight in its camera,
////        // then remove the switch flashlight button...
////        if (!hasFlash()) {
////            switchFlashlightButton.setVisibility(View.GONE);
////        }
//
//        capture = new CaptureManager(this, barcodeScannerView);
//        capture.initializeFromIntent(getIntent(), savedInstanceState);
//        capture.decode();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        capture.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        capture.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        capture.onDestroy();
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        capture.onSaveInstanceState(outState);
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
//    }
//
//}


public class CustomScannerActivity extends Activity {
    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_custom);
        barcodeScannerView = findViewById(R.id.zxing_barcode_scanner_custom);
        Log.i("Miki", "onCreate: " + barcodeScannerView);
//        barcodeScannerView = initializeContent();

        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
