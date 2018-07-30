package me.coderblog.footss.fragment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import me.coderblog.footss.global.Http;
import me.coderblog.footss.util.Utils;

/**
 * 西甲助攻榜Fragment
 */
public class SpainAssistFragment extends BaseAssistFragment {
    @Override
    public void loadDataFromServer() {
        //创建请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(Utils.getContext());
        //创建请求
        StringRequest stringRequest = new StringRequest(Http.URL_ASSIST_SPAIN, this, this);
        //将请求添加到请求队列
        requestQueue.add(stringRequest);
    }
}
