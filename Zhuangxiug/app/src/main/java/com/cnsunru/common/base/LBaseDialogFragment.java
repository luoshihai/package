package com.cnsunru.common.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.cnsunru.R;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.http.NAction;
import com.sunrun.sunrunframwork.http.NetRequestHandler;
import com.sunrun.sunrunframwork.http.NetServer;
import com.sunrun.sunrunframwork.http.cache.NetSession;
import com.sunrun.sunrunframwork.http.utils.UIUpdateHandler;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.StatisticsUtil;
import com.sunrun.sunrunframwork.utils.log.Logger;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN;

public abstract class LBaseDialogFragment extends DialogFragment implements
        NetRequestHandler, UIUpdateHandler {
    protected NetServer mNServer;
    protected Activity that;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    /**
     * 启用eventBus,不用手动关闭
     */
    public void initEventBus() {
        EventBus.getDefault().register(this);
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = getDialog();
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setSoftInputMode(SOFT_INPUT_STATE_ALWAYS_HIDDEN | SOFT_INPUT_ADJUST_RESIZE);
        //设置对话框从底部进入
        dialogWindow.setWindowAnimations(R.style.bottomInWindowAnim);
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = ViewGroup.LayoutParams.MATCH_PARENT;
        p.height = ViewGroup.LayoutParams.MATCH_PARENT;//高度自己设定
        dialogWindow.setAttributes(p);
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        int screenHeight = getScreenHeight(getActivity());
        int statusBarHeight = getStatusBarHeight(getContext());
        int dialogHeight = screenHeight - statusBarHeight;
        dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dialogHeight);
        return dialog;
    }

    public Dialog getDialog(){
       return new Dialog(getActivity(), R.style.NoTitleDialog);
    }
    @LayoutRes
    protected abstract int getLayoutRes();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        that  = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        StatisticsUtil.pageStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        StatisticsUtil.pageEnd(this);
    }

    /**
     * 初始化网络请求模块,由子类在有需要时调用
     */
    protected void initNetServer() {
        if (mNServer == null)
            mNServer = new NetServer(that, this);
    }

    @Override
    public void requestAsynGet(NAction action) {
        initNetServer();
        mNServer.requestAsynGet(action);

    }

    @Override
    public void useCache(boolean useCache) {
        initNetServer();
        mNServer.useCache(useCache);
    }

    @Override
    public void requestAsynPost(NAction action) {
        initNetServer();
        mNServer.requestAsynPost(action);
    }

    @Override
    public void cancelRequest(int requestCode) {
        if (mNServer != null)
            mNServer.cancelRequest(requestCode);
    }

    @Override
    public void cancelAllRequest() {
        if (mNServer != null)
            mNServer.cancelAllRequest();
    }

    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {

    }

    @Override
    public void nofityUpdate(int requestCode, float progress) {

    }

    @Override
    public void dealData(int requestCode, BaseBean bean) {

    }

    @Override
    public void loadFaild(int requestCode, BaseBean bean) {

    }

    @Override
    public void emptyData(int requestCode, BaseBean bean) {

    }

    @Override
    public void requestStart() {

    }

    @Override
    public void requestCancel() {
        UIUtils.cancelLoadDialog();
    }

    @Override
    public void requestFinish() {
        UIUtils.cancelLoadDialog();
    }
    protected void bindDimissListener(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });
    }

    @Override
    public NetSession getSession() {
        return NetSession.instance(that);
    }

    @Override
    public void onDestroyView() {
        cancelAllRequest();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        bind.unbind();
        super.onDestroy();
        Logger.D("视图销毁 " + this);
        that = null;
    }


    public void finish() {
        if (that != null)
            that.finish();
    }


    private static int getScreenHeight(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    private static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}