package com.example.miodragmilosevic.nativetest.scan;

import android.app.Activity;

import com.example.miodragmilosevic.nativetest.R;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

/**
 * Created by miodrag.milosevic on 11/28/2017.
 */

public class SmallCaptureActivity extends CaptureActivity {
    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.capture_small);
        return (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
    }
}
