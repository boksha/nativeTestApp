package com.example.miodragmilosevic.nativetest.opencv;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.TextView;

import com.example.miodragmilosevic.nativetest.NativeHelper;
import com.example.miodragmilosevic.nativetest.R;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by miodrag.milosevic on 1/11/2018.
 */

    public class OpenCvActivity extends Activity implements CameraBridgeViewBase.CvCameraViewListener2 {

        private static final String TAG = "Miki";

        // Used to load the 'native-lib' library on application startup.


        private CameraBridgeViewBase mOpenCvCameraView;

        private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
            @Override
            public void onManagerConnected(int status) {
                switch (status) {
                    case LoaderCallbackInterface.SUCCESS:
                    {
                        Log.i(TAG, "OpenCV loaded successfully");
                        mOpenCvCameraView.enableView();
                    } break;
                    default:
                    {
                        super.onManagerConnected(status);
                    } break;
                }
            }
        };
        private Mat mRgb;
        private Mat mGray;
        private Mat mKenny;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_open_cv);

            // Example of a call to a native method
            TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
            mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.camera_surface_view);

            mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);

            mOpenCvCameraView.setCvCameraViewListener(this);
        }

        @Override
        public void onPause()
        {
            super.onPause();
            if (mOpenCvCameraView != null)
                mOpenCvCameraView.disableView();
        }

        @Override
        public void onResume()
        {
            super.onResume();
            if (!OpenCVLoader.initDebug()) {
                Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
                OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
            } else {
                Log.d(TAG, "OpenCV library found inside package. Using it!");
                mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
            }
        }


        @Override
        public void onCameraViewStarted(int width, int height) {

            mRgb = new Mat(width,height, CvType.CV_8UC4);
            mGray = new Mat(width,height, CvType.CV_8UC1);
            mKenny = new Mat(width,height, CvType.CV_8UC4);
        }

        @Override
        public void onCameraViewStopped() {
            mRgb.release();
            mGray.release();
            mKenny.release();
        }

        @Override
        public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
            mRgb = inputFrame.rgba();
//        NativeHelper.findFeatures(mGray.getNativeObjAddr(), mRgb.getNativeObjAddr());
//                Imgproc.cvtColor(mRgb,mGray,Imgproc.COLOR_RGBA2GRAY);
//        Imgproc.Canny(mGray,mKenny,50,150);
//            NativeHelper.cartoonify(mRgb.getNativeObjAddr(), mGray.getNativeObjAddr());
        NativeHelper.detectEdges(mRgb.getNativeObjAddr(),mKenny.getNativeObjAddr());
        return mKenny;
//            return mRgb;
        }
    }

