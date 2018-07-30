package com.jyt.huangguan.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jyt.huangguan.api.BeanCallback;
import com.jyt.huangguan.bean.BaseJson;
import com.jyt.huangguan.bean.InfoBean;
import com.jyt.huangguan.helper.IntentHelper;
import com.jyt.huangguan.model.InfoModel;
import com.jyt.huangguan.model.impl.InfoModelmpl;
import com.jyt.huangguan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

import static com.jyt.huangguan.util.BaseUtil.getTime;

/**
 * @author LinWei on 2017/12/7 14:50
 */
public class InfoFragment extends BaseFragment {
    @BindView(R.id.iv_blue)
    ImageView mIvBlue;
    @BindView(R.id.tv_progress)
    TextView mTvProgress;
    @BindView(R.id.tv_Rtime)
    TextView mTvRtime;
    @BindView(R.id.rl_progress)
    RelativeLayout mRlProgress;
    Unbinder unbinder;

    private InfoModel mInfoModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initData();
        initListener();
    }

    private void init() {
        mInfoModel = new InfoModelmpl();
    }

    private void initData(){
        mInfoModel.getLatOneProgress(new BeanCallback<BaseJson<InfoBean>>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("@#","P-error: "+e.getMessage());
            }

            @Override
            public void onResponse(BaseJson<InfoBean> response, int id) {
                if (response.ret){
                    InfoBean data = response.data;
                    mTvProgress.setText("进度已达"+data.getProjectName() +"，点击查看");
                    mTvRtime.setText(getTime(data.getUpdateDate()));

                }
            }
        });
    }

    private void initListener() {
        mRlProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.OpenInfoDetailActivity(getActivity(),0);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
