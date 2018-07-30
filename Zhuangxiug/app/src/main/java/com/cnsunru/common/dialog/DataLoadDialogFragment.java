package com.cnsunru.common.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseDialogFragment;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.http.NAction;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

import java.util.LinkedList;
import java.util.Queue;


public class DataLoadDialogFragment extends LBaseDialogFragment {
	public NAction nAction;
	public Queue<NAction> nActions=new LinkedList<>();
	public onDataLoadeListener onDataLoadeListener;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public Dialog getDialog() {
		return new Dialog(getActivity(), R.style.CustomDialog);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getDialog().getWindow().setBackgroundDrawable(
//				new ColorDrawable(Color.TRANSPARENT));
//		getDialog().getWindow().setWindowAnimations(R.style.bottomInWindowAnim);
//		getDialog().setCanceledOnTouchOutside(false);
//		getDialog().setCancelable(false);
		return super.onCreateView(inflater, container, savedInstanceState);
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		loadData();
	}

	@Override
	protected int getLayoutRes() {
		return R.layout.dialog_data_load;
	}

	public void closeDialog(){
		
	}
	private void loadData() {

		NAction nAction = nActions.poll();
		if(nAction==null){
			dismissAllowingStateLoss();
			return;
		}
		UIUtils.showLoadDialog(that,"正在加载数据...");
		if(nAction.getRequestType()==POST){
			requestAsynPost(nAction.setRequestCode(1));
		}else {
			requestAsynGet(nAction.setRequestCode(1));
		}

		// .setTypeToken(ExamTopBean.class)
	}

	@Override
	public void nofityUpdate(int requestCode, BaseBean bean) {
		if (bean.status == 1) {
			onDataLoadeListener.onDataLoaded(bean);
//			dismissAllowingStateLoss();
		} else {
			UIUtils.shortM(bean);
			dismissAllowingStateLoss();
//			UiUtils.shortM(bean);
		}
		super.nofityUpdate(requestCode, bean);
	}

	public static  DataLoadDialogFragment getInstance(FragmentManager fm, NAction action, onDataLoadeListener onDataLoadeListener){
		DataLoadDialogFragment dataLoadDialogFragment=new DataLoadDialogFragment();
		dataLoadDialogFragment.setAction(action);
		dataLoadDialogFragment.setOnDataLoadeListener(onDataLoadeListener);
		dataLoadDialogFragment.show(fm,"DataLoadDialogFragment");
		return dataLoadDialogFragment;
	}
	public static  DataLoadDialogFragment getInstance(FragmentManager fm,onDataLoadeListener onDataLoadeListener, NAction...action){
		DataLoadDialogFragment dataLoadDialogFragment=new DataLoadDialogFragment();
		dataLoadDialogFragment.setAction(action);
		dataLoadDialogFragment.setOnDataLoadeListener(onDataLoadeListener);
		dataLoadDialogFragment.show(fm,"DataLoadDialogFragment");
		return dataLoadDialogFragment;
	}

	public void setAction(NAction...nAction) {
//		this.nAction = nAction;
		for (NAction action : nAction) {
			nActions.add(action);
		}
	}

	public void setOnDataLoadeListener(DataLoadDialogFragment.onDataLoadeListener onDataLoadeListener) {
		this.onDataLoadeListener = onDataLoadeListener;
	}

	public interface onDataLoadeListener{
		void onDataLoaded(BaseBean bean);
	}

	@Override
	public void dismissAllowingStateLoss() {
		UIUtils.cancelLoadDialog();
		super.dismissAllowingStateLoss();
	}

	@Override
	public void requestFinish() {
		loadData();
//		dismiss();
//		super.requestFinish();
	}
}
