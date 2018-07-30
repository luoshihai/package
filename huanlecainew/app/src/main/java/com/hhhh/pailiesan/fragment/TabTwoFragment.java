package com.hhhh.pailiesan.fragment;

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
import android.widget.Toast;

import com.hhhh.pailiesan.adapter.TabTwoAdapter;
import com.hhhh.pailiesan.model.TabTwoModel;
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
 * Created by QYQ on 2017/9/8.
 */

public class TabTwoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rcy_tab2)
    RecyclerView rcyTab2;
    @BindView(R.id.sr_tab2)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    private TabTwoAdapter adapter;
    private List<TabTwoModel> bean = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "已经是最新数据了", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    adapter.setItems(bean);
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        getData();
        return view;
    }
    private void initView(){
        RecyclerView.LayoutManager layoutManager  = new LinearLayoutManager(getActivity());
        rcyTab2.setLayoutManager(layoutManager);
        adapter = new TabTwoAdapter(getActivity());
        rcyTab2.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.font_theme_color);
        swipeRefreshLayout.setOnRefreshListener(this);
    }
    private void getData(){
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://news.zhuoyicp.com/h5/hot/json.json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    String result = response.body().string();
                    Log.e("result",result);
                    if(result!=null&&!"".equals(result)){
                        try {
                            JSONArray jsonObject = new JSONArray(result);
                            for(int i = 0;i<jsonObject.length();i++){
                                JSONObject jsonObject1 = jsonObject.getJSONObject(i);
                                TabTwoModel date = new TabTwoModel();
                                date.setPhoto(jsonObject1.getString("photo"));
                                date.setTitle(jsonObject1.getString("title"));
                                date.setSummary(jsonObject1.getString("summary"));
                                date.setDate(jsonObject1.getString("date"));
                                date.setContentUrl(jsonObject1.getString("contentUrl"));
                                bean.add(date);
                            }
                            handler.sendEmptyMessage(2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("有异常", e.getMessage());
                        }

                    }else {
                        Toast.makeText(getActivity(),"暂时没有数据",Toast.LENGTH_SHORT).show();
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
        handler.sendEmptyMessageDelayed(1,1000);
    }
}
