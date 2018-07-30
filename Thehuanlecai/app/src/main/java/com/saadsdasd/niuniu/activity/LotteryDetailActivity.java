package com.saadsdasd.niuniu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saadsdasd.niuniu.adapter.LotteryResultDetailAdapter;
import com.saadsdasd.niuniu.model.LotteryResultDetailModel;
import com.saadsdasd.niuniu.R;

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
 * Created by QYQ on 2017/9/8.
 */

public class LotteryDetailActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rcy_tab11)
    RecyclerView rcyTab11;
    @BindView(R.id.sr2)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    private String title;
    private String id;
    private LotteryResultDetailAdapter adapter;
    private Intent intent;
    List<LotteryResultDetailModel> bean = new ArrayList<>();
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
                    Toast.makeText(LotteryDetailActivity.this, "已经是最新数据了", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_detail);
        ButterKnife.bind(this);
        initView();
        getDate();
    }

    private void initView() {
        intent = getIntent();
        title = intent.getStringExtra("title");
        id = intent.getStringExtra("id");
        titleName.setText(title+"");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcyTab11.setLayoutManager(layoutManager);
        adapter = new LotteryResultDetailAdapter(this);
        rcyTab11.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.font_theme_color);
        swipeRefreshLayout.setOnRefreshListener(this);
        titleBack.setVisibility(View.VISIBLE);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDate() {
        String url = "http://m.zhuoyicp.com/kaijang/recents";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("getData", "1")
                .add("page", "1")
                .add("playid", id)
                .add("selnum", "20")
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
                if (str != null && !"".equals(str)) {
                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONArray jsonArray = (JSONArray) jsonObject.get("datas");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            LotteryResultDetailModel model = new LotteryResultDetailModel();
                            model.setIssue(object.getString("issue"));
                            model.setHtime(object.getString("htime"));
                            model.setLotteryNumber(object.getString("lotteryNumber"));
                            model.setLotteryName(object.getString("lotteryName"));
                            model.setLotId(object.getString("lotId"));
                            bean.add(model);
                        }
                        handler.sendEmptyMessage(1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("异常", e.getMessage());
                    }
                } else {
                    Toast.makeText(LotteryDetailActivity.this, "暂时没有数据", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(2, 1000);
    }
}
