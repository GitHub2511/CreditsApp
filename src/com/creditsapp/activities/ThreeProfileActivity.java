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
    private TextView tvStitching;
    private TextView tvNoStitching;
    private View itemProfile3;
    private EditText etItemProfile;
    private FrameLayout layoutForProfileItem;
    private LinearLayout openCreditYesLayout;
    private LinearLayout openCreditNoLayout;
    private int closeCredit = 0;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_profile_3);
        addFraglent();
        initView();
    }

    private void initView() {

        TextView tvActProfileNoCredit = (TextView) findViewById(R.id.tvActProfileNoCreditNever);
        tvActProfileNoCredit.setOnClickListener(this);
        TextView tvActProfileCloseCrerit = (TextView) findViewById(R.id.tvActProfileCloseCrerit);
        tvActProfileCloseCrerit.setOnClickListener(this);
        TextView tvActProfileOpenCredit = (TextView) findViewById(R.id.tvActProfileOpenCredit);
        tvActProfileOpenCredit.setOnClickListener(this);

        lnLStitching = (LinearLayout) findViewById(R.id.lnLStitching);
        lnLNoStitching = (LinearLayout) findViewById(R.id.lnLNoStitching);
        layoutForProfileItem = (FrameLayout) findViewById(R.id.flForProfileItem);
        openCreditNoLayout = (LinearLayout) findViewById(R.id.openCreditNoLayout);
        openCreditYesLayout = (LinearLayout) findViewById(R.id.openCreditYesLayout);

        tvStitching = new TextView(this);
        tvStitching.setTextSize(19);
        tvStitching.setText("з прострочками");
        tvNoStitching = new TextView(this);
        tvNoStitching.setTextSize(19);
        tvNoStitching.setText("без прострочок");
        LayoutInflater inflater = getLayoutInflater();

        itemProfile3 = inflater.inflate(R.layout.item_profile_3, null, false);
        etItemProfile = (EditText) itemProfile3.findViewById(R.id.etItemProfile);

    }

    private void addFraglent() {
        FragmentTransaction frgTrans = getFragmentManager().beginTransaction();
        BackAndSettingsFragment backAndSettingsFragment = new BackAndSettingsFragment();
        frgTrans.add(R.id.frgViewProf3, backAndSettingsFragment);
        //  frgTrans.commit();

        FrgRoundButton frgRoundButton = new FrgRoundButton(3);
        frgTrans.add(R.id.frgViewThreeProfile, frgRoundButton);
        frgTrans.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tvActProfileCloseCrerit:
                closeCredit ++;
                if(closeCredit == 1){
                    removeCloseCreditsComponent();
                    removeOpenCreditsComponent();
                } else{
                    showResponsOptions();
                    closeCredit = 0;
                }
                break;

            case R.id.tvActProfileOpenCredit:
                closeCredit++;
                if(closeCredit == 1){
                    removeOpenCreditsComponent();
                    removeCloseCreditsComponent();
                }   else{
                    showProfileItem();
                    closeCredit = 0;
                }
                break;
            case R.id.tvActProfileNoCreditNever:
                break;
        }
    }

    private void addResponseOptions() {
        lnLStitching.addView(tvStitching);
        lnLNoStitching.addView(tvNoStitching);
    }

    private void showResponsOptions() {

        addResponseOptions();

    }

    private void showProfileItem() {

        openCreditYesLayout.addView(tvStitching);
        openCreditNoLayout.addView(tvNoStitching);
        layoutForProfileItem.addView(itemProfile3);

    }
    private void removeOpenCreditsComponent(){
        lnLStitching.removeAllViews();
        lnLNoStitching.removeAllViews();
    }
    private void removeCloseCreditsComponent(){
        openCreditYesLayout.removeAllViews();
        openCreditNoLayout.removeAllViews();
        layoutForProfileItem.removeAllViews();
    }
}
