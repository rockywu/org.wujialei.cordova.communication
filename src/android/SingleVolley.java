package org.wujialei.cordova.communication;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rocky on 15-12-4.
 */
public class SingleVolley {

    private static SingleVolley instance = null;

    private Context appContext = null;

    private RequestQueue requestQueue;

    public static SingleVolley getInstance() {
        if(instance == null) {
            instance = new SingleVolley();
        }
        return instance;
    }

    public void init(Context context) {
        if(appContext == null) {
            appContext = context;
        }
    }

    public void ajax() {

    }
}
