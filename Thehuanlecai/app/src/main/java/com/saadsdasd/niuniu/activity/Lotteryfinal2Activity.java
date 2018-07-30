package com.saadsdasd.niuniu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.saadsdasd.niuniu.adapter.LotterInfo2Adapter;
import com.saadsdasd.niuniu.adapter.NumAdapter;
import com.saadsdasd.niuniu.base.KBaseActivity;
import com.saadsdasd.niuniu.model.LotteryFinalModel;
import com.saadsdasd.niuniu.model.LotteryFinalModel2;
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
 * Created by QYQ on 2017/9/15.
 */

public class Lotteryfinal2Activity extends KBaseActivity {
    @BindView(R.id.tv_lotteryName)
    TextView tvLotteryName;
    @BindView(R.id.tv_qishu)
    TextView tvQishu;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rcy_number)
    RecyclerView rcyNumber;
    @BindView(R.id.tv_money1)
    TextView tvMoney1;
    @BindView(R.id.tv_money2)
    TextView tvMoney2;
    @BindView(R.id.rcy_type_money)
    RecyclerView rcyTypeMoney;
    private String issue,id;
    private Intent intent;
    LotteryFinalModel model;
    private NumAdapter adapter;
    private LotterInfo2Adapter finalAdapter;
    private String name;
    private List<String> number = new ArrayList<>();
    private List<LotteryFinalModel2> bean = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    setTitleName(name);
                    tvLotteryName.setText(model.getLotName());
                    tvQishu.setText("第"+issue+"期");
                    tvTime.setText(model.getPrizeDate());
                    tvMoney1.setText(model.getSale()+"");
                    tvMoney2.setText(model.getPool()+"");
                    adapter = new NumAdapter(Lotteryfinal2Activity.this,model.getLotName());
                    rcyNumber.setAdapter(adapter);
                    adapter.setItems(number);
                    finalAdapter = new LotterInfo2Adapter(Lotteryfinal2Activity.this);
                    rcyTypeMoney.setAdapter(finalAdapter);
                    finalAdapter.setItems(bean);
                    break;

            }
        }
    };
    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_lottery_final2);

    }

    @Override
    protected void initView() {
        super.initView();
        setTitleBack(true,0);
        intent = getIntent();
        issue = intent.getStringExtra("issue");
        id = intent.getStringExtra("id");
        number.addAll(intent.getStringArrayListExtra("number"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcyNumber.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        rcyTypeMoney.setLayoutManager(layoutManager1);

    }

    @Override
    protected void getData() {
        super.getData();
        Log.e("issue",issue);
        Log.e("id",id);
        String url = "http://m.zhuoyicp.com/kaijang/kjinfo";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("getData", "1")
                .add("issue", issue)
                .add("lotid", id)
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
                        model = new LotteryFinalModel();
                        model.setLotName(jsonObject.getJSONObject("datas").getString("lotName"));
                        model.setPrizeDate(jsonObject.getJSONObject("datas").getString("prizeDate"));
                        model.setSale(jsonObject.getJSONObject("datas").getInt("sale"));
                        model.setPool(jsonObject.getJSONObject("datas").getInt("pool"));
                        name = model.getLotName();
                        JSONArray jsonArray = (JSONArray) jsonObject.getJSONObject("datas").get("reList");
                        for(int i = 0;i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            LotteryFinalModel2 date= new LotteryFinalModel2();
                            date.setAwards(object.getString("awards"));
                            date.setSingle_Note_Bonus(object.getInt("single_Note_Bonus"));
                            date.setWinningNote(object.getInt("winningNote"));
                            bean.add(date);
                        }
                        handler.sendEmptyMessage(1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("异常", e.getMessage());
                    }
                } else {
                    show("暂时没有数据");
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
}
