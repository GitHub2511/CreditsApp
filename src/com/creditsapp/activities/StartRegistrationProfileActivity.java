package com.creditsapp.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import com.creditsapp.R;
import com.creditsapp.client.JsonSender;
import com.creditsapp.fragments.BackAndSettingsFragment;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 16.09.14
 * Time: 11:45
 * To change this template use File | Settings | File Templates.
 */
public class StartRegistrationProfileActivity extends ActionBarMenuActivity implements View.OnClickListener, View.OnKeyListener,
        CompoundButton.OnCheckedChangeListener {
    private static final String LOG_TAG = "StartRegistrationProfileActivity";
    private EditText yorSum;
    private TextView peySum;
    private CheckBox checkedInfo;
    private Button checkingStart;
    private String wontTakeSum;
    private BackAndSettingsFragment backAndSettingsFragment;
    private int flags;
    private JSONObject jsonObject;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        flags = getIntent().getFlags();
        Log.d(LOG_TAG, "flags " + flags);
        yorSum = (EditText) findViewById(R.id.enterYorSum);
        yorSum.setOnKeyListener(this);

        peySum = (TextView) findViewById(R.id.calculationSum);
        peySum.setOnKeyListener(this);
        checkedInfo = (CheckBox) findViewById(R.id.checkedYorInfo);
        checkedInfo.setOnCheckedChangeListener(this);
        checkingStart = (Button) findViewById(R.id.startCheckedButton);
       //addFragment();
    }

    @Override
    protected View getContent() {
        View view = getLayoutInflater().inflate(R.layout.activity_start_registration_profile, null, false);
        return view;  //To change body of implemented methods use File | Settings | File Templates.
    }


    class CalculationResponsebl extends AsyncTask{
        private JsonSender jsonSender = JsonSender.getInstans();
        @Override
        protected Object doInBackground(Object... params) {
            jsonObject = jsonSender.sendCalculatorMessages(yorSum.getText().toString(), 4, 5);
            Log.d(LOG_TAG, "yes "+ jsonObject);
            if (yorSum != null) {
                Log.d(LOG_TAG, "go to server");
                wontTakeSum = yorSum.getText().toString();
                if(wontTakeSum.length() != 0){
                    CalculationResponsebl calculationResponsebl = new CalculationResponsebl();
                    calculationResponsebl.execute();
                    //  yorSum.setFocusable(false);
                }
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            try {
                peySum.setText(jsonObject.getString("monthMoney"));
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            Log.d(LOG_TAG, "finish");
        }
    }

    private void addFragment() {
        FragmentTransaction frgTrans = getFragmentManager().beginTransaction();
        backAndSettingsFragment = new BackAndSettingsFragment();
        frgTrans.add(R.id.frgViewStartRegist, backAndSettingsFragment);
        frgTrans.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkedYorInfo:
                Log.d(LOG_TAG, "click");
                break;
            case R.id.startCheckedButton:
                Log.d(LOG_TAG, "activ Button");
                //Start anketa
                Intent firstProfileClient = new Intent(this, FirstProfileClientActivity.class);
                firstProfileClient.addFlags(flags);
                startActivity(firstProfileClient);
                //    Intent authorizationActivity = new Intent(this, AuthorizationActivity.class);
                //   startActivity(authorizationActivity);
//               Intent registrationUserActivity = new Intent(this, RegistrationUserActivity.class);
//                startActivity(registrationUserActivity);
                //      Intent webRegistrationIntent = new Intent(this, WebRegistrationActivity.class);
                //      startActivity(webRegistrationIntent);
                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {


        Log.d(LOG_TAG, "keyCode "+keyCode);




        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.d(LOG_TAG, "enter_key_called");
            if (yorSum != null) {
                Log.d(LOG_TAG, "go to server");
                wontTakeSum = yorSum.getText().toString();
                if(wontTakeSum.length() != 0){
                CalculationResponsebl calculationResponsebl = new CalculationResponsebl();
                calculationResponsebl.execute();
                //  yorSum.setFocusable(false);
                }
            }
        }
        return false;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d(LOG_TAG, "" + isChecked);
        if (wontTakeSum != null) {
            if (isChecked) {
                Log.d(LOG_TAG, "listener activ");
                CheckBox checkBox = (CheckBox) findViewById(R.id.checkedYorInfo);
                checkingStart.setOnClickListener(this);
            }
        }
    }
}
