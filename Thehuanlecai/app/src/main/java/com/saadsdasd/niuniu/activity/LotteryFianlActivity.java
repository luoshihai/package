package com.saadsdasd.niuniu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saadsdasd.niuniu.R;
import com.saadsdasd.niuniu.adapter.LotterInfoAdapter;
import com.saadsdasd.niuniu.adapter.NumAdapter;
import com.saadsdasd.niuniu.model.HistoryModel;
import com.saadsdasd.niuniu.model.LotteryDetailModel;
import com.saadsdasd.niuniu.model.LotteryModel;

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
 * @author QYQ
 * @date 2017/9/8
 */
public class LotteryFianlActivity extends AppCompatActivity {

    @BindView(R.id.tv_text1)
    TextView tvText1;
    @BindView(R.id.tv_text2)
    TextView tvText2;
    @BindView(R.id.rcy_1)
    RecyclerView rcy1;
    @BindView(R.id.rcy_2)
    RecyclerView rcy2;
    @BindView(R.id.tv_text3)
    TextView tvText3;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_text4)
    TextView tvText4;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_history)
    Button btnHistory;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private Intent intent;
    private LotteryModel date;
    private HistoryModel model;
    private NumAdapter adapter;
    private LotterInfoAdapter lotterInfoAdapter;
    private List<String> numbers = new ArrayList<>();
    private List<LotteryDetailModel> bean = new ArrayList<>();
    private String name, term, type;
    private int id;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    lotterInfoAdapter.setItems(bean);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_final);
        ButterKnife.bind(this);
        initView();
        getData();
    }

    private void initView() {
        intent = getIntent();
        id = intent.getIntExtra("id", 0);
        if (id == 1) {
            date = (LotteryModel) intent.getSerializableExtra("info");
            name = date.getName();
            term = date.getTermNo();
            type = date.getType();
            adapter = new NumAdapter(this, date.getType());
            tvTitle.setText(date.getType() + "开奖");
            tvText1.setText("第" + date.getTermNo() + "期");
            tvText2.setText(date.getDate());
            if (TextUtils.isEmpty(date.getSale()) && TextUtils.isEmpty(date.getPrizepool())) {
                ll1.setVisibility(View.GONE);
                ll2.setVisibility(View.GONE);
            } else {
                tvText3.setText(date.getSale() + "元");
                tvText4.setText(date.getPrizepool() + "元");
            }
        } else if (id == 2) {
            model = (HistoryModel) intent.getSerializableExtra("info");
            name = intent.getStringExtra("name");
            term = model.getTerm();
            type = model.getType();
            tvTitle.setText(model.getType() + "开奖");
            tvText1.setText("第" + model.getTerm() + "期");
            tvText2.setText(model.getTime());
            adapter = new NumAdapter(this, model.getType());
            if (TextUtils.isEmpty(model.getSale()) && TextUtils.isEmpty(model.getPrizepool())) {
                ll1.setVisibility(View.GONE);
                ll2.setVisibility(View.GONE);
            } else {
                tvText3.setText(model.getSale() + "元");
                tvText4.setText(model.getPrizepool() + "元");
            }
        }
        numbers = intent.getStringArrayListExtra("number");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcy1.setLayoutManager(layoutManager);


        rcy1.setAdapter(adapter);
        adapter.setItems(numbers);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        rcy2.setLayoutManager(layoutManager1);
        lotterInfoAdapter = new LotterInfoAdapter(this);
        rcy2.setAdapter(lotterInfoAdapter);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LotteryFianlActivity.this, HistoryActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        String url = "http://mapi.159cai.com/phoneKjxq.php";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("type", name)
                .add("term", term)
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
                    JSONObject jsonObject = new JSONObject(str);
                    JSONArray jsonArray = jsonObject.getJSONArray("prizeLevel");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        LotteryDetailModel model = new LotteryDetailModel();
                        model.setName(object.getString("name"));
                        model.setBetnum(object.getString("betnum"));
                        model.setPrize(object.getString("prize"));
                        bean.add(model);
                    }
                    handler.sendEmptyMessage(1);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("异常", e.getMessage());
                }

            }
        });
    }


}
