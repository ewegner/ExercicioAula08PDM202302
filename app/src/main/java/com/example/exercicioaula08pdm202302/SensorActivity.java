package com.example.exercicioaula08pdm202302;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private TextView xViewA = null;
    private TextView yViewA = null;
    private TextView zViewA = null;
    private TextView xViewO = null;
    private TextView yViewO = null;
    private TextView zViewO = null;
    private TextView xViewL = null;
    private TextView xViewP = null;

    SensorManager senmgr;
    Sensor sen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        xViewA = findViewById(R.id.xViewA);
        yViewA = findViewById(R.id.yViewA);
        zViewA = findViewById(R.id.zViewA);
        xViewO = findViewById(R.id.xViewO);
        yViewO = findViewById(R.id.yViewO);
        zViewO = findViewById(R.id.zViewO);
        xViewL = findViewById(R.id.xViewL);
        xViewP = findViewById(R.id.xViewP);

        senmgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        senmgr.registerListener(this, senmgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        senmgr.registerListener(this, senmgr.getDefaultSensor(Sensor.TYPE_PROXIMITY),
                SensorManager.SENSOR_DELAY_NORMAL);
        senmgr.registerListener(this, senmgr.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_NORMAL);
        senmgr.registerListener(this, senmgr.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            xViewA.setText("Accel X: " + sensorEvent.values[0]);
            yViewA.setText("Accel Y: " + sensorEvent.values[1]);
            zViewA.setText("Accel Z: " + sensorEvent.values[2]);
        }

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            xViewO.setText("Orientation X: " + sensorEvent.values[0]);
            yViewO.setText("Orientation Y: " + sensorEvent.values[1]);
            zViewO.setText("Orientation Z: " + sensorEvent.values[2]);
        }

        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            xViewL.setText("Light: " + sensorEvent.values[0]);
        }

        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            xViewP.setText("Proximity: " + sensorEvent.values[0]);
        }

        int orientation = getResources().getConfiguration().orientation;
        LinearLayout layout = findViewById(R.id.layoutSensor);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layout.setBackgroundColor(Color.parseColor("#00FF00"));
        } else {
            layout.setBackgroundColor(Color.parseColor("#0000FF"));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}