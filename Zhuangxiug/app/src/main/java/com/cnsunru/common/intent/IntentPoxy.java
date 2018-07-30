package com.cnsunru.common.intent;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;

import com.sunrun.sunrunframwork.http.cache.NetSession;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Intent跳转代理类
 * Created by WQ on 2017/8/24.
 */

public class IntentPoxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (methodName.startsWith("start")) {//移除方法前缀
            methodName = methodName.replace("start", "");
        }
        int defaultResultCode = 110;
        ResultCode resultCodeAnno = method.getAnnotation(ResultCode.class);
        TargetActivity targetAnno = method.getAnnotation(TargetActivity.class);
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Activity sourceAct = (Activity) args[0];
        String targetActivity;
        if (targetAnno == null) {
            targetActivity = readActivityRealName(sourceAct, methodName);
        } else {
            targetActivity = targetAnno.value().getName();
        }
        if (targetActivity == null) {
            throw new RuntimeException(methodName + " 没有在清单文件中进行配置");
        }
        Class<? extends Activity> clazz = (Class<? extends Activity>) getClass().getClassLoader().loadClass(targetActivity);
        Intent intent = new Intent(sourceAct, clazz);
        for (int i = 1; i < parameterAnnotations.length; i++) {
            //读取方法参数注解.进行参数放置
            Key paramsAnnotation = (Key) searchParamsAnnotation(parameterAnnotations[i], Key.class);
            Object params = args[i];
            //参数放置
            if (paramsAnnotation != null) {
//              boolean result=  putParamsToIntent(intent,paramsAnnotation.value(),  params);
                if (params instanceof String) {
                    intent.putExtra(paramsAnnotation.value(), (String) params);
                } else if (params instanceof CharSequence) {
                    intent.putExtra(paramsAnnotation.value(), (CharSequence) params);
                } else if (params instanceof Integer) {
                    intent.putExtra(paramsAnnotation.value(), (Integer) params);
                } else if (params instanceof Float) {
                    intent.putExtra(paramsAnnotation.value(), (Float) params);
                } else if (params instanceof Double) {
                    intent.putExtra(paramsAnnotation.value(), (Double) params);
                } else if (params instanceof Byte) {
                    intent.putExtra(paramsAnnotation.value(), (Byte) params);
                } else if (params instanceof Short) {
                    intent.putExtra(paramsAnnotation.value(), (Short) params);
                } else if (params instanceof Long) {
                    intent.putExtra(paramsAnnotation.value(), (Long) params);
                } else if (params instanceof Character) {
                    intent.putExtra(paramsAnnotation.value(), (Character) params);
                } else if (params instanceof Boolean) {
                    intent.putExtra(paramsAnnotation.value(), (Boolean) params);
                } else if (params instanceof char[]) {
                    intent.putExtra(paramsAnnotation.value(), (char[]) params);
                } else if (params instanceof int[]) {
                    intent.putExtra(paramsAnnotation.value(), (int[]) params);
                } else if (params instanceof double[]) {
                    intent.putExtra(paramsAnnotation.value(), (double[]) params);
                } else if (params instanceof float[]) {
                    intent.putExtra(paramsAnnotation.value(), (float[]) params);
                } else if (params instanceof short[]) {
                    intent.putExtra(paramsAnnotation.value(), (short[]) params);
                } else if (params instanceof byte[]) {
                    intent.putExtra(paramsAnnotation.value(), (byte[]) params);
                } else if (params instanceof boolean[]) {
                    intent.putExtra(paramsAnnotation.value(), (boolean[]) params);
                } else if (params instanceof String[]) {
                    intent.putExtra(paramsAnnotation.value(), (String[]) params);
                } else if (params instanceof Parcelable[]) {
                    intent.putExtra(paramsAnnotation.value(), (Parcelable[]) params);
                } else if (params instanceof CharSequence[]) {
                    intent.putExtra(paramsAnnotation.value(), (CharSequence[]) params);
                }else if (params instanceof Intent) {
                    intent.putExtras( (Intent) params);
                } else if (params instanceof Bundle) {
                    intent.putExtras( (Bundle) params);
                } else if (params instanceof ArrayList) {
                    intent.putCharSequenceArrayListExtra(paramsAnnotation.value(), (ArrayList) params);
                }else if (params instanceof Serializable) {
                    intent.putExtra(paramsAnnotation.value(), (Serializable) params);
                } else if (params instanceof Parcelable) {
                    intent.putExtra(paramsAnnotation.value(), (Parcelable) params);
                }else {
                    //特殊类型,放置在Session中
                    NetSession.instance(sourceAct).put(paramsAnnotation.value(),  params);
                }
            }else {
                //参数不存在key注解时,尝试当做Intent类型以及 Bundle类型处理
                 if (params instanceof Intent) {
                    intent.putExtras( (Intent) params);
                } else if (params instanceof Bundle) {
                    intent.putExtras( (Bundle) params);
                }else {
                     //使用参数类型类名作为key将参数序列化到Session中
                     NetSession.instance(sourceAct).put(params.getClass().getName(),  params);
                 }
            }
        }
        if (resultCodeAnno != null) {
            defaultResultCode = resultCodeAnno.value();
        }
        sourceAct.startActivityForResult(intent, defaultResultCode);
//        method.getParameterAnnotations()
        return null;
    }


//    private boolean putParamsToIntent(Intent intent,String key,Object val){
//        Method[] declaredMethods = Intent.class.getMethods();
//        for (Method declaredMethod : declaredMethods) {
//            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
//            if(declaredMethod.getName().startsWith("put")&&parameterTypes!=null && parameterTypes.length==2&&parameterTypes[0]==String.class){
//                if(parameterTypes[1]==val.getClass()){
//                    try {
//                        declaredMethod.invoke(intent,key,val);
//                        return true;
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    } catch (InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return false;
//    }

    /**
     * 查找参数注解
     * @param annotations
     * @param clazz
     * @return
     */
    private Annotation searchParamsAnnotation(Annotation[] annotations, Class<?> clazz) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == clazz) {
                return annotation;
            }
        }
        return null;
    }

    /**
     * 读取方法对应的Activity的真实名称
     *
     * @param act
     * @param methodName
     * @return
     */
    private String readActivityRealName(Activity act, String methodName) {

        try {
            if (activitys.isEmpty()) {
                PackageInfo packageInfo = act.getPackageManager().getPackageInfo(act.getPackageName(),
                        PackageManager.GET_ACTIVITIES);
                String targetAcitivyName = null;
                for (ActivityInfo activity : packageInfo.activities) {
                    if (activity.name.contains(methodName)) targetAcitivyName = activity.name;
                    activitys.add(activity.name);
                }
                return targetAcitivyName;
            } else {
                for (String activity : activitys) {
                    if (activity.contains(methodName)) return activity;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 清单文件中配置的Activity缓存
     */
    private Set<String> activitys = new HashSet<>();
    private static StartIntent startIntent;

    /**
     * 获取代理实例
     *
     * @return
     */
    public static StartIntent getProxyInstance() {
        if (startIntent == null) {
            synchronized (StartIntent.class) {
                if (startIntent == null) {
                    startIntent = (StartIntent) Proxy.newProxyInstance(StartIntent.class.getClassLoader(),
                            new Class[]{StartIntent.class},
                            new IntentPoxy());
                }
            }
        }
        return startIntent;
    }

}
