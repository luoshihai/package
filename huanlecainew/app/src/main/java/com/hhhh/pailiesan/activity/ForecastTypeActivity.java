package com.hhhh.pailiesan.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hhhh.pailiesan.adapter.ForecastTypeAdapter;
import com.hhhh.pailiesan.base.KBaseActivity;
import com.hhhh.pailiesan.model.ForecastTypeModel;
import com.hhhh.pailiesan.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.arnaudguyon.xmltojsonlib.XmlToJson;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by QYQ on 2017/9/21.
 */

public class ForecastTypeActivity extends KBaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rcy_forecast)
    RecyclerView rcyLottery;
    @BindView(R.id.sw_forecase)
    SwipeRefreshLayout swipeRefreshLayout;
    private ForecastTypeAdapter adapter;
    private List<ForecastTypeModel> bean = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    adapter.setItems(bean);
                    break;
                case 2:
                    swipeRefreshLayout.setRefreshing(false);
                    show("已经是最新数据了");
                    break;
            }
        }
    };
    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_forecast);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleName("彩票预测");
        setTitleBack(true,0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcyLottery.setLayoutManager(layoutManager);
        adapter = new ForecastTypeAdapter(this);
        rcyLottery.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.font_theme_color);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void getData() {
        super.getData();
        String url = "http://mobile.9188.com/trade/forecast.go";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("name", "yuce")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                if(str!=null&&!"".equals(str)){
                    try {
                        XmlToJson xmlToJson = new XmlToJson.Builder(str).build();
                        String result = xmlToJson.toJson().toString();
                        if(result!=null&&!"".equals(result)){
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray jsonArray = jsonObject.getJSONObject("Resp").getJSONObject("rows").getJSONArray("row");
                            for(int i = 0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                ForecastTypeModel date  = new ForecastTypeModel();
                                date.setName(object.getString("name"));
                                date.setTitle(object.getString("title"));
                                date.setGid(object.getString("gid"));
                                bean.add(date);
                            }
                            handler.sendEmptyMessage(1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("异常",e.getMessage());
                    }
                }else {
                    show("暂时没有数据");
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(2,1000);
    }
}
