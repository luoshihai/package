package com.cnsunru.budget.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 删除确认对话框
 * Created by WQ on 2017/10/11.
 */

public class DeleteConfimDialogFragment extends LBaseDialogFragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    ConfirmActionListener confirmActionListener;
    CancelActionListener cancelActionListener;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title.setText(getArguments().getString("title"));
        tvContent.setText(getArguments().getString("content"));
        btnSubmit.setText(getArguments().getString("confirmBtn"));
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_delete_confim;
    }

    @OnClick({R.id.btnSubmit, R.id.btnCancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                if(confirmActionListener!=null){
                    confirmActionListener.onConfirm(view);
                }
                dismissAllowingStateLoss();
                break;
            case R.id.btnCancel:
                if(cancelActionListener!=null){
                    cancelActionListener.onConfirm(view);
                }
                dismissAllowingStateLoss();
                break;
        }
    }
    /**
     *
     * @param fragmentManager
     * @param confirmActionListener
     */
    public static void showFragment(FragmentManager fragmentManager, ConfirmActionListener confirmActionListener) {
        showFragment(fragmentManager,"警告","删除操作无法撤销，您确认删除?", confirmActionListener);
    }

    /**
     * @param title 标题
     * @param content 提示内容
     */
    public static void showFragment(FragmentManager fragmentManager,String title,String content, ConfirmActionListener confirmActionListener) {
        showFragment(fragmentManager, title, content,"确认", confirmActionListener,null);
    }

    /**
     * @param title 标题
     * @param content 提示内容
     */
    public static void showFragment(FragmentManager fragmentManager,String title,String content,String confirmBtn, ConfirmActionListener confirmActionListener,CancelActionListener cancelActionListener) {
        Bundle args = new Bundle();
        DeleteConfimDialogFragment fragment = new DeleteConfimDialogFragment();
        fragment.setConfirmActionListener(confirmActionListener);
        fragment.setCancelActionListener(cancelActionListener);
        args.putString("title",title);
        args.putString("content",content);
        args.putString("confirmBtn",confirmBtn);
        fragment.setArguments(args);
        fragment.show(fragmentManager, "DeleteConfimDialogFragment");
    }

    public void setConfirmActionListener(ConfirmActionListener confirmActionListener) {
        this.confirmActionListener = confirmActionListener;
    }
    public void setCancelActionListener(CancelActionListener cancelActionListener) {
        this.cancelActionListener = cancelActionListener;
    }
    public interface ConfirmActionListener{
        void onConfirm(View view);
    }
    public interface CancelActionListener{
        void onConfirm(View view);
    }
}
