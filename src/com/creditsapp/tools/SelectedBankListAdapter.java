package com.creditsapp.tools;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.creditsapp.R;
import com.creditsapp.entity.BankDescription;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Windows
 * Date: 26.09.14
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 */
public class SelectedBankListAdapter extends BaseAdapter {
    private final String LOG_TAG = "BankListAdapter";
    private Activity  activity;
    private List<BankDescription> bankDescriptionList;
    private LayoutInflater inflater;

    public SelectedBankListAdapter(Activity activity, List<BankDescription> bankDescriptionList) {
        this.activity = activity;
        this.bankDescriptionList = bankDescriptionList;
        this.inflater = activity.getLayoutInflater();
        Log.d(LOG_TAG, " SelectedBankListAdapter(Activity activity, List<BankDescription> bankDescriptionList) ");
        Log.d(LOG_TAG, " bankDescriptionList size =  "+this.bankDescriptionList.size());

    }

    @Override
    public int getCount() {
        Log.d(LOG_TAG, "getCount()");
        return bankDescriptionList.size();

    }

    @Override
    public Object getItem(int i) {
        Log.d(LOG_TAG, "getItem(int i)");
        return bankDescriptionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        Log.d(LOG_TAG, "getItemId(int i)");
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view =  inflater.inflate(R.layout.item_bank_list,viewGroup,false);
        }
        BankDescription bankDescription = bankDescriptionList.get(i);
        Log.d(LOG_TAG, " BankDescription>>>  durationcredit "+bankDescription.getDurationCredit()+"    MonthlyPayment"+bankDescription.getMonthlyPayment());
          FrameLayout flBankIcon = (FrameLayout) view.findViewById(R.id.flBankIcon);


        ImageView imageView = new ImageView(activity);
        imageView.setImageResource(R.drawable.round_white);


        // flBankIcon.addView(bankDescription.getImageView());

         flBankIcon.addView(imageView);

        TextView tvDurationCredit = (TextView) view.findViewById(R.id.tvDurationCredit);
        tvDurationCredit.setText(""+bankDescription.getDurationCredit());
        TextView tvMonthlyPaymentItem = (TextView) view.findViewById(R.id.tvMonthlyPaymentItem);
        tvMonthlyPaymentItem.setText(""+bankDescription.getMonthlyPayment());

        return view;
    }
}
