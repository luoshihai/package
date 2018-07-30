package com.cnsunru.budget;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.budget.adapters.BaseTableAdapter;
import com.cnsunru.budget.dialogs.DeleteConfimDialogFragment;
import com.cnsunru.budget.mode.ProjectItemDetails;
import com.cnsunru.budget.mode.ProjectItemDetails.ChooseInfoBean.ChooseListBean;
import com.cnsunru.budget.mode.ProjectItemDetails.ProductListBean;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.widget.BoxLayout;
import com.cnsunru.common.widget.TagAdapter;
import com.cnsunru.common.widget.TagFlowLayout;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.order.fragment.AddMaterialDialogFragment;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.DisplayUtil;
import com.sunrun.sunrunframwork.uiutils.LayoutUtil;
import com.sunrun.sunrunframwork.uiutils.TextColorUtils;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.weight.FlowLayout;
import com.sunrun.sunrunframwork.weight.SpanTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import win.smartown.android.library.tableLayout.TableLayout;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.cnsunru.budget.fragment.RoomCustomInfoFragment.EDIT_ROOM_TYPE;
import static com.cnsunru.common.quest.BaseQuestConfig.CHANGE_CALCULATE_OTHER_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.DEL_ONE_PROJECT_DETAIL_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_PROJECT_DETAIL_CODE;

/**
 * 材料详情
 * Created by WQ on 2017/9/15.
 */

