package com.creditsapp.client;

import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 20.10.14
 * Time: 11:34
 * To change this template use File | Settings | File Templates.
 */
public class HttpResponseHandler {
    private static final String LOG_TAG = "HttpResponseHandler";
    InputStream is = null;
    String json = "";
    JSONObject jObj = null;
    StringBuilder sb = null;

    public JSONObject calculatorMessagesHandler(HttpResponse httpResponse){
        try {
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
            Log.e(LOG_TAG, "connection");
            if(is != null){
                Log.e(LOG_TAG, "inputStream "+is);
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        is, "UTF-8"), 8);
                sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    Log.e(LOG_TAG, "Not null first "+ line);
                    sb.append(line + "\n");
                    Log.e(LOG_TAG, "line " +reader.readLine());
                }
                Log.e(LOG_TAG,"line not null: "+ reader.readLine());
                // is.close();
            }
            Log.d("response string", json);
            json = sb.toString();
            Log.d("response string", json);
            jObj = new JSONObject(json);

            // Log.d(LOG_TAG, jObj.toString());
            is.close();
        }catch(JSONException e){

            Log.e("log_tag", "Error parsing data "+e.toString());

        } catch (Exception e) {
            Log.e("log_tag", "Exception "+e.toString());
        }

        return  jObj;
    }

}
