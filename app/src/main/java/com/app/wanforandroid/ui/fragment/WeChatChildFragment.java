package com.app.wanforandroid.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.wanforandroid.R;
import com.app.wanforandroid.anim.CustomAnimation;
import com.app.wanforandroid.constant.Apis;
import com.app.wanforandroid.base.BaseFragment;
import com.app.wanforandroid.model.WeChatListBean;
import com.app.wanforandroid.ui.activity.ContentDetailsActivity;
import com.app.wanforandroid.ui.adapter.WeChatListAdapter;
import com.app.wanforandroid.util.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lib.http.HttpManager;
import com.lib.http.callback.StringCallback;
import com.lib.http.error.ErrorModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;

public class WeChatChildFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.mRefreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerview_wechat)
    RecyclerView mRecyclerView;

    private List<WeChatListBean.DataBean.DatasBean> wechatbeans = new ArrayList<>();

    private WeChatListAdapter mAdapter;

    private int startPage = 1; //列表页码

    @Override
    public int getLayoutResID() {
        return R.layout.app_fragment_wechat_child;
    }

    @Override
    public void initView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new WeChatListAdapter(R.layout.app_item_home_recommend, wechatbeans);
        //mAdapter.isFirstOnly(true);
        //mAdapter.openLoadAnimation(new CustomAnimation()); //添加动画
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.itemView) {
                    Intent intent = new Intent(mActivity, ContentDetailsActivity.class);
                    intent.putExtra("title", wechatbeans.get(position).getTitle());
                    intent.putExtra("link_url", wechatbeans.get(position).getLink());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onLazyLoadingData() {
        super.onLazyLoadingData();
        requestProjectListData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        startPage = 1;
        requestProjectListData();
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        requestProjectListData();
    }

    private void requestProjectListData() {

        HttpManager.get(String.format(Apis.WAN_WECHAT_DATA_LIST, getArguments().getInt("cid"), startPage))
                .tag(this)
                .build()
                .enqueue(new StringCallback<WeChatListBean>() {
                    @Override
                    public void onSuccess(WeChatListBean response, Object... obj) {
                        refreshProjectList(response);
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
     * @param response
     */
    public void refreshProjectList(WeChatListBean response) {
        //1、如果是第一页先清空数据 books不用做非空判断，不可能为空
        if (startPage == 0) {
            wechatbeans.clear();
        }
        //2、装载数据
        mAdapter.addData(response.getData().getDatas());
        //3、页码自增
        startPage++;
        //4、如果没有数据了，禁用加载更多功能
    }

}
