package com.saadsdasd.niuniu.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.saadsdasd.niuniu.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;



/**
 * Created by QYQ on 2017/9/8.
 * 主页
 */

public class TabOneFragment extends Fragment implements OnItemClickListener {
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    Unbinder unbinder;
    @BindView(R.id.ll_tuiqiu)
    LinearLayout llTuiqiu;
    @BindView(R.id.ll_kaiqiu)
    LinearLayout llKaiqiu;
    @BindView(R.id.shuangseqiu)
    LinearLayout shuangseqiu;
    @BindView(R.id.daletou)
    LinearLayout daletou;
    @BindView(R.id.ll_pailie3)
    LinearLayout llPailie3;
    @BindView(R.id.ll_fucai3)
    LinearLayout llFucai3;
    @BindView(R.id.ll_pailie5)
    LinearLayout llPailie5;
    @BindView(R.id.ll_qiuxincai)
    LinearLayout llQiuxincai;
    @BindView(R.id.shiyixuan5)
    LinearLayout shiyixuan5;
    @BindView(R.id.ll_renxuan9)
    LinearLayout llRenxuan9;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private Integer[] img = new Integer[]{R.mipmap.banner_1, R.mipmap.banner_2};
    private Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, null);
        unbinder = ButterKnife.bind(this, view);
        ButterKnife.bind(getActivity());
        initBanner();
        initOnClick();
        return view;
    }

    private void initBanner() {
        for (int i = 0; i < img.length; i++) {
            localImages.add(img[i]);
        }
        //开始自动翻页
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        }, localImages)
                //设置指示器是否可见
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(4000)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.banner_point_nor, R.drawable.banner_point_over})
                //设置指示器的方向（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置点击监听事件
                .setOnItemClickListener(this)
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);
    }

    @Override
    public void onItemClick(int position) {

    }

    //为了方便改写，来实现复杂布局的切换
    private class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            //你可以通过layout文件来创建，不一定是Image，任何控件都可以进行翻页
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }
    }
    private void initOnClick(){
        /*llTuiqiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), DayBallActivity.class);
                startActivity(intent);
            }
        });
        llKaiqiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), LotteryResultActivity.class);
                startActivity(intent);
            }
        });
        shuangseqiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), LotteryFianlActivity.class);
                intent.putExtra("id","51");
                startActivity(intent);
            }
        });
        daletou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), LotteryFianlActivity.class);
                intent.putExtra("id","23529");
                startActivity(intent);
            }
        });
        llPailie3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), LotteryFianlActivity.class);
                intent.putExtra("id","33");
                startActivity(intent);
            }
        });
        llPailie5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), LotteryFianlActivity.class);
                intent.putExtra("id","35");
                startActivity(intent);
            }
        });
        llFucai3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), LotteryFianlActivity.class);
                intent.putExtra("id","52");
                startActivity(intent);
            }
        });
        llQiuxincai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), LotteryFianlActivity.class);
                intent.putExtra("id","10022");
                startActivity(intent);
            }
        });
        shiyixuan5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), LotteryFianlActivity.class);
                intent.putExtra("id","35");
                startActivity(intent);
            }
        });
        llRenxuan9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), LotteryFianlActivity.class);
                intent.putExtra("id","19");
                startActivity(intent);
            }
        });*/
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
