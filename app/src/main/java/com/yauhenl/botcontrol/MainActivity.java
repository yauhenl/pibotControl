package com.yauhenl.botcontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String host = "http://192.168.1.7";

    private Button forward;
    private Button backward;
    private Button right;
    private Button left;
    private ToggleButton power;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forward = (Button) findViewById(R.id.forward);
        forward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_DOWN)
                    request(host + "/move/forward");
                else if (action == MotionEvent.ACTION_UP)
                    request(host + "/move/stop");
                return false;
            }
        });

        backward = (Button) findViewById(R.id.backward);
        backward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_DOWN)
                    request(host + "/move/backward");
                else if (action == MotionEvent.ACTION_UP)
                    request(host + "/move/stop");
                return false;
            }
        });

        right = (Button) findViewById(R.id.right);
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_DOWN)
                    request(host + "/move/right");
                else if (action == MotionEvent.ACTION_UP)
                    request(host + "/move/stop");
                return false;
            }
        });

        left = (Button) findViewById(R.id.left);
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_DOWN)
                    request(host + "/move/left");
                else if (action == MotionEvent.ACTION_UP)
                    request(host + "/move/stop");
                return false;
            }
        });

        power = (ToggleButton) findViewById(R.id.power);
        power.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    request(host + "/switch/on");
                else
                    request(host + "/switch/off");
            }
        });
    }

    private void request(String url) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.getResponseCode();
            con.disconnect();
        } catch (Exception ignore) {
        }
    }
}
