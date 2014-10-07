package com.creditsapp.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.creditsapp.R;
import com.creditsapp.activities.FirstProfileClientActivity;
import com.creditsapp.activities.SecondProfileClientActivity;


/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 25.09.14
 * Time: 23:04
 * To change this template use File | Settings | File Templates.
 */
public class FrgRoundButton extends Fragment implements View.OnClickListener {
    private  int currentStep;

    public FrgRoundButton(){

    }

    public FrgRoundButton(int currentStep) {
        this.currentStep = currentStep;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        View  view = inflater.inflate(R.layout.frg_client_profile,null,false);

        Button btFgrProfileStep1 = (Button) view.findViewById(R.id.btFgrProfileStep1);
        btFgrProfileStep1.setBackgroundResource(R.drawable.round_white);
        btFgrProfileStep1.setOnClickListener(this);

        Button btFgrProfileStep2 = (Button) view.findViewById(R.id.btFgrProfileStep2);
        btFgrProfileStep2.setBackgroundResource(R.drawable.round_white);
        btFgrProfileStep2.setOnClickListener(this);


        Button btFgrProfileStep3 = (Button) view.findViewById(R.id.btFgrProfileStep3);
        btFgrProfileStep3.setBackgroundResource(R.drawable.round_white);
        btFgrProfileStep3.setOnClickListener(this);

        switch (currentStep){
            case 1:
                btFgrProfileStep1.setBackgroundResource(R.drawable.round_green);
                btFgrProfileStep1.setText("1");
                break;
            case 2:
                btFgrProfileStep2.setBackgroundResource(R.drawable.round_green);
                btFgrProfileStep2.setText("2");
                break;
            case 3:
                btFgrProfileStep3.setBackgroundResource(R.drawable.round_green);
                btFgrProfileStep3.setText("3");
                break;
            default:
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btFgrProfileStep1:

                break;
            case R.id.btFgrProfileStep2:
                if(FirstProfileClientActivity.goToSecondActivity == 1){
                     Intent secondProfileIntent = new Intent(getActivity(),SecondProfileClientActivity.class);
                    startActivity(secondProfileIntent);
                }
                break;
            case R.id.btFgrProfileStep3:
                break;

        }
    }
}
