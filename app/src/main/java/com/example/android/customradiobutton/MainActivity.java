package com.example.android.customradiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RadialRadioButton radioButton=(RadialRadioButton)findViewById(R.id.radial_radiobutton);

        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"radius "+radioButton.getRadius());
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"radiusSelector "+radioButton.getRadiusSelector());


        radioButton.setRadius(3);
        radioButton.setRadius(9);

        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"radius "+radioButton.getRadius());
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"radiusSelector "+radioButton.getRadiusSelector());
    }
}
