package com.jyt.huangguan.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.jyt.huangguan.adapter.ImageThumbAdapter;
import com.jyt.huangguan.bean.LocalMedia;
import com.jyt.huangguan.helper.IntentHelper;
import com.jyt.huangguan.view.viewholder.BaseViewHolder;
import com.jyt.huangguan.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by chenweiqi on 2017/1/9.
 */

public class ImgListFragment extends BaseFragment {

    ImageThumbAdapter adapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<LocalMedia> localMedias;
    List<String> selPath;

    public void setOnImageCheckChanged(OnImageCheckChanged onImageCheckChanged) {
        this.onImageCheckChanged = onImageCheckChanged;
    }

    OnImageCheckChanged onImageCheckChanged;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_image_list;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter = new ImageThumbAdapter());
        adapter.setOnBrowseImageClickListener(new BaseViewHolder.OnViewHolderClickListener() {
            @Override
            public void onClick(BaseViewHolder holder) {
                IntentHelper.openBrowseImagesActivity(getContext(),((LocalMedia) holder.getData()).getPath());
            }
        });
        adapter.setOnCheckImageClickListener(new BaseViewHolder.OnViewHolderClickListener() {
            @Override
            public void onClick(BaseViewHolder holder) {
                LocalMedia media = (LocalMedia) holder.getData();
                if (onImageCheckChanged!=null){
                    onImageCheckChanged.onChanged(media.isChecked(),media.getPath());
                }
            }
        });
    }

    public void setData(List<LocalMedia> localMedias,List<String> selPath){
        this.localMedias = localMedias;
        this.selPath = selPath;
        for (int i=0;i<selPath.size();i++){
            for (int j = 0;j<localMedias.size();j++){
                if (selPath.get(i).equals(localMedias.get(j).getPath())){
                    localMedias.get(j).setChecked(true);
                }
            }
        }

        adapter.setDataList(localMedias);
        adapter.notifyDataSetChanged();

    }

    public interface OnImageCheckChanged{
        void onChanged(boolean checked,String path);
    }
}
