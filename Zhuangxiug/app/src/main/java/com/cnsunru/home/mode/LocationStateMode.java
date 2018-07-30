package com.cnsunru.home.mode;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.cnsunru.common.event.LocationBean;
import com.cnsunru.common.intent.IntentPoxy;
import com.cnsunru.common.intent.StartIntent;
import com.cnsunru.common.util.LocationUtil;
import com.sunrun.sunrunframwork.uiutils.TextColorUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 位置信息处理模块
 * Created by WQ on 2017/9/13.
 */

public class LocationStateMode {
    private TextView locationText;
    public static LocationBean locationBean;
    protected StartIntent startIntent = IntentPoxy.getProxyInstance();
    private String showTxtFormat = "";

    /**
     * 使用默认格式 所在城市: %s
     */
    public LocationStateMode(final Activity activity, final TextView locationText) {
      this(activity, locationText,"所在城市: %s");
    }

    /**
     *
     * @param locationText 位置展示文本控件
     * @param showTxtFormat 位置展示格式
     */
    public LocationStateMode(final Activity activity, final TextView locationText, String showTxtFormat) {
        this.locationText = locationText;
        this.showTxtFormat = showTxtFormat;
        locationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent.startCitySelectActivity(activity);
            }
        });
        EventBus.getDefault().register(this);
        if (locationBean == null) {
            LocationUtil locationUtil = new LocationUtil(activity);
            locationUtil.initOnceLocation(new LocationUtil.LocationChange() {
                @Override
                public void locationChanged(AMapLocation amapLocation) {
                    if (amapLocation != null) {
                        LocationBean locationBean = new LocationBean();
                        locationBean.city = amapLocation.getCity();
                        locationBean.latitude = amapLocation.getLatitude();
                        locationBean.longitude = amapLocation.getLongitude();
                        EventBus.getDefault().post(locationBean);
                    }
                    super.locationChanged(amapLocation);
                }
            });

            locationUtil.getLocation(false);
        } else {
            EventBus.getDefault().post(locationBean);
        }
    }

    /**
     * 禁用点击,不显示定位图标和右边箭头
     */
    public void disenableClick() {
        if (locationText != null) {
            locationText.setOnClickListener(null);
            TextColorUtils.setCompoundDrawables(locationText, 0, 0, 0, 0);
        }
    }

    /**
     * 禁用位置模块,不显示
     */
    public void disenableAll() {
        if (locationText != null) {
            locationText.setVisibility(View.GONE);
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe
    public void eventBusMethod(LocationBean locationBean) {
        LocationStateMode.locationBean = locationBean;
        if (locationText != null) {
            locationText.setText(String.format(showTxtFormat, locationBean.city));
        }
    }

    /**
     * 获取位置信息
     *
     * @return
     */
    public LocationBean getLocationBean() {
        return locationBean;
    }
}
