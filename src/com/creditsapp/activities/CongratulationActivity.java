package com.creditsapp.activities;

import android.os.Bundle;
import android.view.View;
import com.creditsapp.R;

/**
 * Created with IntelliJ IDEA.
 * User: Windows
 * Date: 22.09.14
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class CongratulationActivity extends ActionBarMenuActivity implements View.OnClickListener {
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
    }
    @Override
    protected View getContent() {
        View view = getLayoutInflater().inflate(R.layout.activity_congratulation, null, false);
        return view;
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
