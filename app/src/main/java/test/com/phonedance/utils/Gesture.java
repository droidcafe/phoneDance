package test.com.phonedance.utils;

import android.hardware.SensorManager;
import android.util.Log;

import java.io.FileOutputStream;
import java.util.ArrayList;

import test.com.phonedance.Global;

/**
 * Created by jayadeep on 3/13/17.
 */

public class Gesture {

    public static final int IDLE_LIMIT = 30;
    public static final int MIN_COUNT = 70;
    public static final int SENSOR_DELAY = SensorManager.SENSOR_DELAY_NORMAL * 2;

    private ArrayList<SensorData> sensorDataList;
    private String text;

    OnGestureCompleteListener listener;

    public Gesture(OnGestureCompleteListener listener) {
        this.listener = listener;
        sensorDataList = new ArrayList<SensorData>();
        idleCount = 0;
    }

    int idleCount = 0;

    public void addData(float[] values) {
        Log.i("val", values[0] + " " + values[1] + " " + values[2]);
        SensorData data = new SensorData(values);
        sensorDataList.add(data);
        if (data.isIdle())
            idleCount++;
        else
            idleCount = 0;
        if (idleCount >= IDLE_LIMIT && sensorDataList.size() > MIN_COUNT)
            if (listener != null) {
                listener.onGestureComplete();
            }
    }

    public ArrayList<SensorData> getSensorDataList() {
        return sensorDataList;
    }

    public void setText(String t) {
        text = t;
    }

    public String getText() {
        return text;
    }

    public void save() {
        Global.gestures.add(this);
    }

    public String findText() {
        ArrayList<Gesture> gestures = Global.gestures;
        float[] results = new float[gestures.size()];
        for (int i = 0; i < gestures.size(); i++) {
            Gesture gesture = gestures.get(i);
            results[i] = gesture.compare(this);
            Log.i("compare", gesture.getText() + " " + results[i] * 100 + "%");
        }
        int max = 0;
        for (int i = 1; i < results.length; i++) {
            if (results[i] > results[max])
                max = i;
        }
        return gestures.get(max).getText();
    }

    public int getCount() {
        return sensorDataList.size();
    }

    public float compare(Gesture gesture) {
        int total = Math.min(gesture.getCount(), getCount());
        int correct = 0;
        ArrayList<SensorData> data1 = getSensorDataList();
        ArrayList<SensorData> data2 = gesture.getSensorDataList();
        int count1 = data1.size();
        int count2 = data2.size();
        if (count1 > count2) {
            int t = count1;
            count1 = count2;
            count2 = t;
        }
        if (count1 < count2 * 0.65)
            return 0.0f;
        for (int i = 0; i < total; i++) {
            if (data1.get(i).approximatelyEqual(data2.get(i))) {
                correct++;
            }
        }
        return ((float) correct) / total;
    }

    public void stop() {
        Log.i("gestr", "stopping");
        listener.onGestureComplete();
    }

    public interface OnGestureCompleteListener {
        void onGestureComplete();
    }

    public void saveGesture() {
        String filename = "myfile";
        String string = "Hello world!";
        FileOutputStream outputStream;


        try {
            //  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
       /*     outputStream.write(string.getBytes());
            outputStream.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
