package com.creditsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.creditsapp.R;

/**
 * Created by Alex on 14.09.2014.
 */
public class NavigationActivity extends ActionBarMenuActivity implements OnTouchListener, View.OnClickListener {
    private final String LOG_TAG = "NavigationActivity";
    private float firstTouch;
    private ViewFlipper flipper;
    private LayoutInflater inflater;
    private Button cashButton;
    private Button creditPledgesButton;
    private Button avtoCreditButton;
    private Button specialPropouseButton;
    private Button leftInCashButton;
    private Button rightInCashButton;
    private Button rightLocationButtonInCreditBonds;
    private Button leftLocationButtonInCreditBonds;
    private Button rightLocationButtonInMortgages;
    private Button leftLocationButtonInMortgages;
    private Button rightLocationButtonInCreditsCar;
    private Button leftLocationButtonInCreditsCar;
    private Button tvCarCredit;
    private Button rightLocationButtonInSpecialOffer;
    private Button leftLocationButtonInSpecialOffer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        flipper.setOnTouchListener(this);
        inflater = getLayoutInflater();
        initFlipper();
    }

    private void initFlipper() {
        View navigCash = inflater.inflate(R.layout.navig_cashs, null, false);
        cashButton = (Button) navigCash.findViewById(R.id.cashButton);
        leftInCashButton = (Button) navigCash.findViewById(R.id.leftLocationButtonInCash);
        rightInCashButton = (Button) navigCash.findViewById(R.id.rightLocationButtonInCash);
        leftInCashButton.setOnClickListener(this);
        rightInCashButton.setOnClickListener(this);
        cashButton.setOnClickListener(this);
        flipper.addView(navigCash);

        View navigCreditBonds = inflater.inflate(R.layout.navig_credit_bondss, null, false);
        creditPledgesButton = (Button) navigCreditBonds.findViewById(R.id.creditsButton);
        leftLocationButtonInCreditBonds = (Button) navigCreditBonds.findViewById(R.id.leftLocationButtonInCreditBonds);
        rightLocationButtonInCreditBonds = (Button) navigCreditBonds.findViewById(R.id.rightLocationButtonInCreditBonds);
        leftLocationButtonInCreditBonds.setOnClickListener(this);
        rightLocationButtonInCreditBonds.setOnClickListener(this);
        creditPledgesButton.setOnClickListener(this);
        flipper.addView(navigCreditBonds);


        View navigMortgage = inflater.inflate(R.layout.navig_mortgages, null, false);
        avtoCreditButton = (Button) navigMortgage.findViewById(R.id.avtoCredit);
        leftLocationButtonInMortgages = (Button) navigMortgage.findViewById(R.id.leftLocationButtonInMortgages);
        rightLocationButtonInMortgages = (Button) navigMortgage.findViewById(R.id.rightLocationButtonInMortgages);
        leftLocationButtonInMortgages.setOnClickListener(this);
        rightLocationButtonInMortgages.setOnClickListener(this);
        avtoCreditButton.setOnClickListener(this);
        flipper.addView(navigMortgage);

        View navigCarCredit = inflater.inflate(R.layout.navig_car_credits, null, false);
        tvCarCredit = (Button) navigCarCredit.findViewById(R.id.mortgage);
        leftLocationButtonInCreditsCar = (Button) navigCarCredit.findViewById(R.id.leftLocationButtonInCreditsCar);
        rightLocationButtonInCreditsCar = (Button) navigCarCredit.findViewById(R.id.rightLocationButtonInCreditsCar);
        leftLocationButtonInCreditsCar.setOnClickListener(this);
        rightLocationButtonInCreditsCar.setOnClickListener(this);
        tvCarCredit.setOnClickListener(this);
        flipper.addView(navigCarCredit);

        View navigSpecialOffer = inflater.inflate(R.layout.navig_special_offer, null, false);
        specialPropouseButton = (Button) navigSpecialOffer.findViewById(R.id.specialPropouse);
        rightLocationButtonInSpecialOffer = (Button) navigSpecialOffer.findViewById(R.id.rightLocationButtonInSpecialOffer);
        leftLocationButtonInSpecialOffer = (Button) navigSpecialOffer.findViewById(R.id.leftLocationButtonInSpecialOffer);
        rightLocationButtonInSpecialOffer.setOnClickListener(this);
        leftLocationButtonInSpecialOffer.setOnClickListener(this);
        specialPropouseButton.setOnClickListener(this);
        flipper.addView(navigSpecialOffer);
    }

    protected View getContent() {
        View v = getLayoutInflater().inflate(R.layout.activity_navigation, null, false);
        return v;
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
                    flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.go_next_in));
                    flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.go_next_out));
                    flipper.showNext();
                } else if (deltaX < -50) {
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
        int idButtons = view.getId();

        if (idButtons == R.id.leftLocationButtonInCash || idButtons == R.id.leftLocationButtonInCreditBonds
                || idButtons == R.id.leftLocationButtonInMortgages || idButtons == R.id.leftLocationButtonInCreditsCar
                || idButtons == R.id.leftLocationButtonInSpecialOffer) {
            toLeftMove();
        } else {
            if (idButtons == R.id.rightLocationButtonInCash || idButtons == R.id.rightLocationButtonInCreditBonds
                    || idButtons == R.id.rightLocationButtonInMortgages || idButtons == R.id.rightLocationButtonInCreditsCar
                    || idButtons == R.id.rightLocationButtonInSpecialOffer) {
                toRightMove();
            }
        }
        switch (idButtons) {
            case R.id.cashButton:
                Intent startRegistrationProfil = new Intent(this, StartRegistrationProfileActivity.class);
                startRegistrationProfil.addFlags(2);
                startActivity(startRegistrationProfil);
                Log.d(LOG_TAG, "navig_cash");
                break;
            case R.id.creditButton:
                Log.d(LOG_TAG, "navig_credit_bonds");
                Intent yorChoiceIntent = new Intent(this, YorChoiceServicesActivity.class);
                startActivity(yorChoiceIntent);
                break;
            case R.id.mortgage:
                Intent intentTesr = new Intent(this, CongratulationActivity.class);
                startActivity(intentTesr);
                Log.d(LOG_TAG, "tvCarCredit");
                break;
            case R.id.avtoCredit:
                Intent intent3 = new Intent(this, ThreeProfileActivity.class);
                startActivity(intent3);
                Log.d(LOG_TAG, "tvMortgage");
                break;
            case R.id.specialPropouse:
                Intent intent = new Intent(this, SpecialOfferActivity.class);
                startActivity(intent);
                Log.d(LOG_TAG, "tvSpecialOffer");
                break;
            default:
        }
    }

    private void toRightMove() {
        flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.go_next_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.go_next_out));
        flipper.showNext();
    }

    private void toLeftMove() {
        flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.go_prev_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.go_prev_out));
        flipper.showPrevious();
    }
}