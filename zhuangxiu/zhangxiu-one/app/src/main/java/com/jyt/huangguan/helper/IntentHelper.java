package com.jyt.huangguan.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import com.jyt.huangguan.bean.FileBean;
import com.jyt.huangguan.bean.SearchBean;
import com.jyt.huangguan.bean.Tuple;
import com.jyt.huangguan.view.activity.BrowseImagesActivity;
import com.jyt.huangguan.view.activity.CommonProgressActivity;
import com.jyt.huangguan.view.activity.ConstructionActivity;
import com.jyt.huangguan.view.activity.DeliverGoodsActivity;
import com.jyt.huangguan.view.activity.EvaluateActivity;
import com.jyt.huangguan.view.activity.EvaluateDetailActivity;
import com.jyt.huangguan.view.activity.EvaluateSendActivity;
import com.jyt.huangguan.view.activity.FileDetailActivity;
import com.jyt.huangguan.view.activity.FinishSteelHookActivity;
import com.jyt.huangguan.view.activity.InfoDetailActivity;
import com.jyt.huangguan.view.activity.LoginActivity;
import com.jyt.huangguan.view.activity.PrepareConstructActivity;
import com.jyt.huangguan.view.activity.SelImageActivity;
import com.jyt.huangguan.view.activity.SelPeopleActivity;
import com.jyt.huangguan.view.activity.ShopActivity;
import com.jyt.huangguan.view.activity.UpLoadImageActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweiqi on 2017/10/30.
 */

public class IntentHelper {

    //region 选择图片
    public static void openSelImageActivityForResult(Object context, int maxSelCount){

        if (context instanceof  Activity){
            Intent intent = getIntent((Context) context, SelImageActivity.class);
            intent.putExtra(IntentKey.MAX_COUNT,maxSelCount);
            ((Activity) context).startActivityForResult(intent,IntentRequestCode.CODE_SEL_IMAGES);
        }else if (context instanceof Fragment){
            Intent intent = getIntent(((Fragment) context).getContext(), SelImageActivity.class);
            intent.putExtra(IntentKey.MAX_COUNT,maxSelCount);
            ((Fragment) context).startActivityForResult(intent,IntentRequestCode.CODE_SEL_IMAGES);
        }
    }
    public static void openSelImageActivityForResult(Object context, int maxSelCount,List images){
        if(images == null){
            images = new ArrayList();
        }


        if (context instanceof  Activity){
            Intent intent = getIntent((Context) context, SelImageActivity.class);
            intent.putExtra(IntentKey.MAX_COUNT,maxSelCount);
            intent.putStringArrayListExtra(IntentKey.IMAGES, (ArrayList<String>) images);
            ((Activity) context).startActivityForResult(intent,IntentRequestCode.CODE_SEL_IMAGES);
        }else if (context instanceof Fragment){
            Intent intent = getIntent(((Fragment) context).getContext(), SelImageActivity.class);
            intent.putExtra(IntentKey.MAX_COUNT,maxSelCount);
            intent.putStringArrayListExtra(IntentKey.IMAGES, (ArrayList<String>) images);
            ((Fragment) context).startActivityForResult(intent,IntentRequestCode.CODE_SEL_IMAGES);
        }
    }

    /**
     *
     * @param intent
     * @return int maxCount,List images
     */
    public static Tuple SelImageActivityGetPara(Intent intent){
        int maxCount = intent.getIntExtra(IntentKey.MAX_COUNT,0);
        List images = intent.getStringArrayListExtra(IntentKey.IMAGES);
        return new Tuple(maxCount,images);
    }

    /**
     *
     * @param intent
     * @return List images
     */
    public static Tuple SelImageActivityGetResult(Intent intent){
        List images = intent.getStringArrayListExtra(IntentKey.IMAGES);
        return new Tuple(images);
    }
    public static void SelImageActivitySetResult(Activity activity,int resultCode,List images){
        Intent intent = getIntent();
        intent.putStringArrayListExtra(IntentKey.IMAGES, (ArrayList<String>) images);
        activity.setResult(resultCode,intent);
        activity.finish();
    }

    //endregion

