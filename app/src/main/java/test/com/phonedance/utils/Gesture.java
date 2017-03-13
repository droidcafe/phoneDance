package test.com.phonedance.utils;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by jayadeep on 3/13/17.
 */

public class Gesture {

    public static final int THRESHOLD = 2;
    public static final int IDLE_LIMIT = 10;
    public static final int MIN_COUNT = 50;

    ArrayList<SensorData> sensorDataList;

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

    public interface OnGestureCompleteListener {
        void onGestureComplete();
    }
}
