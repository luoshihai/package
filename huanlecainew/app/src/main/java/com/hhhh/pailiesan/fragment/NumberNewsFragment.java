package com.hhhh.pailiesan.fragment;

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

import com.hhhh.pailiesan.adapter.NewsAdapter;
import com.hhhh.pailiesan.base.KBaseFragment;
import com.hhhh.pailiesan.model.NewModel;
import com.hhhh.pailiesan.R;

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
 * Created by QYQ on 2017/9/14.
 */

public class NumberNewsFragment extends KBaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rcy_match)
    RecyclerView rcyMatch;
    @BindView(R.id.sr_match)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    private NewsAdapter adapter;
    private List<NewModel> bean = new ArrayList<>();
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_natch, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        getData();
        return view;
    }
    private void initView(){
        swipeRefreshLayout.setColorSchemeResources(R.color.font_theme_color);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcyMatch.setLayoutManager(layoutManager);
        adapter = new NewsAdapter(getActivity());
        rcyMatch.setAdapter(adapter);
    }
    private void getData(){
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://mapi.159cai.com/discovery/news/szc/index.json").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    if (response.code() == 200) {
                        String result = response.body().string();
                        Log.e("result", result + "");
                        try {
                            JSONArray jsonArray = new JSONArray(result);
                            for(int i = 0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                NewModel model = new NewModel();
                                model.setTitle(object.getString("title"));
                                model.setPubdate(object.getString("pubdate"));
                                model.setUrl(object.getString("url"));
                                model.setImage(object.getString("image"));
                                model.setShorttitle(object.getString("shorttitle"));
                                bean.add(model);
                            }
                            handler.sendEmptyMessage(1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
