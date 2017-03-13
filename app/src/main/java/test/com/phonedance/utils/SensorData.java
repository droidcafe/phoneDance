package test.com.phonedance.utils;

/**
 * Created by jayadeep on 3/13/17.
 */

public class SensorData {
    public enum MotionType{
        INCREASE,
        DECREASE,
        NO_CHANGE
    }

    MotionType x,y,z;

    public SensorData(MotionType x,MotionType y,MotionType z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
