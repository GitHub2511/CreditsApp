package com.creditsapp.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
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
public class SpecialOfferActivity extends ActionBarMenuActivity implements View.OnTouchListener, View.OnClickListener{

    private static final String LOG_TAG = "SpecialOfferActivity";
    private ViewFlipper flipper;
    private float firstTouch;
    private LayoutInflater inflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayoutOfferAct);
        mainLayout.setOnTouchListener(this);
        flipper = (ViewFlipper) findViewById(R.id.flipperOfferAct);
        inflater = getLayoutInflater();
        initFlipper();
    }

    @Override
    protected View getContent() {

        View view = getLayoutInflater().inflate(R.layout.activity_special_offer, null, false);

        return view;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void addFragment(){
        FragmentTransaction frgTrans = getFragmentManager().beginTransaction();
        BackAndSettingsFragment backAndSettingsFragment = new BackAndSettingsFragment();
        frgTrans.add(R.id.spcialFrgView, backAndSettingsFragment);
        frgTrans.commit();
    }


    private void initFlipper(){
        View special_repair = inflater.inflate(R.layout.special_repair,null,false);
        TextView repairText = (TextView) special_repair.findViewById(R.id.textRepair);
        repairText.setOnClickListener(this);
        View special_relax = inflater.inflate(R.layout.special_relax,null,false);
        TextView relaxText = (TextView) special_relax.findViewById(R.id.relaxatingText);
        relaxText.setOnClickListener(this);
        View special_shopping = inflater.inflate(R.layout.special_shopping,null,false);
        TextView shopingText = (TextView) special_shopping.findViewById(R.id.shopingText);
        shopingText.setOnClickListener(this);
        View special_credit_for_gift = inflater.inflate(R.layout.special_credit_for_gift,null,false);
        TextView presentText = (TextView) special_credit_for_gift.findViewById(R.id.prezentText);
        presentText.setOnClickListener(this);
        View special_credit_for_wedding = inflater.inflate(R.layout.special_credit_for_wedding,null,false);
        TextView weddingText = (TextView) special_credit_for_wedding.findViewById(R.id.weddingText);
        weddingText.setOnClickListener(this);
        View special_closure_credit = inflater.inflate(R.layout.special_closure_credit,null,false);
        TextView closeCreditText = (TextView) special_closure_credit.findViewById(R.id.closeCreditText);
        closeCreditText.setOnClickListener(this);
        View special_municipal_debts = inflater.inflate(R.layout.special_municipal_debts,null,false);
        TextView municipalText = (TextView) special_municipal_debts.findViewById(R.id.municipalText);
        municipalText.setOnClickListener(this);
        View special_for_study = inflater.inflate(R.layout.special_for_study,null,false);
        TextView specialStadyText = (TextView) special_for_study.findViewById(R.id.specialStadyText);
        specialStadyText.setOnClickListener(this);
        View special_for_treatment = inflater.inflate(R.layout.special_for_treatment,null,false);
        TextView specialTreatmentText = (TextView) special_for_treatment.findViewById(R.id.specialTreatmentText);
        specialTreatmentText.setOnClickListener(this);

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
                Log.d(LOG_TAG, "touch");
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
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.textRepair :
                Log.d(LOG_TAG, "click");
                startRegstrationActivity();
                break;
            case R.id.relaxatingText :

                break;
            case R.id.shopingText :

                break;
            case R.id.prezentText :

                break;
            case R.id.weddingText :

                break;
            case R.id.closeCreditText :

                break;
            case R.id.municipalText :

                break;
            case R.id.specialStadyText:

                break;
            case R.id.specialTreatmentText:

                break;
        }

    }
    private void startRegstrationActivity(){
        Intent startRegistrationIntent = new Intent(this, StartRegistrationProfileActivity.class);
        startActivity(startRegistrationIntent);
    }
}
