package com.creditsapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.creditsapp.R;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 30.09.14
 * Time: 11:06
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationUserActivity extends ActionBarMenuActivity implements View.OnClickListener {
    private Button authorizationButton;
    private Button creditBrokerButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        authorizationButton = (Button) findViewById(R.id.authorizationButton);
        creditBrokerButton = (Button) findViewById(R.id.cretidBrokerButton);


    }


    @Override
    protected View getContent() {
        View v = getLayoutInflater().inflate(R.layout.activity_registration, null, false);
        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.authorizationButton:

                break;
            case R.id.cretidBrokerButton:

                break;
        }


    }
}