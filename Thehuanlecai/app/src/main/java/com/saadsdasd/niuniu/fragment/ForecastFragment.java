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

import com.saadsdasd.niuniu.adapter.LotteryResultAdapter;
import com.saadsdasd.niuniu.base.KBaseFragment;
import com.saadsdasd.niuniu.model.LotteryModel;
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
 * Created by QYQ on 2017/9/14.
 */

public class ForecastFragment extends KBaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rcy_lottery)
    RecyclerView rcyLottery;
    @BindView(R.id.sr_lottery)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    private LotteryResultAdapter adapter;
    private List<LotteryModel> bean = new ArrayList<>();
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
        View view = inflater.inflate(R.layout.fragment_lottery, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        getData();
        return view;
    }
    private void initView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcyLottery.setLayoutManager(layoutManager);
        adapter = new LotteryResultAdapter(getActivity());
        rcyLottery.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.font_theme_color);
        swipeRefreshLayout.setOnRefreshListener(this);
    }
    private void getData() {//get
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://mapi.159cai.com/phoneKaijiang.php")
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
                            JSONArray jsonArray = new JSONArray(result);
                            for(int i = 0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                LotteryModel model = new LotteryModel();
                                model.setType(object.getString("type"));
                                model.setTermNo(object.getString("termNo"));
                                model.setDate(object.getString("date"));
                                model.setResult(object.getString("result"));
                                model.setPrizepool(object.getString("prizepool"));
                                model.setSale(object.getString("sale"));
                                model.setName(object.getString("name"));
                                if("14sfc".equals(object.getString("name"))||"r9".equals(object.getString("name"))||"6cb".equals(object.getString("name"))||
                                        "4cjq".equals(object.getString("name"))){
                                    bean.add(model);
                                }
                            }
                            handler.sendEmptyMessage(1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("异常",e.getMessage());
                        }
                    }else {
                        show("暂时没有数据");
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
