package org.wujialei.cordova.communication;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by prive on 15/12/8.
 */
public interface Callback {

    abstract void getStringResult(String s);

    abstract void getJsonArrayResult(JSONArray jsonArray);

    abstract void getJsonObjectResult(JSONObject jsonObject);

    abstract void error(String message);
}
