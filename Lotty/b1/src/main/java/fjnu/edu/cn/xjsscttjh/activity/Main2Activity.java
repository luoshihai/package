package fjnu.edu.cn.xjsscttjh.activity;

import com.lsh.packagelibrary.TempActivity;

import fjnu.edu.cn.xjsscttjh.R;


public class Main2Activity extends TempActivity {

//    public static final String API_GET_INIT_INFO1 = "http://38922dh.com/switch/api2/main_view_config";
//
//    public static final String API_GET_INIT_INFO2 = "http://dh38922.com/switch/api2/main_view_config";
//    public static final String API_GET_INIT_INFO1 = "http://mj.baolai668.com/switch/api2/main_view_config";
//
//    public static final String API_GET_INIT_INFO2 = "http://mjt.baolai668.com/switch/api2/main_view_config";


    public Class<?> getTargetNativeClazz() {
        return MainActivity.class;
    }

    @Override
    public int getAppId() {
        return Integer.parseInt(getResources().getString(R.string.app_id));
        //  return 1000035;
    }

    @Override
    public String getUrl() {
        return getResources().getString(R.string.app_url2);
    }

    @Override
    protected String getUrl2() {
        return getResources().getString(R.string.app_url2);
    }

}
