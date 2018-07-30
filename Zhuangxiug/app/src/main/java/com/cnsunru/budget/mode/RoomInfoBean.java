package com.cnsunru.budget.mode;

import android.text.InputType;

import java.util.List;

/**
 * Created by WQ on 2017/9/25.
 */

public class RoomInfoBean {



    /**
     * length : 4.91
     * width : 3.69
     * high : 2.80
     * acreage : 4.91
     * perimeter : 3.69
     */

    public HouseInfoBean house_info;
    /**
     * id : 1
     * title : 港多 水晶吊灯客厅灯欧式灯具卧室灯锌合金云石酒店饭厅灯餐厅灯别墅 单层6头+送全套LED灯泡
     * width : 0.0
     * length : 0.0
     * product_id : 3
     */

    public List<DoorInfoBean> door_info;
    /**
     * id : 1
     * title : 港多 水晶吊灯客厅灯欧式灯具卧室灯锌合金云石酒店饭厅灯餐厅灯别墅 单层6头+送全套LED灯泡
     * width : 0.0
     * length : 0.0
     * product_id : 3
     */

    public List<DoorInfoBean> window_info;
    /**
     * is_dsiplay : 0
     * status : 0
     */

    public GanshiBean ganshi;


    public static class HouseItemBean{
        public String title;
        public String value;
        public String unit;
        public String hint;
        public int inputType= InputType.TYPE_CLASS_TEXT;
        public HouseItemBean() {
        }

        /**
         *
         * @param title lab文字
         * @param value 默认值
         * @param unit  单位
         * @param hint  输入框文字提示
         */
        public HouseItemBean(String title, String value,String unit,String hint) {
            this.title = title;
            this.value = value;
            this.unit=unit;
            this.hint=hint;
        }

        public HouseItemBean inputType(int inputType){
            this.inputType=inputType;
            return this;
        }
    }
    public static class HouseInfoBean {
        public String id;
        public String length;
        public String width;
        public String high;
        public String acreage;
        public String perimeter;
        public String name;
        public String title;

        public String typeValue;
    }
    public static class DoorItemBean{
        public String title;
        public String operaName;
        public List<DoorInfoBean> listInfos;
        public int type=0;//0 门,1 窗
        public DoorItemBean(String title, String operaName, List<DoorInfoBean> listInfos,int type) {
            this.title = title;
            this.operaName = operaName;
            this.listInfos = listInfos;
            this.type=type;
        }
    }
    public static class DoorInfoBean {
        public String id;
        public String title;
        public String width;
        public String length;
        public String product_id;
    }

    public static class GanshiBean {
        public int is_dsiplay;
        public int status;

        public boolean isShowGanShi(){
            return  is_dsiplay==1;
        }
        public boolean isOpen(){
            return  status==1;
        }
    }
}
