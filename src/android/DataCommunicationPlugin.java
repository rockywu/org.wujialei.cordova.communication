/*
 * 
 * @author rockywu
 * @email wjl19890427@hotmail.com
 * @copyright Wujialei Inc
 * @created 2015-07-17
 */
package org.wujialei.cordova.datacommunication;

import android.content.Context;
import android.util.Log;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewEngine;

public class DataCommunicationPlugin extends CordovaPlugin {

    private static final String LOG_TAG = "LocalFilePlugin";

    private Context mContext;

    private SystemWebView systemWebView;

    private CordovaWebView webView;

    public static final String SCHEME_TAG = "localfile://";

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        // your init code here
        this.mContext = cordova.getActivity();
        this.webView = webView;
        SystemWebViewEngine engine = (SystemWebViewEngine)webView.getEngine();
        this.systemWebView = (SystemWebView) webView.getEngine().getView();
        this.systemWebView.setWebViewClient(new LocalFileWebViewClient(engine, this));
    }

    public static void log(String msg) {
        Log.v(LOG_TAG, msg);
    }
}
