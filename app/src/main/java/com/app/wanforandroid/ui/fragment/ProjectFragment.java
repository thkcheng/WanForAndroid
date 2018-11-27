package com.app.wanforandroid.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.app.wanforandroid.R;
import com.app.wanforandroid.constant.Apis;
import com.app.wanforandroid.base.BaseFragment;
import com.app.wanforandroid.model.ProjectTitleBean;
import com.flyco.tablayout.SlidingTabLayout;
import com.lib.http.HttpManager;
import com.lib.http.callback.StringCallback;
import com.lib.http.error.ErrorModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//https://blog.csdn.net/lupengfei1009/article/details/51746849
public class ProjectFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewpager_project)
    ViewPager viewPager;

    private List<ProjectTitleBean.DataBean> titleDatas = new ArrayList<>();

    private String[] mTtitles;

    @Override
    public int getLayoutResID() {
        return R.layout.app_fragment_project;
    }

    @Override
    public void initData() {
        requestTitleList();
    }

    private void requestTitleList() {
        HttpManager.get(Apis.WAN_PROJECT_TITLE_LIST)
                .tag(this)
                .build()
                .enqueue(new StringCallback<ProjectTitleBean>() {
                    @Override
                    public void onSuccess(ProjectTitleBean response, Object... obj) {
                        titleDatas.clear();
                        titleDatas.addAll(response.getData());

                        mTtitles = new String[titleDatas.size()];
                        for (int i = 0; i < titleDatas.size(); i++) {
                            mTtitles[i] = titleDatas.get(i).getName();
                        }
                        ProjectViewPagerAdapter mAdapter = new ProjectViewPagerAdapter(getChildFragmentManager());
                        viewPager.setAdapter(mAdapter);
                        tabLayout.setViewPager(viewPager, mTtitles);
                        viewPager.setOffscreenPageLimit(3);
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                    }
                });
    }

    private class ProjectViewPagerAdapter extends FragmentPagerAdapter {

        public ProjectViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ProjectChildFragment.getInstance(titleDatas.get(position).getId());
        }

        @Override
        public int getCount() {
            return titleDatas.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleDatas.get(position).getName();
        }
    }
}
