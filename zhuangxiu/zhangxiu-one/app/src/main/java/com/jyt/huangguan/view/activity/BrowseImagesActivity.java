package com.jyt.huangguan.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jyt.huangguan.adapter.TouchImageAdapter;
import com.jyt.huangguan.api.Const;
import com.jyt.huangguan.bean.Tuple;
import com.jyt.huangguan.helper.IntentHelper;
import com.jyt.huangguan.model.ShareModel;
import com.jyt.huangguan.util.BaseUtil;
import com.jyt.huangguan.view.dialog.DownloadDialog;
import com.jyt.huangguan.view.widget.ExtendedViewPager;
import com.jyt.huangguan.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by chenweiqi on 2017/10/30.
 */
//查看图片
public class BrowseImagesActivity extends BaseActivity {
    @BindView(R.id.v_viewPager)
    ExtendedViewPager vViewPager;


    TouchImageAdapter adapter;
    //数据源
    List images = new ArrayList();
    //开始查看index
    int startIndex = 0;
    private DownloadDialog mDownloadDialog;
    private ShareModel mShareModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_browse_images;
    }

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //0 判断目录是否存在
        File files = new File(Const.mMainFile);
        if (!files.exists()){
            //目录不存在
            files.mkdirs();
        }
        mDownloadDialog = new DownloadDialog(this);
        mShareModel = new ShareModel();
        Tuple tuple = IntentHelper.BrowseImagesActivityGetPara(getIntent());
        images = (List) tuple.getItem1();
        startIndex = (int) tuple.getItem2();
        mDownloadDialog.setOnDownloadListener(new DownloadDialog.OnDownloadListener() {
            @Override
            public void OnClick() {
                String filePath = (String) images.get(startIndex);
                int xiegang = filePath.lastIndexOf("/");
                String fileNameAndType = filePath.substring(xiegang+1);
                Log.e("@#",filePath);
                mShareModel.DownloadFile(filePath, fileNameAndType, new ShareModel.OnDownloadFileListener() {
                    @Override
                    public void Before(String tag) {

                    }

                    @Override
                    public void Progress(int progress) {
                    }

                    @Override
                    public void Result(boolean isSuccess) {
                        if (isSuccess){
                            BaseUtil.makeText("下载成功");
                        }else {
                            BaseUtil.makeText("下载失败");
                        }
                    }
                });
            }
        });

        vViewPager.setAdapter(adapter = new TouchImageAdapter());
        adapter.setImages(images);
        adapter.notifyDataSetChanged();
        vViewPager.setCurrentItem(startIndex);
        adapter.setOnLongClickListener(new TouchImageAdapter.OnLongClickListener() {
            @Override
            public void OnLongClick(String data) {
                mDownloadDialog.show();
            }
        });


    }
}
