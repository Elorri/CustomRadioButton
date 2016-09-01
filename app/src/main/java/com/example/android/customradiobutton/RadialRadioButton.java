package com.example.android.customradiobutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

/**
 * Created by nebo-android2016 on 01/09/16.
 */
public class RadialRadioButton extends RadioButton {

    private static final int DEFAULT_MINIMUM_RADIUS = 6;
    private static final int DEFAULT_MINIMUM_RADIUS_SELECTOR = 8;

    //The radius of the point that will be drawn in the center of the view
    int radius;

    //The radius of the circle that will be drawn around the point when the radio button will be selected
    int radiusSelector;


    public RadialRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.RadialRadioButton,0, 0);
        try {
            radius = a.getInteger(R.styleable.RadialRadioButton_radius, DEFAULT_MINIMUM_RADIUS);
            radiusSelector = a.getInteger(R.styleable.RadialRadioButton_radiusSelector, DEFAULT_MINIMUM_RADIUS_SELECTOR);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"radius "+radius);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"radiusSelector "+radiusSelector);
        } finally {
            a.recycle();
        }
    }


    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
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


}
