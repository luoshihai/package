package com.cnsunru.common.event;

/**
 * Created by WQ on 2017/9/18.
 */

public class LocationBean {
    public String city;
    public static String cityId;
    public String address;
    public static double latitude,longitude;
    public boolean hasId(){
        return cityId!=null;
    }
    public LocationBean(String city, String address) {
        this.city = city;
        this.address = address;
    }
    public LocationBean() {
    }

    @Override
    public String toString() {
        return "LocationBean{" +
                "city='" + city + '\'' +
                ", cityId='" + cityId + '\'' +
                '}';
    }
}
