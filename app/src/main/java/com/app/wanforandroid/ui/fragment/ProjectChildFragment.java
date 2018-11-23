package com.app.wanforandroid.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.wanforandroid.R;
import com.app.wanforandroid.anim.CustomAnimation;
import com.app.wanforandroid.api.Apis;
import com.app.wanforandroid.base.BaseFragment;
import com.app.wanforandroid.model.ProjectListBean;
import com.app.wanforandroid.ui.adapter.ProjectListAdapter;
import com.lib.http.HttpManager;
import com.lib.http.callback.StringCallback;
import com.lib.http.error.ErrorModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;

public class ProjectChildFragment extends BaseFragment {

    @BindView(R.id.recyclerview_project)
    RecyclerView mRecyclerView;

    private List<ProjectListBean.DataBean.DatasBean> projectbeans = new ArrayList<>();

    private ProjectListAdapter mAdapter;

    private int startPage = 0; //列表页码

    private int cid;

    public static ProjectChildFragment getInstance(int id) {
        ProjectChildFragment cfragment = new ProjectChildFragment();
        cfragment.cid = id;
        return cfragment;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.app_fragment_project_child;
    }

    @Override
    public void initData() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ProjectListAdapter(R.layout.app_item_project_list, projectbeans);
        mAdapter.isFirstOnly(true);
        mAdapter.openLoadAnimation(new CustomAnimation()); //添加动画
        mRecyclerView.setAdapter(mAdapter);

        requestProjectListData();
    }

    private void requestProjectListData() {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("cid", cid);

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
