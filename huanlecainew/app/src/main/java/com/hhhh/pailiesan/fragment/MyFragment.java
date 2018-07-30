package com.hhhh.pailiesan.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhhh.pailiesan.R;
import com.hhhh.pailiesan.activity.LoginActivity;
import com.hhhh.pailiesan.activity.LotteryActivity;
import com.hhhh.pailiesan.activity.MyIntegration;
import com.hhhh.pailiesan.activity.MyNoticeActivity;
import com.hhhh.pailiesan.activity.NewsSettingActivity;
import com.hhhh.pailiesan.activity.PersonInfoActivity;
import com.hhhh.pailiesan.base.KBaseFragment;
import com.hhhh.pailiesan.utils.DataCleanManager;
import com.hhhh.pailiesan.utils.RoundImageView;
import com.hhhh.pailiesan.utils.Tools;
import com.hhhh.pailiesan.utils.UserCacheData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.hhhh.pailiesan.activity.PersonInfoActivity.getBitmapByUri;

/**
 * Created by QYQ on 2017/9/21.
 */

public class MyFragment extends KBaseFragment {
    @BindView(R.id.iv_head)
    RoundImageView ivHead;
    @BindView(R.id.name)
    TextView name;
    //@BindView(R.id.btn_edit)
    //Button btnEdit;
    @BindView(R.id.btn_qiandao)
    Button btnQiandao;
    @BindView(R.id.iv_img1)
    ImageView ivImg1;
    @BindView(R.id.rl_myMsg)
    RelativeLayout rlMyMsg;
    @BindView(R.id.iv_img2)
    ImageView ivImg2;

    @BindView(R.id.rl_kaijiang)
    RelativeLayout rlKaijiang;


//    @BindView(R.id.iv_img3)
//    RelativeLayout banner_setting;
//    @BindView(R.id.banner_setting)
//    ImageView ivImg3;

    @BindView(R.id.banner_setting)
    RelativeLayout banner_setting;
    @BindView(R.id.iv_img3)
    ImageView ivImg3;


    @BindView(R.id.rl_jifen)
    RelativeLayout rlJifen;
    @BindView(R.id.iv_img5)
    ImageView ivImg5;
    @BindView(R.id.rl_tuixong)
    RelativeLayout rlTuixong;
    @BindView(R.id.iv_img10)
    ImageView ivImg10;
    @BindView(R.id.rl_version)
    RelativeLayout rlVersion;
    @BindView(R.id.iv_img6)
    ImageView ivImg6;
    @BindView(R.id.iv_img8)
    ImageView ivImg8;
    @BindView(R.id.tv_huancun)
    TextView tvHuancun;
    @BindView(R.id.rl_huancun)
    RelativeLayout rlHuancun;
    @BindView(R.id.iv_img7)
    ImageView ivImg7;
    @BindView(R.id.rl_loginOut)
    RelativeLayout rlLoginOut;
    Unbinder unbinder;
    private Intent intent;
    AlertDialog.Builder builder;
    AlertDialog alert;
    private DataCleanManager dataCleanManager;
    private String cacheSize;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initClick();
        GoneView();
        return view;
    }

    private void GoneView() {
        if (!Tools.getResoult(getActivity(), R.string.背景)) {
            banner_setting.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.我的消息)) {
            rlMyMsg.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.开奖信息)) {
            rlKaijiang.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.我的积分)) {
            rlJifen .setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.消息推送)) {
            rlTuixong.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.检查版本更新)) {
            rlVersion .setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.清除缓存)) {
            rlHuancun.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.退出登录)) {
            rlLoginOut.setVisibility(View.GONE);
        }

    }

    private void initView(){
        builder = new AlertDialog.Builder(getActivity());
        dataCleanManager = new DataCleanManager();
        try {
            cacheSize = dataCleanManager.getTotalCacheSize(getActivity());
            if("0.0Byte".equals(cacheSize)){
                tvHuancun.setText("0.0M");
            }else {
                tvHuancun.setText(cacheSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        name.setText((String)UserCacheData.get(getActivity(),"name","账号110"));
        Uri uri = Uri.parse((String) UserCacheData.get(getActivity(),"photo",""));
        if(!TextUtils.isEmpty((String) UserCacheData.get(getActivity(),"photo",""))){
            ivHead.setImageBitmap(getBitmapByUri(getActivity(), uri));
        }
        if((Boolean) UserCacheData.get(getActivity(),"sign",false)){
            btnQiandao.setBackgroundResource(R.color.font_light_black);
            btnQiandao.setText("已签到");
            btnQiandao.setClickable(false);
        }else {
            btnQiandao.setBackgroundResource(R.drawable.rectangle_round_red_bg);
            btnQiandao.setText("签到");
        }
    }
    private void initClick(){
        ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), PersonInfoActivity.class);
                startActivity(intent);
            }
        });
        /*btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        rlMyMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), MyNoticeActivity.class);
                startActivity(intent);
            }
        });
        rlKaijiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), LotteryActivity.class);
                startActivity(intent);
            }
        });
        btnQiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserCacheData.put(getActivity(),"sign",true);
                UserCacheData.put(getActivity(),"integration","5");
                btnQiandao.setBackgroundResource(R.color.font_light_black);
                btnQiandao.setText("已签到");
                btnQiandao.setClickable(false);
            }
        });
        rlJifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), MyIntegration.class);
                startActivity(intent);
            }
        });
        rlTuixong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), NewsSettingActivity.class);
                startActivity(intent);
            }
        });
        rlVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show("当前为最新版本!");
            }
        });
        rlHuancun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("确定清理缓存？")
                        .setCancelable(false)
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                dataCleanManager.clearAllCache(getActivity());//清理缓存
                                try {
                                    cacheSize = dataCleanManager.getTotalCacheSize(getActivity());//计算缓存大小
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if("0.0Byte".equals(cacheSize)){
                                    tvHuancun.setText("0.0M");
                                    show("清理成功");
                                    dialog.dismiss();
                                }
                            }
                        });
                alert = builder.create();
                alert.show();
            }
        });
        rlLoginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("确定退出？")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                UserCacheData.put(getActivity(),"isLogin",false);
                                intent = new Intent(getActivity(), LoginActivity.class);
//                                startActivity(intent);
                                getActivity().finish();
                                System.exit(0);

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        name.setText((String)UserCacheData.get(getActivity(),"name",getResources().getString(R.string.app_name)));
        Uri uri = Uri.parse((String) UserCacheData.get(getActivity(),"photo",""));
        if(!TextUtils.isEmpty((String) UserCacheData.get(getActivity(),"photo",""))){
            ivHead.setImageBitmap(getBitmapByUri(getActivity(), uri));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
