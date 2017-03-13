package test.com.phonedance;

import android.app.Application;

import java.util.ArrayList;

import test.com.phonedance.utils.Gesture;

/**
 * Created by jayadeep on 3/13/17.
 */

public class Global extends Application {
    public static ArrayList<Gesture> gestures = new ArrayList<Gesture>();

    @Override
    public void onCreate() {
        super.onCreate();


    }


}
