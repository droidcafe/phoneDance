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
import android.widget.TextView;
import android.widget.Toast;

import test.com.phonedance.utils.Gesture;

public class TestActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener, Gesture.OnGestureCompleteListener {


    Sensor accelerometer;
    SensorManager sensorManager;

    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        findViewById(R.id.button_test).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_test:
                createGesture();
//                text = testGesture();
//                showText();
                break;
        }
    }

    Gesture gesture;

    private void createGesture() {
        gesture = new Gesture(this);
        text = "";
        showText();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onGestureComplete() {
        sensorManager.unregisterListener(this);
        Log.i("sensor", "finished");
        Log.i("sensor", gesture.getSensorDataList().size() + "");
        text = gesture.findText();
        showText();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void showText() {
        TextView textView = (TextView) findViewById(R.id.gesture_text);
        textView.setText(text);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        gesture.addData(sensorEvent.values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
