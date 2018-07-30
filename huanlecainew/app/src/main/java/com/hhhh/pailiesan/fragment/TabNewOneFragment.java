package com.hhhh.pailiesan.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.hhhh.pailiesan.R;
import com.hhhh.pailiesan.activity.ForecastTypeActivity;
import com.hhhh.pailiesan.activity.InformationActivity;
import com.hhhh.pailiesan.activity.LotteryDetailActivity;
import com.hhhh.pailiesan.activity.NoticeActivity;
import com.hhhh.pailiesan.base.KBaseFragment;
import com.hhhh.pailiesan.utils.Tools;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by QYQ on 2017/9/21.
 */

public class TabNewOneFragment extends KBaseFragment implements OnItemClickListener {
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    Unbinder unbinder;
    @BindView(R.id.ll_fenxi)
    LinearLayout llFenxi;
    @BindView(R.id.ll_miji)
    LinearLayout llMiji;
    @BindView(R.id.ll_gonggao)
    LinearLayout llGonggao;
    @BindView(R.id.ll_daletou)
    LinearLayout llDaletou;
    @BindView(R.id.ll_shuangseqiu)
    LinearLayout llShuangseqiu;
    @BindView(R.id.ll_shengfucai)
    LinearLayout llShengfucai;
    @BindView(R.id.ll_11for5)
    LinearLayout ll11for5;
    @BindView(R.id.ll_fucai3d)
    LinearLayout llFucai3d;
    @BindView(R.id.ll_pailie3)
    LinearLayout llPailie3;
    @BindView(R.id.ll_pailie5)
    LinearLayout llPailie5;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private Integer[] img = new Integer[]{R.mipmap.banner_1, R.mipmap.banner_2, R.mipmap.banner_3};
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1new, null);
        unbinder = ButterKnife.bind(this, view);
        initBanner();
        initClick();
        initViewGone();
        return view;
    }

    private void initViewGone() {
//   <!--彩票分析 ，彩票公告， 新闻公告-->
//    <string name="app_caipiaofenxi">显示</string>
//    <string name="app_caipiaozixun">显示</string>
//    <string name="app_xinwengonggao">显示</string>
//    <!--大乐透 ，双色球 ，胜负彩， 七乐彩， 福彩3D ， 排列三 ，排列五 显示隐藏-->
//    <string name="app_大乐透">显示</string>
//    <string name="app_双色球">显示</string>
//    <string name="app_胜负彩">显示</string>
//    <string name="app_七乐彩">显示</string>
//    <string name="app_福彩3D">显示</string>
//    <string name="app_排列三">显示</string>
//    <string name="app_排列五">显示</string>
//    <!--上面的轮播图，可以控制、-->
//    <string name="lunbotu">隐藏</string>
        if (!Tools.getResoult(getActivity(), R.string.lunbotu)) {
            convenientBanner.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.app_caipiaofenxi)) {
            llFenxi.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.app_caipiaozixun)) {
            llMiji.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.app_xinwengonggao)) {
            llGonggao.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.app_大乐透)) {
            llDaletou.setVisibility(View.GONE);
        }

        if (!Tools.getResoult(getActivity(), R.string.app_双色球)) {
            llShuangseqiu.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.app_胜负彩)) {
            llShengfucai.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.app_七乐彩)) {
            ll11for5.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.app_福彩3D)) {
            llFucai3d.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.app_排列三)) {
            llPailie3.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.app_排列五)) {
            llPailie5.setVisibility(View.GONE);
        }
//        if (!Tools.getResoult(getActivity(), R.string.app_shouye)) {
//            radioHome.setVisibility(View.GONE);
//        }

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

    private void initClick() {
        llFenxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), ForecastTypeActivity.class);
                startActivity(intent);
            }
        });
        llMiji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), InformationActivity.class);
                startActivity(intent);
            }
        });
        llGonggao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent);
            }
        });
        llDaletou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), LotteryDetailActivity.class);
                intent.putExtra("title", "大乐透");
                intent.putExtra("id", "23529");
                startActivity(intent);
            }
        });
        llShuangseqiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), LotteryDetailActivity.class);
                intent.putExtra("title", "双色球");
                intent.putExtra("id", "51");
                startActivity(intent);
            }
        });
        llShengfucai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), LotteryDetailActivity.class);
                intent.putExtra("title", "胜负彩");
                intent.putExtra("id", "19");
                startActivity(intent);
            }
        });
        ll11for5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), LotteryDetailActivity.class);
                intent.putExtra("title", "七乐彩");
                intent.putExtra("id", "23528");
                startActivity(intent);
            }
        });
        llFucai3d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), LotteryDetailActivity.class);
                intent.putExtra("title", "福彩3D");
                intent.putExtra("id", "52");
                startActivity(intent);
            }
        });
        llPailie3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), LotteryDetailActivity.class);
                intent.putExtra("title", "排列三");
                intent.putExtra("id", "33");
                startActivity(intent);
            }
        });
        llPailie5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), LotteryDetailActivity.class);
                intent.putExtra("title", "排列五");
                intent.putExtra("id", "35");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
