package com.example.jasper.virgielowee2016;

import android.app.Activity;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class BiertjeActivity extends Activity {

    private TextView xText, yText, zText;
    private ImageView imageView;
    private Sensor acceleroMeter;
    private Sensor magnetoMeter;
    private SensorManager sensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biertje);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fill_beer);
        imageView = (ImageView) findViewById(R.id.img_biertje);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                acceleroMeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sensorManager.registerListener(new RotationListener(), acceleroMeter, sensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        imageView.startAnimation(animation);

        xText = (TextView) findViewById(R.id.x_text);
    }

    private class RotationListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            int rotation = (int) (sensorEvent.values[0] * 10);
            xText.setText("X: " + rotation);

            Matrix matrix = new Matrix();
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
            matrix.setScale(2.5f, 2.5f, imageView.getWidth() / 2, 200);
            if (Math.abs(rotation) < 35) {
                matrix.postRotate(rotation, imageView.getWidth() / 2, 200);
            } else if (rotation < -35) {
                matrix.postRotate(rotation, imageView.getWidth() * 0.7f, 0);
            } else if (rotation > 35) {
                matrix.postRotate(rotation, imageView.getWidth() * 0.3f, 0);
            }
            imageView.setImageMatrix(matrix);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }
}
