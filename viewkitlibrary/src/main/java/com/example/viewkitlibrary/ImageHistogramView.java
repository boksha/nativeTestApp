package com.example.viewkitlibrary;

/**
 * Created by miodrag.milosevic on 12/25/2017.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ImageHistogramView extends View {

    private static final String TAG = "Miki";
    private static final int MAX_RGB_VALUE = 255;
    private static final int BLACK_STROKE_WIDTH = 10;
    private static final int YOFFSET = 5;
    private static final int XOFFSET = 5;
    private static final int X_START = BLACK_STROKE_WIDTH + XOFFSET;
    public static final int BLACK_TEXT_SIZE = 24;
    private int[] mRedHistoArray = new int[MAX_RGB_VALUE + 1];
    private int[] mGreenHistoArray = new int[MAX_RGB_VALUE + 1];
    private int[] mBlueHistoArray = new int[MAX_RGB_VALUE + 1];
    private int maxValueRed;
    private int maxValueBlue;
    private int maxValueGreen;
    private Paint mRedPaint;
    private Paint mGreenPaint;
    private Paint mBluePaint;
    private Paint mBlackPaint;
    private Paint mTextPaint;
    private float ybStart;//y start of blue histogram
    private float ygStart;
    private float yrStart;
    private float coeff;//coeff in histo calculation, which is max value in pixels for given graph
    private int leftx;
    private int rightx;
    private int topy;
    private int bottomy;
    private int scaleX;//for calculation of scaling width in order to show everything on the screen


    public ImageHistogramView(Context context) {
        this(context, null);
    }

    public ImageHistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int maxTextWidth = Math.round(mTextPaint.measureText(String.valueOf(MAX_RGB_VALUE)));
        int desiredWidth = maxTextWidth + X_START + MAX_RGB_VALUE + 1 + getPaddingLeft() + getPaddingRight();
        int desiredHeight = maxTextWidth + X_START + MAX_RGB_VALUE + 1 + getPaddingTop() + getPaddingBottom();//just make it square
        setMeasuredDimension(resolveSize(desiredWidth, widthMeasureSpec),
                resolveSize(desiredHeight, heightMeasureSpec));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //calculate here coordinates for drawing!!! here is where width and height are valid!!!!
        // Account for padding
        float xpad = (float) (getPaddingLeft() + getPaddingRight());
        float ypad = (float) (getPaddingTop() + getPaddingBottom());

        float viewWidth = (float) w - xpad;
        float viewheight = (float) h - ypad;
        leftx = getPaddingLeft();
        rightx = getWidth() - getPaddingRight();
        topy = getPaddingTop();
        bottomy = getHeight() - getPaddingBottom();
        ybStart = viewheight - BLACK_STROKE_WIDTH - YOFFSET + topy;// getHeight()/2;
        ygStart = (viewheight * 2f / 3f) - BLACK_STROKE_WIDTH - YOFFSET + topy;
        yrStart = (viewheight / 3f) - BLACK_STROKE_WIDTH - YOFFSET + topy;
        coeff = (viewheight / 3f) - BLACK_STROKE_WIDTH - YOFFSET;

        scaleX = (int) ((viewWidth - X_START) / (MAX_RGB_VALUE + 1));//3 because 1 pixel for draw and 2 for empty for visibility
        Log.i(TAG, "onDraw: scaleX " + scaleX);
        if (scaleX < 1) scaleX = 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw: width " + getWidth() + " height " + getHeight());
        Log.i(TAG, "onDraw: ybStart " + ybStart + " ygStart " + ygStart + " yrStart " + yrStart);
        // black frame lines
        canvas.drawLine(leftx, bottomy - BLACK_STROKE_WIDTH / 2 - YOFFSET, rightx,
                bottomy - BLACK_STROKE_WIDTH / 2 - YOFFSET, mBlackPaint);
        canvas.drawLine(BLACK_STROKE_WIDTH / 2 + XOFFSET + leftx, bottomy,
                BLACK_STROKE_WIDTH / 2 + XOFFSET + leftx, topy, mBlackPaint);
        //maxValues line
        canvas.drawLine(XOFFSET + leftx, ygStart + BLACK_STROKE_WIDTH + YOFFSET,
                XOFFSET + 2 * BLACK_STROKE_WIDTH + leftx, ygStart + BLACK_STROKE_WIDTH + YOFFSET, mBlackPaint);
        canvas.drawText("maxBlue " + maxValueBlue, XOFFSET + 4 * BLACK_STROKE_WIDTH + leftx,
                ygStart + 2 * BLACK_STROKE_WIDTH + YOFFSET, mTextPaint);

        canvas.drawLine(XOFFSET + leftx, yrStart + BLACK_STROKE_WIDTH + YOFFSET,
                XOFFSET + 2 * BLACK_STROKE_WIDTH + leftx, yrStart + BLACK_STROKE_WIDTH + YOFFSET, mBlackPaint);
        canvas.drawText("maxGreen " + maxValueGreen, XOFFSET + 4 * BLACK_STROKE_WIDTH + leftx,
                yrStart + 2 * BLACK_STROKE_WIDTH + YOFFSET, mTextPaint);
        canvas.drawLine(XOFFSET + leftx, BLACK_STROKE_WIDTH + YOFFSET + topy,
                XOFFSET + 2 * BLACK_STROKE_WIDTH + leftx, BLACK_STROKE_WIDTH + YOFFSET + topy, mBlackPaint);
        canvas.drawText("maxRed " + maxValueRed, XOFFSET + 4 * BLACK_STROKE_WIDTH + leftx,
                2 * BLACK_STROKE_WIDTH + YOFFSET + topy, mTextPaint);

        for (int i = 0; i < MAX_RGB_VALUE + 1; i++) {
            int xcoord = X_START + leftx + i * scaleX;
            if (i > 0 && i % 50 == 0) {
                //50 indication
                canvas.drawLine(xcoord, ybStart - BLACK_STROKE_WIDTH / 2, xcoord, ybStart + BLACK_STROKE_WIDTH + YOFFSET, mBlackPaint);
                canvas.drawText("" + i, xcoord, ybStart - BLACK_STROKE_WIDTH, mTextPaint);
            }
            if (mRedHistoArray[i] > 0) {
                canvas.drawLine(xcoord, yrStart, xcoord, yrStart - (coeff * mRedHistoArray[i] / maxValueRed), mRedPaint);
            }
            if (mGreenHistoArray[i] > 0) {
                canvas.drawLine(xcoord, ygStart, xcoord, ygStart - (coeff * mGreenHistoArray[i] / maxValueGreen), mGreenPaint);
            }
            if (mBlueHistoArray[i] > 0) {
                canvas.drawLine(xcoord, ybStart, xcoord, ybStart - (coeff * mBlueHistoArray[i] / maxValueBlue), mBluePaint);
            }
        }
    }

    public void setBitmapArray(int[] bitmapArray) {
        calculateHistoArrays(bitmapArray);
        requestLayout();
        invalidate();
    }

    private void calculateHistoArrays(int[] bitmapArray) {
        int r, g, b;
        for (int pixel : bitmapArray) {
            r = Color.red(pixel);
            g = Color.green(pixel);
            b = Color.blue(pixel);
            mRedHistoArray[r]++;
            mGreenHistoArray[g]++;
            mBlueHistoArray[b]++;
            if (mRedHistoArray[r] > maxValueRed) maxValueRed = mRedHistoArray[r];
            if (mGreenHistoArray[g] > maxValueGreen) maxValueGreen = mGreenHistoArray[g];
            if (mBlueHistoArray[b] > maxValueBlue) maxValueBlue = mBlueHistoArray[b];
        }
        Log.i(TAG, "calculateHistoArrays: " + mRedHistoArray + " maxB " + maxValueBlue
                + " maxr " + maxValueRed + " maxg " + maxValueGreen);
    }

    private void initPaint() {
        mRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRedPaint.setColor(Color.RED);
        mRedPaint.setStrokeWidth(0);

        mGreenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGreenPaint.setColor(Color.GREEN);
        mGreenPaint.setStrokeWidth(0);

        mBluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBluePaint.setStyle(Paint.Style.STROKE);
        mBluePaint.setColor(Color.BLUE);//just testing!
        mBluePaint.setAntiAlias(false);
        mBluePaint.setStrokeWidth(1f);

        mBlackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBlackPaint.setColor(Color.BLACK);
        mBlackPaint.setStrokeWidth(BLACK_STROKE_WIDTH);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStrokeWidth(BLACK_STROKE_WIDTH);
        mTextPaint.setTextSize(BLACK_TEXT_SIZE);

    }
}
