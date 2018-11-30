package com.app.wanforandroid.ui.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.wanforandroid.R;
import com.app.wanforandroid.base.BaseActivity;
import com.app.wanforandroid.constant.Apis;
import com.app.wanforandroid.model.CollectBean;
import com.app.wanforandroid.ui.adapter.CollectAdapter;
import com.app.wanforandroid.util.ToastUtil;
import com.lib.http.HttpManager;
import com.lib.http.callback.StringCallback;
import com.lib.http.error.ErrorModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CollectListActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.mRefreshLayout)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerview_collect)
    RecyclerView mRecyclerView;

    private List<CollectBean.DataBean.DatasBean> collectbeans = new ArrayList<>();

    private CollectAdapter mAdapter;

    private int startPage = 0; //列表页码

    @Override
    public int getLayoutResID() {
        return R.layout.app_activity_collect;
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CollectAdapter(R.layout.app_item_home_recommend, collectbeans);
        mRecyclerView.setAdapter(mAdapter);

        requestCollectData();
    }

    @Override
    public void setListener() {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        requestCollectData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        startPage = 0;
        requestCollectData();
    }


    private void requestCollectData() {

        HttpManager.get(String.format(Apis.WAN_COLLECT_LIST, startPage))
                .tag(this)
                .build()
                .enqueue(new StringCallback<CollectBean>() {
                    @Override
                    public void onSuccess(CollectBean response, Object... obj) {
                        refreshCollectList(response);
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                        ToastUtil.showToast(errorModel.getMessage());
                    }

                    @Override
                    public void onAfter(boolean success) {
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                        hideLoading(success);
                    }
                });
    }

    /**
     * 装载RecyclerView数据
     *
     * @param response
     */
    public void refreshCollectList(CollectBean response) {
        //1、如果是第一页先清空数据 books不用做非空判断，不可能为空
        if (startPage == 0) {
            collectbeans.clear();
        }
        if (response.getData() == null) {
            ToastUtil.showToast("Cookie时效请重新登录");
            return;
        }
        //2、装载数据
        mAdapter.addData(response.getData().getDatas());
        //3、页码自增
        startPage++;
        //4、如果没有数据了，禁用加载更多功能
    }
}
