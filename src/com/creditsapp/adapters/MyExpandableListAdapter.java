package com.creditsapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.creditsapp.R;
import com.creditsapp.entity.InfoForBanks;
import com.creditsapp.url.ImageLoader;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 31.10.14
 * Time: 11:19
 * To change this template use File | Settings | File Templates.
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private final String LOG_TAG = "MyExpandableListAdapter";
    private Context context;
    private List<InfoForBanks> infoForBanks;
    private View groupView;

    public MyExpandableListAdapter(Context context, List<InfoForBanks> infoForBanks) {
        this.context = context;
        this.infoForBanks = infoForBanks;
    }


    @Override
    public int getGroupCount() {
        return infoForBanks.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return 1;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getGroup(int groupPosition) {
        Log.d(LOG_TAG, "getGroup1 " + groupPosition);
        return infoForBanks.get(groupPosition);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Log.d(LOG_TAG, "getChild " + groupPosition);
        Log.d(LOG_TAG, "getChild  " + childPosition);
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getGroupId(int groupPosition) {
        Log.d(LOG_TAG, "getGroupId " + groupPosition);

        return groupPosition;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        Log.d(LOG_TAG, "getChieldId " + groupPosition);
        Log.d(LOG_TAG, "getChieldId " + childPosition);
        return childPosition;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean hasStableIds() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.d(LOG_TAG, "getGroup2");
        groupView = inflater.inflate(R.layout.adapter_list, null, false);

        TextView bankNameText = (TextView) groupView.findViewById(R.id.bankNameText);
        ImageView logoBankImg = (ImageView) groupView.findViewById(R.id.imageView);


        InfoForBanks infoForBanks = (InfoForBanks) getGroup(groupPosition);

        bankNameText.setText(infoForBanks.getNameForBanks());
       // logoBankImg.setImageResource(R.drawable.ic_launcher);

        ImageLoader imageLoader = new ImageLoader(context);
        imageLoader.DisplayImage(infoForBanks.getLogoForBanks(), logoBankImg);

        return groupView;  //To change body of implemented methods use File | Settings | File Templates.

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.adapter_children, null, false);
        Log.d(LOG_TAG, "getChield");
        return view;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
