package com.creditsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;
import com.creditsapp.R;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 25.10.14
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
public class YorChoiceServicesActivity extends ActionBarMenuActivity implements View.OnClickListener, View.OnTouchListener {

    private final String LOG_TAG = "YorChoiceServicesActivity";
    private float firstTouch;
    private ViewFlipper flipper;
    private LayoutInflater inflater;
    private Button creditsRightButton;
    private Button creditsLeftButton;
    private Button depositRightButton;
    private Button depositLeftButton;
    private Button bankServiceRightButton;
    private Button bankServiceLeftButton;
    private Button creditButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flipper = (ViewFlipper) findViewById(R.id.flipperChoice);
        flipper.setOnTouchListener(this);
        inflater = getLayoutInflater();
        initFlipper();
    }

    private void initFlipper() {
        View yorChoiceCredits = inflater.inflate(R.layout.activity_yor_choice_creditss, null, false);
        creditsRightButton = (Button) yorChoiceCredits.findViewById(R.id.rightLocationButtonInCredits);
        creditsLeftButton = (Button) yorChoiceCredits.findViewById(R.id.leftLocationButtonInCredits);
        creditButton = (Button) yorChoiceCredits.findViewById(R.id.creditButton);
        creditButton.setOnClickListener(this);
        creditsRightButton.setOnClickListener(this);
        creditsLeftButton.setOnClickListener(this);
        View yorChoiceDeposit = inflater.inflate(R.layout.activity_yor_choice_deposits, null, false);
        depositRightButton = (Button) yorChoiceDeposit.findViewById(R.id.rightLocationButtonInDeposit);
        depositLeftButton = (Button) yorChoiceDeposit.findViewById(R.id.leftLocationButtonInDeposit);
        depositRightButton.setOnClickListener(this);
        depositLeftButton.setOnClickListener(this);
        View yorChoiceBankService = inflater.inflate(R.layout.activity_yor_choice_bank_services, null, false);
        bankServiceRightButton = (Button) yorChoiceBankService.findViewById(R.id.rightLocationButtonInBankService);
        bankServiceLeftButton = (Button) yorChoiceBankService.findViewById(R.id.leftLocationButtonInBankService);
        bankServiceRightButton.setOnClickListener(this);
        bankServiceLeftButton.setOnClickListener(this);
        flipper.addView(yorChoiceCredits);
        flipper.addView(yorChoiceDeposit);
        flipper.addView(yorChoiceBankService);
    }

    @Override
    protected View getContent() {
        View view = getLayoutInflater().inflate(R.layout.activity_yor_choice_flipper, null, false);

        return view;
    }

    @Override
    public void onClick(View v) {
         int idButtons = v.getId();

        if(idButtons == R.id.rightLocationButtonInCredits || idButtons == R.id.rightLocationButtonInDeposit
                || idButtons == R.id.rightLocationButtonInBankService){
            toRightMove();
        }
        if (idButtons == R.id.leftLocationButtonInCredits || idButtons == R.id.leftLocationButtonInDeposit
                || idButtons == R.id.leftLocationButtonInBankService){
            toLeftMove();
        }
        if(idButtons == R.id.creditButton){
            Log.d(LOG_TAG, "1");
            Intent navigationIntent = new Intent(this, NavigationActivity.class);
            Log.d(LOG_TAG, "2");
            startActivity(navigationIntent);
//            Intent intent = new Intent(this, SelectBankActivity.class);
//            startActivity(intent);
            overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);
            Log.d(LOG_TAG, "3");
        }
    }

    public boolean onTouch(View view, MotionEvent event) {
        Log.d(LOG_TAG, "Click start");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstTouch = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float toPosition = event.getX();

                float deltaX = firstTouch - toPosition;

                if (deltaX > 50) {
                  toRightMove();
                } else if (deltaX < -50) {
                    toLeftMove();
                }
            default:
                break;
        }
        return true;
    }
    private void toRightMove(){
        flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.go_next_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.go_next_out));
        flipper.showNext();
    }
    private void toLeftMove(){
        flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.go_prev_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.go_prev_out));
        flipper.showPrevious();
    }
}