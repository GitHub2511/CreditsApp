package com.creditsapp.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import com.creditsapp.R;
import com.creditsapp.fragments.BackAndSettingsFragment;

/**
 * Created with IntelliJ IDEA.
 * User: Windows
 * Date: 22.09.14
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class CongratulationActivity extends Activity implements View.OnClickListener {
    private BackAndSettingsFragment backAndSettingsFragment;

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_congratulation);
        addFragment();
      //  initView();
    }

    private void initView(){
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.congratulation);
        LinearLayout animView = (LinearLayout) findViewById(R.id.animViewCongrarulation);
        animView.setAnimation(anim);

    }

    private void addFragment(){
        FragmentTransaction frgTrans = getFragmentManager().beginTransaction();
         backAndSettingsFragment = new BackAndSettingsFragment();
        frgTrans.add(R.id.frgViewCongratulation, backAndSettingsFragment);
        frgTrans.commit();

        Button btActCongratFinish = (Button) findViewById(R.id.btActCongratFinish);
        btActCongratFinish.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case  R.id.btActCongratFinish:
               finish();
            break;
       }
    }
}
