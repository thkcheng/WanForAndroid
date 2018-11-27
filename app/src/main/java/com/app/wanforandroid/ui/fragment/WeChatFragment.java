package com.app.wanforandroid.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.app.wanforandroid.R;
import com.app.wanforandroid.constant.Apis;
import com.app.wanforandroid.base.BaseFragment;
import com.app.wanforandroid.model.WeChatTitleBean;
import com.flyco.tablayout.SlidingTabLayout;
import com.lib.http.HttpManager;
import com.lib.http.callback.StringCallback;
import com.lib.http.error.ErrorModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WeChatFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewpager_wechat)
    ViewPager viewPager;

    private List<WeChatTitleBean.DataBean> titleDatas = new ArrayList<>();

    private String[] mTtitles;

    @Override
    public int getLayoutResID() {
        return R.layout.app_fragment_wechat;
    }

    @Override
    public void initData() {
        requestTitleList();
    }

    private void requestTitleList() {
        HttpManager.get(Apis.WAN_WECHAT_TITLE_LIST)
                .tag(this)
                .build()
                .enqueue(new StringCallback<WeChatTitleBean>() {
                    @Override
                    public void onSuccess(WeChatTitleBean response, Object... obj) {
                        titleDatas.clear();
                        titleDatas.addAll(response.getData());

                        mTtitles = new String[titleDatas.size()];
                        for (int i = 0; i < titleDatas.size(); i++) {
                            mTtitles[i] = titleDatas.get(i).getName();
                        }
                        WeChatViewPagerAdapter mAdapter = new WeChatViewPagerAdapter(getChildFragmentManager());
                        viewPager.setAdapter(mAdapter);
                        tabLayout.setViewPager(viewPager, mTtitles);
                        viewPager.setOffscreenPageLimit(3);
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                    }
                });
    }

    private class WeChatViewPagerAdapter extends FragmentPagerAdapter {

        public WeChatViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return WeChatChildFragment.getInstance(titleDatas.get(position).getId());
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