public class MaterialDetailsActivity extends LBaseActivity {
    public static final String PROJECT_LIST_ID = "change_product_project_list_id";
//    public static final String PROJECT_LIST_ID = "change_product_project_list_id";
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.labTxt)
    TextView labTxt;
    @BindView(R.id.categoryTags)
    TagFlowLayout categoryTags;
    @BindView(R.id.txtTip)
    TextView txtTip;
    @BindView(R.id.txtLab)
    TextView txtLab;
    @BindView(R.id.layBigTitle)
    BoxLayout layBigTitle;
    @BindView(R.id.materialsTable)
    TableLayout materialsTable;
    final String[] fields = {"材料名称", "规格", "品牌", "单价"};
    Drawable drawable;

    ProjectItemDetails projectItemDetails;
    String project_list_id;
    public static final String DELETE_PROJECT="DELETE_PROJECT";
    public static final String MODIFY_PROJECT="MATERIAL_MODIFY_PROJECT";
    public static final String MODIFY_TOP="MATERIAL_MODIFY_TOP_TYPE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEventBus();
        setContentView(R.layout.activity_material_details);
        titleBar.setTitle(getIntent().getStringExtra("name"));
        drawable = getResources().getDrawable(R.drawable.project_icon_self_select);
        drawable= TextColorUtils.getVaildDrawable(drawable);

        loadPageData();

    }

    private void loadPageData() {
        UIUtils.showLoadDialog(this);
        project_list_id = getIntent().getStringExtra("id");
        BaseQuestStart.get_project_detail(this, project_list_id);
    }

    @OnClick(R.id.btnDelete)
    public void btnDelete(View view ){
        DeleteConfimDialogFragment.showFragment(getSupportFragmentManager(), new DeleteConfimDialogFragment.ConfirmActionListener() {
            @Override
            public void onConfirm(View view) {
                UIUtils.showLoadDialog(that,"删除中...");
                BaseQuestStart.del_one_project_detail(that,getIntent().getStringExtra("id"));
            }
        });

    }

    public void nofityUpdate(int requestCode,BaseBean bean){
        switch(requestCode){
            case GET_PROJECT_DETAIL_CODE:
                if(bean.status==1){
                    projectItemDetails=bean.Data();
                    setTopType(projectItemDetails.choose_info);
                    setTableInfo(projectItemDetails.product_list);
                }
                break;
            case DEL_ONE_PROJECT_DETAIL_CODE:
                UIUtils.shortM(bean);
                if(bean.status==1){
                    EventBus.getDefault().post(DELETE_PROJECT);
                    finish();
                }
                break;
            case CHANGE_CALCULATE_OTHER_CODE:
                UIUtils.shortM(bean);
                if(bean.status==1){
                    EventBus.getDefault().post(MODIFY_PROJECT);
                    loadPageData();
                }
                break;
        }
        super.nofityUpdate(requestCode,bean);
    }

    private void setTableInfo(final List<ProductListBean> product_list) {
        materialsTable.setAdapter(new BaseTableAdapter() {
            @Override
            public void onInterceptItemView(TextView itemView,final int row, int col) {
                itemView.setPadding(DisplayUtil.dp2px(that, 5), 0, DisplayUtil.dp2px(that, 5), 0);

                itemView.setCompoundDrawablePadding(DisplayUtil.dp2px(that, 5));
                LayoutUtil.setLayout(itemView, DisplayUtil.dp2px(that, 100), DisplayUtil.dp2px(that, 30));
                if (row != 0 && col == 0) {
                    SpanTextView.SpanEditable spanEditable=new SpanTextView.SpanEditable(itemView.getText().toString());
                    spanEditable.setSpan("#",new ImageSpan(drawable));
                    itemView.setText(spanEditable.commit());
//                    TextColorUtils.setCompoundDrawables(itemView,0,0,R.drawable.project_icon_self_select,0);
                    itemView.setTextColor(that.getResources().getColor(R.color.main_color_red));
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ProductListBean productListBean = product_list.get(row-1);//获取每行的数据
                            getSession().put(EDIT_ROOM_TYPE,3);//设置编辑模式
                            getSession().put(PROJECT_LIST_ID,project_list_id);
                            AddMaterialDialogFragment.showFragment(getSupportFragmentManager(),productListBean.id);
                        }
                    });
                }else {
                    itemView.setOnClickListener(null);
                }
                itemView.setSingleLine(true);
                itemView.setEllipsize(TextUtils.TruncateAt.END);
            }

            @Override
            public int getRowCount() {
                return product_list.size();
            }

            @Override
            public String getItemVal(int row, int col) {
                ProductListBean productListBean = product_list.get(row);//获取每行的数据
//                productListBean.
                switch (col){
                    case 0:return "# "+productListBean.title;
                    case 1:return productListBean.standard;
                    case 2:return productListBean.brand;
                    case 3:return productListBean.price;

                }
                return "";
            }

            @Override
            public String[] getHeadFields() {
                return fields;
            }
        });
    }
    @Subscribe
    public void eventAction(String action) {
        switch (action) {
            case MODIFY_PROJECT://重新获取页面数据
                loadPageData();
                break;
        }
    }

    private void setTopType(ProjectItemDetails.ChooseInfoBean choose_info) {
        final int   height = DisplayUtil.dp2px(that, 30);
        final int leftPadding = DisplayUtil.dp2px(that, 15);
        labTxt.setText(choose_info.title);
      final   List<ChooseListBean> choose_list = choose_info.choose_list;
        final TagAdapter<ChooseListBean> adapter = new TagAdapter<ChooseListBean>(choose_list) {
            @Override
            public View getView(FlowLayout parent, int position, ChooseListBean item) {
//                boolean isSelect =getPreCheckedList().contains(position);
                boolean isSelect ="1".equals(item.status);
                View inflate = LayoutInflater.from(that).inflate(R.layout.item_simple_text, parent, false);
                TextView text = (TextView) inflate.findViewById(R.id.text);
                text.setText(item.title);
                if (isSelect) {
                    text.setTextColor(Color.WHITE);
                    text.setBackgroundResource(R.drawable.shape_red_bg);
                } else {
                    text.setTextColor(that.getResources().getColor(R.color.main_text_color));
                    text.setBackground(null);
                }
                text.setPadding(leftPadding, 0, leftPadding, 0);
                LayoutUtil.setLayout(text, WRAP_CONTENT, height);
                return inflate;
            }
        };
        categoryTags.setAdapter(adapter);
//        adapter.setSelectedList(0);
        categoryTags.setOnTagClickListener(new TagFlowLayout.OnTagClickListener(){

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                adapter.getPreCheckedList().clear();
                for (int i = 0; i < choose_list.size(); i++) {
                    ChooseListBean chooseListBean = choose_list.get(i);
                    chooseListBean.status=position==i?"1":"0";
                }
                adapter.setSelectedList(position);
                UIUtils.showLoadDialog(that);
                BaseQuestStart.change_calculate_other(that,adapter.getItem(position).id);
                return false;
            }
        });
    }
}
