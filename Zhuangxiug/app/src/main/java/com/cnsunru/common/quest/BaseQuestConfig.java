package com.cnsunru.common.quest;


/**
 * 接口参数帮助类
 */
public class BaseQuestConfig implements NetQuestConfig {


    public static String TEST_IMAGE = "http://s9.knowsky.com/bizhi/l/35001-45000/200952904241438473283.jpg";

    /**
     * 商品分类
     */
    public static final String GET_CATEGORY = HTTP_API + "App/Product/Product/get_category";
    public static final int GET_CATEGORY_CODE = 0x11;

    /**
     * 首页轮播图
     */
    public static final String BANNER = HTTP_API + "App/Common/Common/banner";
    public static final int BANNER_CODE = 0x07;
    /**
     * 热搜
     */
    public static final String SEARCH_HOT = HTTP_API + "App/Common/Search/search_hot";
    public static final int SEARCH_HOT_CODE = 0x06;

    /**
     * 爆款
     */
    public static final String GET_EXPLOSION = HTTP_API + "App/Product/Product/get_explosion";
    public static final int GET_EXPLOSION_CODE = 0x10;
    /**
     * 限时抢购
     */
    public static final String GET_PRODUCT_LIMITED = HTTP_API + "App/Product/Product/get_product_limited";
    public static final int GET_PRODUCT_LIMITED_CODE = 0x14;

    /**
     * 获取优惠券
     */
    public static final String DISCOUNT_LIST = HTTP_API + "App/Product/Discount/get_list";
    public static final int DISCOUNT_LIST_CODE = 0x21;


    /**
     * 注册发送短信验证码
     */
    public static String GET_VERIFICATION_CODE_URL = HTTP_API + "App/User/Register/get_code";
    public static final int QUEST_VERIFICATION_CODE = 0X1;

    /**
     * 注册
     */
    public static String USER_REGIST = HTTP_API + "App/User/Register/register";
    public static final int QUEST_USER_REGIST_CODE = 0X3;

    /**
     * 登录
     */
    public static String USER_LOGIN = HTTP_API + "App/User/Register/login";
    public static final int QUEST_USER_LOGIN_CODE = 0X4;

    /**
     * 最新活动
     */
    public static String GET_ACTIVITY_LIST = HTTP_API + "App/Home/Activity/get_list";
    public static final int QUEST_GET_ACTIVITY_LIST_CODE = 0X15;

    /**
     * 活动详情
     */
    public static String ACTIVITY_DETAIL_INFO = HTTP_API + "App/Home/Activity/info";
    /**
     * 工地详情
     */
    public static String BUIDINGSITE_DETAIL_INFO = HTTP_API + "App/Home/BuildingSite/info";


    /**
     * 装修攻略详情
     */
    public static String TRAVELGUIDE_DETAIL = HTTP_API + "App/Home/TravelGuide/info";

    /**
     * 公司详情
     */
    public static String COMPANY_DETAIL_INFO = HTTP_API + "App/Home/Company/info";

    /**
     * 获取城市列表
     */
    public static String GET_AREA = HTTP_API + "App/Common/Area/get_area";
    public static final int GET_AREA_CODE = 0X22;

    /**
     * 获取在建工地列表
     */
    public static String BUILDINGSITE_GET_LIST = HTTP_API + "App/Home/BuildingSite/get_list";
    public static final int GET_BUILDINGSITE_GET_LIST_CODE = 0X25;

    /**
     * 修改资料
     */
    public static String EDIT_DATA = HTTP_API + "App/User/Forget/edit_data";
    public static final int EDIT_DATA_CODE = 0X33;

    /**
     * 我的优惠劵
     */
    public static String MY_DISCOUNT = HTTP_API + "App/Product/Discount/my_discount";
    public static final int MY_DISCOUNT_CODE = 0X27;

    /**
     * 设置默认城市
     */
    public static String PUT_AREA = HTTP_API + "App/Common/Area/put_area";
    public static final int PUT_AREA_CODE = 0X43;

