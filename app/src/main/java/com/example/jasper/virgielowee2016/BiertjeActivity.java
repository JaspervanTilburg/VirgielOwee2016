package com.example.jasper.virgielowee2016;

import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class BiertjeActivity extends AppCompatActivity implements SensorEventListener{

    private TextView xText, yText, zText;
    private float maxRotation;
    private ImageView imageView;
    private Sensor acceleroMeter;
    private Sensor magnetoMeter;
    private SensorManager sensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_biertje);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acceleroMeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, acceleroMeter, sensorManager.SENSOR_DELAY_NORMAL);

        maxRotation = 35;
        xText = (TextView) findViewById(R.id.x_text);
        /*yText = (TextView) findViewById(R.id.y_text);
        zText = (TextView) findViewById(R.id.z_text);*/
        imageView = (ImageView) findViewById(R.id.img_biertje);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        /*yText.setText("Y: " + sensorEvent.values[1]);
        zText.setText("Z: " + sensorEvent.values[2]);*/

        float[] g = new float[3];
        g = sensorEvent.values.clone();
        double norm_Of_g = Math.sqrt(g[0] * g[0] + g[1] * g[1] + g[2] * g[2]);

        g[0] = (float) (g[0] / norm_Of_g);
        g[1] = (float) (g[1] / norm_Of_g);
        g[2] = (float) (g[2] / norm_Of_g);

        float rotation = (float) Math.toDegrees(Math.atan2(g[0], g[1]));
        xText.setText("X: " + rotation);

        Matrix matrix = new Matrix();
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        int padding = (int) (100 + (maxRotation - 35) * 7.5);
        if (Math.abs(rotation) > maxRotation) {
            matrix.postRotate(rotation, imageView.getWidth(), padding);
            maxRotation = Math.abs(rotation);
        } else {
            matrix.postRotate(rotation, imageView.getWidth() / 2, padding);
        }
        imageView.setImageMatrix(matrix);
        imageView.setPadding(0, padding, 0, 0);
        System.out.println("Padding: " + padding + ", MaxRotation: " + maxRotation);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
