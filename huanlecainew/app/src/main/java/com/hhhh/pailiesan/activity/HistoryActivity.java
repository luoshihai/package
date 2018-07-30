package com.hhhh.pailiesan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hhhh.pailiesan.adapter.HistoryAdapter;
import com.hhhh.pailiesan.base.KBaseActivity;
import com.hhhh.pailiesan.model.HistoryModel;
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
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by QYQ on 2017/9/19.
 */

public class HistoryActivity extends KBaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rcy_history)
    RecyclerView rcyHistory;
    @BindView(R.id.sr_history)
    SwipeRefreshLayout swipeRefreshLayout;
    private Intent intent;
    private HistoryAdapter adapter;
    private List<HistoryModel> bean = new ArrayList<>();
    private String name,type;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    swipeRefreshLayout.setRefreshing(false);
                    show("已经是最新数据了");
                    break;
                case 2:
                    adapter.setItems(bean);
                    break;
            }
        }
    };
    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_history);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleBack(true,0);
        swipeRefreshLayout.setColorSchemeResources(R.color.font_theme_color);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcyHistory.setLayoutManager(layoutManager);
        intent = getIntent();
        name = intent.getStringExtra("name");
        type = intent.getStringExtra("type");
        setTitleName(type+"历史开奖");
        adapter = new HistoryAdapter(this,name);
        rcyHistory.setAdapter(adapter);
    }

    @Override
    protected void getData() {
        super.getData();
        String url = "http://mapi.159cai.com/phoneKjls.php";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("page", "1")
                .add("type", name)
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
                Log.e("结果", str);
                try {
                    JSONArray jsonArray = new JSONArray(str);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        HistoryModel model = new HistoryModel();
                        model.setTerm(object.getString("term"));
                        model.setTime(object.getString("time"));
                        model.setResult(object.getString("result"));
                        model.setType(object.getString("type"));
                        model.setPrizepool(object.getString("prizepool"));
                        model.setSale(object.getString("sale"));
                        bean.add(model);
                    }
                    handler.sendEmptyMessage(2);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("异常", e.getMessage());
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
        handler.sendEmptyMessageDelayed(1,1000);
    }
}
