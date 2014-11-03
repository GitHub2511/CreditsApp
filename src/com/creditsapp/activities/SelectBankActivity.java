package com.creditsapp.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import com.creditsapp.R;
import com.creditsapp.adapters.MyExpandableListAdapter;
import com.creditsapp.client.JsonSender;
import com.creditsapp.entity.InfoForBanks;
import com.creditsapp.fragments.BackAndSettingsFragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Windows
 * Date: 26.09.14
 * Time: 16:22
 * To change this template use File | Settings | File Templates.
 */
public class SelectBankActivity extends ActionBarMenuActivity {
    private final String LOG_TAG = "SelectBankActivity";
    private BackAndSettingsFragment backAndSettingsFragment;
    private JSONObject jsonObject;
    private String[] banksLogoArray = null;
    private String[] banksNameArary = null;
    private ImageView imageView;
    private int arraySize;
    private List<InfoForBanks> infoForBanksList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bank_list_view);


        RequestAsynTask requestAsynTask = new RequestAsynTask();
        requestAsynTask.execute();

    }

    @Override
    protected View getContent() {
        View view = getLayoutInflater().inflate(R.layout.activity_select_bank, null, false);
        return view;

    }


    class RequestAsynTask extends AsyncTask {
        private JsonSender jsonSender = JsonSender.getInstans();

        @Override
        protected Object doInBackground(Object... params) {
            jsonObject = jsonSender.sendBanksMessage();
            try {


                JSONArray jsonArray = jsonObject.getJSONArray("data");
                int jsonArraySize = jsonArray.length();
                infoForBanksList = new ArrayList<InfoForBanks>();
                for (int i = 0; i < jsonArraySize; i++) {
                    jsonObject = (JSONObject) jsonArray.get(i);
                    InfoForBanks infoForBanks = new InfoForBanks(jsonObject.get("id").toString(),
                    jsonObject.get("bank").toString(), jsonObject.get("logo").toString(), "");



                    infoForBanksList.add(infoForBanks);

                }

            } catch (JSONException e) {
                Log.d(LOG_TAG, "EXCEPTION ");

                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (infoForBanksList != null) {
                MyExpandableListAdapter myExpandableListAdapter = new MyExpandableListAdapter(SelectBankActivity.this, infoForBanksList);
                ExpandableListView elvMain = (ExpandableListView) findViewById(R.id.elvMain);
                elvMain.setAdapter(myExpandableListAdapter);
            }
        }
    }
}
