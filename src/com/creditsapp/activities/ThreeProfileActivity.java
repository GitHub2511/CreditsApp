package com.creditsapp.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.creditsapp.R;
import com.creditsapp.client.JsonSender;
import com.creditsapp.fragments.BackAndSettingsFragment;
import com.creditsapp.fragments.FrgRoundButton;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Windows
 * Date: 23.09.14
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
public class ThreeProfileActivity extends ActionBarMenuActivity implements View.OnClickListener, View.OnKeyListener {
    private static final String LOG_TAG = "ThreeProfileActivity";
    private EditText etItemProfile;
    private RadioButton noCreditRadioButton;
    private RadioButton closeCreditRadioButton;
    private RadioButton openCreditRadioButton;
    private LinearLayout inRadioButtonsLayout;
    private LinearLayout linearLayout;
    private LinearLayout testLiner;
   private EditText yorSumEditText;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        testLiner = (LinearLayout) findViewById(R.id.tLayout);
        noCreditRadioButton = (RadioButton) findViewById(R.id.noCreditsRadioButton);
        noCreditRadioButton.setOnClickListener(this);
        closeCreditRadioButton = (RadioButton) findViewById(R.id.closeCreditsRadioButton);
        closeCreditRadioButton.setOnClickListener(this);
        openCreditRadioButton = (RadioButton) findViewById(R.id.openCreditsRadioButton);
        openCreditRadioButton.setOnClickListener(this);
        inRadioButtonsLayout = (LinearLayout) findViewById(R.id.inRadioButtonLinearLayout);
        yorSumEditText = (EditText) findViewById(R.id.enterYorSumThreeActivity);
        yorSumEditText.setOnKeyListener(this);
        //  linearLayout = (LinearLayout) findViewById(R.id.testLayout);
        addFragment();

        SendUserInfoAsynTask sendUserInfoAsynTask = new SendUserInfoAsynTask();
        sendUserInfoAsynTask.execute();

    }

    @Override
    protected View getContent() {
       View view = getLayoutInflater().inflate(R.layout.activity_profile_3, null, false);

        return view;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void addFragment() {
        FragmentTransaction frgTrans = getFragmentManager().beginTransaction();
      //  BackAndSettingsFragment backAndSettingsFragment = new BackAndSettingsFragment();
        FrgRoundButton frgRoundButton = new FrgRoundButton(3);
        //frgTrans.add(R.id.frgViewProf3, backAndSettingsFragment);
        frgTrans.add(R.id.frgViewThreeProfile, frgRoundButton);
        frgTrans.commit();
    }

    private void showInRadioButtonsComponents(){
        inRadioButtonsLayout.setVisibility(View.VISIBLE);
    }
    private void hideInRadioButtonsComponents(){
        inRadioButtonsLayout.setVisibility(View.GONE);
    }

    private void showBottomComponents() {

        testLiner.setVisibility(View.VISIBLE);
    }

    private void hideBottomComponents() {
      testLiner.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {

        Log.d(LOG_TAG, "id = " + view.getId());

        switch (view.getId()) {

            case R.id.closeCreditsRadioButton:
                hideBottomComponents();
                showInRadioButtonsComponents();

                break;

            case R.id.openCreditsRadioButton:
               hideInRadioButtonsComponents();
                showBottomComponents();

                break;

            case R.id.noCreditsRadioButton:
                hideBottomComponents();
                hideInRadioButtonsComponents();

                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.d(LOG_TAG, "enter_key_called");

            if (yorSumEditText != null) {
                Log.d(LOG_TAG, "go to server");
               String wontTakeSum = yorSumEditText.getText().toString();
                if(wontTakeSum.length() != 0){
                   Intent intent = new Intent(this, SelectBankActivity.class);
                    startActivity(intent);

                }
            }
        }

        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
    public class SendUserInfoAsynTask extends AsyncTask{

        private JsonSender jsonSender = JsonSender.getInstans();
        @Override
        protected Object doInBackground(Object... params) {
             JSONObject jsonObject = jsonSender.sendIfoForUser();
                Log.d(LOG_TAG, jsonObject.toString());

            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }


}
