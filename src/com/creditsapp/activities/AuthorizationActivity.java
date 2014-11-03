package com.creditsapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.creditsapp.R;


/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 30.09.14
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class AuthorizationActivity extends ActionBarMenuActivity implements View.OnClickListener, View.OnKeyListener{
    private static final String LOG_TAG = "AuthorizationActivity";
    private Button falseLoginOrPasswordButton;
    private EditText loginEdit;
    private EditText passwordEdit;
    private String yorLogin;
    private String yorPassword;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        falseLoginOrPasswordButton = (Button) findViewById(R.id.falseLoginOrPassword);
        loginEdit = (EditText) findViewById(R.id.loginUserEdit);
        loginEdit.setOnKeyListener(this);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        passwordEdit.setOnKeyListener(this);
    }

    @Override
    protected View getContent() {
       View v = getLayoutInflater().inflate(R.layout.activity_authrization, null, false);
        return v;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.falseLoginOrPassword:
                break;

        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.d(LOG_TAG, "enter_key_called");
            if(passwordEdit != null && loginEdit != null){
                Log.d(LOG_TAG, "go to server");
                yorLogin = loginEdit.getText().toString();
                yorPassword = passwordEdit.getText().toString();
            }

        }
        return false;
    }


}