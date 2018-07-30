package com.saadsdasd.niuniu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saadsdasd.niuniu.adapter.ForecastAdapter;
import com.saadsdasd.niuniu.model.ForecastModel;
import com.saadsdasd.niuniu.R;

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
 * Created by QYQ on 2017/9/11.
 */

public class ForecastActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rcy_forecast)
    RecyclerView rcyForecast;
    @BindView(R.id.sw_forecase)
    SwipeRefreshLayout swipeRefreshLayout;
    private String gid;
    private Intent intent;
    private ForecastAdapter adapter;
    private List<ForecastModel> bean = new ArrayList<>();
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
                    Toast.makeText(ForecastActivity.this, "已经是最新数据了", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);
        initView();
        getData();
    }
    private void initView(){
        titleName.setText("预测");
        titleBack.setVisibility(View.VISIBLE);
        intent = getIntent();
        gid = intent.getStringExtra("gid");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcyForecast.setLayoutManager(layoutManager);
        adapter = new ForecastAdapter(this);
        rcyForecast.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.font_theme_color);
        swipeRefreshLayout.setOnRefreshListener(this);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getData(){
        String url = "http://mobile.9188.com/trade/forecast.go";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("gid", gid)
                .add("pn", "1")
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
                        Log.e("json",result);
                        if(result!=null&&!"".equals(result)){
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray jsonArray = jsonObject.getJSONObject("Resp").getJSONObject("rows").getJSONArray("row");
                            for(int i = 0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                ForecastModel date = new ForecastModel();
                                date.setNtitle(object.getString("ntitle"));
                                date.setDescription(object.getString("description"));
                                date.setNdate(object.getString("ndate"));
                                date.setArcurl(object.getString("arcurl"));
                                bean.add(date);
                            }
                            handler.sendEmptyMessage(1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("异常",e.getMessage());
                    }
                }else {
                    Toast.makeText(ForecastActivity.this,"暂时没有数据",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(2,1000);
    }
}
