package com.saadsdasd.niuniu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.saadsdasd.niuniu.activity.CommentDetailActivity;
import com.saadsdasd.niuniu.activity.HotListActivity;
import com.saadsdasd.niuniu.adapter.TabThreeAdapter;
import com.saadsdasd.niuniu.model.TabThreeModel;
import com.saadsdasd.niuniu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by QYQ on 2017/9/8.
 */

public class TabThreeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rcy_tab3)
    RecyclerView rcyTab3;
    Unbinder unbinder;
    @BindView(R.id.ll_ssq)
    LinearLayout llSsq;
    @BindView(R.id.ll_dlt)
    LinearLayout llDlt;
    @BindView(R.id.ll_fucai3d)
    LinearLayout llFucai3d;
    @BindView(R.id.ll_other)
    LinearLayout llOther;
    @BindView(R.id.ll_zhongjiang)
    LinearLayout llZhongjiang;
    @BindView(R.id.sw_fragment3)
    SwipeRefreshLayout swipeRefreshLayout;
    private Intent intent;
    private TabThreeAdapter adapter;
    private List<TabThreeModel> bean = new ArrayList<>();
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
                    Toast.makeText(getActivity(), "已经是最新数据了", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private TabThreeAdapter.OnRecyclerviewItemClickListener onRecyclerviewItemClickListener = new TabThreeAdapter.OnRecyclerviewItemClickListener() {
        @Override
        public void onItemClickListener(View v, int position) {
            intent = new Intent(getActivity(), CommentDetailActivity.class);
            intent.putExtra("data",bean.get(position));
            startActivity(intent);
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        getData();
        return view;
    }

    private void initView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcyTab3.setLayoutManager(layoutManager);
        adapter = new TabThreeAdapter(getActivity(),onRecyclerviewItemClickListener);
        rcyTab3.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.font_theme_color);
        swipeRefreshLayout.setOnRefreshListener(this);
        llSsq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), HotListActivity.class);
                intent.putExtra("title","双色球");
                intent.putExtra("type","shuangseqiu");
                startActivity(intent);
            }
        });
        llDlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), HotListActivity.class);
                intent.putExtra("title","大乐透");
                intent.putExtra("type","daletou");
                startActivity(intent);
            }
        });
        llFucai3d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), HotListActivity.class);
                intent.putExtra("title","福彩3D");
                intent.putExtra("type","fucai3d");
                startActivity(intent);
            }
        });
        llOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), HotListActivity.class);
                intent.putExtra("title","其他彩种");
                intent.putExtra("type","other");
                startActivity(intent);
            }
        });
        llZhongjiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), HotListActivity.class);
                intent.putExtra("title","中奖");
                intent.putExtra("type","1");
                startActivity(intent);
            }
        });
    }

    private void getData() {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://api.icaipiao123.com/api/v7/social/hotlist?count=10&page=1")
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(2,1000);
    }
}
