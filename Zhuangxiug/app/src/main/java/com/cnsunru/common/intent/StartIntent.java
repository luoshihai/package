package com.cnsunru.common.intent;

import android.app.Activity;
import android.content.Context;

import com.cnsunru.common.WebActivity;
import com.sunrun.sunrunframwork.uibase.BaseActivity;

/**
 * 所有的Activity启动定义
 * Created by WQ on 2017/8/24.
 */

public interface StartIntent {
    void startNavigatorActivity(BaseActivity that);//导航

    void startLoginActivity(BaseActivity that);//登录
    @ResultCode(100)
    void startRegisterActivity(BaseActivity that);//注册
    @ResultCode(101)
    void startForgetPasswordActivity(BaseActivity that);//忘记密码
    void startSettingActivity(Activity that);//设置
    void startEditUserInfoActivity(Activity that, @Key("index") int index);//用户信息编辑
    void startConsumptionRecordActivity(Activity that);//资金记录
    void startMyCollectActivity(Activity that);//我的收藏
    void startMyProjectActivity(Activity that);//我的项目
    void startCitySelectActivity(Activity that);//选择城市

    void startMyActivityActivity(Activity that);//参加的活动
    void startFeedbackActivity(Activity that);//意见反馈
    void startAboutActivity(Activity that);//关于
    void startMyCouponActivity(Activity that,@Key("type")int type);//优惠劵列表
    void startSearchGoodsActivity(Activity that,@Key("searchType")String type);//商品搜索
    //商品列表, type 0 默认分类页进入 1,限时, 2 爆款 ,3 从预算过来 , 4 搜索 , 5 产品选择,cid分类id
    void startGoodsListActivity(Activity that,@Key("type")int type,@Key("cid") String cid);
    void startGoodsDetailsActivity(Activity that,@Key("id")String id);//商品详情
    void startMallPurchaseDetailsActivity(Activity that,@Key("id")String id);//
    int SCAN_QR_REQUEST = 0x0010;
    @ResultCode(SCAN_QR_REQUEST)
    void startScanQRActivity(Activity that);
    void startCompanyDetailsActivity(Activity that,@Key("id")String id); //公司详情
    void startCompanyListActivity(Activity that); //公司列表
    void startCompanyPlanListActivity(Activity that); //公司方案列表
    void startCompanyPlanDetailsActivity(Activity that,@Key("id")String id,@Key("name")String name); //公司方案详情
    void startWorkSiteListActivity(Activity that); //在建工地列表
    void startWorkSiteDetailsActivity(Activity that,@Key("id")String id); //在建工地详情
    void startSelectionRaiderListActivity(Activity that); //选材攻略列表
    void startSelectionRaiderDetailsActivity(Activity that,@Key("id")String id); //选材攻略详情
    void startActivityDetailsActivity(Activity that,@Key("id")String id); //活动详情
    void startMyActivityActivity(Activity that,@Key("type")int type); //活动列表 type 0参加的活动 type 1,所有活动
    //预算模块
    void startProjectListActivity(Activity that);//工程清单
//    void startDetailedEngineeringList(Activity that);//详细工程清单
    void startProjectDetailsActivity(Activity that,@Key("id") String id); //详细工程清单
    void startProjectRoomDetailsActivity(Activity that,@Key("id") String id); //房间详细工程清单

    void startRoomInfoActivity(Activity that,@Key("id")String id,@Key("name")String name); //房间信息


    void startMaterialListInfoActivity(Activity that);//材料清单
    @ResultCode(101)
    void startHouseTypeSelectActivity(Activity that);//房屋类型
    @ResultCode(101)
    void startRoomTypeSelectActivity(Activity that,@Key("projectId")String projectId);//房间类型

    void startMaterialDetailsActivity(Context mContext,@Key("id")String project_id,@Key("name")String name);//材料详情

    void startShopFilterActivity(BaseActivity that);//商品分类
    @TargetActivity(WebActivity.class)
    void startWebActivity(Activity that, @Key("web_url")String pid, @Key("title")String title);

    void startMyActivityWebActivity(Activity that, @Key("web_url")String pid, @Key("title")String title,@Key("id")String id);//活动详情

    void startWorkSiteListWebActivity(Activity that,  @Key("web_url")String pid, @Key("title")String title,@Key("id")String id);//工地详情

    void startCompanyDetailsWebActivity(Activity that,@Key("web_url")String pid, @Key("title")String title,@Key("id")String id); //公司详情

    void startProjectRoomAddProjectItemActivity(Activity that); //添加自定义项目
    void startModifyPasswordActivity(Activity that); //密码修改
    void startProductCategorySelectActivity(Activity that); //产品分类选择
    void startMyProjectAddActivity(Activity that); //我的项目添加
    void startSelectWorkTypeActivity(Activity that,@Key("ids")String other_calculate_ids); //施工项目选择
}
