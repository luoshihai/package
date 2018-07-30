/**
 *
 */
package com.saadsdasd.niuniu.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saadsdasd.niuniu.R;

import butterknife.BindView;


/**
 * Created by MMF on 2016/6/14.
 * 提示框
 */
public class RemindDialog extends Dialog {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.linearLayout1)
    LinearLayout linearLayout1;
    private Context context;

    /**
     * @param context
     */
    public RemindDialog(Context context) {
        super(context, R.style.setting_dialog_style);
        this.setContentView(R.layout.remind_dialog);
        this.context = context;
        init();
    }

    public RemindDialog(Context context, boolean cancelable,
                        OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.setContentView(R.layout.remind_dialog);
        init();
    }

    public RemindDialog(Context context, int theme) {

        super(context, theme);
        this.setContentView(R.layout.remind_dialog);
        init();
    }


    private void init() {
        tvTitle = (TextView) this.findViewById(R.id.tv_title);
        tvContent = (TextView) this.findViewById(R.id.tv_content);
        btnCancel = (Button) this.findViewById(R.id.btn_cancel);
        btnConfirm = (Button) this.findViewById(R.id.btn_confirm);
        viewLine = (View) this.findViewById(R.id.view_line);

    }

    public void setContent(String content) {

        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(content);

    }

    public void setTitle(String title) {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
    }

    public void setButtonInfoLeft(String bt1text, View.OnClickListener onClickListener) {
        btnCancel.setText(bt1text);
        btnCancel.setOnClickListener((View.OnClickListener) onClickListener);
    }

    public void setButtonInfoRight(String bt1text, View.OnClickListener onClickListener) {
        btnConfirm.setText(bt1text);
        btnConfirm.setOnClickListener((View.OnClickListener) onClickListener);

    }

    public void setOneButtonLeft(String bt1text, View.OnClickListener onClickListener) {

        btnCancel.setText(bt1text);
        btnCancel.setOnClickListener((View.OnClickListener) onClickListener);
        viewLine.setVisibility(View.GONE);
        btnConfirm.setVisibility(View.GONE);

    }

    /**
     * 设置单个按钮，取消对话框
     *
     * @param text
     */
    public void setButtonDismiss(String text) {

        btnCancel.setText(text);
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RemindDialog.this.dismiss();
            }
        });
        viewLine.setVisibility(View.GONE);
        btnConfirm.setVisibility(View.GONE);

    }

    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {
            System.out.println("show错误");
        }

    }

    @Override
    public void dismiss() {
        //	countTimer.cancel();
        try {
            super.dismiss();
        } catch (Exception e) {
            System.out.println("dismiss错误");
        }
    }

}
