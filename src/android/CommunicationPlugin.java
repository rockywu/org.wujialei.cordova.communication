/*
 * 
 * @author rockywu
 * @email wjl19890427@hotmail.com
 * @copyright Wujialei Inc
 * @created 2015-07-17
 */
package org.wujialei.cordova.communication;

import android.content.Context;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CommunicationPlugin extends CordovaPlugin {

    private static final String LOG_TAG = "CommunicationPlugin";

    private Context mContext;

    private CordovaWebView webView;

    private Context gContext;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        // your init code here
        this.mContext = cordova.getActivity();
        this.webView = webView;
        this.gContext = cordova.getActivity().getApplicationContext();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        Log.v(LOG_TAG, "action : " + action);
        if(action.equals("ajax")) {
            Log.v(LOG_TAG, "action : " + action + " is running");
            try {
                this.ajax(args, callbackContext);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean ajax(JSONArray args,final CallbackContext callbackContext) throws Exception {
        String url = args.getString(0);
        String type = args.getString(1);
        String dataType = args.getString(2);
        JSONObject data = args.getJSONObject(3);
        HashMap<String,String> params = new HashMap<String, String>();
        for(Iterator keys = data.keys();keys.hasNext();){
            String key = (String)keys.next();
            String value = null;

            if(data.get(key) instanceof String){
                value = (String) data.get(key);
            }else{
                value = data.get(key).toString();
            }
            if(value == null){
                throw new RuntimeException(new Exception("param value class error"));
            }
            params.put(key, value);
        }

        VolleyService vs = VolleyService.getInstance(gContext);
        vs.send(type, url, params, dataType, new Callback() {
            @Override
            public void getStringResult(String s) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, s));
            }

            @Override
            public void getJsonArrayResult(JSONArray jsonArray) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jsonArray));
            }

            @Override
            public void getJsonObjectResult(JSONObject jsonObject) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jsonObject));
            }

            @Override
            public void error(String message) {
                try {
                    Log.v(LOG_TAG, message);
                } catch(Exception e) {
                    e.printStackTrace();
                    Log.v(LOG_TAG, e.getMessage());
                }
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, message));
            }
        });
        return true;
    }
}
