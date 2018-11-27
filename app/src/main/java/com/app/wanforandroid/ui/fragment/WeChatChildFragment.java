package com.app.wanforandroid.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.wanforandroid.R;
import com.app.wanforandroid.anim.CustomAnimation;
import com.app.wanforandroid.constant.Apis;
import com.app.wanforandroid.base.BaseFragment;
import com.app.wanforandroid.model.WeChatListBean;
import com.app.wanforandroid.ui.adapter.WeChatListAdapter;
import com.app.wanforandroid.util.ToastUtil;
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

    private int cid;

    public static WeChatChildFragment getInstance(int id) {
        WeChatChildFragment cfragment = new WeChatChildFragment();
        cfragment.cid = id;
        return cfragment;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.app_fragment_wechat_child;
    }

    @Override
    public void initData() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new WeChatListAdapter(R.layout.app_item_home_recommend, wechatbeans);
        mAdapter.isFirstOnly(true);
        mAdapter.openLoadAnimation(new CustomAnimation()); //添加动画
        mRecyclerView.setAdapter(mAdapter);

        requestProjectListData();
    }

    @Override
    public void setListener() {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
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
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("cid", cid);

        HttpManager.get(String.format(Apis.WAN_WECHAT_DATA_LIST, cid, startPage))
                .tag(this)
                .params(params)
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
