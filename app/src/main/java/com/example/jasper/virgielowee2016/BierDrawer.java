package com.example.jasper.virgielowee2016;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Jasper on 26-6-2016.
 */
public class BierDrawer extends View implements SensorEventListener {

    public static final int START_Y = 0;
    public static final int START_X_CATCHER = 200;

    private int CANVAS_WIDTH;
    private int CANVAS_HEIGHT;
    private int chance;
    private int counter;
    private int speed;
    private int score;
    private ArrayList<Pint> pints;
    private Vangnet vangnet;
    private Random r;

    public BierDrawer(Context context) {
        super(context);
        initialize();
    }

    public BierDrawer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize();
    }

    public BierDrawer(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        initialize();
    }

    public void initialize() {
        SensorManager sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor acceleroMeter = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorManager.registerListener(this, acceleroMeter, SensorManager.SENSOR_DELAY_FASTEST);

        pints = new ArrayList<>();
        Drawable d = getResources().getDrawable(R.drawable.bar);
        vangnet = new Vangnet(START_X_CATCHER, d);
        r = new Random();
        counter = 0;
        chance = 80;
        speed = 5;
        score = 0;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        CANVAS_WIDTH = canvas.getWidth();
        CANVAS_HEIGHT = canvas.getHeight();

        for (Pint p : pints) {
            p.draw(canvas);
        }

        vangnet.draw(canvas);

        checkNewPint();
        incrValues();
        if (detectCollision()) {
            invalidate();
        } else {
            gameOver();
        }
    }

    public void checkNewPint() {
        if (r.nextInt(chance) == 0) {
            Drawable d = getResources().getDrawable(R.drawable.pint);
            int x = (int) (Math.random() * (CANVAS_WIDTH - Pint.PINT_WIDTH));
            Pint p = new Pint(x, START_Y, speed + (int) r.nextGaussian(), d);
            pints.add(p);
        }
    }

    public void incrValues() {
        if (counter == 400) {
            speed++;
            chance -= 2;
            counter = 0;
            System.out.println("Speed: " + speed + "    Chance: " + chance);
        } else {
            counter++;
        }
    }

    public boolean detectCollision() {
        Rect bar = new Rect(vangnet.getX(), CANVAS_HEIGHT - Vangnet.IMG_HEIGHT, vangnet.getX() + Vangnet.IMG_WIDTH, CANVAS_HEIGHT);
        Iterator<Pint> it = pints.iterator();
        while (it.hasNext()) {
            Pint p = it.next();
            Rect pRec = new Rect(p.getX(), p.getY(), p.getX() + Pint.PINT_WIDTH, p.getY() + Pint.PINT_HEIGHT);
            if (bar.intersect(pRec)) {
                score++;
                it.remove();
                TextView scoreView2 = (TextView) getRootView().findViewById(R.id.score_text);
                scoreView2.setText("" + score);
            } else if (p.getY() > CANVAS_HEIGHT - Pint.PINT_HEIGHT) {
                return false;
            }
        }
        return true;
    }

    public void gameOver() {
        final EditText input = new EditText(getContext());
        input.setSingleLine();
        input.setText("");

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Game Over!");
        builder.setMessage("Vul je naam in");
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!input.getText().equals("")) {
                    dialogInterface.dismiss();
                    FragmentTransaction fragmentTransaction = ((MainActivity) getContext()).getSupportFragmentManager().
                            beginTransaction().replace(R.id.fragment_container, new HighscoreFragment());
                    fragmentTransaction.commit();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                FragmentTransaction fragmentTransaction = ((MainActivity) getContext()).getSupportFragmentManager().
                        beginTransaction().replace(R.id.fragment_container, new MainFragment());
                fragmentTransaction.commit();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.donkerblauw));
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.donkerblauw));
            }
        });
        dialog.show();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float value = sensorEvent.values[0];
        int result = convert(value);
        int diff = Math.abs(vangnet.getX() - result);
        vangnet.setX((int) result);
    }

    public int convert(float value) {
        int result = (int) (((-value + 3) / 6) * (CANVAS_WIDTH - Vangnet.IMG_WIDTH));
        if (result < 0) {
            result = 0;
        } else if (result > CANVAS_WIDTH - Vangnet.IMG_WIDTH) {
            result = CANVAS_WIDTH - Vangnet.IMG_WIDTH;
        }
        return result;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}