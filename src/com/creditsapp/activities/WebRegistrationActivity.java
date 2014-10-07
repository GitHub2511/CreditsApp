package com.creditsapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import com.creditsapp.R;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 30.09.14
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */
public class WebRegistrationActivity extends Activity implements View.OnClickListener, View.OnKeyListener,
        CompoundButton.OnCheckedChangeListener {
private Button registrationButton;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_registration);

        registrationButton = (Button) findViewById(R.id.registratedButton);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        return false;
    }
}