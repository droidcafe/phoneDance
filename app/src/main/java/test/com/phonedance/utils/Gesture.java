package test.com.phonedance.utils;

import android.provider.Settings;
import android.util.Log;

import java.util.ArrayList;

import test.com.phonedance.Global;

/**
 * Created by jayadeep on 3/13/17.
 */

public class Gesture {

    public static final int IDLE_LIMIT = 10;
    public static final int MIN_COUNT = 50;

    ArrayList<SensorData> sensorDataList;
    String text;

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
        for (int i = 0; i < total; i++) {
            if (data1.get(i).approximatelyEqual(data2.get(i))) {
                correct++;
            }
        }
        return ((float) correct) / total;
    }

    public interface OnGestureCompleteListener {
        void onGestureComplete();
    }
}
