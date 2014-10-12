package com.creditsapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import com.creditsapp.R;


/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 29.09.14
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class WaitingActivity extends Activity {
    private Handler h;
    final int START_NEXT_ACTIVITY = 1;
    private ProgressBar progressBar;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.

                startActivity(new Intent(WaitingActivity.this, CongratulationActivity.class));
                overridePendingTransition(R.anim.congratulation,R.anim.push_down_out);
                finish();
            }
        },3000) ;

    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        h = new Handler() {
//            public void handleMessage(android.os.Message msg) {
//                switch (msg.what) {
//                    case START_NEXT_ACTIVITY:
//
//                        break;
//                }
//            };
//        };
//
//
//        Thread t = new Thread(new Runnable() {
//            public void run() {
//                try {
//                    TimeUnit.SECONDS.sleep(3);
//                    h.sendEmptyMessage(START_NEXT_ACTIVITY);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        t.start();
//
//
//    }
//


}