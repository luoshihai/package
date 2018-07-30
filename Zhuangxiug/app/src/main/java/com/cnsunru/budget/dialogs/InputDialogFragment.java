package com.cnsunru.budget.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseDialogFragment;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.EmptyDeal;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;

/**
 * Created by WQ on 2017/9/26.
 *
 * @Describe 输入框
 */

public class InputDialogFragment extends LBaseDialogFragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.edit_money)
    EditText editMoney;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    ConfirmInputListener confirmInputListener;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title.setText(getArguments().getString("title"));
        editMoney.setHint(getArguments().getString("hint"));
        editMoney.setText(getArguments().getString("value"));
        editMoney.setInputType(getArguments().getInt("inputTpye"));
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_save_project;
    }

    /**
     *
     * @param title 标题
     * @param hint  输入框提示文字
     * @param value 输入框默认值
     * @param inputTpye 输入框类型限制
     * @param confirmInputListener  确认输入的监听
     */
    public static void showFragment(FragmentManager fragmentManager, String title,String hint,String value,int inputTpye,ConfirmInputListener confirmInputListener) {
        InputDialogFragment dialogFragment = new InputDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("hint", hint);
        bundle.putString("value", value);
        bundle.putInt("inputTpye",inputTpye);
        dialogFragment.setConfirmInputListener(confirmInputListener);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(fragmentManager, "SaveProjectDialogFragment");
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setSoftInputMode(SOFT_INPUT_STATE_ALWAYS_VISIBLE | SOFT_INPUT_ADJUST_RESIZE);
        return dialog;
    }


    @OnClick({R.id.btnSubmit, R.id.btnCancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                if(EmptyDeal.isEmpy(editMoney)){
                    UIUtils.shortM(editMoney.getHint());
                    return;
                }
                if(confirmInputListener!=null){
                    confirmInputListener.onConfirmInput(editMoney);
                }
                dismissAllowingStateLoss();
                break;
            case R.id.btnCancel:
                dismissAllowingStateLoss();
                break;
        }
    }

    /**
     * 设置确认监听
     * @param confirmInputListener 确认监听
     */
    public void setConfirmInputListener(ConfirmInputListener confirmInputListener) {
        this.confirmInputListener = confirmInputListener;
    }

    public interface ConfirmInputListener{
       void onConfirmInput(EditText editText);
    }
}
