package com.cnsunru.order.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.budget.MaterialDetailsActivity;
import com.cnsunru.budget.activity.ProjectListActivity;
import com.cnsunru.common.CommonApp;
import com.cnsunru.common.base.LBaseDialogFragment;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.common.intent.IntentPoxy;
import com.cnsunru.common.intent.StartIntent;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.widget.TagAdapter;
import com.cnsunru.common.widget.TagFlowLayout;
import com.cnsunru.order.GoodsListActivity;
import com.cnsunru.order.adapters.GoodsAttrAdapter;
import com.cnsunru.order.mode.GoodsDetails;
import com.cnsunru.order.mode.GoodsDetails.HouseProjectBean;
import com.cnsunru.order.mode.GoodsDetails.HouseProjectBean.HouseTypeBean;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.DisplayUtil;
import com.sunrun.sunrunframwork.uiutils.LayoutUtil;
import com.sunrun.sunrunframwork.uiutils.TextColorUtils;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.EmptyDeal;
import com.sunrun.sunrunframwork.weight.FlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.cnsunru.budget.MaterialDetailsActivity.MODIFY_PROJECT;
import static com.cnsunru.budget.RoomInfoActivity.EDIT_HOUSE_ID;
import static com.cnsunru.budget.fragment.RoomCustomInfoFragment.EDIT_ROOM_TYPE;
import static com.cnsunru.budget.fragment.RoomCustomInfoFragment.MODIFY_DOOR_WINDOW;
import static com.cnsunru.common.quest.BaseQuestConfig.ADD_DOOR_INFO_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.ADD_PRODUCT_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.ADD_WINDOW_INFO_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.CHANGE_PRODUCT_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_PRODUCT_DETAIL_CODE;
import static com.cnsunru.order.GoodsDetailsActivity.isSelectMaterial;

/**
 * Created by WQ on 2017/9/13.
 *
 * @Describe 添加商品-浮层
 */

public class AddGoodsDialogFragment extends LBaseDialogFragment {


    @BindView(R.id.imgProduct)
    ImageView imgProduct;
    @BindView(R.id.itemTitle)
    TextView itemTitle;
    @BindView(R.id.txtLab1)
    TextView txtLab1;
    @BindView(R.id.txtLab2)
    TextView txtLab2;
    @BindView(R.id.txtLab3)
    TextView txtLab3;
    @BindView(R.id.txtLab4)
    TextView txtLab4;
    @BindView(R.id.txtProductPrice)
    TextView txtProductPrice;
    @BindView(R.id.rightProductLayout)
    LinearLayout rightProductLayout;
    @BindView(R.id.attrRecylv)
    RecyclerView attrRecylv;
    @BindView(R.id.txtCurrentProject)
    TextView txtCurrentProject;
    @BindView(R.id.btnAddProject)
    Button btnAddProject;
    @BindView(R.id.txtCategoryLab)
    TextView txtCategoryLab;
    @BindView(R.id.attrTags)
    TagFlowLayout attrTags;
    @BindView(R.id.addGoods)
    Button addGoods;
    @BindView(R.id.btnCancel)
    Button btnCancel;

