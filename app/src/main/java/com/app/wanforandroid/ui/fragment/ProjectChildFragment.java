package com.app.wanforandroid.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.wanforandroid.R;
import com.app.wanforandroid.anim.CustomAnimation;
import com.app.wanforandroid.constant.Apis;
import com.app.wanforandroid.base.BaseFragment;
import com.app.wanforandroid.model.ProjectListBean;
import com.app.wanforandroid.ui.activity.ContentDetailsActivity;
import com.app.wanforandroid.ui.adapter.ProjectListAdapter;
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

public class ProjectChildFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.mRefreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerview_project)
    RecyclerView mRecyclerView;

    private List<ProjectListBean.DataBean.DatasBean> projectbeans = new ArrayList<>();

    private ProjectListAdapter mAdapter;

    private int startPage = 1; //列表页码

    @Override
    public int getLayoutResID() {
        return R.layout.app_fragment_project_child;
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ProjectListAdapter(R.layout.app_item_project_list, projectbeans);
        //mAdapter.isFirstOnly(true);
        //mAdapter.openLoadAnimation(new CustomAnimation()); //添加动画
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);

        mAdapter.setOnItemChildClickListener((adapter,view,position) -> {
            if (view.getId() == R.id.itemView) {
                Intent intent = new Intent(mActivity, ContentDetailsActivity.class);
                intent.putExtra("title", projectbeans.get(position).getTitle());
                intent.putExtra("link_url", projectbeans.get(position).getLink());
                startActivity(intent);
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
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("cid", getArguments().getInt("cid"));

        HttpManager.get(String.format(Apis.WAN_PROJECT_DATA_LIST, startPage))
                .tag(this)
                .params(params)
                .build()
                .enqueue(new StringCallback<ProjectListBean>() {
                    @Override
                    public void onSuccess(ProjectListBean response, Object... obj) {
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
    public void refreshProjectList(ProjectListBean response) {
        //1、如果是第一页先清空数据 books不用做非空判断，不可能为空
        if (startPage == 0) {
            projectbeans.clear();
        }
        //2、装载数据
        mAdapter.addData(response.getData().getDatas());
        //3、页码自增
        startPage++;
        //4、如果没有数据了，禁用加载更多功能
    }

}
