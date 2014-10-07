package com.creditsapp.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import com.creditsapp.R;


/**
 * Created with IntelliJ IDEA.
 * User: Windows
 * Date: 18.09.14
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class DialogSettings extends AlertDialog {

    public DialogSettings(Activity activity){
        super(activity);

        LayoutInflater inflater = activity.getLayoutInflater();

        View view = inflater.inflate(R.layout.diolog_settings,null,false);
        setView(view);
        show();
    }

}
