package org.wujialei.cordova.communication;

import com.android.volley.Response;

/**
 * Created by rocky on 15-12-4.
 */
public class VolleyConfigImpl implements VolleyConfigInterface {

    private String url;

    private String dataType;

    private String methodType;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String getDataType() {
        return null;
    }

    @Override
    public void setMethodType(String methodType) {

    }

    @Override
    public String getMethodType() {
        return null;
    }

    @Override
    public void setListener(Response.Listener listener) {

    }

    @Override
    public Response.Listener getListener() {
        return null;
    }

    @Override
    public void setErrorListener(Response.ErrorListener errorListener) {

    }

    @Override
    public Response.ErrorListener getErrorListener() {
        return null;
    }
}
