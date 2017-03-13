package test.com.phonedance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

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
                break;
        }

    }

    private void saveGesture() {
        if (pair_text == null || pair_text.equals("")) {
            Toast.makeText(this,"Please enter a pair text",Toast.LENGTH_LONG).show();
            return;
        }
        Log.d(TAG, "saveGesture: pair "+pair_text);
    }

    private void createGesture() {

    }
}
