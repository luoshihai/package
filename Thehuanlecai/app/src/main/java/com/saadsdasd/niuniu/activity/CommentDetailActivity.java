package com.saadsdasd.niuniu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.saadsdasd.niuniu.R;
import com.saadsdasd.niuniu.adapter.CommentDetailAdapter;
import com.saadsdasd.niuniu.base.KBaseActivity;
import com.saadsdasd.niuniu.model.CommentModel;
import com.saadsdasd.niuniu.model.MyCommentModel;
import com.saadsdasd.niuniu.model.TabThreeModel;
import com.saadsdasd.niuniu.utils.BitmapHelp;
import com.saadsdasd.niuniu.utils.RoundImageView;
import com.saadsdasd.niuniu.utils.UserCacheData;
import com.lidroid.xutils.BitmapUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
 * Created by QYQ on 2017/9/22.
 */

public class CommentDetailActivity extends KBaseActivity {
    @BindView(R.id.iv_headImg)
    RoundImageView ivHeadImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_content)
    ImageView ivContent;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_commentNum)
    TextView tvCommentNum;
    @BindView(R.id.ll_comment)
    LinearLayout llComment;
    /*@BindView(R.id.notice)
    Button notice;*/
    @BindView(R.id.rcy_comment)
    RecyclerView rcyComment;
    @BindView(R.id.sr_comment)
    ScrollView srComment;
    @BindView(R.id.etContent)
    EditText etContent;
    private Intent intent;
    private TabThreeModel date;
    BitmapUtils bitmapUtils;
    private int id;
    private List<CommentModel> bean = new ArrayList<>();
    private CommentDetailAdapter adapter;
    private String error;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    adapter.setItems(bean);
                    break;
                case 2:
                    show(error);
                    break;
            }
        }
    };
    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_comment_detail);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleName("详情");
        setTitleBack(true,0);
        bitmapUtils = BitmapHelp.getBitmapUtils(this);
        intent = getIntent();
        date = (TabThreeModel) intent.getSerializableExtra("data");
        //bitmapUtils.display(ivHeadImg,date.getUser().getIcon());

        Glide.with(this).load(date.getUser().getIcon()).into(ivHeadImg);

        /*
        /long类型的时间戳转为日期
         */
        SimpleDateFormat long2FormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String re_StrTime = long2FormatTime.format(date.getPublish_time());
        tvTime.setText(re_StrTime);
        tvContent.setText(date.getContent());
        if (date.getImages() != null) {
            bitmapUtils.display(ivContent, date.getImages().get(0));
        }
        if (date.getPosition() != null) {
            tvLocation.setText(date.getPosition().getLandmarker() + "");
        }
        tvCommentNum.setText(date.getComment_count()+"");
        tvName.setText(date.getUser().getName());
        id = date.getId();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcyComment.setLayoutManager(layoutManager);
        adapter = new CommentDetailAdapter(this);
        rcyComment.setAdapter(adapter);
        etContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // TODO Auto-generated method stub
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    comment();
                    return true;
                }

                return false;
            }
        });
    }
    public void comment() {
        String content = etContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(CommentDetailActivity.this,"请输入评价内容",Toast.LENGTH_SHORT).show();
            //T.showLong("请输入评价内容~");
        } else {
            /*final RemindDialog dialog = new RemindDialog(this);
            dialog.setContent("(40006)等级未到，暂时无法发布评论，请耐心等待");
            dialog.setButtonInfoLeft("取消", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.setButtonInfoRight("确定", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   dialog.dismiss();
                }
            });
            dialog.show();*/
            String url = "http://api.icaipiao123.com/api/v6/social/comment";
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("message_id", id+"")
                    .add("content", content)
                    .build();
            Request request = new Request.Builder()
                    .addHeader("sid", (String) UserCacheData.get(CommentDetailActivity.this,"sid",""))
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
                    Log.e("评论",str);
                    Gson gson = new Gson();
                    MyCommentModel date = gson.fromJson(str,MyCommentModel.class);
                    if("0".equals(date.getStatus())){
                        getData();
                        closeKeyboard();
                    }else {
                        error = date.getMsg();
                        handler.sendEmptyMessage(2);
                    }
                }
            });
        }
    }
    @Override
    protected void getData() {
        super.getData();
        bean = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://api.icaipiao123.com/api/v6/social/message/"+id)
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
                        JSONArray jsonArray = jsonObject.getJSONArray("comments");
                        for(int i = 0;i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            CommentModel model = new CommentModel();
                            CommentModel.UserBean userBean = new CommentModel.UserBean();
                            userBean.setName(object.getJSONObject("user").getString("name"));
                            userBean.setIcon(object.getJSONObject("user").getString("icon"));
                            model.setUser(userBean);
                            model.setPublish_time(object.getLong("publish_time"));
                            model.setContent(object.getString("content"));
                            bean.add(model);
                        }
                        handler.sendEmptyMessage(1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        });
    }
    private void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
