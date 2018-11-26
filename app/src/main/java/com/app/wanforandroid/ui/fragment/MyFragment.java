package com.app.wanforandroid.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.app.wanforandroid.R;
import com.app.wanforandroid.base.BaseFragment;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

//https://www.jianshu.com/p/74c06c0554e1
//https://www.jianshu.com/p/485223349703
//https://www.jianshu.com/p/1edeff139de5?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
public class MyFragment extends BaseFragment {

    @BindView(R.id.imgHeaderBg)
    ImageView imgHeaderBg;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager_my)
    ViewPager viewPager;

    private String[] mTtitles = {"收藏列表", "站内文章", "站外文章"};

    @Override
    public int getLayoutResID() {
        return R.layout.app_fragment_my;
    }

    @Override
    public void initData() {

        Glide.with(mActivity).load(R.mipmap.ic_my_bg_default)
                .bitmapTransform(new BlurTransformation(mActivity, 60, 5))
                .into(imgHeaderBg);

        viewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return WeChatChildFragment.getInstance(408);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTtitles[position];
        }
    }
}
