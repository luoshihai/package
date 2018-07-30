package com.cnsunru.common.quest;

import com.cnsunru.budget.mode.AllListBean;
import com.cnsunru.budget.mode.CalculateDefaultData;
import com.cnsunru.budget.mode.CalculateTypeInfo;
import com.cnsunru.budget.mode.DefaltCalculateInfo;
import com.cnsunru.budget.mode.FilterAttrBean;
import com.cnsunru.budget.mode.ProjectItemDetails;
import com.cnsunru.budget.mode.RoomInfoBean;
import com.cnsunru.budget.mode.WorkTypeBean;
import com.cnsunru.common.event.LocationBean;
import com.cnsunru.common.model.LoginInfo;
import com.cnsunru.common.model.MyActivityInfo;
import com.cnsunru.common.model.PageBean;
import com.cnsunru.home.mode.BannerBean;
import com.cnsunru.home.mode.BuidingListInfo;
import com.cnsunru.home.mode.BuildingCollectInfo;
import com.cnsunru.home.mode.CampanyListInfo;
import com.cnsunru.home.mode.CategoryListInfo;
import com.cnsunru.home.mode.CityMode;
import com.cnsunru.home.mode.CouponBean;
import com.cnsunru.home.mode.HomeHotGoods;
import com.cnsunru.home.mode.SearchHotBean;
import com.cnsunru.home.mode.SearchResultBean;
import com.cnsunru.home.mode.TraverGuideListInfo;
import com.cnsunru.login.mode.RegistInfo;
import com.cnsunru.order.mode.GoodsCategory;
import com.cnsunru.order.mode.GoodsDetails;
import com.cnsunru.user.mode.CollectSignInfo;
import com.cnsunru.user.mode.MyProjectBean;
import com.google.gson.reflect.TypeToken;
import com.sunrun.sunrunframwork.http.NAction;
import com.sunrun.sunrunframwork.http.NetRequestHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 接口请求调用帮助类
 */
public class BaseQuestStart extends BaseQuestConfig {


    /**
     * 注册发送验证码
     *
     * @param netRequestHandler
     * @param mobile
     */
    public static void getVerificationCodeUrl(NetRequestHandler netRequestHandler, String mobile) {
        netRequestHandler.requestAsynPost(new NAction()
                .setUrl(BaseQuestConfig.GET_VERIFICATION_CODE_URL)
                .put("mobile", mobile)
                .setRequestCode(BaseQuestConfig.QUEST_VERIFICATION_CODE));
    }

    /**
     * 注册
     *
     * @param netRequestHandler
     * @param mobile
     */
    public static void register(NetRequestHandler netRequestHandler, String mobile, String code, String password) {
        netRequestHandler.requestAsynPost(new NAction()
                .setUrl(BaseQuestConfig.USER_REGIST)
                .put("mobile", mobile)
                .put("code", code)
                .put("password", password)
                .setRequestCode(BaseQuestConfig.QUEST_USER_REGIST_CODE)
                .setTypeToken(RegistInfo.class));
    }

