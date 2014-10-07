package com.creditsapp.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.creditsapp.R;
import com.creditsapp.fragments.BackAndSettingsFragment;

/**
 * Created by Alex on 14.09.2014.
 */
public class NavigationActivity extends Activity implements OnTouchListener, View.OnClickListener {
    private final String LOG_TAG = "NavigationActivity";
    private float firstTouch;
    private ViewFlipper flipper;
    private LayoutInflater inflater;
    private BackAndSettingsFragment backAndSettingsFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        mainLayout.setOnTouchListener(this);

        flipper = (ViewFlipper) findViewById(R.id.flipper);
        inflater = getLayoutInflater();
        addFragment();
        initFlipper();

    }

     private void addFragment(){
         FragmentTransaction frgTrans = getFragmentManager().beginTransaction();
         backAndSettingsFragment = new BackAndSettingsFragment(false);
         frgTrans.add(R.id.navigFrgView, backAndSettingsFragment);
         frgTrans.commit();
     }


    private void initFlipper() {
        View navig_cash = inflater.inflate(R.layout.navig_cash, null, false);
        TextView tvCash = (TextView) navig_cash.findViewById(R.id.tvCash);
        tvCash.setOnClickListener(this);
        flipper.addView(navig_cash);

        View navig_credit_bonds = inflater.inflate(R.layout.navig_credit_bonds, null, false);
        TextView tvCreditBonds = (TextView) navig_credit_bonds.findViewById(R.id.tvCreditBonds);
        tvCreditBonds.setOnClickListener(this);
        flipper.addView(navig_credit_bonds);

        View navig_car_credit = inflater.inflate(R.layout.navig_car_credit, null, false);
        TextView tvCarCredit = (TextView) navig_car_credit.findViewById(R.id.tvCarCredit);
        tvCarCredit.setOnClickListener(this);
        flipper.addView(navig_car_credit);

        View navig_mortgage = inflater.inflate(R.layout.navig_mortgage, null, false);
        TextView tvMortgage = (TextView) navig_mortgage.findViewById(R.id.tvMortgage);
        tvMortgage.setOnClickListener(this);
        flipper.addView(navig_mortgage);


        View navig_special_offer = inflater.inflate(R.layout.navig_special_offer, null, false);
        TextView tvSpecialOffer = (TextView) navig_special_offer.findViewById(R.id.tvSpecialOffer);
        tvSpecialOffer.setOnClickListener(this);
        flipper.addView(navig_special_offer);

    }


    public boolean onTouch(View view, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstTouch= event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float toPosition = event.getX();

               float deltaX = firstTouch - toPosition;

                if (deltaX > 50) {
                    flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.go_next_in));
                    flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.go_next_out));
                    flipper.showNext();
                } else if (deltaX<-50) {
                    flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.go_prev_in));
                    flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.go_prev_out));
                    flipper.showPrevious();
                }
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCash:
                Intent startRegistrationProfil = new Intent(this,StartRegistrationProfileActivity.class);
                startRegistrationProfil.addFlags(2);
                startActivity(startRegistrationProfil);
                Log.d(LOG_TAG, "navig_cash");
                break;
            case R.id.tvCreditBonds:
                Log.d(LOG_TAG, "navig_credit_bonds");
                break;
            case R.id.tvCarCredit:
                Intent intentTesr = new Intent(this,CongratulationActivity.class);
                startActivity(intentTesr);
                Log.d(LOG_TAG, "tvCarCredit");
                break;
            case R.id.tvMortgage:
                Intent intent3 = new Intent(this,ThreeProfileActivity.class);
                startActivity(intent3);
                Log.d(LOG_TAG, "tvMortgage");
                break;
            case R.id.tvSpecialOffer:
                Intent intent = new Intent(this,SpecialOfferActivity.class);
                startActivity(intent);
                Log.d(LOG_TAG, "tvSpecialOffer");
                break;
            default:
        }
    }


}