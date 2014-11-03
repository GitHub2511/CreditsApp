package com.creditsapp.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import com.creditsapp.R;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 03.11.14
 * Time: 12:50
 * To change this template use File | Settings | File Templates.
 */
public class DialogYorChoise extends AlertDialog {
    public DialogYorChoise(Activity activity) {
        super(activity);
        LayoutInflater inflater = activity.getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_yor_choice,null,false);
        setView(view);
        show();

    }
}
