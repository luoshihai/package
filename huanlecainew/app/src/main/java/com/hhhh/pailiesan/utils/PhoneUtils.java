package com.hhhh.pailiesan.utils;

/**
 * Created by DL on 2016/12/26.
 */
public class PhoneUtils {
    public static boolean isPhone(String str){//判断是否为手机号码
        if(str.matches("^[1][3,4,5,7,8][0-9]{9}$")&&str.length()==11){
            return true;
        }else{
            return false;
        }
    }
}
