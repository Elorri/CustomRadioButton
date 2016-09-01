package com.example.android.customradiobutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

/**
 * Created by nebo-android2016 on 01/09/16.
 */
public class RadialRadioButton extends RadioButton {

    private static final int DEFAULT_MINIMUM_RADIUS = 6;
    private static final int DEFAULT_MINIMUM_RADIUS_SELECTOR = 8;
    private static final int DEFAULT_POINT_COLOR = 6;
    private static final int DEFAULT_SELECTOR_COLOR = 8;

    //The pointRadius of the point that will be drawn in the center of the view
    int radiusPoint;

    //The colorPoint of the point
    int colorPoint;

    //The radiusPoint of the circle that will be drawn around the point when the radio button will be selected
    int radiusSelector;

    //The colorPoint of the selector
    int colorSelector;





    public RadialRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.RadialRadioButton,0, 0);
        try {
            radiusPoint = a.getInteger(R.styleable.RadialRadioButton_radiusPoint, DEFAULT_MINIMUM_RADIUS);
            radiusSelector = a.getInteger(R.styleable.RadialRadioButton_radiusSelector, DEFAULT_MINIMUM_RADIUS_SELECTOR);
            colorPoint = a.getInteger(R.styleable.RadialRadioButton_colorPoint, DEFAULT_POINT_COLOR);
            colorSelector = a.getInteger(R.styleable.RadialRadioButton_colorSelector, DEFAULT_SELECTOR_COLOR);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"radiusPoint "+ radiusPoint);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"radiusSelector "+radiusSelector);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"colorPoint "+ colorPoint);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"colorSelector "+colorSelector);
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
        super.onDraw(canvas);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"in onDraw ");

        if(isChecked()){
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"isChecked "+this);
        }else{
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"isUnchecked "+this);
        }
    }


}
