package com.creditsapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.creditsapp.R;
import com.creditsapp.client.JsonSender;
import com.creditsapp.dialogs.DialogSettings;
import com.creditsapp.dialogs.DialogYorChoise;
import com.creditsapp.fragments.BackAndSettingsFragment;
import com.creditsapp.fragments.FrgRoundButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class FirstProfileClientActivity extends ActionBarMenuActivity implements View.OnClickListener{

    private static final String LOG_TAG = "FirstProfileClientActivity";
    public static int goToSecondActivity = 0;

    private TextView whyYouNeedCredit;
    private EditText enterName;
    private EditText enterTelephone;
    private TextView yorChoisdActivity;
    private TextView yorData;
    private String myName = "";
    private String myTelephone = "";
    private String birthday;
    private BackAndSettingsFragment backAndSettingsFragment;
    private EditText dataEdit;
    private EditText dataEditT;
    private LinearLayout birthdayLinearLayout;
    private TextView employeeView;
    private TextView pensionerView;
    private TextView businessView;
    private  TextView notWorkView;
    private LinearLayout linearLayout;
    private int mDay;
    private int mYear;
    private int mMonth;
    private int indexYorActivityClick = 0;
    private JSONObject jsonObject = null;
    private String[] townsList;
    private boolean isConnecting = true;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        enterName = (EditText) findViewById(R.id.yorName);
        enterTelephone = (EditText) findViewById(R.id.yorTelephone);
        yorChoisdActivity = (TextView) findViewById(R.id.yorChoisActivity);
        yorChoisdActivity.setOnClickListener(this);
        if(isConnecting){
            isConnecting = false;
            FirstActivityGetTownResponsebl firstActivityGetTownResponsebl = new FirstActivityGetTownResponsebl();
            firstActivityGetTownResponsebl.execute();
        }

      //  yorData = (TextView) findViewById(R.id.yorData);
       // yorData.setOnClickListener(this);
        addFragment();

        Intent intent = getIntent();
        int flag = intent.getFlags();
        if (flag == 2) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.notSpecialOfferLayout);
            View v = getLayoutInflater().inflate(R.layout.text_view_in_second_activity, null, false);
            whyYouNeedCredit = (TextView) v.findViewById(R.id.whyYouNeedCredit);
            whyYouNeedCredit.setOnClickListener(this);
            linearLayout.addView(v);

        }
    }

    @Override
    protected View getContent() {
        View v = getLayoutInflater().inflate(R.layout.activity_first_profile_client, null, false);
        return v;
    }

    private void addFragment() {
        FragmentTransaction frgTrans = getFragmentManager().beginTransaction();
        backAndSettingsFragment = new BackAndSettingsFragment();
     //  frgTrans.add(R.id.firstProfileFrgView, backAndSettingsFragment);
        //  frgTrans.commit();
        FrgRoundButton frgRoundButton = new FrgRoundButton(1);
        frgTrans.add(R.id.frgViewFirstPofile, frgRoundButton);
        frgTrans.commit();
    }

    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG,"onClick");
        switch (v.getId()) {

            case R.id.whyYouNeedCredit:
               new DialogSettings(this);
                break;

            case R.id.yorChoisActivity:
                indexYorActivityClick ++;
                if(indexYorActivityClick == 1){
                    createActivitisView();
                  //  DialogYorChoise dialogYorChoise = new DialogYorChoise(this);
                }else{
                    removeActivitisView();
                    indexYorActivityClick = 0;
                }
                break;

            case R.id.notWorked:
                if(checkedEditTexts()){
                    goToSecondActivity ++;
                    Intent waitingIntent = new Intent(this, WaitingActivity.class);
                    startActivity(waitingIntent);
                }
                break;

            case R.id.businesswoman:
                if(checkedEditTexts()){
                    goToSecondActivity ++;
                    createSecondProfileIntent(2);
                }
                break;

            case R.id.pensioner:
                if(checkedEditTexts()){
                    goToSecondActivity ++;
                    createSecondProfileIntent(3);
                }
                break;

            case R.id.employee:
                if(checkedEditTexts()){
                    goToSecondActivity ++;
                    createSecondProfileIntent(1);
                }
                break;
//            case R.id.yorData:
//
//
//                if(dataEditT == null){
//                    Log.d(LOG_TAG,"null "+birthday);
//                }
//                break;
        }
    }
    private void createSecondProfileIntent(int flags){
        Intent secondProfileIntent = new Intent(this, SecondProfileClientActivity.class);
        secondProfileIntent.addFlags(flags);
        startActivity(secondProfileIntent);
        goToSecondActivity = 1;
    }

    private void createActivitisView(){
       // linearLayout = (LinearLayout) findViewById(R.id.kindActivityLayout);
        linearLayout.addView(getLayoutInflater().inflate(R.layout.activity_first_not_special_propous, null, false));
        employeeView = (TextView) findViewById(R.id.employee);
        pensionerView = (TextView) findViewById(R.id.pensioner);
        businessView = (TextView) findViewById(R.id.businesswoman);
        notWorkView = (TextView) findViewById(R.id.notWorked);
        employeeView.setOnClickListener(this);
        pensionerView.setOnClickListener(this);
        businessView.setOnClickListener(this);
        notWorkView.setOnClickListener(this);
    }
    private void removeActivitisView(){
        linearLayout.removeAllViews();
    }
    private boolean checkedEditTexts(){
        if(enterName != null && enterTelephone != null){
            myName = enterName.getText().toString();
            myTelephone = enterTelephone.getText().toString();
            if(myName.length() > 0 && myTelephone.length() > 0){
                return true;
            }
            return false;
        }
        return false;
    }

    private void serchTown(String[] townList){
        //Creating the instance of ArrayAdapter containing list of language names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,townList);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView


    }


    class FirstActivityGetTownResponsebl extends AsyncTask{
        private JsonSender jsonSender = JsonSender.getInstans();
        @Override
        protected Object doInBackground(Object... params) {
                   jsonObject = null;
            jsonObject = jsonSender.sendTownMessages();
            Log.d(LOG_TAG, "yes "+ jsonObject);
            try {

          JSONArray jsonArray = (JSONArray) jsonObject.get("data");
                    int arraySize = jsonArray.length();
                  townsList = new String[arraySize];
                 for(int i = 0; i < arraySize ; i++){
                     jsonObject = jsonArray.getJSONObject(i);
                    townsList[i] = jsonObject.get("city").toString();
                  Log.d(LOG_TAG, jsonArray.getString(i));
                     Log.d(LOG_TAG, jsonObject.get("city").toString());
                 }

            } catch (JSONException e) {
                Log.d(LOG_TAG, "Exception");
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);    //To change body of overridden methods use File | Settings | File Templates.

            serchTown(townsList);
            isConnecting = true;
        }
    }
}