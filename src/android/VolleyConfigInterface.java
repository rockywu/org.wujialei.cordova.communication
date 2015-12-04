package org.wujialei.cordova.communication;

import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;

/**
 * Created by rocky on 15-12-4.
 */
public interface VolleyConfigInterface {

    /**
     * 设置API Url
     * @param url
     * @return void
     */
    abstract void setUrl(String url);

    /**
     * 获取API Url
     * @return
     */
    abstract String getUrl();

    /**
     * 设置返回值类型
     * @param dataType
     * @return void
     */
    abstract void setDataType(String dataType);

    /**
     * 获取API数据返回值类型
     * @return String
     */
    abstract String getDataType();

    /**
     * set volley request method type
     * @param methodType
     * @return void
     */
    abstract void setMethodType(String methodType);

    /**
     * get volley request method type;
     * @return String
     */
    abstract String getMethodType();

    /**
     * 设置listener
     * @param listener
     * @return void
     */
    abstract void setListener(Listener listener);

    /**
     * 获取listener
     * @return Listener
     */
    abstract Listener getListener();

    /**
     * 设置 errorListener
     * @param errorListener
     * @return void
     */
    abstract void setErrorListener(ErrorListener errorListener);

    /**
     * 获取 errorListener
     * @return ErrorListener
     */
    abstract ErrorListener getErrorListener();

}
