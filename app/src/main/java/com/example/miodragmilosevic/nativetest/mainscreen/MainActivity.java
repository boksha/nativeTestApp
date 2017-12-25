package com.example.miodragmilosevic.nativetest.mainscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miodragmilosevic.nativetest.scan.CustomScannerActivity;
import com.example.miodragmilosevic.nativetest.imagegallery.mvp.ImageGalleryActivity;
import com.example.miodragmilosevic.nativetest.MashData;
import com.example.miodragmilosevic.nativetest.NativeHelper;
import com.example.miodragmilosevic.nativetest.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        tv =  findViewById(R.id.sample_text);
        Button scanBtn = findViewById(R.id.scan_button);
        scanBtn.setOnClickListener((view)-> scan());
//        Log.d("Miki", calculateArea(5.5f));
        Button loadBtn = findViewById(R.id.load_images_btn);
        loadBtn.setOnClickListener((view)-> startActivity(new Intent(this,ImageGalleryActivity.class)));
//        Log.d("Miki", calculateArea(5.5f));
        tv.setText(NativeHelper.getInstance().stringFromJNI()
                + NativeHelper.getInstance().getMemberFieldFromNative(new MashData(4)));
    }

    private void scan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Miki:Scan a barcode");
        integrator.setCaptureActivity(CustomScannerActivity.class);
//        integrator.setCameraId(0);  // Use a specific camera of the device
//        integrator.setBeepEnabled(false);
//        integrator.setBarcodeImageEnabled(false);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                tv.setText("Scanresult =" + result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
