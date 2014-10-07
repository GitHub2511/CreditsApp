package com.creditsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.creditsapp.activities.NavigationActivity;

import java.util.concurrent.TimeUnit;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        try {
            TimeUnit.SECONDS.sleep(0);
            Intent intent = new Intent(this, NavigationActivity.class);
            startActivity(intent);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
  }

