package com.app.wanforandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.app.wanforandroid.R;
import com.app.wanforandroid.model.HomeListBean;
import com.app.wanforandroid.util.TimeUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class HomeRecommendAdapter extends BaseQuickAdapter<HomeListBean.DataBean.DatasBean, BaseViewHolder> {

    public HomeRecommendAdapter(int layoutResId, @Nullable List<HomeListBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeListBean.DataBean.DatasBean bean) {

        helper.setText(R.id.tvTitle, bean.getTitle())
                .setText(R.id.tvTime, TimeUtil.getPublishTime(bean.getPublishTime()))
                .setText(R.id.tvSuperChapterName, bean.getSuperChapterName() + " / ")
                .setText(R.id.tvChapterName, bean.getChapterName())
                .setText(R.id.tvAuthor, bean.getAuthor())
                .addOnClickListener(R.id.itemView)
                .addOnClickListener(R.id.imgLike);

        ImageView imgLike = helper.getView(R.id.imgLike);
        if (bean.isCollect()) {
            imgLike.setImageResource(R.mipmap.icon_like_press);
        }else {
            imgLike.setImageResource(R.mipmap.icon_like_default);
        }
    }
}
