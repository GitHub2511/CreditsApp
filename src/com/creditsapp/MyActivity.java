package com.creditsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.FrameLayout;
import com.creditsapp.activities.NavigationActivity;
import com.creditsapp.activities.YorChoiceServicesActivity;
import com.creditsapp.location.MyLocationListener;

import java.util.concurrent.TimeUnit;

public class MyActivity extends Activity {
    private static final String LOG_TAG = "MyActivity";
    private FrameLayout frameLayout;
    private Handler h;
    final int START_NEXT_ACTIVITY = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        MyLocationListener.SetUpLocationListener(this);
        Log.d(LOG_TAG, MyLocationListener.countryNow);
    }

    @Override
    protected void onResume() {
        super.onResume();

        h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case START_NEXT_ACTIVITY:
                        startActivity(new Intent(MyActivity.this, YorChoiceServicesActivity.class));
                        overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);
                        finish();
                        break;
                }
            };
        };

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(0);
                    h.sendEmptyMessage(START_NEXT_ACTIVITY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}

