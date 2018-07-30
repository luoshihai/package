package com.cnsunru.budget.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseDialogFragment;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.EditTextHelper;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.EmptyDeal;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cnsunru.common.quest.BaseQuestConfig.SAVE_PROJECT_NAME_CODE;

/**
 * Created by WQ on 2017/9/26.
 *
 * @Describe 保存工程
 */

public class SaveProjectDialogFragment extends LBaseDialogFragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.edit_money)
    EditText editMoney;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    String project_id,content;
    public static final String SAVE_PROJECT="SaveProject";
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        project_id=getArguments().getString("project_id");
        content=getArguments().getString("content");
        EditTextHelper.setTextAndSelectEnd(editMoney,content);

    }


    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_save_project;
    }

    /**
     * 显示保存对话框
     * @param fragmentManager
     * @param project_id    项目id
     * @param content   已有的项目名称
     */
    public static void showFragment(FragmentManager fragmentManager, String project_id,String content) {
        SaveProjectDialogFragment addGoodsDialogFragment = new SaveProjectDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("project_id", project_id);
        bundle.putString("content", content);
        addGoodsDialogFragment.setArguments(bundle);
        addGoodsDialogFragment.show(fragmentManager, "SaveProjectDialogFragment");
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setGravity(Gravity.CENTER);
        return dialog;
    }

    public void nofityUpdate(int requestCode,BaseBean bean){
        switch(requestCode){
            case SAVE_PROJECT_NAME_CODE:
                UIUtils.shortM(bean);
                if(bean.status==1){
                    dismissAllowingStateLoss();
                    EventBus.getDefault().post(SAVE_PROJECT);
                }
                break;
        }
        super.nofityUpdate(requestCode,bean);
    }

    @OnClick({R.id.btnSubmit, R.id.btnCancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                if(EmptyDeal.isEmpy(editMoney)){
                    UIUtils.shortM("请输入工程名称");
                    return;
                }
                UIUtils.showLoadDialog(that,"保存中...");
                BaseQuestStart.save_project_name(this,project_id,editMoney.getText().toString());
                break;
            case R.id.btnCancel:
                dismissAllowingStateLoss();
                break;
        }
    }
}
