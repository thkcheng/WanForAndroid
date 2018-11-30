package com.app.wanforandroid.ui.adapter;

import android.support.annotation.Nullable;

import com.app.wanforandroid.R;
import com.app.wanforandroid.model.WeChatListBean;
import com.app.wanforandroid.util.TimeUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class WeChatListAdapter extends BaseQuickAdapter<WeChatListBean.DataBean.DatasBean, BaseViewHolder> {

    public WeChatListAdapter(int layoutResId, @Nullable List<WeChatListBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeChatListBean.DataBean.DatasBean bean) {
        helper.setText(R.id.tvTitle, bean.getTitle())
                .setText(R.id.tvTime, TimeUtil.getPublishTime(bean.getPublishTime()))
                .setText(R.id.tvChapterName, bean.getChapterName())
                .setText(R.id.tvAuthor, bean.getAuthor())
                .addOnClickListener(R.id.itemView);
    }
}
