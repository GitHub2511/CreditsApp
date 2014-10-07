package com.creditsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import com.creditsapp.activities.NavigationActivity;

import java.util.concurrent.TimeUnit;

public class MyActivity extends Activity {
    private FrameLayout frameLayout;
    private Handler h;
    final int START_NEXT_ACTIVITY = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        // frameLayout = (FrameLayout) findViewById(R.id.content_frame);


    }


    @Override
    protected void onResume() {
        super.onResume();

        h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case START_NEXT_ACTIVITY:
                        Intent intent = new Intent(MyActivity.this, NavigationActivity.class);
                        startActivity(intent);
                        break;
                }
            };
        };


        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    h.sendEmptyMessage(START_NEXT_ACTIVITY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}

