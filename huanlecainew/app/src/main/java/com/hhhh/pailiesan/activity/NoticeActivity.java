package com.hhhh.pailiesan.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hhhh.pailiesan.adapter.NoticeAdapter;
import com.hhhh.pailiesan.base.KBaseActivity;
import com.hhhh.pailiesan.model.NoticeModel;
import com.hhhh.pailiesan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by QYQ on 2017/9/21.
 */

public class NoticeActivity extends KBaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rcy_forecast)
    RecyclerView rcyForecast;
    @BindView(R.id.sw_forecase)
    SwipeRefreshLayout swipeRefreshLayout;
    private NoticeAdapter adapter;
    private List<NoticeModel> bean = new ArrayList<>();
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
        setTitleName("公告");
        setTitleBack(true,0);
        swipeRefreshLayout.setColorSchemeResources(R.color.font_theme_color);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcyForecast.setLayoutManager(layoutManager);
        adapter = new NoticeAdapter(this);
        rcyForecast.setAdapter(adapter);
    }

    @Override
    protected void getData() {
        super.getData();
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://mapi.159cai.com/discovery/notice/index.json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    String result = response.body().string();
                    //Log.e("公告",result);
                    if(result!=null&&!"".equals(result)){
                        try {
                            JSONArray jsonArray = new JSONArray(result);
                            for(int i = 0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                NoticeModel model = new NoticeModel();
                                model.setTitle(object.getString("title"));
                                model.setDate(object.getString("date"));
                                model.setUrl(object.getString("url"));
                                bean.add(model);
                            }
                            handler.sendEmptyMessage(1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("有异常", e.getMessage());
                        }
                    }else {
                        show("暂时没有数据");
                    }

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
