package com.example.android.customradiobutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RadioButton;

/**
 * Created by nebo-android2016 on 01/09/16.
 */
public class RadialRadioButton extends RadioButton {

    private static final int DEFAULT_MINIMUM_RADIUS_IN_DP = 6;
    private static final int DEFAULT_MINIMUM_RADIUS_SELECTOR_IN_DP = 8;
    private static final int DEFAULT_POINT_COLOR = 6;
    private static final int DEFAULT_SELECTOR_COLOR = 8;
    private static final float DEFAULT_STROKE_WIDTH_IN_DP = 4;

    //The pointRadius of the point that will be drawn in the center of the view
    int radiusPoint;

    //The colorPoint of the point
    int colorPoint;

    //The radiusPoint of the circle that will be drawn around the point when the radio button will be selected
    int radiusSelector;

    //The colorPoint of the selector
    int colorSelector;

    //Canvas dimensions
    private int heightInPx;
    private int widthInPx;


    public RadialRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RadialRadioButton, 0, 0);
        try {
            radiusPoint = a.getInteger(R.styleable.RadialRadioButton_radiusPoint, DEFAULT_MINIMUM_RADIUS_IN_DP);
            radiusSelector = a.getInteger(R.styleable.RadialRadioButton_radiusSelector, DEFAULT_MINIMUM_RADIUS_SELECTOR_IN_DP);
            colorPoint = a.getInteger(R.styleable.RadialRadioButton_colorPoint, DEFAULT_POINT_COLOR);
            colorSelector = a.getInteger(R.styleable.RadialRadioButton_colorSelector, DEFAULT_SELECTOR_COLOR);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "radiusPoint " + radiusPoint);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "radiusSelector " + radiusSelector);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "colorPoint " + colorPoint);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "colorSelector " + colorSelector);
        } finally {
            a.recycle();
        }
    }


    public int getRadiusPoint() {
        return radiusPoint;
    }

    public void setRadiusPoint(int radiusPoint) {
        this.radiusPoint = radiusPoint;
        invalidate();
        requestLayout();
    }

    public int getRadiusSelector() {
        return radiusSelector;
    }

    public void setRadiusSelector(int radiusSelector) {
        this.radiusSelector = radiusSelector;
        invalidate();
        requestLayout();
    }

    public int getColorPoint() {
        return colorPoint;
    }

    public void setColorPoint(int colorPoint) {
        this.colorPoint = colorPoint;
        invalidate();
        requestLayout();
    }

    public int getColorSelector() {
        return colorSelector;
    }

    public void setColorSelector(int colorSelector) {
        this.colorSelector = colorSelector;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas default size is 64px x 64px. To make sure our canvas is big enough, we can't use canvas.getWidth() and canvas.getHeight() method.
        //We will wait for onSizeChanged to be called to get the size of the canvas that is able to fit the view instead.

        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "width in px " + canvas.getWidth() + " height in px " + canvas.getHeight());
        Paint paint = new Paint();
        heightInPx=canvas.getHeight();
        widthInPx=canvas.getWidth();
        if (isChecked()) {
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "isChecked " + this);
            drawPoint(canvas, paint);
            drawSelector(canvas, paint);
        } else {
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "isUnchecked " + this);
            drawPoint(canvas, paint);
        }
    }


    private void drawPoint(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        colorPoint = Color.BLUE;
        paint.setColor(colorPoint);
        int radiusPointInPx = convertToPx(radiusPoint);
        canvas.drawCircle(widthInPx / 2, heightInPx / 2, radiusPointInPx, paint);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "radius in dp" + radiusPoint + " radius in px" + radiusPointInPx);
    }


    private void drawSelector(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DEFAULT_STROKE_WIDTH_IN_DP);
        colorSelector = Color.RED;
        paint.setColor(colorSelector);
        int radiusSelectorInPx = convertToPx(radiusSelector);
        canvas.drawCircle(widthInPx / 2, heightInPx / 2, radiusSelectorInPx, paint);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "radius in dp" + radiusSelector + " radius in px" + radiusSelectorInPx);
    }


    private int convertToPx(int dp) {
//        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        return dp*5;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        heightInPx=h;
        widthInPx=w;
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "old heightInPx" + oldh + "old widthInPx" + oldw);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "heightInPx" + heightInPx + " widthInPx" + widthInPx);
    }
}
