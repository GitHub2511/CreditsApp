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


public class FirstProfileClientActivity extends Activity implements View.OnClickListener, View.OnKeyListener {

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
        enterName.setOnKeyListener(this);
        enterTelephone = (EditText) findViewById(R.id.yorTelephone);
        enterTelephone.setOnKeyListener(this);
        yorLocation = (TextView) findViewById(R.id.yorLocation);
        yorLocation.setOnClickListener(this);
        yorChoisdActivity = (TextView) findViewById(R.id.yorChoisActivity);
        yorChoisdActivity.setOnClickListener(this);
        birthdayLinearLayout = (LinearLayout) findViewById(R.id.birthdayLayout);
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
                String[] yorKindActivity = new String[]{"Наемный сотрудник", "Предприниматель", "Пенсионер", "Клиент не работает"};
                showTownList(yorKindActivity);
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
                if(myTelephone.length() != 0 && myName.length() != 0){

                    goToSecondActivity ++;

                Intent waitingIntent = new Intent(this, WaitingActivity.class);
                startActivity(waitingIntent);
                }
             break;

            case R.id.businesswoman:
                if(myTelephone.length() != 0 && myName.length() != 0){

                    goToSecondActivity ++;

                 createSecondProfileIntent(2);
                }
                break;
            case R.id.pensioner:
                if(myTelephone.length() != 0 && myName.length() != 0){

                    goToSecondActivity ++;

                createSecondProfileIntent(3);
                }
                break;
            case R.id.employee:
                if(myTelephone.length() != 0 && myName.length() != 0){

                    goToSecondActivity ++;

                createSecondProfileIntent(1);
                }
                break;
            case R.id.yorData:
                   createdEditDataView();
                    createDatePicker();

                   birthday = dataEditT.getText().toString();
                Log.d(LOG_TAG, "data " + dataEditT.getText());
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

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        Log.d(LOG_TAG, "woring now");

        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.d(LOG_TAG, "enter_key_called");
            if (enterName != null) {
                Log.d(LOG_TAG, "go to server");
                myName = enterName.getText().toString();
               Log.d(LOG_TAG, "myname "+ myName);

            }
            if(enterTelephone != null){
                myTelephone = enterTelephone.getText().toString();
                Log.d(LOG_TAG,"My telephone "+myTelephone);
            }



        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
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




    private void createdEditDataView(){
             birthdayLinearLayout.addView(getLayoutInflater().inflate(R.layout.activity_first_profile_edit_component, null, false));
        dataEditT = (EditText) findViewById(R.id.dataEditText);
    }
    private void createDatePicker(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);



        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                      dataEditT.setText(dayOfMonth + "-"
                              + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);


        dpd.show();

    }

}