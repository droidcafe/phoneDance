package test.com.phonedance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

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


        return false;
    }

    private void setList() {
        RecyclerView list = (RecyclerView) findViewById(R.id.gesture_list);
        list.setVisibility(View.VISIBLE);

    }

}
