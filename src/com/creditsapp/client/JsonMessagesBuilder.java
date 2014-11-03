package com.creditsapp.client;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 20.10.14
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class JsonMessagesBuilder {

    public List<NameValuePair> buildCalculatorMessages(String sum, double precent, int termin ){

    Map<String, Object> jsonMap = new HashMap<String, Object>();
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("sum", sum);
    data.put("precent", ""+precent);
    data.put("termin", ""+termin);
    jsonMap.put("action", "users.calculator");
    jsonMap.put("data", new JSONObject(data));
    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
    nameValuePairs.add(new BasicNameValuePair("data", new JSONObject(jsonMap).toString()));
    return nameValuePairs;
}
    public List<NameValuePair> buildBunksActivity(){
        Map<String, Object> data = new HashMap<String, Object>();

        data.put("action", "users.get-all-banks");
        data.put("data", "{}");
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("data", new JSONObject(data).toString()));
        return nameValuePairs;
    }
    public List<NameValuePair> buildTownInFirstActivity(){
        Map<String, Object> data = new HashMap<String, Object>();

        data.put("action", "users.get-town");
        data.put("data", "{}");
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("data", new JSONObject(data).toString()));

        return nameValuePairs;
    }
    public List<NameValuePair> buildInfoForUser(){

        Map<String, Object> jsonMap = new HashMap<String, Object>();

            jsonMap.put("action", "users.kredit-banks");

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("birthday","18/08/1989");
        data.put("work", "1");
        data.put("history", "1");
        data.put("vid", "1");
        data.put("sum", "20000");
       jsonMap.put("data", new JSONObject(data));
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("data", new JSONObject(jsonMap).toString()));
        return nameValuePairs;
    }
}
