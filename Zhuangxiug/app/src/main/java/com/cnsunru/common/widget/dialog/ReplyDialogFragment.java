package com.cnsunru.common.widget.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseDialogFragment;
import com.cnsunru.common.quest.BaseQuestConfig;
import com.cnsunru.common.quest.BaseQuestStart;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.InputMethodUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;

/**
 * 评论的对话框
 * Created by WQ on 2017/5/8.
 */

public class ReplyDialogFragment extends LBaseDialogFragment {

    @BindView(R.id.edit_content)
    EditText editContent;
    private String comment_id;
    private String member_id;
    private String expert_id;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String hintInfo = getArguments().getString("hintInfo");
        if (hintInfo != null) {
            editContent.setHint(hintInfo);
        }
        comment_id = getArguments().getString("comment_id");
        member_id = getArguments().getString("member_id");
        expert_id = getArguments().getString("expert_id");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_reply;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.NoTitleDialog);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setSoftInputMode(SOFT_INPUT_STATE_ALWAYS_VISIBLE | SOFT_INPUT_ADJUST_RESIZE);
        //设置对话框从底部进入
        dialogWindow.setWindowAnimations(R.style.bottomInWindowAnim);
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = ViewGroup.LayoutParams.MATCH_PARENT;
        p.height = ViewGroup.LayoutParams.MATCH_PARENT;//高度自己设定
        dialogWindow.setAttributes(p);
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    /**
     * 显示对话框
     *
     * @param fragmentManager
     */
    public static void showReplyDialog(FragmentManager fragmentManager, String hintInfo, String comment_id, String member_id, String expert_id) {
        String tag = ReplyDialogFragment.class.getName();
        DialogFragment dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag(tag);
        if (dialogFragment == null) {
            dialogFragment = new ReplyDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("hintInfo", hintInfo);
            bundle.putString("comment_id", comment_id);
            bundle.putString("member_id", member_id);
            bundle.putString("expert_id", expert_id);
            dialogFragment.setArguments(bundle);
        }
        dialogFragment.show(fragmentManager, ReplyDialogFragment.class.getName());

    }

    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        if (requestCode == 1)
        {
            if (bean.status > 0) {
                UIUtils.shortM(bean.msg);
                this.dismiss();
                EventBus.getDefault().post("reply_refresh");
            }
        }
        super.nofityUpdate(requestCode, bean);
    }

    @OnClick({R.id.img_back, R.id.btn_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                //发送评论
                String content = editContent.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    UIUtils.shortM("内容不能为空");
                    return;
                }
             //   BaseQuestStart.replyComment(this, comment_id, member_id, expert_id, editContent.getText().toString());
                break;
            case R.id.img_back:
                //隐藏软键盘
                InputMethodUtil.HideKeyboard(getActivity());
                break;

        }
    }

}
