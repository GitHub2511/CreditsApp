package com.creditsapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.creditsapp.R;
import com.creditsapp.fragments.BackAndSettingsFragment;
import com.creditsapp.fragments.FrgRoundButton;

import java.util.Calendar;


public class FirstProfileClientActivity extends Activity implements View.OnClickListener{

    private static final String LOG_TAG = "FirstProfileClientActivity";
    public static int goToSecondActivity = 0;

    private EditText enterName;
    private EditText enterTelephone;
    private TextView yorLocation;
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_profile_client);


        enterName = (EditText) findViewById(R.id.yorName);
        enterTelephone = (EditText) findViewById(R.id.yorTelephone);
        yorLocation = (TextView) findViewById(R.id.yorLocation);
        yorLocation.setOnClickListener(this);
        yorChoisdActivity = (TextView) findViewById(R.id.yorChoisActivity);
        yorChoisdActivity.setOnClickListener(this);
        yorData = (TextView) findViewById(R.id.yorData);
        yorData.setOnClickListener(this);
        addFragment();

        Intent intent = getIntent();
        int flag = intent.getFlags();
        if (flag == 2) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.notSpecialOfferLayout);
            TextView textView = new TextView(this);
            textView.setText("Для чего Вам нужен кредит?");
            textView.setId(1);
            textView.setOnClickListener(this);
            linearLayout.addView(textView);
        }

    }


    private void addFragment() {
        FragmentTransaction frgTrans = getFragmentManager().beginTransaction();
        backAndSettingsFragment = new BackAndSettingsFragment();
        frgTrans.add(R.id.firstProfileFrgView, backAndSettingsFragment);
        //   frgTrans.commit();

        FrgRoundButton frgRoundButton = new FrgRoundButton(1);
        frgTrans.add(R.id.frgViewFirstPofile, frgRoundButton);
        frgTrans.commit();
    }

    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG,"onClick");
        switch (v.getId()) {

            case R.id.yorLocation:
//                String[] yorKindActivity = new String[]{"Наемный сотрудник", "Предприниматель", "Пенсионер", "Клиент не работает"};
//                showTownList(yorKindActivity);
                break;

            case 1:
                Intent intent = new Intent(this, SpecialOfferActivity.class);
                startActivity(intent);
                break;

            case R.id.yorChoisActivity:
                indexYorActivityClick ++;
                if(indexYorActivityClick == 1){
                    createActivitisView();
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
            case R.id.yorData:


                if(dataEditT == null){
                    Log.d(LOG_TAG,"null "+birthday);
                }
        }
    }
    private void createSecondProfileIntent(int flags){
        Intent secondProfileIntent = new Intent(this, SecondProfileClientActivity.class);
        secondProfileIntent.addFlags(flags);
        startActivity(secondProfileIntent);
        goToSecondActivity = 1;
    }


    public void showTownList(String[] array) {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(FirstProfileClientActivity.this);

        builderSingle.setTitle("Выбери свой город");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                FirstProfileClientActivity.this, android.R.layout.select_dialog_singlechoice, array);

        builderSingle.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
//
                        Log.d(LOG_TAG, "click");
                    }
                });
        builderSingle.show();
    }
    private void createActivitisView(){
        linearLayout = (LinearLayout) findViewById(R.id.kindActivityLayout);
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
}