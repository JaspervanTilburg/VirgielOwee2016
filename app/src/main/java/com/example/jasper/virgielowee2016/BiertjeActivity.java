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

public class BiertjeActivity extends Activity implements SensorEventListener{

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
        imageView.startAnimation(animation);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acceleroMeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, acceleroMeter, sensorManager.SENSOR_DELAY_NORMAL);

        xText = (TextView) findViewById(R.id.x_text);
        /*yText = (TextView) findViewById(R.id.y_text);
        zText = (TextView) findViewById(R.id.z_text);*/
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

        int rotation = (int) Math.toDegrees(Math.atan2(g[0], g[1]));
        xText.setText("X: " + rotation);

        Matrix matrix = new Matrix();
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        matrix.setScale(3, 3, imageView.getWidth() / 2, 0);
        if (Math.abs(rotation) < 35) {
            matrix.postRotate(rotation, imageView.getWidth() / 2, 0);
        } else if (rotation < -35){
            matrix.postRotate(rotation, imageView.getWidth() - 200, 0);
        } else if (rotation > 35) {
            matrix.postRotate(rotation, 200, 0);
        }
        imageView.setImageMatrix(matrix);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
