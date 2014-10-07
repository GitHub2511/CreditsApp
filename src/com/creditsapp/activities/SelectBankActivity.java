package com.creditsapp.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import com.creditsapp.R;
import com.creditsapp.entity.BankDescription;
import com.creditsapp.fragments.BackAndSettingsFragment;
import com.creditsapp.tools.SelectedBankListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Windows
 * Date: 26.09.14
 * Time: 16:22
 * To change this template use File | Settings | File Templates.
 */
public class SelectBankActivity extends Activity {
    private final String LOG_TAG = "SelectBankActivity";
    private BackAndSettingsFragment backAndSettingsFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bank);

       addFragment();
       initView();
    }

    private void initView(){
        ListView lvBankList = (ListView) findViewById(R.id.lvBankList);

       List<BankDescription> bankDescriptionList = new ArrayList<BankDescription>();


        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.round_white);

        BankDescription description1 = new BankDescription();
        description1.setImageView(imageView);
        description1.setDurationCredit(8);
        description1.setMonthlyPayment(300);

        Log.d(LOG_TAG, " SelectBankActivity>>>  durationcredit " + description1.getDurationCredit() + "    MonthlyPayment" + description1.getMonthlyPayment());

         bankDescriptionList.add(description1);





       SelectedBankListAdapter listAdapter = new SelectedBankListAdapter(this,bankDescriptionList);
        lvBankList.setAdapter(listAdapter);



    }


    private void addFragment(){
        FragmentTransaction frgTrans = getFragmentManager().beginTransaction();
        backAndSettingsFragment = new BackAndSettingsFragment("Оберіть банк");
        frgTrans.add(R.id.frgViewSelectBank, backAndSettingsFragment);
        frgTrans.commit();
    }

}
