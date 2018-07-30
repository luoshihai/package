package com.cnsunru.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.common.util.BannerUtils;
import com.cnsunru.common.util.TestData;
import com.cnsunru.common.widget.MomentPicView;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;
import com.sunrun.sunrunframwork.uiutils.PictureShow;
import com.sunrun.sunrunframwork.weight.ListViewForScroll;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WQ on 2017/9/5.
 *
 * @Describe 工地详情
 */

public class WorkSiteDetailsActivity extends LBaseActivity {
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.banner)
    ConvenientBanner banner;

    @BindView(R.id.txtNumber)
    TextView txtNumber;
    @BindView(R.id.itemTitle)
    TextView itemTitle;
    @BindView(R.id.itemTitleDesc)
    TextView itemTitleDesc;
    @BindView(R.id.txtProductPrice)
    TextView txtProductPrice;
    @BindView(R.id.listDaily)
    ListViewForScroll listDaily;
    @BindView(R.id.layDaily)
    LinearLayout layDaily;
    @BindView(R.id.txtCollect)
    TextView txtCollect;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    PictureShow pictureShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worksite_details);
        pictureShow = new PictureShow(that);
        setAdapters();

    }

    private void setAdapters() {
        final List<String> bannerTestData = TestData.createTestData(6, TestData.TEST_IMAGE);
        BannerUtils.setNetBanner(banner, bannerTestData, true);
        listDaily.setAdapter(new ViewHolderAdapter<String>(that, TestData.createTestData(9, ""), R.layout.item_worksite_daily_) {
            @Override
            public void fillView(ViewHodler viewHodler, String o, int i) {
                MomentPicView mpvDailyImages = viewHodler.getView(R.id.mpvDailyImages);
                List<String> testData = TestData.createTestData((i+1)%9, TestData.TEST_IMAGE);
                mpvDailyImages.setImageUrls(testData);
                mpvDailyImages.setOnClickItemListener(new MomentPicView.OnClickItemListener() {
                    @Override
                    public void onClick(int position, ArrayList<String> url) {
                        pictureShow.setArgment(url,position);
                        pictureShow.show();
                    }
                });
            }
        });
        banner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                pictureShow.setArgment(bannerTestData, position);
                pictureShow.show();
            }
        });

    }

    @OnClick({R.id.txtCollect, R.id.btnAdd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtCollect:
                break;
            case R.id.btnAdd:
                break;
        }
    }
}
