package com.jyt.huangguan.cptools;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import java.lang.reflect.Type;

/**
 * 作者：WangJintao
 * 时间：2017/3/20
 * 邮箱：wangjintao1988@163.com
 */

public class GsonUtils {
    static  final String TAG= "GsonUtils";
    //采取单例模式
    private static Gson gson = new Gson();

    private GsonUtils() {
    }

    /**
     * @param src :将要被转化的对象
     * @return :转化后的JSON串
     * @MethodName : toJson
     * @Description : 将对象转为JSON串，此方法能够满足大部分需求
     */
    public static String toJson(Object src) {
        if (src == null) {
            return gson.toJson(JsonNull.INSTANCE);
        }
        return gson.toJson(src);
    }

    /**
     * @param json
     * @param classOfT
     * @return
     * @MethodName : fromJson
     * @Description : 用来将JSON串转为对象，但此方法不可用来转带泛型的集合
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, (Type) classOfT);
    }

    /**
     * @param json
     * @param typeOfT
     * @return
     * @MethodName : fromJson
     * @Description : 用来将JSON串转为对象，此方法可用来转带泛型的集合，如：Type为
     * new TypeToken<List<T>>(){}.getType()
     * ，其它类也可以用此方法调用，就是将List<T>替换为你想要转成的类
     */
    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

    public static <T> T fromJson(JsonReader reader, Type typeOfT) {
        return gson.fromJson(reader, typeOfT);
    }

    public static <T> T deserialize(JsonObject json, Class<T> clz) throws JsonSyntaxException {
        return gson.fromJson(json, clz);
    }
}