    /**
     * 分类
     */
    public static String TRAVELGUIDE_GET_CATEGORY = HTTP_API + "App/Home/TravelGuide/get_category";
    public static final int PUT_TRAVELGUIDE_GET_CATEGORY_CODE = 0X19;

    /**
     * 获取攻略列表
     */
    public static String TRAVELGUIDE_GET_LIST = HTTP_API + "App/Home/TravelGuide/get_list";
    public static final int PUT_TRAVELGUIDE_GET_LIST_CODE = 0X20;

    /**
     * 领取优惠券
     */
    public static String GET_DISCOUNT = HTTP_API + "App/Product/Discount/get_discount";
    public static final int GET_DISCOUNT_CODE = 0X24;

    /**
     * 获取装修公司列表
     */
    public static String GET_CAMPANY_LIST = HTTP_API + "App/Home/Company/get_list";
    public static final int GET_GET_CAMPANY_LIST_CODE = 0X26;
    /**
     * 活动是否收藏、报名
     */
    public static String GET_IS_COLLECT_SIGN = HTTP_API + "App/Home/Activity/is_collect_sign";
    public static final int GET_IS_COLLECT_SIGN_CODE = 0X40;
    /**
     * 活动报名
     */
    public static String SIGN_ACTIVITY = HTTP_API + "App/Home/Activity/sign_activity";
    public static final int GET_SIGN_ACTIVITY_CODE = 0X17;
    /**
     * 添加收藏
     */
    public static String ADD_COLLECT = HTTP_API + "App/Common/Collect/add_collect";
    public static final int GET_ADD_COLLECT_CODE = 0X8;
    /**
     * 取消收藏
     */
    public static String DEL_COLLECT = HTTP_API + "App/Common/Collect/del_collect";
    public static final int GET_DEL_COLLECT_CODE = 0X9;


    /**
     * 我的活动
     */
    public static String MY_ACTIVITY = HTTP_API + "App/Home/Activity/my_activity";
    public static final int MY_ACTIVITY_CODE = 0X14;
    /**
     * 工地是否收藏 和报名
     */
    public static String BUILDINGSITE_COLLECT_SUBCREIBE = HTTP_API + "App/Home/BuildingSite/is_collect_subscribe";
    public static final int MY_BUILDINGSITE_COLLECT_SUBCREIBE_CODE = 0X34;
    /**
     * 工地预约
     */
    public static String BUILDINGSITE_SUBSCREIBE = HTTP_API + "App/Home/BuildingSite/build_subscribe";
    public static final int MY_BUILDINGSITE_SUBSCREIBE_CODE = 0X31;
    /**
     * 房屋类型数据获取
     */
    public static String CALCULATE_GETAPARTMENT = HTTP_API + "App/Calculate/Calculate/get_apartment_layout";
    public static final int GET_CALCULATE_GETAPARTMENT_CODE = 0X0001;
    /**
     * 获取默认预算计算界面展示数据
     */
    public static String CALCULATE_GET_DEFAULT_DATA = HTTP_API + "App/Calculate/Calculate/get_default_data";
    public static final int GET_CALCULATE_GET_DEFAULT_DATA_CODE = 0X0003;

    /**
     * 我的收藏
     */
    public static String GET_COLLEC_LIST = HTTP_API + "App/Common/Collect/get_collec_list";
    public static final int GET_COLLEC_LIST_CODE = 0X15;
    /**
     * 获取分类商品的品牌和属性筛选条件
     */
    public static String GET_CATEGORY_ATTR = HTTP_API + "App/Product/Product/get_category_attr";
    public static final int GET_CATEGORY_ATTR_CODE = 0X29;

    /**
     * 获取爆款限时购的品牌和属性筛选条件
     */
    public static String GET_SPEC_ATTR = HTTP_API + "App/Product/Product/get_spec_attr";
    public static final int GET_SPEC_ATTR_CODE = 0X29;

