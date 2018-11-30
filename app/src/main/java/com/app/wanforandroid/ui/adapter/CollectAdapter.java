package com.app.wanforandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.app.wanforandroid.R;
import com.app.wanforandroid.model.CollectBean;
import com.app.wanforandroid.model.HomeListBean;
import com.app.wanforandroid.util.TimeUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class CollectAdapter extends BaseQuickAdapter<CollectBean.DataBean.DatasBean, BaseViewHolder> {

    public CollectAdapter(int layoutResId, @Nullable List<CollectBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectBean.DataBean.DatasBean bean) {

        helper.setText(R.id.tvTitle, bean.getTitle());
        helper.setText(R.id.tvTime, TimeUtil.getPublishTime(bean.getPublishTime()));
        helper.setText(R.id.tvChapterName, bean.getChapterName());
        helper.setText(R.id.tvAuthor, bean.getAuthor());

        ImageView imgLike = helper.getView(R.id.imgLike);
        imgLike.setImageResource(R.mipmap.icon_like_press);
    }
}
