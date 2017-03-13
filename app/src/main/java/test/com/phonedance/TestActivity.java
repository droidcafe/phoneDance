package test.com.phonedance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.button_test).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_test:
                text = testGesture();
                showText();
                break;
        }
    }

    private String testGesture() {
        return null;
    }

    public void showText() {
        TextView textView = (TextView) findViewById(R.id.gesture_text);
        textView.setText(text);
    }
}
