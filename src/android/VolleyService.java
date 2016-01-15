package org.wujialei.cordova.communication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.anjuke.direct.sale.MainApplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingFormatArgumentException;

/**
 * Created by prive on 15/12/8.
 */
public class VolleyService{

    private static final String LOG_TAG = "VolleyService";

    private static VolleyService SINGLE_INSTANCE = null;
    private static RequestQueue REQUEST_QUEUE = null;

    private static final String REQUEST_GET = "GET";
    private static final String REQUEST_POST = "POST";

    private static final String RESULT_XML = "XML";
    private static final String RESULT_JSON = "JSON";
    private static final String RESULT_TEXT = "TEXT";

    private VolleyService(){};

    public static VolleyService getInstance(){
        if(REQUEST_QUEUE == null || SINGLE_INSTANCE == null){
            throw new RuntimeException(new MissingFormatArgumentException("instance null"));
        }
        return SINGLE_INSTANCE;
    }

    public static VolleyService getInstance(Context app){
        if(SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new VolleyService();
        }
        if(app == null){
            throw new RuntimeException(new InvalidParameterException("app null"));
        }
        if(REQUEST_QUEUE == null){
            REQUEST_QUEUE = Volley.newRequestQueue(app);
        }

        return SINGLE_INSTANCE;
    }

    /**
     * http send function
     * @param type GET or POST def:GET
     * @param url http address
     * @param data params
     * @param resultType JSON TEXT XML def:JSON
     * @param callback callback interface for result return
     */
    public void send(String type,String url,HashMap<String,String> data,String resultType,Callback callback){
        if (type == null)
            type = VolleyService.REQUEST_GET;

        if (url == null || url.equals(""))
            throw new RuntimeException(new InvalidParameterException("param url error"));

        if (callback==null)
            throw new RuntimeException(new InvalidParameterException("param callback error"));

        if (resultType==null || resultType.equals(""))
            resultType = VolleyService.RESULT_JSON;

        if (REQUEST_GET.equals(type.toUpperCase())) {
            doGet(url,resultType,data,callback);
        } else if (REQUEST_POST.equals(type.toUpperCase())) {
            doPost(url,resultType,data,callback);
        } else {
            Log.v(LOG_TAG,"Type:"+type+" error");
        }
    }

    public void send(String url,String resultType,Callback callback){
        this.send(VolleyService.REQUEST_GET, url, null, resultType, callback);
    }

    public void send(String url,HashMap<String,String> data,String resultType,Callback callback){
        this.send(VolleyService.REQUEST_GET, url, data, resultType, callback);
    }

    public void send(String type,String url,String resultType,Callback callback){
        this.send(type, url, null, resultType, callback);
    }

    private void doGet(String url,String resultType,HashMap<String,String> data,Callback callback){
        if(data != null && !data.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String,String> param : data.entrySet()){
                sb.append(param.getKey()).append("=").append(param.getValue()).append("&");
            }

            if(!url.contains("?"))
                url+="?";
            else
                url+="&";

            url+=sb.toString();

            url = url.substring(0,url.length()-1);
        }

        Request request = null;
        if(VolleyService.RESULT_TEXT.equals(resultType.toUpperCase())){
            Response.Listener<String> listener = getListener(callback);
            request = new StringRequest(Request.Method.GET,url,listener,getErrorListener(callback));
        }else if(VolleyService.RESULT_JSON.equals(resultType.toUpperCase())){
            Response.Listener<JSONObject> listener = getListener(callback);
            request = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(data), listener, getErrorListener(callback));
        }else{
            throw new RuntimeException(new InvalidParameterException("param resultType error"));
        }

        REQUEST_QUEUE.add(request);
    }

    private void doPost(String url,String resultType,final HashMap<String,String> data,Callback callback){
        if(data == null) {
            Log.v(LOG_TAG,"data null");
            return ;
        }

        if(url.contains("?")){
            url = url.substring(0,url.indexOf('?'));

            String params = url.substring(url.indexOf('?') + 1,url.length());
            String[] paramArray = params.split("&");
            for(String param : paramArray){
                String[] kv = param.split("=");
                if(kv.length == 2){
                    data.put(kv[0],kv[1]);
                }
            }
        }

        Request request = null;
        if(VolleyService.RESULT_TEXT.equals(resultType.toUpperCase())) {
            Response.Listener<String> listener = getListener(callback);
            request = new StringRequest(Request.Method.POST, url, listener, getErrorListener(callback)) {
                @Override
                protected Map<String, String> getParams() {
                    return data;
                }
            };
        }else if(VolleyService.RESULT_JSON.equals(resultType.toUpperCase())) {
            Response.Listener<JSONObject> listener = getListener(callback);
            request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data), listener, getErrorListener(callback));
        }else{
            throw new RuntimeException(new InvalidParameterException("param resultType error"));
        }

        REQUEST_QUEUE.add(request);
    }

    private <T> Response.Listener<T> getListener(final Callback callback){
        return new Response.Listener<T>(){
            @Override
            public void onResponse(T t){
                if(t instanceof String){
                    callback.getStringResult((String) t);
                }else if(t instanceof JSONArray){
                    callback.getJsonArrayResult((JSONArray) t);
                }else if(t instanceof JSONObject){
                    callback.getJsonObjectResult((JSONObject) t);
                }
            }
        };
    }

    private Response.ErrorListener getErrorListener(final Callback callback){
        return new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.v(LOG_TAG,String.valueOf(volleyError.getStackTrace()));
                callback.error(volleyError.getMessage());
            }
        };
    }
}
