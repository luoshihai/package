package com.cnsunru.home.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.home.mode.CityMode;
import com.cnsunru.home.mode.SideBarSortMode;

/**
 * @author WQ
 * 城市列表适配器
 */
public class CityListAdapter extends BaseQuickAdapter<CityMode,BaseViewHolder> {
    private SideBarSortMode sideBarSortMode;
    public CityListAdapter(SideBarSortMode sideBarSortMode) {
        super(R.layout.item_city);
        this.sideBarSortMode=sideBarSortMode;
    }


    @Override
    protected void convert(BaseViewHolder helper, CityMode item) {
        String sortLetterTitle = sideBarSortMode.getSortLetterTitle(helper.getLayoutPosition());
        helper.setText(R.id.txtCityInitial,sortLetterTitle);
        helper.setText(R.id.txtCityName,item.toString());
        helper.setVisible(R.id.txtCityInitial,sortLetterTitle!=null);
    }
}
