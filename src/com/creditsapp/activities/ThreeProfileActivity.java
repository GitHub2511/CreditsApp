package com.creditsapp.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.creditsapp.R;
import com.creditsapp.fragments.BackAndSettingsFragment;
import com.creditsapp.fragments.FrgRoundButton;


/**
 * Created with IntelliJ IDEA.
 * User: Windows
 * Date: 23.09.14
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
public class ThreeProfileActivity extends Activity implements View.OnClickListener {
    private LinearLayout lnLStitching;
    private LinearLayout lnLNoStitching;

    private   TextView tvStitching;
    private   TextView tvNoStitching;

    private boolean isSelectCloseCrerit = false;
    private boolean isAddProfileItem = false;
    private   View itemProfile3;
    private   EditText etItemProfile;
    private  FrameLayout layoutForProfileItem;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_profile_3);
        addFraglent();
        initView();


    }

    private void initView(){
        TextView tvActProfileNoCredit= (TextView) findViewById(R.id.tvActProfileNoCredit);
        tvActProfileNoCredit.setOnClickListener(this);

        TextView tvActProfileCloseCrerit= (TextView) findViewById(R.id.tvActProfileCloseCrerit);
        tvActProfileCloseCrerit.setOnClickListener(this);

        TextView tvActProfileOpenCredit= (TextView) findViewById(R.id.tvActProfileOpenCredit);
        tvActProfileOpenCredit.setOnClickListener(this);

        lnLStitching = (LinearLayout) findViewById(R.id.lnLStitching);
        lnLNoStitching = (LinearLayout) findViewById(R.id.lnLNoStitching);
        layoutForProfileItem=(FrameLayout) findViewById(R.id.flForProfileItem);

        tvStitching = new TextView(this);
        tvStitching.setTextSize(19);
        tvStitching.setText("з прострочками");

        tvNoStitching = new TextView(this);
        tvNoStitching.setTextSize(19);
        tvNoStitching.setText("без прострочок");
        LayoutInflater inflater = getLayoutInflater();

        itemProfile3 = inflater.inflate(R.layout.item_profile_3,null,false);
        etItemProfile = (EditText) itemProfile3.findViewById(R.id.etItemProfile);

    }

    private void addFraglent(){
        FragmentTransaction frgTrans = getFragmentManager().beginTransaction();
        BackAndSettingsFragment backAndSettingsFragment = new BackAndSettingsFragment();
        frgTrans.add(R.id.frgViewProf3, backAndSettingsFragment);
        //  frgTrans.commit();

        FrgRoundButton frgRoundButton = new FrgRoundButton(3);
        frgTrans.add(R.id.frgViewThreeProfile,frgRoundButton);
        frgTrans.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvActProfileNoCredit:
                break;

            case  R.id.tvActProfileCloseCrerit:
                showResponsOptions();
                break;

            case R.id.tvActProfileOpenCredit:
                showProfileItem();
                break;
        }
    }

    private void addResponseOptions(){
        lnLStitching.addView(tvStitching);
        lnLNoStitching.addView(tvNoStitching);
    }

    private void showResponsOptions(){
        if(isSelectCloseCrerit == false ){
            addResponseOptions();
            isSelectCloseCrerit = true;
        }
    }

    private void showProfileItem(){
        if(isAddProfileItem == false){
            layoutForProfileItem.addView(itemProfile3);
            isAddProfileItem=true;
        }

    }

}