    //region 浏览图片
    public static void openBrowseImagesActivity(Context context ,String image){
        List images = new ArrayList();
        images.add(image);
        openBrowseImagesActivity(context,images,0);
    }

    public static void openBrowseImagesActivity(Context context, List<String> images, int startIndex){
        Intent intent = getIntent(context, BrowseImagesActivity.class);
        intent.putExtra(IntentKey.START_INDEX,startIndex);
        intent.putStringArrayListExtra(IntentKey.IMAGES, (ArrayList<String>) images);

        context.startActivity(intent);
    }
    /**
     *
     * @param intent
     * @return images list  , startIndex int
     */
    public static Tuple BrowseImagesActivityGetPara(Intent intent){
        List list = intent.getStringArrayListExtra(IntentKey.IMAGES);
        int startIndex = intent.getIntExtra(IntentKey.START_INDEX,0);
        return new Tuple(list,startIndex);
    }
    //endregion

    // region 测量中
    public static void openUploadImagesActivityForResult(Object context,Parcelable project,int count){
        Intent intent = getIntent((Context) context, UpLoadImageActivity.class);
        intent.putExtra(IntentKey.PROJECT,project);
        intent.putExtra(IntentKey.MAX_COUNT,count);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, IntentRequestCode.CODE_UPLOAD_IMAGES);
        }else if (context instanceof Fragment){
            ((Fragment) context).startActivityForResult(intent, IntentRequestCode.CODE_UPLOAD_IMAGES);
        }
    }

    // endregion

    //region 测量完毕 设计完毕 客户已审批 待店主确认 店主已确认 待审图纸 已审图纸 待生产招牌 待审材料 已审材料 施工完毕

    /**
     * 测量完毕
     * @param context
     * @param project
     */
    public static void openMeasureFinishActivity(Context context,Parcelable project){
            Intent intent = getIntent(context, CommonProgressActivity.class);
            intent.putExtra(IntentKey.TYPE,CommonProgressActivity.TYPE_MEASURE_FINISH);
            intent.putExtra(IntentKey.DATA,project);
            context.startActivity(intent);
    }

    /**
     * 设计完毕
     * @param context
     * @param project
     */
    public static void openDesignFinishActivity(Context context,Parcelable project){
        Intent intent = getIntent(context, CommonProgressActivity.class);
        intent.putExtra(IntentKey.TYPE,CommonProgressActivity.TYPE_DESIGN_FINISH);
        intent.putExtra(IntentKey.DATA,project);
        context.startActivity(intent);
    }

    /**
     * 客户已审批
     * @param context
     * @param project
     */
    public static void openCustomerConfirmedActivity(Context context,Parcelable project){
        Intent intent = getIntent(context, CommonProgressActivity.class);
        intent.putExtra(IntentKey.TYPE,CommonProgressActivity.TYPE_CUSTOMER_VERIFIED);
        intent.putExtra(IntentKey.DATA,project);
        context.startActivity(intent);
    }

    /**
     * 待店主确认
     * @param context
     * @param project
     */
    public static void openWaitShopkeeperConfirmActivity(Context context,Parcelable project){
        Intent intent = getIntent(context, CommonProgressActivity.class);
        intent.putExtra(IntentKey.TYPE,CommonProgressActivity.TYPE_WAIT_SHOPKEEPER_CONFIRM);
        intent.putExtra(IntentKey.DATA,project);
        context.startActivity(intent);
    }

    /**
     * 店主已确认
     * @param context
     * @param project
     */
    public static void openShopkeeperConfirmedActivity(Context context,Parcelable project){
        Intent intent = getIntent(context, CommonProgressActivity.class);
        intent.putExtra(IntentKey.TYPE,CommonProgressActivity.TYPE_SHOPKEEPER_CONFIRMED);
        intent.putExtra(IntentKey.DATA,project);
        context.startActivity(intent);
    }

    /**
     * 待审图纸
     * @param context
     * @param project
     */
    public static void openWaitVerifiedDrawingActivity(Context context,Parcelable project){
        Intent intent = getIntent(context, CommonProgressActivity.class);
        intent.putExtra(IntentKey.TYPE,CommonProgressActivity.TYPE_WAIT_VERIFIED_DRAWING);
        intent.putExtra(IntentKey.DATA,project);
        context.startActivity(intent);
    }

    /**
     * 已审图纸
     * @param context
     * @param project
     */
    public static void openVerifiedDrawingActivity(Context context,Parcelable project){
        Intent intent = getIntent(context, CommonProgressActivity.class);
        intent.putExtra(IntentKey.TYPE,CommonProgressActivity.TYPE_VERIFIED_DRAWING);
        intent.putExtra(IntentKey.DATA,project);
        context.startActivity(intent);
    }

    /**
     * 待生产招牌
     * @param context
     * @param project
     */
    public static void openWaitProduceSignActivity(Context context,Parcelable project){
        Intent intent = getIntent(context, CommonProgressActivity.class);
        intent.putExtra(IntentKey.TYPE,CommonProgressActivity.TYPE_WAIT_PRODUCE_SIGN);
        intent.putExtra(IntentKey.DATA,project);
        context.startActivity(intent);
    }
    /**
     *待审材料
     * @param context
     * @param project
     */
    public static void openWaitVerifyMaterialActivity(Context context,Parcelable project){
        Intent intent = getIntent(context, CommonProgressActivity.class);
        intent.putExtra(IntentKey.TYPE,CommonProgressActivity.TYPE_WAIT_VERIFY_MATERIAL);
        intent.putExtra(IntentKey.DATA,project);
        context.startActivity(intent);
    }

    /**
     * 已审材料
     * @param context
     * @param project
     */
    public static void openConstructionCompleteActivity(Context context,Parcelable project){
        Intent intent = getIntent(context, CommonProgressActivity.class);
        intent.putExtra(IntentKey.TYPE,CommonProgressActivity.TYPE_CONSTRUCTION_COMPLETE);
        intent.putExtra(IntentKey.DATA,project);
        context.startActivity(intent);
    }

    /**
     * 施工完毕
     * @param context
     * @param project
     */
    public static void openVerifiedMaterialActivity(Context context,Parcelable project){
        Intent intent = getIntent(context, CommonProgressActivity.class);
        intent.putExtra(IntentKey.TYPE,CommonProgressActivity.TYPE_VERIFIED_MATERIAL);
        intent.putExtra(IntentKey.DATA,project);
        context.startActivity(intent);
    }

    public static void openCommonProgressActivity(Context context,Parcelable project ,Parcelable beforeProject) {
        Intent intent = getIntent(context, CommonProgressActivity.class);
//        intent.putExtra(IntentKey.TYPE,CommonProgressActivity.TYPE_VERIFIED_MATERIAL);
        intent.putExtra(IntentKey.DATA,project);
        intent.putExtra(IntentKey.DATA2,beforeProject);

        context.startActivity(intent);
    }

    public static void openCommonProgressActivity(Context context,Parcelable project ,Parcelable beforeProject,boolean isConfirm) {
        Intent intent = getIntent(context, CommonProgressActivity.class);
        //        intent.putExtra(IntentKey.TYPE,CommonProgressActivity.TYPE_VERIFIED_MATERIAL);
        intent.putExtra(IntentKey.DATA,project);
        intent.putExtra(IntentKey.DATA2,beforeProject);
        intent.putExtra(IntentKey.Confirm,isConfirm);
        context.startActivity(intent);
    }

    public static void openCompleteCommonProgressActivity(Context context,Parcelable project ,Parcelable beforeProject) {
        Intent intent = getIntent(context, CommonProgressActivity.class);
        //        intent.putExtra(IntentKey.TYPE,CommonProgressActivity.TYPE_VERIFIED_MATERIAL);
        intent.putExtra(IntentKey.DATA,project);
        intent.putExtra(IntentKey.TYPE,10);
        intent.putExtra(IntentKey.DATA2,beforeProject);
        context.startActivity(intent);
    }
    /**
     * CommonProgressActivity 读取数据
     * @param intent
     * @return int type , Parcelable data
     */
    public static Tuple CommonProgressActivityGetPara(Intent intent){
        int type = intent.getIntExtra(IntentKey.TYPE,0);
        Parcelable data = intent.getParcelableExtra(IntentKey.DATA);
        Parcelable data2 = intent.getParcelableExtra(IntentKey.DATA2);

        return new Tuple(type,data,data2);
    }

    /**
     * 商店信息页
     * @param context
     * @param bean 商店的请求信息
     */
    public static void openShopActivity(Context context, SearchBean bean){
        Intent intent = new Intent(context,ShopActivity.class);
        intent.putExtra(IntentKey.SHOPINFO,(Serializable) bean);
        context.startActivity(intent);
    }

    /**
     * 登出操作，跳转到登录界面
     * @param context
     */
    public static void DoLogout(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 通过ID进入商店详细页面
     * @param activity
     * @param ProjectID
     */
    public static void OpenShopActivityByID(Activity activity,String ProjectID,String shopname){
        Intent intent = new Intent(activity,ShopActivity.class);
        intent.putExtra(IntentKey.PROJECTID,ProjectID);
        intent.putExtra(IntentKey.SHOPNAME,shopname);
        activity.startActivity(intent);

    }

    /**
     * 打开评价人员界面
     * 内部人员 店主 品牌方
     * @param activity
     * @param projectId
     */
    public static void OpenEvaluateActivity(Activity activity , String projectId){
        Intent intent = new Intent(activity,EvaluateActivity.class);
        intent.putExtra(IntentKey.PROJECTID,projectId);
        activity.startActivity(intent);
    }

    /**
     * 打开评价详细界面
     * 店主 品牌方
     * @param activity
     * @param projectId
     */
    public static void OpenEvaluateDetailActivity(Activity activity , String projectId , int state,boolean sendState){
        Intent intent = new Intent(activity,EvaluateDetailActivity.class);
        intent.putExtra(IntentKey.PROJECTID,projectId);
        intent.putExtra(IntentKey.STATE,state);
        intent.putExtra(IntentKey.SEND_STATE,sendState);
        activity.startActivity(intent);
    }



    /**
     * 打开评价发送界面
     * 店主 品牌方
     * @param activity
     * @param projectId
     */
    public static void OpenEvaluateSendActivity(Activity activity , String projectId , int state,boolean isShop){
        Intent intent = new Intent(activity,EvaluateSendActivity.class);
        intent.putExtra(IntentKey.PROJECTID,projectId);
        intent.putExtra(IntentKey.STATE,state);
        intent.putExtra(IntentKey.SHOP,isShop);
        activity.startActivityForResult(intent,IntentKey.REQUEST_SEND);
    }



    /**
     * 打开InfoDetailActivity
     * @param context
     * @param state 0/1/2 界面一致，消息数据分三种来源
     */
    public static void OpenInfoDetailActivity(Context context , int state){
        Intent intent = new Intent(context,InfoDetailActivity.class);
        intent.putExtra(IntentKey.STATE,state);
        context.startActivity(intent);
    }
    //endregion

    //region 待发货 已发货
    public static void openWaitSendActivity(Context context,Parcelable project){
        Intent intent = getIntent(context, DeliverGoodsActivity.class);
        intent.putExtra(IntentKey.TYPE,DeliverGoodsActivity.TYPE_WAITE_SEND);
        intent.putExtra(IntentKey.PEOPLE,project);
        context.startActivity(intent);
    }

    public static void openSentActivity(Context context,Parcelable project){
        Intent intent = getIntent(context, DeliverGoodsActivity.class);
        intent.putExtra(IntentKey.TYPE,DeliverGoodsActivity.TYPE_SENT);
        intent.putExtra(IntentKey.PEOPLE,project);
        context.startActivity(intent);
    }

    public static Tuple DeliverGoodsActivityGetPara(Intent intent){
        int type = intent.getIntExtra(IntentKey.TYPE,0);
        Parcelable project = intent.getParcelableExtra(IntentKey.PEOPLE);
        return new Tuple(type,project);
    }
    //endregion

    //region 选择员工

    public static void openSelSingleManagerActivityForResult(Object context){
        Intent intent = getIntent();
        intent.putExtra(IntentKey.TYPE,SelPeopleActivity.TYPE_PROJECT_MANAGER);

        if (context instanceof Activity){
            intent.setClass((Context) context,SelPeopleActivity.class);
            ((Activity) context).startActivityForResult(intent,IntentRequestCode.CODE_SEL_SINGLE_WORKER);
        }else if (context instanceof Fragment){
            intent.setClass(((Fragment) context).getContext(),SelPeopleActivity.class);
            ((Fragment) context).startActivityForResult(intent,IntentRequestCode.CODE_SEL_SINGLE_WORKER);
        }
    }

    public static Tuple SelSingleWorkerGetPara(Intent intent){
        int type = intent.getIntExtra(IntentKey.TYPE,0);
        String parentId = intent.getStringExtra(IntentKey.ID);

        return new Tuple(type,parentId);
    }

    public static void openSelSingleMonitorActivityForResult(Object context,String parentId){
        Intent intent = getIntent();
        intent.putExtra(IntentKey.TYPE,SelPeopleActivity.TYPE_PROJECT_MONITOR);
        intent.putExtra(IntentKey.ID,parentId);

        if (context instanceof Activity){
            intent.setClass((Context) context,SelPeopleActivity.class);
            ((Activity) context).startActivityForResult(intent,IntentRequestCode.CODE_SEL_SINGLE_MONITOR);
        }else if (context instanceof Fragment){
            intent.setClass(((Fragment) context).getContext(),SelPeopleActivity.class);
            ((Fragment) context).startActivityForResult(intent,IntentRequestCode.CODE_SEL_SINGLE_MONITOR);
        }
    }
    public static void SelSinglePersonSetResult(Activity activity,Parcelable data){
        Intent intent = getIntent();
        intent.putExtra(IntentKey.PEOPLE,data);
        activity.setResult(Activity.RESULT_OK,intent);
    }
    public static Tuple SelSinglePersonGetResult(Intent intent){
        Parcelable people = intent.getParcelableExtra(IntentKey.PEOPLE);
        return new Tuple(people);
    }

    //endregion

    //region 货到待施工 安排施工人员完毕
    public static void openWaitingConstructActivity(Context context,Parcelable  project) {
        Intent intent = getIntent(context, PrepareConstructActivity.class);
        intent.putExtra(IntentKey.TYPE,PrepareConstructActivity.TYPE_PREPARE);
        intent.putExtra(IntentKey.PEOPLE,project);
        context.startActivity(intent);
    }
    public static void openPrepareConstructCompleteActivity(Context context,Parcelable  project) {
        Intent intent = getIntent(context, PrepareConstructActivity.class);
        intent.putExtra(IntentKey.TYPE,PrepareConstructActivity.TYPE_PREPARE_FINISH);
        intent.putExtra(IntentKey.PEOPLE,project);
        context.startActivity(intent);
    }

    public static Tuple ConstructCompleteActivityGetPara(Intent intent){
        int type = intent.getIntExtra(IntentKey.TYPE,0);
        Parcelable project = intent.getParcelableExtra(IntentKey.PEOPLE);
        return new Tuple(type,project);
    }


    //endregion

    //region 施工中
    public static void openConstructionActivity(Context context,Parcelable project){
        Intent intent = getIntent(context, ConstructionActivity.class);
        intent.putExtra(IntentKey.DATA,project);
        context.startActivity(intent);
    }
    public static Tuple ConstructionActivityGetPara(Intent intent){
        Parcelable project = intent.getParcelableExtra(IntentKey.DATA);
        return new Tuple(project);
    }
    //endregion

    //region 钢挂已完成
    public static void openFinishSteelHookActivity(Context context,Parcelable project){
        Intent intent = getIntent(context, FinishSteelHookActivity.class);
        intent.putExtra(IntentKey.DATA,project);
        context.startActivity(intent);
    }
    public static Tuple FinishSteelHookActivityGetPara(Intent intent){
        Parcelable project = intent.getParcelableExtra(IntentKey.DATA);
        return new Tuple(project);
    }
    //endregion

    public static void openFileDetailActivity(Context context, FileBean fileBean) {
        Intent intent = getIntent(context, FileDetailActivity.class);
        intent.putExtra("ShareFile",fileBean);
        context.startActivity(intent);

    }

    public static Intent getIntent(){
        return new Intent();
    }

    public static Intent getIntent(Context context, Class activityClass){
        return new Intent(context,activityClass);
    }


}
