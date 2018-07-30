package com.saadsdasd.niuniu.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.saadsdasd.niuniu.adapter.AllMatchAdater;
import com.saadsdasd.niuniu.base.KBaseFragment;
import com.saadsdasd.niuniu.model.MatchModel;
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
 * Created by QYQ on 2017/9/13.
 */

public class ZCMatchFragment extends KBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rcy_match)
    RecyclerView rcyMatch;
    @BindView(R.id.sr_match)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    private AllMatchAdater allMatchAdater;
    private List<MatchModel> bean = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    allMatchAdater.setItems(bean);
                    break;
                case 2:
                    swipeRefreshLayout.setRefreshing(false);
                    show("已经是最新数据了");
                    break;
                case 3:
                    llEmpty.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setVisibility(View.GONE);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_natch, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        getData();
        return view;
    }

    private void initView() {
        swipeRefreshLayout.setColorSchemeResources(R.color.font_theme_color);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcyMatch.setLayoutManager(layoutManager);
        allMatchAdater = new AllMatchAdater(getActivity());
        rcyMatch.setAdapter(allMatchAdater);
    }

    private void getData() {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .addHeader("Accept", "application/vnd.zycp.v2+json")
                .url("http://live.zhuoyicp.com/api/fscore?issue=&uid=&mtype=csl").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    String result = response.body().string();
                    Log.e("result", result + "");
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
                        if (jsonArray.length() > 0) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                MatchModel date = new MatchModel();
                                date.setName(object.getString("name"));
                                date.setHome(object.getString("home"));
                                date.setAwary(object.getString("awary"));
                                date.setHome_logo(object.getString("home_logo"));
                                date.setGuest_logo(object.getString("guest_logo"));
                                date.setSc(object.getString("sc"));
                                date.setDt(object.getString("dt"));
                                date.setSs(object.getInt("ss"));
                                date.setMatch_id(object.getInt("match_id"));
                                bean.add(date);
                            }
                            handler.sendEmptyMessage(1);
                        } else {
                            handler.sendEmptyMessage(3);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
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
        handler.sendEmptyMessageDelayed(2, 1000);
    }
}

