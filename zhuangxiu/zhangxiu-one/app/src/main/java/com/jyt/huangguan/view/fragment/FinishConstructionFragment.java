package com.jyt.huangguan.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.jyt.huangguan.adapter.ShowImageAdapter;
import com.jyt.huangguan.api.BeanCallback;
import com.jyt.huangguan.api.Const;
import com.jyt.huangguan.api.Path;
import com.jyt.huangguan.api.PutObjectSamples;
import com.jyt.huangguan.bean.BaseJson;
import com.jyt.huangguan.bean.OssBean;
import com.jyt.huangguan.bean.ProgressBean;
import com.jyt.huangguan.bean.ProgressFileBean;
import com.jyt.huangguan.bean.Tuple;
import com.jyt.huangguan.helper.IntentHelper;
import com.jyt.huangguan.helper.IntentKey;
import com.jyt.huangguan.helper.IntentRequestCode;
import com.jyt.huangguan.itemDecoration.RcvGridSpaceItemDecoration;
import com.jyt.huangguan.model.ManeuverModel;
import com.jyt.huangguan.model.ProjectDetailModel;
import com.jyt.huangguan.util.BaseUtil;
import com.jyt.huangguan.util.L;
import com.jyt.huangguan.util.ScreenUtils;
import com.jyt.huangguan.util.T;
import com.jyt.huangguan.view.viewholder.BaseViewHolder;
import com.jyt.huangguan.view.widget.LoadingDialog;
import com.jyt.huangguan.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static android.app.Activity.RESULT_OK;

/**
 * Created by chenweiqi on 2017/11/6.
 */

public class FinishConstructionFragment extends BaseFragment {
    @BindView(R.id.v_rcv)
    RecyclerView vRcv;
    @BindView(R.id.text_imageCount)
    TextView textImageCount;
    @BindView(R.id.btn_submit)
    LinearLayout btnSubmit;


    ShowImageAdapter adapter;
    //当前数量
    int currentCount;
    //上传最大数量
    int maxCount = 20;
    //图片链接
    List imageList;
    //rcv显示列数
    final int columnCount = 4;
    //图片边距百分比
    final float imageMarginPercent = 0.011f;

    ProjectDetailModel projectDetailModel;
    private ManeuverModel mManeuverModel;
    private ProgressBean mBean;
    private OSS mOSS;
    private List<ProgressFileBean> mUploadList;

    @Override
    protected int getLayoutId() {
        return R.layout.framgment_finish_construction;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

        int windowWidth = ScreenUtils.getScreenWidth(getContext());

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(columnCount,StaggeredGridLayoutManager.VERTICAL);
        vRcv.setLayoutManager(gridLayoutManager);
        vRcv.setAdapter(adapter = new ShowImageAdapter());

        int margin_image_percent = (int) (windowWidth*imageMarginPercent);
        vRcv.addItemDecoration(new RcvGridSpaceItemDecoration(columnCount,margin_image_percent,true));

        adapter.setOnViewHolderClickListener(new BaseViewHolder.OnViewHolderClickListener() {
            @Override
            public void onClick(BaseViewHolder holder) {
                if (holder.getData() instanceof Integer){
                    List images = new ArrayList(Arrays.asList( new Integer[imageList.size()]));
                    Collections.copy(images,imageList);
                    L.e("size"+images.size());
                    images.remove(images.size()-1);
                    int selCount = maxCount-currentCount;
                    if (selCount>0){
                        IntentHelper.openSelImageActivityForResult(FinishConstructionFragment.this,selCount,images);

                    }else {
                        T.showShort(getContext(),"已达到限制，无法继续添加");
                    }
                }else {
                    IntentHelper.openBrowseImagesActivity(getContext(),holder.getData().toString());
                }
            }
        });

        if (imageList==null){
            imageList = new ArrayList();
        }
//        imageList.add("http://img1.imgtn.bdimg.com/it/u=3191256922,1392369155&fm=214&gp=0.jpg");
//        imageList.add("http://img1.imgtn.bdimg.com/it/u=3191256922,1392369155&fm=214&gp=0.jpg");
//        imageList.add("http://img1.imgtn.bdimg.com/it/u=3191256922,1392369155&fm=214&gp=0.jpg");

        imageList.add(imageList.size(),new Integer(0));

        adapter.setDataList(imageList);
        upDateView();
    }

    public void upDateView(){
        currentCount = imageList.size()-1;
        textImageCount.setText(getString(R.string.uploadImages_finish_text,imageList.size()-1+"",maxCount+""));
    }

    private void init() {
        mBean = getArguments().getParcelable(IntentKey.PROGRESS);
        mManeuverModel=new ManeuverModel();
        mUploadList = new ArrayList<>();
        //初始化OSS
        mManeuverModel.getOssAliyunKey(new ManeuverModel.OngetOssAliyunListener() {
            @Override
            public void Result(boolean isSuccess, OssBean bean) {
                if (isSuccess){
                    Log.e("@#",bean.getAccessKeyId());
                    Log.e("@#",bean.getAccessKeySecret());
                    Log.e("@#",bean.getSecurityToken());
                    String AccessKeyId = bean.getAccessKeyId();
                    String SecretId = bean.getAccessKeySecret();
                    String token = bean.getSecurityToken();
                    OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(AccessKeyId,SecretId,token);
                    ClientConfiguration conf = new ClientConfiguration();
                    conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
                    conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
                    conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
                    conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
                    mOSS = new OSSClient(BaseUtil.getContext(), Const.endpoint,credentialProvider,conf);
                }
            }
        });
    }

    //上传图片
    @OnClick(R.id.btn_submit)
    public void onSubmitImageClick() {
        if (imageList.size()<=1){
            BaseUtil.makeText("请添加上传的工程图片");
            return;
        }

        final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.show();
        //提交aliyun
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < imageList.size()-1; i++) {
                    String value = (String)(imageList.get(i));
                    int lastIndex = value.lastIndexOf("/");
                    PutObjectSamples putObjectSamples = new PutObjectSamples(mOSS,Const.BucketName,new Date().getTime()+value.substring(lastIndex+1),value);
                    PutObjectRequest request = putObjectSamples.putObjectFromLocalFile();
                    String remotePath = Path.URL_Ayiyun+request.getObjectKey();
                    mUploadList.add(new ProgressFileBean(remotePath,"2"));
                    if (i==imageList.size()-2){
                        //返回数据,提交后台
                        projectDetailModel.pushFileList(mBean.getProjectId(), mUploadList, new BeanCallback<BaseJson>() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                loadingDialog.dismiss();
                                BaseUtil.makeText("上传失败，请重试");
                            }

                            @Override
                            public void onResponse(BaseJson response, int id) {
                                loadingDialog.dismiss();
                                if (response.ret){
                                    Log.e("@#","SSSAAADDD");
                                    imageList.clear();
                                    imageList.add(imageList.size(),new Integer(0));
                                    adapter.notifyData(imageList);
                                    BaseUtil.makeText("上传完成");
                                    getActivity().finish();
                                }
                            }
                        });
                    }
                }
            }
        }).start();

    }

    public void setProjectDetailModel(ProjectDetailModel projectDetailModel) {
        this.projectDetailModel = projectDetailModel;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentRequestCode.CODE_SEL_IMAGES && resultCode == RESULT_OK){
            Tuple result = IntentHelper.SelImageActivityGetResult(data);
            imageList = (List) result.getItem1();
            imageList.add(imageList.size(),new Integer(0));
            adapter.setDataList(imageList);
            adapter.notifyDataSetChanged();
            upDateView();
        }
    }
}
