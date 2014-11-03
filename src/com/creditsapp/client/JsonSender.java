package com.creditsapp.client;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 20.10.14
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */
public class JsonSender {
    private static JsonSender jsonSender = new JsonSender();
    private JsonMessagesBuilder jMessagesBuilder;
    private HttpResponseHandler httpResponseHandler;
    private final DefaultHttpClient httpClient;
    private static final String URL_CALCULATOR = "http://beestore.com.ua/kredit/api/main.php";
    private JSONObject jObj = null;
    private JsonSender(){
        jMessagesBuilder  = new  JsonMessagesBuilder();
        httpClient = new DefaultHttpClient();
        httpResponseHandler = new HttpResponseHandler();
    }

    public static JsonSender getInstans() {
        return jsonSender;
    }

    public JSONObject sendCalculatorMessages(String sum, double precent, int termin ){

        List<NameValuePair> nameValuePairs = jMessagesBuilder.buildCalculatorMessages(sum,precent,termin);
        HttpPost httpPost = new HttpPost(URL_CALCULATOR);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            jObj = httpResponseHandler.calculatorMessagesHandler(httpResponse);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return jObj;
    }
    public JSONObject sendTownMessages(){
        List<NameValuePair> nameValuePairs = jMessagesBuilder.buildTownInFirstActivity();
        HttpPost httpPost = new HttpPost(URL_CALCULATOR);
        try{
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            jObj = httpResponseHandler.calculatorMessagesHandler(httpResponse);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return jObj;
    }
    public JSONObject sendBanksMessage(){
        List<NameValuePair> nameValuePairs = jMessagesBuilder.buildBunksActivity();
        HttpPost httpPost = new HttpPost(URL_CALCULATOR);
        try{
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            jObj = httpResponseHandler.calculatorMessagesHandler(httpResponse);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return jObj;
    }
    public JSONObject sendIfoForUser(){
        List<NameValuePair> nameValuePairs = jMessagesBuilder.buildInfoForUser();
        HttpPost httpPost = new HttpPost(URL_CALCULATOR);
 try{
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse httpResponse = httpClient.execute(httpPost);
        jObj = httpResponseHandler.calculatorMessagesHandler(httpResponse);
    }catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }catch (IOException e) {
        e.printStackTrace();
    }

    return jObj;

    }

}

