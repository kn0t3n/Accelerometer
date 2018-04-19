package com.example.leon.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView xText, yText, zText, xMaxText, yMaxText, zMaxText;
    private Sensor accelerometer;
    private SensorManager sensorManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        xText = findViewById(R.id.xText);
        yText = findViewById(R.id.yText);
        zText = findViewById(R.id.zText);

        xMaxText = findViewById(R.id.xMaxText);
        yMaxText = findViewById(R.id.yMaxText);
        zMaxText = findViewById(R.id.zMaxText);

        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    float now = SensorManager.GRAVITY_EARTH;
    float last = SensorManager.GRAVITY_EARTH;


    @Override
    public void onSensorChanged(SensorEvent event) {
        xText.setText("X: " + event.values[0]);
        yText.setText("Y: " + event.values[1]);
        zText.setText("Z: " + event.values[2]);

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        last = now;
        now = (float) Math.sqrt(x * x + y * y + z * z);
        float grenzWert = now - last;

        if(grenzWert > 12){
            Toast.makeText(this, "Schüttel mich nicht", Toast.LENGTH_LONG).show();
            xMaxText.setText("X-Max: " + event.values[0]);
            yMaxText.setText("Y-Max: " + event.values[1]);
            zMaxText.setText("Z-Max: " + event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
