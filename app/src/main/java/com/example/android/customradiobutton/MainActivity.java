package com.example.android.customradiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//
//        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"radius1 "+radioButton.getRadiusPoint());
//        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"radiusSelector1 "+radioButton.getRadiusSelector());
//
//        radioButton.setRadiusPoint(3);
//        radioButton.setRadiusPoint(9);
//
//        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"radius2 "+radioButton.getRadiusPoint());
//        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"radiusSelector2 "+radioButton.getRadiusSelector());

        RadialRadioButton radioButton1=(RadialRadioButton)findViewById(R.id.radial_radiobutton1);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"radiusSelector1 isChecked "+radioButton1.isChecked()+" "+radioButton1);

        RadialRadioButton radioButton2=(RadialRadioButton)findViewById(R.id.radial_radiobutton2);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"radiusSelector2 isChecked "+radioButton2.isChecked()+" "+radioButton2);
    }
}
