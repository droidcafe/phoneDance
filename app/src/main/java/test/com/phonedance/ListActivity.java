package test.com.phonedance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import test.com.phonedance.utils.Gesture;

import static test.com.phonedance.Global.gestures;

public class ListActivity extends AppCompatActivity {

    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getList()){
            setList();
        }
    }

    private boolean getList() {
        if(gestures == null || gestures.size() == 0)
            return false;
        else{
            list = new ArrayList<>();
            ArrayList<Gesture> gestures = new ArrayList<>();
            gestures = Global.gestures;
            for (Gesture gesture: gestures) {
                list.add(gesture.getText());
            }

            return true;
        }

    }

    private void setList() {
        findViewById(R.id.error).setVisibility(View.GONE);
        RecyclerView list_view = (RecyclerView) findViewById(R.id.gesture_list);
        list_view.setVisibility(View.VISIBLE);

        list_view.setLayoutManager(new LinearLayoutManager(this));
        list_view.setAdapter(new ListAdapter(list));
        list_view.setHasFixedSize(true);

    }

    private void hideList() {
        findViewById(R.id.error).setVisibility(View.VISIBLE);
        findViewById(R.id.gesture_list).setVisibility(View.GONE);
    }

}
