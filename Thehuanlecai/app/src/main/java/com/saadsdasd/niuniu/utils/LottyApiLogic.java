package com.saadsdasd.niuniu.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.saadsdasd.niuniu.model.ServerSettingObj;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tgw on 2017/8/1.
 */

public class LottyApiLogic {
    ServerSettingObj serverObj;
    private RequestQueue requestQueue;

    private  static LottyApiLogic instance= null;

    private LottyApiLogic() {

    }

    public  static  LottyApiLogic getInstance() {

        if (instance == null) {

            instance = new LottyApiLogic();
        }
        return  instance;
    }


    public void checkApiState(final Loadcomplete complete, String appid,Context mcontext) {


        //check setting from server

        String urls = "http://tzxpsm.com/lotto/api";
        JSONObject parameters = new JSONObject();
        try {

            parameters.put("jsonrpc", "2.0");
            parameters.put("id", 1);
            parameters.put("method", "lotto.monitor");
            JSONObject paramsDetail = new JSONObject();
            paramsDetail.put("appid", appid);
            parameters.put("params", paramsDetail);

        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest jsObjRequset = new JsonObjectRequest(Request.Method.POST,urls,parameters,new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response) {

                try {
                    Log.e("数据",response.toString());
                    serverObj = new ServerSettingObj(response.getJSONObject("result"));
                    complete.onloadSuccess(serverObj);
                }catch (JSONException e){
                    e.printStackTrace();
                    serverObj = new ServerSettingObj();
                    complete.onloadSuccess(serverObj);

                }
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                //檢查失敗錯誤處理
                serverObj = new ServerSettingObj();
                complete.onloadSuccess(serverObj);

            }

        });
        RequestQueue queue = Volley.newRequestQueue(mcontext);
        queue.add(jsObjRequset);

    }
}
