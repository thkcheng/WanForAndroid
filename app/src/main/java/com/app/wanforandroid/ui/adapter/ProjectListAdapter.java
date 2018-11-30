package com.app.wanforandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.app.wanforandroid.R;
import com.app.wanforandroid.image.ImageManager;
import com.app.wanforandroid.model.ProjectListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by thkcheng on 2018/11/23.
 */

public class ProjectListAdapter extends BaseQuickAdapter<ProjectListBean.DataBean.DatasBean, BaseViewHolder> {

    public ProjectListAdapter(int layoutResId, @Nullable List<ProjectListBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectListBean.DataBean.DatasBean bean) {

        helper.setText(R.id.tvTitle, bean.getTitle())
            .setText(R.id.tvDescribe, bean.getDesc())
            .addOnClickListener(R.id.itemView);

        ImageView imgSample = helper.getView(R.id.imgSample);
        ImageManager.loadImage(mContext, imgSample, bean.getEnvelopePic());
    }
}