    /**
     * 获取分类商品列表
     */

    public static String GET_CATEGORY_PRODUCT = HTTP_API + "App/Product/Product/get_category_product";
    public static final int GET_CATEGORY_PRODUCT_CODE = 0X13;

    /**
     * 搜索
     */
    public static String SEARCH = HTTP_API + "App/Common/Search/search";
    public static final int SEARCH_CODE = 0X05;
    /**
     * 商品详情-html
     */
    public static String PRODUCT_INFO = HTTP_API + "App/Product/Product/info?id=%s";

    /**
     * 获取商品详情
     */
    public static String GET_PRODUCT_DETAIL = HTTP_API + "App/Product/Product/get_product_detail";
    public static final int GET_PRODUCT_DETAIL_CODE = 0X30;


    /**
     * 默认预算计算
     */
    public static String COMMINT_DETAULT_CALCULATE_DETAIL = HTTP_API + "App/Calculate/Calculate/default_calculate";
    public static final int GET_COMMINT_DETAULT_CALCULATE_DETAIL_CODE = 0X00002;

    /**
     * 【预算】获取房间信息
     */
    public static String EDIT_HOUSE_INFO = HTTP_API + "App/Calculate/Calculate/edit_house_info";
    public static final int EDIT_HOUSE_INFO_CODE = 0X10006;

    /**
     * 【预算】工程详情
     */
    public static String GET_PROJECT_DETAIL = HTTP_API + "App/Calculate/Calculate/get_project_detail";
    public static final int GET_PROJECT_DETAIL_CODE = 0X10009;

    /**
     * 【预算】查看全部清单
     */
    public static String GET_ALL_LIST = HTTP_API + "App/Calculate/Calculate/get_all_list";
    public static final int GET_ALL_LIST_CODE = 0X10005;

    /**
     * 【预算】保存项目名称
     */
    public static String SAVE_PROJECT_NAME = HTTP_API + "App/Calculate/Calculate/save_project_name";
    public static final int SAVE_PROJECT_NAME_CODE = 0X10015;

    /**
     * 【预算】获取每个房间预算结果
     */
    public static String GET_HOUSE_LIST = HTTP_API + "App/Calculate/Calculate/get_house_list";
    public static final int GET_HOUSE_LIST_CODE = 0X10004;

    /**
     * 【预算】房间信息-添加房间
     */
    public static String ADD_HOUSE = HTTP_API + "App/Calculate/Calculate/add_house";
    public static final int ADD_HOUSE_CODE = 0X10016;

    /**
     * 【预算】房间信息-删除窗
     */
    public static String DEL_WINDOWS = HTTP_API + "App/Calculate/Calculate/del_windows";
    public static final int DEL_WINDOWS_CODE = 0X10010;
    /**
     * 【预算】房间信息-删除门
     */
    public static String DEL_DOOR = HTTP_API + "App/Calculate/Calculate/del_door";
    public static final int DEL_DOOR_CODE = 0X10011;
    /**
     * 【预算】房间信息-删除房间
     */
    public static String DEL_HOUSE = HTTP_API + "App/Calculate/Calculate/del_house";
    public static final int DEL_HOUSE_CODE = 0X10013;

    /**
     * 【预算】获取预算结果
     */
    public static String GET_CALCULATE_RESULT = HTTP_API + "App/Calculate/Calculate/get_calculate_result";
    public static final int GET_CALCULATE_RESULT_CODE = 0X10021;

    /**
     * 【预算】房间信息-修改房间信息
     */
    public static String EDIT_HOUSE = HTTP_API + "App/Calculate/Calculate/edit_house";
    public static final int EDIT_HOUSE_CODE = 0X10018;


    /**
     * 忘记密码
     */
    public static String GET_PASSWORD_BACK = HTTP_API + "App/User/Register/get_password_back";
    public static final int GET_PASSWORD_BACK_CODE = 0X10041;