    /**
     * 获取商品分类
     *
     * @param requestHandler
     */
    public static void get_category(NetRequestHandler requestHandler) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(GET_CATEGORY_CODE).
                setUrl(GET_CATEGORY).cachePriority(true).
                setTypeToken(new TypeToken<List<GoodsCategory>>() {
                })
        );
    }

    /**
     * 登录
     *
     * @param netRequestHandler
     * @param mobile
     */
    public static void login(NetRequestHandler netRequestHandler, String mobile, String password) {
        netRequestHandler.requestAsynPost(new NAction()
                .setUrl(BaseQuestConfig.USER_LOGIN)
                .put("mobile", mobile)
                .put("password", password)
                .setRequestCode(BaseQuestConfig.QUEST_USER_LOGIN_CODE)
                .setTypeToken(RegistInfo.class)
                .setTypeToken(LoginInfo.class));
    }


    /**
     * 获取Banner
     *
     * @param requestHandler
     */
    public static void getBanner(NetRequestHandler requestHandler) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(BANNER_CODE).
                setUrl(BANNER).cachePriority(true).
                setTypeToken(new TypeToken<List<BannerBean>>() {
                })

        );
    }

    /**
     * 热门搜索关键词
     *
     * @param requestHandler
     */
    public static void getSearchHot(NetRequestHandler requestHandler,String searchType) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(SEARCH_HOT_CODE).
                setUrl(SEARCH_HOT).
                put("type", searchType).
                setTypeToken(new TypeToken<List<SearchHotBean>>() {
                })

        );
    }

    /**
     * 获取爆款商品/列表
     *
     * @param requestHandler
     */
    public static void getExplosion(NetRequestHandler requestHandler, String brand_id, String price_min, String price_max, String attr_value_id, String keywords, String sort, int p) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(GET_EXPLOSION_CODE).
                setUrl(GET_EXPLOSION).
                setTypeToken(new TypeToken<PageBean<HomeHotGoods>>() {
                }).
                put("type", "2").
                put("brand_id", brand_id).
                put("price_min", price_min).
                put("price_max", price_max).
                put("attr_value_id", attr_value_id).put("keywords", keywords).
                put("sort", sort).
                put("p", p)

        );
    }

    /**
     * 获取爆款商品/首页
     *
     * @param requestHandler
     */
    public static void getExplosion(NetRequestHandler requestHandler) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(GET_EXPLOSION_CODE).
                setUrl(GET_EXPLOSION).cachePriority(true).
                setTypeToken(new TypeToken<List<HomeHotGoods>>() {
                }).
                put("type", "1")

        );
    }

    /**
     * 获取限时抢购商品/列表
     *
     * @param requestHandler
     */
    public static void getProductLimited(NetRequestHandler requestHandler, String brand_id, String price_min, String price_max, String attr_value_id, String keywords, String sort, int p) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(GET_PRODUCT_LIMITED_CODE).
                setUrl(GET_PRODUCT_LIMITED).
                setTypeToken(new TypeToken<PageBean<HomeHotGoods>>() {
                }).
                put("type", "2").
                put("brand_id", brand_id).
                put("price_min", price_min).
                put("price_max", price_max).
                put("attr_value_id", attr_value_id).put("keywords", keywords).
                put("sort", sort).
                put("p", p)

        );
    }

    /**
     * 获取限时抢购商品/首页
     *
     * @param requestHandler
     */
    public static void getProductLimited(NetRequestHandler requestHandler) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(GET_PRODUCT_LIMITED_CODE).
                setUrl(GET_PRODUCT_LIMITED).cachePriority(true).
                setTypeToken(new TypeToken<PageBean<HomeHotGoods>>() {
                }).
                put("type", "1")

        );
    }

    /**
     * 获取获取优惠券/首页
     *
     * @param requestHandler
     */
    public static void getDiscountList(NetRequestHandler requestHandler) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(DISCOUNT_LIST_CODE).
                setUrl(DISCOUNT_LIST).cachePriority(true).
                setTypeToken(new TypeToken<List<CouponBean>>() {
                }).
                put("type", "1")

        );
    }

    /**
     * 获取获取优惠券/列表
     *
     * @param requestHandler
     */
    public static void getDiscountList(NetRequestHandler requestHandler, int p) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(DISCOUNT_LIST_CODE).
                setUrl(DISCOUNT_LIST).
                setTypeToken(new TypeToken<PageBean<CouponBean>>() {
                }).
                put("type", "2")

        );
    }

    /**
     * 获取城市列表
     *
     * @param requestHandler
     */
    public static void getArea(NetRequestHandler requestHandler) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(GET_AREA_CODE).
                setUrl(GET_AREA).
                setTypeToken(new TypeToken<List<CityMode>>() {
                })

        );
    }


    /**
     * 修改资料
     *
     * @param requestHandler
     */
    public static void editData(NetRequestHandler requestHandler, String title, File headImg) {
        requestHandler.requestAsynPost(Config.UAction().
                setRequestCode(EDIT_DATA_CODE).
                setUrl(EDIT_DATA).
                put("title", title).
                put("headImg", headImg)

        );
    }

    /**
     * 获取活动列表
     *
     * @param requestHandler
     */
    public static void getActivityList(NetRequestHandler requestHandler, int p) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(QUEST_GET_ACTIVITY_LIST_CODE).
                setUrl(GET_ACTIVITY_LIST).
                setTypeToken(MyActivityInfo.class).
                put("p", p)

        );
    }

    /**
     * 获取在建工地列表
     *
     * @param requestHandler
     */
    public static void getBuildingList(NetRequestHandler requestHandler, String city, float latitude, float longitude, String sort, int p) {
        requestHandler.requestAsynGet(new NAction()
                .setUrl(BUILDINGSITE_GET_LIST)
                .put("city", city)
                .put("latitude", latitude)
                .put("longitude", longitude)
                .put("sort", sort)
                .put("p", p)
                .setRequestCode(GET_BUILDINGSITE_GET_LIST_CODE)
                .setTypeToken(BuidingListInfo.class));
    }


    /**
     * 我的优惠劵
     *
     * @param requestHandler
     */
    public static void getMyDiscount(NetRequestHandler requestHandler, int p) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(MY_DISCOUNT_CODE).
                setUrl(MY_DISCOUNT).
                setTypeToken(new TypeToken<PageBean<CouponBean>>() {
                }).
                put("p", p)

        );
    }

    /**
     * 设置默认城市
     *
     * @param requestHandler
     */
    public static void put_area(NetRequestHandler requestHandler, String title) {
        requestHandler.requestAsynPost(Config.UAction().
                setRequestCode(PUT_AREA_CODE).
                setUrl(PUT_AREA).
                put("title", title)

        );
    }

    /**
     * 分类
     *
     * @param requestHandler
     */
    public static void getCategoryList(NetRequestHandler requestHandler) {
        requestHandler.requestAsynGet(new NAction().
                setRequestCode(PUT_TRAVELGUIDE_GET_CATEGORY_CODE).
                setUrl(TRAVELGUIDE_GET_CATEGORY)
                .setTypeToken(new TypeToken<List<CategoryListInfo>>() {
                }));
    }

    /**
     * 攻略列表
     *
     * @param requestHandler
     */
    public static void getTraverlGuideGetList(NetRequestHandler requestHandler, String category_id, Object keywords, int p) {
        requestHandler.requestAsynGet(new NAction().
                setRequestCode(PUT_TRAVELGUIDE_GET_LIST_CODE).
                setUrl(TRAVELGUIDE_GET_LIST)
                .put("category_id", category_id)
                .put("keywords", keywords)
                .put("p", p)
                .setTypeToken(TraverGuideListInfo.class));
    }

    /**
     * 领取优惠券
     *
     * @param requestHandler
     */
    public static void getDiscount(NetRequestHandler requestHandler, String discount_id) {
        requestHandler.requestAsynPost(Config.UAction().
                setRequestCode(GET_DISCOUNT_CODE).
                setUrl(GET_DISCOUNT).
                put("discount_id", discount_id)
        );
    }

    /**
     * 获取装修公司列表
     *
     * @param requestHandler
     */
    public static void getCampanyList(NetRequestHandler requestHandler, String city, float latitude, float longitude, String sort, int p) {
        requestHandler.requestAsynGet(new NAction().
                setUrl(GET_CAMPANY_LIST).
                put("city", city).
                put("latitude", latitude).
                put("longitude", longitude).
                put("sort", sort).
                put("p", p).
                setRequestCode(GET_GET_CAMPANY_LIST_CODE)
                .setTypeToken(CampanyListInfo.class)
        );
    }


    /**
     * 活动是否收藏、报名
     *
     * @param requestHandler
     */
    public static void getIsCellectSign(NetRequestHandler requestHandler, String id) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(GET_IS_COLLECT_SIGN).
                put("id", id).
                setRequestCode(GET_IS_COLLECT_SIGN_CODE)
                .setTypeToken(CollectSignInfo.class)
        );
    }

    /**
     * 添加收藏
     *
     * @param requestHandler
     */
    public static void addCollect(NetRequestHandler requestHandler, String pid, int type) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(ADD_COLLECT).
                put("pid", pid).
                put("type", type).
                setRequestCode(GET_ADD_COLLECT_CODE));
    }

    /**
     * 取消收藏
     *
     * @param requestHandler
     */
    public static void delCollect(NetRequestHandler requestHandler, String pid[], int type) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(DEL_COLLECT).
                put("pid", pid).
                put("type", type).
                setRequestCode(GET_DEL_COLLECT_CODE));
    }

    /**
     * 马上报名-- 活动
     *
     * @param requestHandler
     */
    public static void joinActivity(NetRequestHandler requestHandler, String pid) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(SIGN_ACTIVITY).
                put("pid", pid).
                setRequestCode(GET_SIGN_ACTIVITY_CODE));
    }

    /**
     * 我的活动
     *
     * @param requestHandler
     */
    public static void getMyActivity(NetRequestHandler requestHandler, int p) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(MY_ACTIVITY_CODE).
                setUrl(MY_ACTIVITY).
                setTypeToken(MyActivityInfo.class).
                put("p", p)
        );
    }

    /**
     * 工地是否收藏、报名
     *
     * @param requestHandler
     */
    public static void buildingCollect(NetRequestHandler requestHandler, String build_id) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(BUILDINGSITE_COLLECT_SUBCREIBE).
                put("build_id", build_id).
                setRequestCode(MY_BUILDINGSITE_COLLECT_SUBCREIBE_CODE)
                .setTypeToken(BuildingCollectInfo.class)
        );
    }

    /**
     * 马上报名-- 活动
     *
     * @param requestHandler
     */
    public static void joinBuildingSite(NetRequestHandler requestHandler, String build_id) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(BUILDINGSITE_SUBSCREIBE).
                put("build_id", build_id).
                setRequestCode(MY_BUILDINGSITE_SUBSCREIBE_CODE));
    }


    /**
     * 我的收藏
     *
     * @param requestHandler
     */
    public static void getCollecList(NetRequestHandler requestHandler, int type, int p) {
        NAction action = Config.UAction().
                setRequestCode(GET_COLLEC_LIST_CODE).
                setUrl(GET_COLLEC_LIST).
                put("p", p)
                .put("city", LocationBean.cityId)
                .put("latitude", LocationBean.latitude)
                .put("longitude", LocationBean.longitude).
                        put("type", type);
        if (type == 1) {
            action.setTypeToken(new TypeToken<PageBean<MyActivityInfo.ListBean>>() {
            });
        } else {
            action.setTypeToken(new TypeToken<PageBean<BuidingListInfo.ListBean>>() {
            });

        }
        requestHandler.requestAsynGet(action
        );
    }

    /**
     * 获取爆款限时购的品牌和属性筛选条件
     *
     * @param requestHandler
     */
    public static void get_spec_attr(NetRequestHandler requestHandler, int type) {
        NAction action = Config.UAction().
                setRequestCode(GET_SPEC_ATTR_CODE).
                setUrl(GET_SPEC_ATTR).
                put("type", type);
        action.setTypeToken(new TypeToken<FilterAttrBean>() {
        });
        requestHandler.requestAsynGet(action
        );
    }

    /**
     * 获取分类商品的品牌和属性筛选条件
     *
     * @param requestHandler
     */
    public static void get_category_attr(NetRequestHandler requestHandler, String cid) {
        NAction action = Config.UAction().
                setRequestCode(GET_CATEGORY_ATTR_CODE).
                setUrl(GET_CATEGORY_ATTR).
                put("cid", cid);
        action.setTypeToken(new TypeToken<FilterAttrBean>() {
        });
        requestHandler.requestAsynGet(action
        );
    }


    /**
     * 获取分类商品列表
     *
     * @param requestHandler
     */
    public static void get_category_product(NetRequestHandler requestHandler, String cid, String brand_id, String price_min, String price_max, String attr_value_id, String keywords, String sort, int p) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(GET_CATEGORY_PRODUCT_CODE).
                setUrl(GET_CATEGORY_PRODUCT).
                setTypeToken(new TypeToken<PageBean<HomeHotGoods>>() {
                }).
                put("brand_id", brand_id).
                put("cid", cid).
                put("price_min", price_min).
                put("price_max", price_max).
                put("attr_value_id", attr_value_id).put("keywords", keywords).
                put("sort", sort).
                put("p", p)

        );
    }

    /**
     * 搜索
     *
     * @param requestHandler
     */
    public static void search(NetRequestHandler requestHandler, String keywords, String type) {
        requestHandler.requestAsynGet(Config.UAction().
                setRequestCode(SEARCH_CODE).
                setUrl(SEARCH).
                setTypeToken(new TypeToken<PageBean<SearchResultBean>>() {
                }).
                put("keywords", keywords).
                put("type", type)

        );
    }

    /**
     * 获取商品详情
     *
     * @param requestHandler
     */
    public static void getProductDetails(NetRequestHandler requestHandler, String id) {
        requestHandler.requestAsynPost(Config.UAction().
                setRequestCode(GET_PRODUCT_DETAIL_CODE).
                setUrl(GET_PRODUCT_DETAIL).
                setTypeToken(new TypeToken<GoodsDetails>() {
                }).
                put("id", id)
        );
    }

    /**
     * 获取默认预算计算界面展示数据
     *
     * @param requestHandler
     */
    public static void getCalculateDefaultData(NetRequestHandler requestHandler) {
        requestHandler.requestAsynGet(new NAction().
                setUrl(CALCULATE_GET_DEFAULT_DATA).
                setRequestCode(GET_CALCULATE_GET_DEFAULT_DATA_CODE)
                .setTypeToken(CalculateDefaultData.class));
    }

    /**
     * 房屋类型数据获取
     *
     * @param requestHandler
     */
    public static void getCalculateTypeData(NetRequestHandler requestHandler) {
        requestHandler.requestAsynGet(new NAction().
                setUrl(CALCULATE_GETAPARTMENT).
                setRequestCode(GET_CALCULATE_GETAPARTMENT_CODE).cachePriority(true)
                .setTypeToken(new TypeToken<List<CalculateTypeInfo>>() {
                }));
    }


    /**
     * 默认预算计算
     *
     * @param requestHandler
     */
    public static void commitCalcatuDefault(NetRequestHandler requestHandler, String acreageacreage, LinkedHashSet<String> house_pattern, String area_id, String other_calculate_id) {
        List<String> typeIds = new ArrayList<>();
        for (String value : house_pattern) {
            typeIds.add(value);
        }
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(COMMINT_DETAULT_CALCULATE_DETAIL).
                put("acreage", acreageacreage).
                put("house_pattern", typeIds).
                put("area_id", area_id).
                put("other_calculate_id", other_calculate_id).
                setRequestCode(GET_COMMINT_DETAULT_CALCULATE_DETAIL_CODE)
        );
    }

    /**
     * 房间信息
     *
     * @param requestHandler
     */
    public static void edit_house_info(NetRequestHandler requestHandler, String house_id) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(EDIT_HOUSE_INFO).
                put("house_id", house_id).
                setRequestCode(EDIT_HOUSE_INFO_CODE)
                .setTypeToken(RoomInfoBean.class));
    }

    /**
     * 【预算】工程详情
     *
     * @param requestHandler
     */
    public static void get_project_detail(NetRequestHandler requestHandler, String project_id) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(GET_PROJECT_DETAIL).
                put("project_id", project_id).
                setRequestCode(GET_PROJECT_DETAIL_CODE)
                .setTypeToken(ProjectItemDetails.class));
    }

    /**
     * 【预算】查看全部清单
     *
     * @param requestHandler
     */
    public static void get_all_list(NetRequestHandler requestHandler, String project_id) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(GET_ALL_LIST).
                put("project_id", project_id).
                setRequestCode(GET_ALL_LIST_CODE)
                .setTypeToken(new TypeToken<List<AllListBean>>() {
                }));
    }


    /**
     * 【预算】保存项目名称
     *
     * @param requestHandler
     */
    public static void save_project_name(NetRequestHandler requestHandler, String project_id, String title) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(SAVE_PROJECT_NAME).
                put("project_id", project_id).
                put("title", title).
                setRequestCode(SAVE_PROJECT_NAME_CODE)
        );
    }

    /**
     * 【预算】获取每个房间预算结果
     *
     * @param requestHandler
     */
    public static void get_house_list(NetRequestHandler requestHandler, String house_id) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(GET_HOUSE_LIST).
                put("house_id", house_id).
                setRequestCode(GET_HOUSE_LIST_CODE)
                .setTypeToken(new TypeToken<AllListBean>() {
                })
        );
    }


    /**
     * 【预算】房间信息-删除窗
     *
     * @param requestHandler
     */
    public static void del_windows(NetRequestHandler requestHandler,
                                   String house_id,
                                   String window_id) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(DEL_WINDOWS).
                put("house_id", house_id).
                put("window_id", window_id).
                setRequestCode(DEL_WINDOWS_CODE)
        );
    }

    /**
     * 【预算】房间信息-删除门
     *
     * @param requestHandler
     */
    public static void del_door(NetRequestHandler requestHandler,
                                String house_id,
                                String door_id) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(DEL_DOOR).
                put("house_id", house_id).
                put("door_id", door_id).
                setRequestCode(DEL_DOOR_CODE)
        );
    }

    /**
     * 【预算】房间信息-删除房间
     *
     * @param requestHandler
     */
    public static void del_house(NetRequestHandler requestHandler,
                                 String house_id) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(DEL_HOUSE).
                put("house_id", house_id).
                setRequestCode(DEL_HOUSE_CODE)
        );
    }

    /**
     * 【预算】获取预算结果
     */
    public static NAction get_calculate_result(String project_id) {
        return Config.UAction().
                setUrl(GET_CALCULATE_RESULT).
                put("project_id", project_id).
                setRequestCode(GET_CALCULATE_RESULT_CODE)
                .setTypeToken(DefaltCalculateInfo.class).setRequestType(NetRequestHandler.POST);
    }


    /**
     * 修改密码
     *
     * @param requestHandler
     */
    public static void edit_pwd(NetRequestHandler requestHandler,
                                Object old_pwd,
                                Object password,
                                Object repassword
    ) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(EDIT_PWD).
                put("old_pwd", old_pwd).
                put("password", password).
                put("repassword", repassword).
                setRequestCode(EDIT_PWD_CODE)
        );
    }

    /**
     * 忘记密码-获取验证码
     *
     * @param requestHandler
     */
    public static void get_forget_code(NetRequestHandler requestHandler,
                                       String mobile
    ) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(GET_FORGET_CODE).
                put("mobile", mobile).
                setRequestCode(GET_FORGET_CODE_CODE)
        );
    }

    /**
     * 忘记密码
     *
     * @param requestHandler
     */
    public static void get_password_back(NetRequestHandler requestHandler,
                                         String mobile, String code, String password
    ) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(GET_PASSWORD_BACK).
                put("mobile", mobile).
                put("code", code).
                put("password", password).
                setRequestCode(GET_PASSWORD_BACK_CODE)
        );
    }

    /**
     * 【预算】添加门的信息（还有一个修改门信息的接口，修改是为了修改商品信息）
     *
     * @param requestHandler
     */
    public static void add_door_info(NetRequestHandler requestHandler,
                                     String hourse_id, String product_id
    ) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(ADD_DOOR_INFO).
                put("hourse_id", hourse_id).
                put("product_id", product_id).
                setRequestCode(ADD_DOOR_INFO_CODE)
        );
    }

    /**
     * 【预算】工程详情-删除项目
     *
     * @param requestHandler
     * @param project_list_id 施工项ID
     */
    public static void del_one_project_detail(NetRequestHandler requestHandler,
                                              String project_list_id
    ) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(DEL_ONE_PROJECT_DETAIL).
                put("project_list_id", project_list_id).
                setRequestCode(DEL_ONE_PROJECT_DETAIL_CODE)
        );
    }

    /**
     * 【预算】房间信息-添加房间
     *
     * @param requestHandler
     */
    public static void add_house(NetRequestHandler requestHandler,
                                 String project_id,
                                 String house_type,
                                 String length,
                                 String width,
                                 String high,
                                 String acreage,
                                 String perimeter) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(ADD_HOUSE).
                put("project_id", project_id).
                put("house_type", house_type).
                put("length", length).
                put("width", width).
                put("high", high).
                put("acreage", acreage).
                put("perimeter", perimeter).
                setRequestCode(ADD_HOUSE_CODE)
        );
    }

    /**
     * 【预算】房间信息-修改房间信息
     *
     * @param requestHandler
     */
    public static void edit_house(NetRequestHandler requestHandler,
                                  String house_id,
                                  String house_type,
                                  String length,
                                  String width,
                                  String high, String acreage,
                                  String perimeter
    ) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(EDIT_HOUSE).
                put("house_id", house_id).
                put("house_type", house_type).
                put("length", length).
                put("width", width).
                put("high", high).
                put("acreage", acreage).
                put("perimeter", perimeter).
                setRequestCode(EDIT_HOUSE_CODE)
        );
    }


    /**
     * 【预算】新增房间获取房间类型
     *
     * @param requestHandler
     */
    public static void add_house_type(NetRequestHandler requestHandler,
                                      String project_id
    ) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(ADD_HOUSE_TYPE).
                put("project_id", project_id).
                setRequestCode(ADD_HOUSE_TYPE_CODE).cachePriority(true)
                .setTypeToken(new TypeToken<List<CalculateTypeInfo>>() {
                })
        );
    }

    /**
     * 【预算】自定义项目添加
     *
     * @param requestHandler
     */
    public static void add_project_customize(NetRequestHandler requestHandler,
                                             String product_id, String main_product_fee, String calculate_house_info_id, String calculate_house_list_id,
                                             String values[]
    ) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(ADD_PROJECT_CUSTOMIZE).
                put("product_id", product_id).
                put("calculate_house_info_id", calculate_house_info_id).
                put("calculate_house_list_id", calculate_house_list_id).
                put("cons_project_title", values[0]).
                put("main_people_fee", values[1]).
                put("main_product_fee", main_product_fee).
                put("second_product_fee", values[3]).
                put("people_fee", values[4]).
                put("loss_fee", values[5]).
                put("calculate_formula", values[6]).
                setRequestCode(ADD_PROJECT_CUSTOMIZE_CODE)
        );
    }


    /**
     * 【预算】添加门到商城的接口
     */
    public static NAction add_door_category() {
        return Config.UAction().
                setUrl(ADD_DOOR_CATEGORY).
                setRequestCode(ADD_DOOR_CATEGORY_CODE).setRequestType(NetRequestHandler.GET);
    }

    /**
     * 【预算】添加窗到商城的接口
     */
    public static NAction add_window_category() {
        return Config.UAction().
                setUrl(ADD_WINDOW_CATEGORY).
                setRequestCode(ADD_WINDOW_CATEGORY_CODE).setRequestType(NetRequestHandler.GET);
    }

    /**
     * 【预算】添加门的信息（还有一个修改门信息的接口，修改是为了修改商品信息）
     *
     * @param requestHandler
     */
    public static void add_window_info(NetRequestHandler requestHandler,
                                       String hourse_id, String product_id
    ) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(ADD_WINDOW_INFO).
                put("hourse_id", hourse_id).
                put("product_id", product_id).
                setRequestCode(ADD_WINDOW_INFO_CODE)
        );
    }

    /**
     * 【预算】材料替换
     *
     * @param requestHandler
     */
    public static void change_product(NetRequestHandler requestHandler,
                                      String project_list_id, String product_id
    ) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(CHANGE_PRODUCT).
                put("project_list_id", project_list_id).
                put("product_id", product_id).
                setRequestCode(CHANGE_PRODUCT_CODE)
        );
    }


    /**
     * 【预算】从商城添加商品到预算
     *
     * @param requestHandler
     */
    public static void add_product(NetRequestHandler requestHandler, String hours_type,
                                   String product_id, String project_id, String price, String num
    ) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(ADD_PRODUCT).
                put("hours_type", hours_type).
                put("product_id", product_id).
                put("project_id", project_id).
                put("price", price).
                put("num", num).
                setRequestCode(ADD_PRODUCT_CODE)
        );
    }


    /**
     * 【预算】从商城添加商品到预算
     *
     * @param requestHandler
     */
    public static void my_project(NetRequestHandler requestHandler, String type,
                                  Object p) {
        requestHandler.requestAsynGet(Config.UAction().
                setUrl(MY_PROJECT).
                put("type", type).
                put("p", p).
                setRequestCode(MY_PROJECT_CODE)
                .setTypeToken(new TypeToken<PageBean<MyProjectBean>>() {
                })
        );
    }


    /**
     * 【预算】重新计算预算
     *
     * @param requestHandler
     */
    public static void review_calculate(NetRequestHandler requestHandler, String project_id) {
        requestHandler.requestAsynGet(Config.UAction().
                setUrl(REVIEW_CALCULATE).
                put("project_id", project_id).
                setRequestCode(REVIEW_CALCULATE_CODE)
        );
    }

    /**
     * 【预算】重新计算预算
     */
    public static NAction review_calculate(String project_id) {
        return Config.UAction().
                setUrl(REVIEW_CALCULATE).
                put("project_id", project_id).
                setRequestCode(REVIEW_CALCULATE_CODE).setRequestType(NetRequestHandler.POST);
    }


    /**
     * 【预算】获取勾选施工清单
     *
     * @param requestHandler
     */
    public static void get_list_project_other(NetRequestHandler requestHandler) {
        requestHandler.requestAsynGet(Config.UAction().
                setUrl(GET_LIST_PROJECT_OTHER).
                setRequestCode(GET_LIST_PROJECT_OTHER_CODE)
                .setTypeToken(new TypeToken<List<WorkTypeBean>>() {
                })
        );
    }

    /**
     * 【预算】修改预算勾选项接口
     *
     * @param requestHandler
     */
    public static void change_calculate_other(NetRequestHandler requestHandler, String choose_id ) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(CHANGE_CALCULATE_OTHER).
                setRequestCode(CHANGE_CALCULATE_OTHER_CODE)
                .put("choose_id", choose_id)
        );
    }


    /**
     * 【预算】
     *
     * @param requestHandler
     */
    public static void change_dry_wet(NetRequestHandler requestHandler, String house_id,String status ) {
        requestHandler.requestAsynPost(Config.UAction().
                setUrl(CHANGE_DRY_WET).
                setRequestCode(CHANGE_DRY_WET_CODE)
                .put("house_id", house_id)
                .put("status", status)
        );
    }
}
