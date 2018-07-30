package com.jyt.huangguan.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jyt.huangguan.adapter.ImageFolderAdapter;
import com.jyt.huangguan.bean.LocalMedia;
import com.jyt.huangguan.bean.LocalMediaFolder;
import com.jyt.huangguan.view.viewholder.BaseViewHolder;
import com.jyt.huangguan.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by chenweiqi on 2017/1/9.
 */

public class ImgFolderFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.emptyMsg)
    TextView emptyMsg;




    OnFolderClickListener onFolderClickListener;
    ImageFolderAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_img_folder;
    }

    public void setOnFolderClickListener(OnFolderClickListener onFolderClickListener) {
        this.onFolderClickListener = onFolderClickListener;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter = new ImageFolderAdapter());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));


        adapter.setOnViewHolderClickListener(new BaseViewHolder.OnViewHolderClickListener() {
            @Override
            public void onClick(BaseViewHolder holder) {
                if (onFolderClickListener!=null){
                    onFolderClickListener.onClick(((LocalMediaFolder) holder.getData()).getImages());
                }
            }
        });
    }

    public void setDataList(List<LocalMediaFolder> folders){
        boolean haveContent = (folders!=null&&folders.size()!=0);
        emptyMsg.setVisibility(haveContent?View.GONE:View.VISIBLE);

        adapter.setDataList(folders);
        adapter.notifyDataSetChanged();
    }

    public interface OnFolderClickListener{
        void onClick(List<LocalMedia> medias);
    }
}
