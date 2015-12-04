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

public class CommunicationPlugin extends CordovaPlugin {

    private static final String LOG_TAG = "CommunicationPlugin";

    private Context mContext;

    private CordovaWebView webView;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        // your init code here
        this.mContext = cordova.getActivity();
        this.webView = webView;
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        Log.v(LOG_TAG, "action : " + action);
        if(action.equals("ajax")) {
            Log.v(LOG_TAG, "action : " + action + " is running");
            if(this.ajax(args)) {
                Log.v(LOG_TAG, "action : " + action + " Status.OK");
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,""));
            } else {
                Log.v(LOG_TAG, "action : " + action + " Status.Error");
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,""));
            }
        }
        return true;
    }

    public boolean ajax(JSONArray args) {
        return true;
    }
}