    /**
     * 忘记密码-获取验证码
     */
    public static String GET_FORGET_CODE = HTTP_API + "App/User/Register/get_forget_code";
    public static final int GET_FORGET_CODE_CODE = 0X10042;

    /**
     * 修改密码
     */
    public static String EDIT_PWD = HTTP_API + "App/User/Forget/edit_pwd";
    public static final int EDIT_PWD_CODE = 0X10032;


    /**
     * 【预算】添加门的信息（还有一个修改门信息的接口，修改是为了修改商品信息）
     */
    public static String ADD_DOOR_INFO = HTTP_API + "App/Calculate/Calculate/add_door_info";
    public static final int ADD_DOOR_INFO_CODE = 0X10008;


    /**
     * 【预算】工程详情-删除项目
     */
    public static String DEL_ONE_PROJECT_DETAIL = HTTP_API + "App/Calculate/Calculate/del_one_project_detail";
    public static final int DEL_ONE_PROJECT_DETAIL_CODE = 0X20009;


    /**
     * 【预算】新增房间获取房间类型
     */
    public static String ADD_HOUSE_TYPE = HTTP_API + "App/Calculate/Calculate/add_house_type";
    public static final int ADD_HOUSE_TYPE_CODE = 0X10025;

    /**
     * 【预算】自定义项目添加
     */
    public static String ADD_PROJECT_CUSTOMIZE = HTTP_API + "App/Calculate/Calculate/add_project_customize";
    public static final int ADD_PROJECT_CUSTOMIZE_CODE = 0X10021;

    /**
     * 【预算】添加门到商城的接口
     */
    public static String ADD_DOOR_CATEGORY = HTTP_API + "App/Calculate/Calculate/add_door_category";
    public static final int ADD_DOOR_CATEGORY_CODE = 0X10030;

    /**
     * 【预算】添加窗到商城的接口
     */
    public static String ADD_WINDOW_CATEGORY = HTTP_API + "App/Calculate/Calculate/add_window_category";
    public static final int ADD_WINDOW_CATEGORY_CODE = 0X10031;

    /**
     * 【预算】添加窗的信息
     */
    public static String ADD_WINDOW_INFO = HTTP_API + "App/Calculate/Calculate/add_window_info";
    public static final int ADD_WINDOW_INFO_CODE = 0X10032;

    /**
     * 【预算】材料替换
     */
    public static String CHANGE_PRODUCT = HTTP_API + "App/Calculate/Calculate/change_product";
    public static final int CHANGE_PRODUCT_CODE = 0X10033;





    /**
     * 【预算】从商城添加商品到预算
     */
    public static String ADD_PRODUCT = HTTP_API + "App/Calculate/Calculate/add_product";
    public static final int ADD_PRODUCT_CODE = 0X10034;


    /**
     * 个人中心-我的项目
     */
    public static String MY_PROJECT = HTTP_API + "App/Calculate/Calculate/my_project";
    public static final int MY_PROJECT_CODE = 0X10035;

    /**
     * 【预算】重新计算预算
     */
    public static String REVIEW_CALCULATE = HTTP_API + "App/Calculate/Calculate/review_calculate";
    public static final int REVIEW_CALCULATE_CODE = 0X10036;


    /**
     * 【预算】获取勾选施工清单
     */
    public static String GET_LIST_PROJECT_OTHER = HTTP_API + "App/Calculate/Calculate/get_list_project_other";
    public static final int GET_LIST_PROJECT_OTHER_CODE = 0X10037;

    /**
     * 【预算】修改预算勾选项接口
     */
    public static String CHANGE_CALCULATE_OTHER = HTTP_API + "App/Calculate/Calculate/change_calculate_other";
    public static final int CHANGE_CALCULATE_OTHER_CODE = 0X10038;

    /**
     * 【预算】修改干湿分区接口
     */
    public static String CHANGE_DRY_WET = HTTP_API + "App/Calculate/Calculate/change_ganshi";
    public static final int CHANGE_DRY_WET_CODE = 0X10040;

}



