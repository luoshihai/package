package com.jyt.huangguan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author LinWei on 2017/11/14 18:24
 */
public class SearchBean implements Serializable,Parcelable {
    private String projectId;
    private String brandName;
    private String subClassName;
    private String projectName;
    private String longitude;
    private String latitude;
    private String city;
    private String area;
    private String address;
    private String schedule;
    private String time;
    private String isfrozen;

    public SearchBean(){}

    public SearchBean(Parcel in) {
        projectId = in.readString();
        brandName = in.readString();
        subClassName = in.readString();
        projectName = in.readString();
        longitude = in.readString();
        latitude = in.readString();
        city = in.readString();
        area = in.readString();
        address = in.readString();
        schedule = in.readString();
        time = in.readString();
        isfrozen =in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(projectId);
        dest.writeString(brandName);
        dest.writeString(subClassName);
        dest.writeString(projectName);
        dest.writeString(longitude);
        dest.writeString(latitude);
        dest.writeString(city);
        dest.writeString(area);
        dest.writeString(address);
        dest.writeString(schedule);
        dest.writeString(time);
        dest.writeString(isfrozen);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SearchBean> CREATOR = new Creator<SearchBean>() {
        @Override
        public SearchBean createFromParcel(Parcel in) {
            return new SearchBean(in);
        }

        @Override
        public SearchBean[] newArray(int size) {
            return new SearchBean[size];
        }
    };

    public String getProjectId() {
        return projectId;
    }



    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSubClassName() {
        return subClassName;
    }

    public void setSubClassName(String subClassName) {
        this.subClassName = subClassName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIsfrozen() {
        return isfrozen;
    }

    public void setIsfrozen(String isfrozen) {
        this.isfrozen = isfrozen;
    }
}
