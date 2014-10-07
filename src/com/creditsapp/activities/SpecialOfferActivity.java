package com.creditsapp.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;
import com.creditsapp.R;
import com.creditsapp.fragments.BackAndSettingsFragment;

/**
 * Created with IntelliJ IDEA.
 * User: Windows
 * Date: 15.09.14
 * Time: 11:00
 * To change this template use File | Settings | File Templates.
 */
public class SpecialOfferActivity extends Activity implements View.OnTouchListener, View.OnClickListener {

    private static final String LOG_TAG = "SpecialOfferActivity";
    private ViewFlipper flipper;
    private float firstTouch;;
    private LayoutInflater inflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_offer);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout_offer_act);
        mainLayout.setOnTouchListener(this);
        flipper = (ViewFlipper) findViewById(R.id.flipper_offer_act);
        inflater = getLayoutInflater();
        addFragment();
        initFlipper();
    }

       private void addFragment(){
           FragmentTransaction frgTrans = getFragmentManager().beginTransaction();
           BackAndSettingsFragment backAndSettingsFragment = new BackAndSettingsFragment();
           frgTrans.add(R.id.spcialFrgView, backAndSettingsFragment);
           frgTrans.commit();
       }


    private void initFlipper(){
        View special_repair = inflater.inflate(R.layout.special_repair,null,false);
         special_repair.setOnClickListener(this);
        View special_relax = inflater.inflate(R.layout.special_relax,null,false);
          special_relax.setOnClickListener(this);
        View special_shopping = inflater.inflate(R.layout.special_shopping,null,false);
             special_shopping.setOnClickListener(this);
        View special_credit_for_gift = inflater.inflate(R.layout.special_credit_for_gift,null,false);
             special_credit_for_gift.setOnClickListener(this);
        View special_credit_for_wedding = inflater.inflate(R.layout.special_credit_for_wedding,null,false);
             special_credit_for_wedding.setOnClickListener(this);
        View special_closure_credit = inflater.inflate(R.layout.special_closure_credit,null,false);
             special_closure_credit.setOnClickListener(this);
        View special_municipal_debts = inflater.inflate(R.layout.special_municipal_debts,null,false);
             special_municipal_debts.setOnClickListener(this);
        View special_for_study = inflater.inflate(R.layout.special_for_study,null,false);
             special_for_study.setOnClickListener(this);
        View special_for_treatment = inflater.inflate(R.layout.special_for_treatment,null,false);
             special_for_treatment.setOnClickListener(this);

        flipper.addView(special_repair);
        flipper.addView(special_relax);
        flipper.addView(special_shopping);
        flipper.addView(special_credit_for_gift);
        flipper.addView(special_credit_for_wedding);
        flipper.addView(special_closure_credit);
        flipper.addView(special_municipal_debts);
        flipper.addView( special_for_study);
        flipper.addView( special_for_treatment);
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
        Log.d(LOG_TAG, "click start "+ view.getId());

              Intent startRegistrationProfileIntent = new Intent(this, StartRegistrationProfileActivity.class);
        startRegistrationProfileIntent.addFlags(1);
              startActivity(startRegistrationProfileIntent);

    }
}