    @BindView(R.id.layMyProject)
    View layMyProject;
    @BindView(R.id.layRoomType)
    View layRoomType;
    int height;
    int leftPadding;
    ListPopupWindow listPopupWindow;
    GoodsDetails goodsDetails;
    String id;
    List<HouseProjectBean> peoject_list ;
    int selectProject=0;
    StartIntent startIntent= IntentPoxy.getProxyInstance();
    String houseType,projectId;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getArguments().getString("id");
        bindDimissListener(view);
//
        BaseQuestStart.getProductDetails(this, id);
    }


    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case GET_PRODUCT_DETAIL_CODE:
                if (bean.status == 1) {
                    goodsDetails = bean.Data();
                    setPageData(goodsDetails);
                }
                break;
            case ADD_PRODUCT_CODE:

                if(bean.status==1){
                    UIUtils.shortM("加入成功,请在我的项目中查看!");
                    dismissAllowingStateLoss();
                }else {
                    UIUtils.shortM(bean);
                }
                break;
            case ADD_WINDOW_INFO_CODE:
            case ADD_DOOR_INFO_CODE:
            case CHANGE_PRODUCT_CODE:
                UIUtils.shortM(bean);
                if (bean.status == 1) {
                    //修改门窗,替换材料,关掉列表
                    CommonApp.getInstance().closeActivitys(GoodsListActivity.class);
                    EventBus.getDefault().post(MODIFY_DOOR_WINDOW);
                    EventBus.getDefault().post(MODIFY_PROJECT);
                    finish();
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    private void setPageData(GoodsDetails goodsDetails) {
        GlideMediaLoader.load(this, imgProduct, goodsDetails.image);
        itemTitle.setText(goodsDetails.title);
        txtProductPrice.setText(String.format("¥ %s", goodsDetails.price));
        txtLab1.setText(String.format("品牌: %s", goodsDetails.getBrand_name()));
        txtLab2.setText(String.format("规格: 套", goodsDetails.brand_name));
        txtLab3.setText(String.format("宽度: %sm", goodsDetails.width));
        txtLab4.setText(String.format("高度: %sm", goodsDetails.height));

        attrRecylv.setLayoutManager(new LinearLayoutManager(that, LinearLayoutManager.VERTICAL, false));
        GoodsAttrAdapter goodsAttrAdapter = new GoodsAttrAdapter(goodsDetails.spec);
        attrRecylv.setAdapter(goodsAttrAdapter);
        height = DisplayUtil.dp2px(that, 30);
        leftPadding = DisplayUtil.dp2px(that, 15);
        peoject_list = goodsDetails.peoject_list;
        if(isSelectMaterial){
            int edit_room_type = getSession().getInt(EDIT_ROOM_TYPE);////判断编辑类型 1 添加门 2 添加窗 3 材料替换
            if(edit_room_type==3){
                addGoods.setText("替换材料");
            }
            layRoomType.setVisibility(View.GONE);
            layMyProject.setVisibility(View.GONE);

        }
        setHouseRoomType(selectProject);

    }

    /**
     * 设置房间类型选择
     * @param position
     */
    private void setHouseRoomType(int position) {
        if(!EmptyDeal.isEmpy(peoject_list)){
            HouseProjectBean projectBean = peoject_list.get(position);
            final List<HouseTypeBean> house_type = projectBean.house_type;
            txtCurrentProject.setText(projectBean.title);
            projectId=projectBean.id;
//            List<String> attrStrs = Arrays.asList("主卧", "次卧", "厨房", "卫生间", "阳台");
            final TagAdapter<HouseTypeBean> adapter = new TagAdapter<HouseTypeBean>(house_type) {
                @Override
                public View getView(FlowLayout parent, int position, HouseTypeBean item) {
                    boolean isSelect = getPreCheckedList().contains(position);
                    View inflate = LayoutInflater.from(that).inflate(R.layout.item_simple_text, parent, false);
                    TextView text = (TextView) inflate.findViewById(R.id.text);
                    text.setText(item.house_title);
                    if (isSelect) {
                        text.setTextColor(Color.WHITE);
                        text.setBackgroundResource(R.drawable.shape_red_bg);
                    } else {
                        text.setTextColor(that.getResources().getColor(R.color.text4));
                        text.setBackground(null);
                    }
                    text.setPadding(leftPadding, 0, leftPadding, 0);
                    LayoutUtil.setLayout(text, WRAP_CONTENT, height);
                    return inflate;
                }
            };
            attrTags.setAdapter(adapter);
//            adapter.setSelectedList(0);
            attrTags.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    adapter.getPreCheckedList().clear();
                    adapter.setSelectedList(position);
                    houseType=house_type.get(position).id;
                    return false;
                }
            });
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_dialog_add_goods;
    }

    public static void showFragment(FragmentManager fragmentManager, String id) {
        AddGoodsDialogFragment addGoodsDialogFragment = new AddGoodsDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        addGoodsDialogFragment.setArguments(bundle);
        addGoodsDialogFragment.show(fragmentManager, "AddGoodsDialogFragment");
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        return dialog;
    }



    @OnClick({R.id.txtCurrentProject, R.id.btnAddProject, R.id.addGoods, R.id.btnCancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtCurrentProject:
                if(peoject_list==null){
                    UIUtils.shortM("尚未添加任何项目");
                    return;
                }
                if (listPopupWindow == null || !listPopupWindow.isShowing()) {
                    if (listPopupWindow == null) {
                        listPopupWindow = new ListPopupWindow(that);
                        listPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                //isShow = false;
                                TextColorUtils.setCompoundDrawables(txtCurrentProject, 0, 0, R.drawable.home_icon_droplist_n, 0);
                            }
                        });
                        //设置最大高度为120
                        int itemHeight=DisplayUtil.dp2px(that, 40);
                        int totalHeight=itemHeight*EmptyDeal.size(peoject_list);
                        int adviceHeight=Math.min(totalHeight,DisplayUtil.dp2px(that, 120));
                        listPopupWindow.setHeight(adviceHeight);
                        if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.KITKAT) {
                            listPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                        }
