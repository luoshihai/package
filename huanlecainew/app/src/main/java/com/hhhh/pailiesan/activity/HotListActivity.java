package com.hhhh.pailiesan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hhhh.pailiesan.adapter.TabThreeAdapter;
import com.hhhh.pailiesan.base.KBaseActivity;
import com.hhhh.pailiesan.model.TabThreeModel;
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
 * Created by QYQ on 2017/9/22.
 */

public class HotListActivity extends KBaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recy_list)
    RecyclerView rcyTab3;
    @BindView(R.id.sr_list)
    SwipeRefreshLayout swipeRefreshLayout;
    private TabThreeAdapter adapter;
    private List<TabThreeModel> bean = new ArrayList<>();
    private Intent intent ;
    private String title;
    private String type = "";
    private String url;
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
                    Toast.makeText(HotListActivity.this, "已经是最新数据了", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private TabThreeAdapter.OnRecyclerviewItemClickListener onRecyclerviewItemClickListener = new TabThreeAdapter.OnRecyclerviewItemClickListener() {
        @Override
        public void onItemClickListener(View v, int position) {
            intent = new Intent(HotListActivity.this, CommentDetailActivity.class);
            intent.putExtra("data",bean.get(position));
            startActivity(intent);
        }
    };
    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_hotlist);
    }

    @Override
    protected void initView() {
        super.initView();
        intent = getIntent();
        title = intent.getStringExtra("title");
        type = intent.getStringExtra("type");
        if("1".equals(type)){
            url  = "http://api.icaipiao123.com/api/v7/social/hitlist?count=10&max_id=0";
        }else {
            url = "http://api.icaipiao123.com/api/v7/social/hotlist?count=10&lottery_key="+type+"&page=1";
        }
        setTitleName(title);
        setTitleBack(true,0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcyTab3.setLayoutManager(layoutManager);
        adapter = new TabThreeAdapter(this,onRecyclerviewItemClickListener);
        rcyTab3.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.font_theme_color);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void getData() {
        super.getData();
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    String result = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            TabThreeModel model = new TabThreeModel();
                            TabThreeModel.UserBean userBean = new TabThreeModel.UserBean();
                            userBean.setName(object.getJSONObject("user").getString("name"));
                            userBean.setIcon(object.getJSONObject("user").getString("icon"));
                            model.setPublish_time(object.getLong("publish_time"));
                            model.setContent(object.getString("content"));
                            if ("".equals(object.getString("predict_data")) && !"".equals(object.getString("images"))) {
                                List<String> img = new ArrayList<String>();
                                img.add(object.getJSONArray("images").get(0) + "");
                                model.setImages(img);
                            }
                            if(!"".equals(object.getString("position"))){
                                TabThreeModel.PositionBean positionBean = new TabThreeModel.PositionBean();
                                positionBean.setLandmarker(object.getJSONObject("position").getString("landmarker"));
                                model.setPosition(positionBean);
                            }
                            model.setUser(userBean);
                            model.setThumb_up_count(object.getInt("thumb_up_count"));
                            model.setComment_count(object.getInt("comment_count"));
                            model.setId(object.getInt("id"));
                            bean.add(model);
                        }
                        handler.sendEmptyMessage(1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("异常", e.getMessage());
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
