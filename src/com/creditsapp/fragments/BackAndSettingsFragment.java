package com.creditsapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.creditsapp.R;
import com.creditsapp.dialogs.DialogSettings;

/**
 * Created with IntelliJ IDEA.
 * User: Windows
 * Date: 22.09.14
 * Time: 13:02
 * To change this template use File | Settings | File Templates.
 */
public class BackAndSettingsFragment extends Fragment implements View.OnClickListener {
    private String title;
    private boolean isVisible = true;

    public BackAndSettingsFragment(){

    }

    public BackAndSettingsFragment(boolean isVisible){
        this.isVisible = isVisible;
    }

    public BackAndSettingsFragment(String title, boolean visible) {
        this.title = title;
        isVisible = visible;
    }

    public BackAndSettingsFragment(String title){
        this.title = title;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        View  view = inflater.inflate(R.layout.frg_back_and_settings,null,false);
        Button  btFrgBackStngBack = (Button) view.findViewById(R.id.btFrgBackStngBack);
        if(!isVisible){
            btFrgBackStngBack.setVisibility(View.GONE);
        }

        btFrgBackStngBack.setOnClickListener(this);

        Button btFrgBackStngSettings = (Button) view.findViewById(R.id.btFrgBackStngSettings);
        btFrgBackStngSettings.setOnClickListener(this);

        TextView tvTitle = (TextView) view.findViewById(R.id.tvFrgBackStngTitle);
        if(title != null){
            tvTitle.setText(title);
        }


        return view;
    }




    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case  R.id.btFrgBackStngBack:
               getActivity().finish();
               break;

           case  R.id.btFrgBackStngSettings:
               new DialogSettings(getActivity());
               break;
           default:
               return;
       }
    }
}
