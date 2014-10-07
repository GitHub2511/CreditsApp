package com.creditsapp.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.creditsapp.R;
import com.creditsapp.fragments.BackAndSettingsFragment;
import com.creditsapp.fragments.FrgRoundButton;


public class SecondProfileClientActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, View.OnKeyListener {
    private static final String LOG_TAG = "SecondProfileClientActivity";
    private TextView firstMainQuestion;
    private TextView firstAnswer;
    private TextView secondAnswer;
    private LinearLayout firstLayout;
    private LinearLayout secondLayout;
    private LinearLayout startLayout;
    private int notOfficialClick = 0;
    private int officialClick = 0;
    private int flag;
    private CheckBox officialWorked;
    private EditText editYorPay;
    private EditText yorWorkSelery;
    private String yorPay;
    private EditText yorAddSalary;
    private String yorAddSalaryString;
    private String yorWorkSalaryString;
    private BackAndSettingsFragment backAndSettingsFragment;
    private boolean isOfficialDocum = false;
    private EditText yorBuisenessWorkSalary;
    private EditText yorBuisenesAddSelery;
    private String yorBuisenessWorkSalaryString;
    private String yorAddBuisenesSeleryString;
    private CheckBox yorOfficialBuisenesDoc;
    private boolean isOfficialBuisnessDocum = false;
    private EditText yorPensionWorkSalary;
    private EditText pensionerWorkSalary;
    private CheckBox pensionerOfficialWorkDoc;
    private CheckBox yorOfficialPensionDoc;
    private boolean isPensionerOfficialWorkDoc = false;
    private boolean isYorOfficialPensionDoc = false;
    private String pensionerWorkSalaryString;
    private String yorPensionWorkSeleryString;
    private EditText yorPensionEdit;
    private EditText yorPensionSalary;
    private String yorPensionEditString;
    private String yorPensionSalaryString;
    private CheckBox yorOfficialPensionDocument;
    private boolean isYorOfficialPensionDocument = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_profile_client);

        firstMainQuestion = (TextView) findViewById(R.id.yorFormOfQuestion);
        firstAnswer = (TextView) findViewById(R.id.notOfficialWork);
        firstAnswer.setOnClickListener(this);
        secondAnswer = (TextView) findViewById(R.id.officialWork);
        secondAnswer.setOnClickListener(this);
        firstLayout = (LinearLayout) findViewById(R.id.firstLayout);
        secondLayout = (LinearLayout) findViewById(R.id.secondLayout);
        startLayout = (LinearLayout) findViewById(R.id.startLayout);
        addFragment();
        flag = getIntent().getFlags();
        Log.d(LOG_TAG, "flag " + flag);
        if (flag == 1) {
            createEmployeetype();
        }
        if (flag == 2) {
            createBuisnesswoman();
        }

        if (flag == 3) {
            createPensioner();
        }
    }

    private void addFragment() {
        FragmentTransaction frgTrans = getFragmentManager().beginTransaction();
        backAndSettingsFragment = new BackAndSettingsFragment();
        frgTrans.add(R.id.frgViewStartRegist, backAndSettingsFragment);
        FrgRoundButton frgRoundButton = new FrgRoundButton(2);
        frgTrans.add(R.id.frgViewSecondProfile, frgRoundButton);
        frgTrans.commit();
    }

    private void createEmployeetype() {
        firstMainQuestion.setText("Укажите форму трудоустойства?");
        firstAnswer.setText("Работа по срочному трудовому договору");
        secondAnswer.setText("Официальная работа");
    }

    private void createBuisnesswoman() {
        firstMainQuestion.setText("Давно ли Вы зарегистрированы как предприниматель?");
        firstAnswer.setText("Меньше 12 мес");
        secondAnswer.setText("Больше 12 мес");
    }

    private void createPensioner() {
        firstMainQuestion.setText("Вы работаете?");
        firstAnswer.setText("Да");
        secondAnswer.setText("Нет");
    }

    private View createEmployeeView() {
        View v = getLayoutInflater().inflate(R.layout.activity_second_employee, null, false);
        return v;
    }

    private void removeOfficialWorkOnClickEmployee() {
        startLayout.removeAllViews();
        firstLayout.removeAllViews();
        secondLayout.removeAllViews();
    }

    private void removeNotOfficialWorkOnClickEmployee() {
        startLayout.removeAllViews();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.notOfficialWork:
                if (flag == 1) {
                    notOfficialWorkClickEmployee();
                }
                if (flag == 2) {
                    notOfficialWorkClickEmployee();
                }
                if (flag == 3) {
                    notOfficialWorkClickEmployee();
                    Log.d(LOG_TAG, "not officialOnClick");
                }
                break;
            case R.id.officialWork:
                if (flag == 1) {
                    officialWorkClickEmployee();
                }
                if (flag == 2) {
                    officialWorkClickEmployee();
                }
                if (flag == 3) {
                    Log.d(LOG_TAG, "officialOnClick");
                    officialWorkClickEmployee();
                }
                break;
        }
    }

    private void notOfficialWorkClickEmployee() {
        Log.d(LOG_TAG, "flag " + flag);
        notOfficialClick++;
        if (notOfficialClick == 1) {
            removeNotOfficialWorkOnClickEmployee();
            removeOfficialWorkOnClickEmployee();
            if (flag == 1) {
                View v = createPeyContractView();
                editYorPay = (EditText) v.findViewById(R.id.editYorPey);
                editYorPay.setOnKeyListener(this);
                firstLayout.addView(v);
            }
            if (flag == 2) {
                View v = createBuisenessView();
                yorBuisenesAddSelery = (EditText) v.findViewById(R.id.yorAddBuisenesSelery);
                yorBuisenesAddSelery.setOnKeyListener(this);
                yorBuisenessWorkSalary = (EditText) v.findViewById(R.id.yorBuisenessWorkSalary);
                yorBuisenessWorkSalary.setOnKeyListener(this);
                yorOfficialBuisenesDoc = (CheckBox) v.findViewById(R.id.yorOfficialBuisenesDoc);
                yorOfficialBuisenesDoc.setOnCheckedChangeListener(this);
                firstLayout.addView(v);
            }
            if (flag == 3) {
                View v = createWorkedPensionerVitew();
                pensionerWorkSalary = (EditText) v.findViewById(R.id.pensionerWorkSalary);
                pensionerWorkSalary.setOnKeyListener(this);
                yorPensionWorkSalary = (EditText) v.findViewById(R.id.yorPensionWorkSalary);
                yorPensionWorkSalary.setOnKeyListener(this);
                pensionerOfficialWorkDoc = (CheckBox) v.findViewById(R.id.pensionerOfficialWorkDoc);
                pensionerOfficialWorkDoc.setOnCheckedChangeListener(this);
                yorOfficialPensionDoc = (CheckBox) v.findViewById(R.id.yorOfficialPensionDoc);
                yorOfficialPensionDoc.setOnCheckedChangeListener(this);

                firstLayout.addView(v);
                Log.d(LOG_TAG, "pensioner first click");
            }
        } else {
            removeNotOfficialWorkOnClickEmployee();
            removeOfficialWorkOnClickEmployee();
            notOfficialClick = 0;
            Log.d(LOG_TAG, "Notofficial 2");
        }
    }

    private void officialWorkClickEmployee() {
        officialClick++;
        if (officialClick == 1) {
            Log.d(LOG_TAG, "official");
            removeOfficialWorkOnClickEmployee();
            removeNotOfficialWorkOnClickEmployee();
            if (flag == 1) {
                View v = createEmployeeView();
                yorWorkSelery = (EditText) v.findViewById(R.id.yorWorkSalary);
                yorWorkSelery.setOnKeyListener(this);
                yorAddSalary = (EditText) v.findViewById(R.id.yorAddSalary);
                yorAddSalary.setOnKeyListener(this);
                officialWorked = (CheckBox) v.findViewById(R.id.yorOfficialDocum);
                officialWorked.setOnCheckedChangeListener(this);
                firstLayout.addView(v);
            }
            if (flag == 2) {
                View v = createBuisenessView();
                yorBuisenesAddSelery = (EditText) v.findViewById(R.id.yorAddBuisenesSelery);
                yorBuisenesAddSelery.setOnKeyListener(this);
                yorBuisenessWorkSalary = (EditText) v.findViewById(R.id.yorBuisenessWorkSalary);
                yorBuisenessWorkSalary.setOnKeyListener(this);
                yorOfficialBuisenesDoc = (CheckBox) v.findViewById(R.id.yorOfficialBuisenesDoc);
                yorOfficialBuisenesDoc.setOnCheckedChangeListener(this);
                firstLayout.addView(v);
            }
            if (flag == 3) {
                Log.d(LOG_TAG, "official pensioner");
                View v = createNotWorkPensionerView();
                yorPensionEdit = (EditText) v.findViewById(R.id.yorPensionEdit);
                yorPensionEdit.setOnKeyListener(this);
                yorPensionSalary = (EditText) v.findViewById(R.id.yorPensionSalary);
                yorPensionSalary.setOnKeyListener(this);
                yorOfficialPensionDocument = (CheckBox) v.findViewById(R.id.yorOfficialPensionDocum);
                yorOfficialPensionDocument.setOnCheckedChangeListener(this);
                firstLayout.addView(v);
            }
        } else {
            removeOfficialWorkOnClickEmployee();
            removeNotOfficialWorkOnClickEmployee();
            officialClick = 0;
            Log.d(LOG_TAG, "official 2");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d(LOG_TAG, " checked Now");
        switch (buttonView.getId()) {
            case R.id.yorOfficialDocum:
                Log.d(LOG_TAG, "norm id");
                if (isChecked) {

                    isOfficialDocum = true;
                    allWritingOfficialWorking(yorAddSalaryString, yorWorkSalaryString, isOfficialDocum);
                } else {
                    isOfficialDocum = false;
                }
                break;

            case R.id.yorOfficialBuisenesDoc:
                Log.d(LOG_TAG, "norm id");
                if (isChecked) {
                    Log.d(LOG_TAG, "check true");
                    isOfficialBuisnessDocum = true;
                    allWritingOfficialWorking(yorBuisenessWorkSalaryString, yorAddBuisenesSeleryString, isOfficialBuisnessDocum);
                } else {
                    isOfficialDocum = false;
                }
                break;
            case R.id.pensionerOfficialWorkDoc:
                Log.d(LOG_TAG, "pensionOff");
                isPensionerOfficialWorkDoc = true;
                allWritingPensionerWorking(pensionerWorkSalaryString, yorPensionWorkSeleryString, isYorOfficialPensionDoc, isPensionerOfficialWorkDoc);
                break;
            case R.id.yorOfficialPensionDoc:
                Log.d(LOG_TAG, "yorPens");
                isYorOfficialPensionDoc = true;
                allWritingPensionerWorking(pensionerWorkSalaryString, yorPensionWorkSeleryString, isYorOfficialPensionDoc, isPensionerOfficialWorkDoc);
                break;
            case R.id.yorOfficialPensionDocum:
                Log.d(LOG_TAG, "yorOfficialPensionDocum");
                isYorOfficialPensionDocument = true;
                allWritingOfficialWorking(yorPensionEditString, yorPensionSalaryString, isYorOfficialPensionDocument);
                break;
        }
    }

    private View createPeyContractView() {
        View view = getLayoutInflater().inflate(R.layout.activity_second_work_contract, null, false);

        return view;
    }

    private View createBuisenessView() {
        View v = getLayoutInflater().inflate(R.layout.activity_second_businessman, null, false);
        return v;
    }

    private View createNotWorkPensionerView() {
        View v = getLayoutInflater().inflate(R.layout.activity_second_pensioner_not_work, null, false);
        return v;
    }

    private View createWorkedPensionerVitew() {
        View v = getLayoutInflater().inflate(R.layout.activity_second_pensioner_work, null, false);


        return v;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (v.getId()) {
                case R.id.editYorPey:
                    Log.d(LOG_TAG, "editYorPay");
                    yorPay = editYorPay.getText().toString();
                    if (yorPay.length() != 0) {
                        Intent treeProfileIntent = new Intent(this, ThreeProfileActivity.class);
                        startActivity(treeProfileIntent);
                        break;
                    }
                case R.id.yorAddSalary:
                    Log.d(LOG_TAG, "yorAddSelery");
                    yorAddSalaryString = yorAddSalary.getText().toString();
                    allWritingOfficialWorking(yorAddSalaryString, yorWorkSalaryString, isOfficialDocum);
                    break;
                case R.id.yorWorkSalary:
                    Log.d(LOG_TAG, "yorWorkSelery");
                    yorWorkSalaryString = yorWorkSelery.getText().toString();
                    allWritingOfficialWorking(yorAddSalaryString, yorWorkSalaryString, isOfficialDocum);
                    break;
                case R.id.yorAddBuisenesSelery:
                    Log.d(LOG_TAG, "yorAddBuisenessSelery");
                    yorAddBuisenesSeleryString = yorBuisenesAddSelery.getText().toString();
                    allWritingOfficialWorking(yorBuisenessWorkSalaryString, yorAddBuisenesSeleryString, isOfficialBuisnessDocum);
                    break;
                case R.id.yorBuisenessWorkSalary:
                    Log.d(LOG_TAG, "yorBuisenessWorkSelery");
                    yorBuisenessWorkSalaryString = yorBuisenessWorkSalary.getText().toString();
                    allWritingOfficialWorking(yorBuisenessWorkSalaryString, yorAddBuisenesSeleryString, isOfficialBuisnessDocum);
                    break;
                case R.id.pensionerWorkSalary:
                    Log.d(LOG_TAG, "pensionerWorkSalaryString keyClick");
                    pensionerWorkSalaryString = pensionerWorkSalary.getText().toString();
                    allWritingPensionerWorking(pensionerWorkSalaryString, yorPensionWorkSeleryString, isYorOfficialPensionDoc, isPensionerOfficialWorkDoc);
                    break;
                case R.id.yorPensionWorkSalary:

                    Log.d(LOG_TAG, "yorPensionWorkSalaryString keyClick");
                    yorPensionWorkSeleryString = yorPensionWorkSalary.getText().toString();
                    allWritingPensionerWorking(pensionerWorkSalaryString, yorPensionWorkSeleryString, isYorOfficialPensionDoc, isPensionerOfficialWorkDoc);
                    break;
                case R.id.yorPensionEdit:
                    Log.d(LOG_TAG, "yorPensionEdit keyClick");
                      yorPensionEditString = yorPensionEdit.getText().toString();
                    allWritingOfficialWorking(yorPensionEditString, yorPensionSalaryString, isYorOfficialPensionDocument);
                    break;
                case R.id.yorPensionSalary:
                    Log.d(LOG_TAG, "yorPensionSalary keyClick");
                    yorPensionSalaryString = yorPensionSalary.getText().toString();
                    allWritingOfficialWorking(yorPensionEditString, yorPensionSalaryString, isYorOfficialPensionDocument);

                    break;
            }
        }
        return false;
    }

    private void allWritingOfficialWorking(String firstEditText, String secondEditText, boolean isOfficical) {
        Log.d(LOG_TAG, "firstEdit " + firstEditText + " secondEdit " + secondEditText + " boolean " + isOfficical);
        if (firstEditText != null && secondEditText != null) {
            Log.d(LOG_TAG, "not null");
            if (firstEditText.length() != 0 && secondEditText.length() != 0 && isOfficical) {
                Intent threeProfileIntent = new Intent(this, ThreeProfileActivity.class);
                startActivity(threeProfileIntent);
            }
        } else {
            Log.d(LOG_TAG, "not normal");
        }
    }

    private void allWritingPensionerWorking(String firstEditText, String secondEditText, boolean isFirst, boolean isSecond) {
        Log.d(LOG_TAG, "firstEditText " + firstEditText + " secondEditText " + secondEditText + " booleanFirst " + isFirst + " booleanSecond " + isSecond);
        if (firstEditText != null && secondEditText != null) {
            Log.d(LOG_TAG, "not null");
            if (firstEditText.length() != 0 && secondEditText.length() != 0 && isFirst && isSecond) {
                Intent threeProfileIntent = new Intent(this, ThreeProfileActivity.class);
                startActivity(threeProfileIntent);
            }
        } else {
            Log.d(LOG_TAG, "not normal");
        }

    }


}