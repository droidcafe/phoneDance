package test.com.phonedance;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import test.com.phonedance.utils.Gesture;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener, Gesture.OnGestureCompleteListener {

    Sensor accelerometer;
    SensorManager sensorManager;

    private static final String TAG = SecondActivity.class.getSimpleName();
    String pair_text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.button_save).setOnClickListener(this);
        findViewById(R.id.gesture_new).setOnClickListener(this);
        findViewById(R.id.gesture_stop).setOnClickListener(this);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
//        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL * 5);
//        createGesture();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_save:
                EditText text = (EditText) findViewById(R.id.text);
                pair_text = text.getText().toString();
                saveGesture();
                break;

            case R.id.gesture_new:
                createGesture();
                findViewById(R.id.button_test_stop).setVisibility(View.VISIBLE);
                break;
            case R.id.gesture_stop:

                break;
        }

    }

    Gesture gesture;

    private void saveGesture() {
        if (gesture == null) {
            Toast.makeText(this, "Please train a gesture", Toast.LENGTH_SHORT).show();
        }
        if (pair_text == null || pair_text.equals("")) {
            Toast.makeText(this, "Please enter a pair text", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(TAG, "saveGesture: pair " + pair_text);
        gesture.setText(pair_text);
        gesture.save(this);
        finish();
    }

    private void createGesture() {
        gesture = new Gesture(this);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL * 5);
    }


    float[] oldValues;
//    float[] values = new float[3];

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

//        values[0] = sensorEvent.values[0];
//        values[1] = sensorEvent.values[1];
//        values[2] = sensorEvent.values[2];

//        if (oldValues == null) {
//            oldValues = new int[3];
//            oldValues[0] = values[0];
//            oldValues[1] = values[1];
//            oldValues[2] = values[2];
//            return;
//        }

        gesture.addData(sensorEvent.values);

//        Log.i("val", values[0] + " " + values[1] + " " + values[2]);
//        Log.i("val",getDirection(values[0],oldValues[0])+getDirection(values[1],oldValues[1])+getDirection(values[2],oldValues[2]));

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onGestureComplete() {
        sensorManager.unregisterListener(this);
        Log.i("sensor", "finished");
        Log.i("sensor", gesture.getSensorDataList().size() + "");

        findViewById(R.id.button_save).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
