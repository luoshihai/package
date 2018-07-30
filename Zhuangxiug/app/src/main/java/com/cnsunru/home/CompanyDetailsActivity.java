package com.cnsunru.home;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.common.util.BannerUtils;
import com.cnsunru.common.util.TestData;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;
import com.sunrun.sunrunframwork.common.PhotoView;
import com.sunrun.sunrunframwork.uiutils.PictureShow;
import com.sunrun.sunrunframwork.weight.GridViewForScroll;
import com.sunrun.sunrunframwork.weight.ListViewForScroll;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WQ on 2017/9/1.
 * @Describe 公司详情
 */

public class CompanyDetailsActivity extends LBaseActivity {
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
    @BindView(R.id.txtShowAllPlan)
    TextView txtShowAllPlan;
    @BindView(R.id.listPlan)
    ListViewForScroll listPlan;
    @BindView(R.id.gridCertifications)
    GridViewForScroll gridCertifications;
//    @BindView(R.id.itemCompanyDate)
//    TextView itemCompanyDate;
//    @BindView(R.id.itemCompanyMonry)
//    TextView itemCompanyMonry;
//    @BindView(R.id.itemCompanyOrgan)
//    TextView itemCompanyOrgan;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    PictureShow pictureShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        pictureShow=new PictureShow(that);
        setAdapters();

    }

    private void setAdapters() {
        final List<String> bannerTestData = TestData.createTestData(6, TestData.TEST_IMAGE);
        BannerUtils.setNetBanner(banner, bannerTestData,true);
        listPlan.setAdapter(new ViewHolderAdapter<String>(that,TestData.createTestData(2,""),R.layout.item_company_plan) {
            @Override
            public void fillView(ViewHodler viewHodler, String o, int i) {
                viewHodler.setImageResourse(R.id.imgProduct,R.drawable.project_img_banner);
            }
        });
        final List<String> testData = TestData.createTestData(4, TestData.TEST_IMAGE);
        gridCertifications.setAdapter(new ViewHolderAdapter<String>(that, testData,R.layout.item_simple_image) {
            @Override
            public void fillView(ViewHodler viewHodler, String o, int i) {
                GlideMediaLoader.load(mContext,viewHodler.getView(R.id.imageView),o);
            }
        } );
        gridCertifications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pictureShow.setArgment(testData,position);
                pictureShow.show();
            }
        });
        banner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                pictureShow.setArgment(bannerTestData,position);
                pictureShow.show();
            }
        });

    }

    @OnClick({R.id.txtShowAllPlan, R.id.btnAdd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtShowAllPlan:
                startIntent.startCompanyPlanListActivity(that);
                break;
            case R.id.btnAdd:
                break;
        }
    }
}
