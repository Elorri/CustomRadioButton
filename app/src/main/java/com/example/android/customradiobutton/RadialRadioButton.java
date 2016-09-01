package com.example.android.customradiobutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
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
    private static final int DEFAULT_TEXT_COLOR = Color.DKGRAY;
    private static final int DEFAULT_TEXT_SIZE = 24;
    private static final int DEFAULT_TEXT_MARGIN_TOP = 24;
    private static final Typeface DEFAULT_NORMAL_TYPEFACE = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);;


    //The radius of the point that will be drawn in the center of the view
    int radiusPoint;

    //The color of the point
    int colorPoint;

    //The radius of the circle that will be drawn around the point when the radio button will be selected
    int radiusSelector;

    //The color of the selector
    int colorSelector;

    //The text of the radiobutton if there is some
    String text;


    //Size requested by the xml layout_width and layout_height
    private int measureHeightInPx;
    private int measuredWidthInPx;


//    //Size of the rectangle in which to bigger circle (point or selector) + text (if there is some) fits = minimum size rectangle to see the drawing completely
//    private int minimumHeightInPx;
//    private int minimumWidthInPx;
//
//    //Size of the rectangle in which fit the text
//    private int textWidth;
//    private int textHeight;


    public RadialRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RadialRadioButton, 0, 0);
        try {
            radiusPoint = a.getInteger(R.styleable.RadialRadioButton_radiusPoint, DEFAULT_MINIMUM_RADIUS_IN_DP);
            radiusSelector = a.getInteger(R.styleable.RadialRadioButton_radiusSelector, DEFAULT_MINIMUM_RADIUS_SELECTOR_IN_DP);
            colorPoint = a.getInteger(R.styleable.RadialRadioButton_colorPoint, DEFAULT_POINT_COLOR);
            colorSelector = a.getInteger(R.styleable.RadialRadioButton_colorSelector, DEFAULT_SELECTOR_COLOR);
            text = a.getString(R.styleable.RadialRadioButton_text);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "radiusPoint " + radiusPoint);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "radiusSelector " + radiusSelector);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "colorPoint " + colorPoint);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "colorSelector " + colorSelector);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "text " + text);
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
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
        requestLayout();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //canvas default size is 64px x 64px. To make sure our canvas is big enough, we can't use canvas.getWidth() and canvas.getHeight() method.
        //I try waiting for onSizeChanged to be called to get the size of the canvas that is able to fit the view instead, but I don't get better result.
        //I will rely only on the onMeasureMethod

        int radiusPointInPx = (int) convertToPx(radiusPoint);
        int radiusSelectorInPx = (int) convertToPx(radiusSelector);
        int minDiameterCirclesInPx = Math.max(radiusPointInPx * 2, radiusSelectorInPx * 2);

        int centerShapeX;
        int centerYcircles = 0;
        int textHeight = 0;
        int textWidth = 0;
        int minShapeHeight = 0;


        Paint paint;
        if (text != null) {
            paint = createTextPaint();
            Rect textBounds = new Rect();
            paint.getTextBounds(text, 0, text.length(), textBounds);
            textHeight = textBounds.height();
            textWidth = textBounds.width();
            int minShapeWidth = Math.max(minDiameterCirclesInPx, textWidth);
            centerShapeX = Math.max(minShapeWidth / 2, measuredWidthInPx / 2);
            minShapeHeight = minDiameterCirclesInPx + textHeight + DEFAULT_TEXT_MARGIN_TOP;
            centerYcircles = (minDiameterCirclesInPx / 2) + ((measureHeightInPx - minShapeHeight) / 2);
        } else {
            paint = new Paint();
            centerShapeX = Math.max(minDiameterCirclesInPx / 2, measuredWidthInPx / 2);
            minShapeHeight = minDiameterCirclesInPx;
            centerYcircles = (minDiameterCirclesInPx / 2) + ((measureHeightInPx - minShapeHeight) / 2);
        }


        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "width in px " + canvas.getWidth() + " height in px " + canvas.getHeight());
        if (isChecked()) {
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "isChecked " + this);
            drawPoint(canvas, paint, centerShapeX, centerYcircles, radiusPointInPx);
            drawSelector(canvas, paint, centerShapeX, centerYcircles, radiusSelectorInPx);
            drawText(canvas, paint, centerShapeX, minDiameterCirclesInPx, textHeight, textWidth, minShapeHeight);
        } else {
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "isUnchecked " + this);
            drawPoint(canvas, paint, centerShapeX, centerYcircles, radiusPointInPx);
            drawText(canvas, paint, centerShapeX, minDiameterCirclesInPx, textHeight, textWidth, minShapeHeight);
        }
    }

    private Paint createTextPaint() {
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "Offer possibility to customise color here ");
        int colorText = DEFAULT_TEXT_COLOR;
        Paint paint = new Paint();
        paint.setColor(colorText);
        paint.setTypeface(DEFAULT_NORMAL_TYPEFACE);
        paint.setAntiAlias(true);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "Offer possibility to customise size here ");
        float textSize = DEFAULT_TEXT_SIZE;
        paint.setTextSize(textSize);
        return paint;
    }


    private void drawPoint(Canvas canvas, Paint paint, int centerShapeX, int centerYcircles, int radiusPointInPx) {
        paint.setStyle(Paint.Style.FILL);
        colorPoint = Color.BLUE;
        paint.setColor(colorPoint);
        canvas.drawCircle(centerShapeX, centerYcircles, radiusPointInPx, paint);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "radius in dp" + radiusPoint + " radius in px" + radiusPointInPx);
    }


    private void drawSelector(Canvas canvas, Paint paint, int centerShapeX, int centerYcircles, int radiusSelectorInPx) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DEFAULT_STROKE_WIDTH_IN_DP);
        colorSelector = Color.RED;
        paint.setColor(colorSelector);
        canvas.drawCircle(centerShapeX, centerYcircles, radiusSelectorInPx, paint);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "radius in dp" + radiusSelector + " radius in px" + radiusSelectorInPx);
    }

    private void drawText(Canvas canvas, Paint paint, int centerShapeX, int minDiameterCirclesInPx, int textHeight, int textWidth, int minShapeHeight) {
        if (text != null) {
            int centerYText = minDiameterCirclesInPx + textHeight + ((measureHeightInPx - minShapeHeight) / 2) + DEFAULT_TEXT_MARGIN_TOP;
            canvas.drawText(text, centerShapeX - textWidth / 2, centerYText, paint);
        }
    }


    private float convertToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredWidthInPx = getMeasuredWidth();
        measureHeightInPx = getMeasuredHeight();
        setMeasuredDimension(measuredWidthInPx, measureHeightInPx);
    }
}
