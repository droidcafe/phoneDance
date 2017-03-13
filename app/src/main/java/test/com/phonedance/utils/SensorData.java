package test.com.phonedance.utils;

/**
 * Created by jayadeep on 3/13/17.
 */

public class SensorData {

    float x, y, z;

    public static final int IDLE_THRESCHOLD = 2;

    public SensorData(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SensorData(float[] values) {
        this.x = values[0];
        this.y = values[1];
        this.z = values[2];
    }

    boolean isIdle() {
//        return ((((int) x) == 0) && (((int) y) == 0) && (((int) z) == 0));
        return (
                (int) Math.abs(x) < IDLE_THRESCHOLD &&
                        (int) Math.abs(y) < IDLE_THRESCHOLD &&
                        (int) Math.abs(z) < IDLE_THRESCHOLD
        );
    }
}