//                        List<String> attrStrs = Arrays.asList("方案1", "方案2", "方案3", "方案4", "方案5", "方案1", "方案2", "方案3", "方案4", "方案5");
                        listPopupWindow.setAdapter(new ViewHolderAdapter<HouseProjectBean>(that, peoject_list, R.layout.item_simple_list_text) {
                            @Override
                            public void fillView(ViewHodler viewHodler, HouseProjectBean item, int i) {
                                viewHodler.setText(R.id.text, item.title);
                            }
                        });
                        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                selectProject = position;
                                setHouseRoomType(selectProject);
                                listPopupWindow.dismiss();
                            }
                        });
                    }
                    listPopupWindow.setSelection(selectProject);
                    listPopupWindow.setAnimationStyle(R.style.PopupAnimation);
                    listPopupWindow.setAnchorView(view);
                    listPopupWindow.setModal(true);
                    listPopupWindow.setDropDownGravity(Gravity.BOTTOM);
                    listPopupWindow.show();
                    ListView listView = listPopupWindow.getListView();
                    listView.setDivider(null);
                    listView.setVerticalScrollBarEnabled(false);
                    listView.setHorizontalScrollBarEnabled(false);
                    TextColorUtils.setCompoundDrawables(txtCurrentProject, 0, 0, R.drawable.home_icon_droplist_up, 0);
                } else {
                    TextColorUtils.setCompoundDrawables(txtCurrentProject, 0, 0, R.drawable.home_icon_droplist_n, 0);
                    listPopupWindow.dismiss();
                }

                break;
            case R.id.btnAddProject:
                startIntent.startMyProjectAddActivity(that);
                dismissAllowingStateLoss();
                break;
            case R.id.addGoods:
                if (!isSelectMaterial) {//直接从商城过来
                    addGoods2Project();
                } else {//从预算页面过来替换或者选取材料
                    UIUtils.showLoadDialog(that, "操作中..");
                    int edit_room_type = getSession().getInt(EDIT_ROOM_TYPE);//判断编辑类型 1 添加门 2 添加窗 3 材料替换
                    if (edit_room_type == 1) {
                        BaseQuestStart.add_door_info(this, getSession().getString(EDIT_HOUSE_ID), id);
                    } else if(edit_room_type==2){
                        BaseQuestStart.add_window_info(this, getSession().getString(EDIT_HOUSE_ID), id);
                    }else if(edit_room_type==3){
                        BaseQuestStart.change_product(this,getSession().getString(MaterialDetailsActivity.PROJECT_LIST_ID),id);
                    }
                }

                break;
            case R.id.btnCancel:
                dismissAllowingStateLoss();
                break;
        }
    }

    private void addGoods2Project() {
        UIUtils.showLoadDialog(that,"操作中..");
        BaseQuestStart.add_product(this,houseType,id,projectId,goodsDetails.price,"1");
    }
}
