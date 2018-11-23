package com.app.wanforandroid.ui.activity;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.wanforandroid.base.BaseActivity;
import com.app.wanforandroid.R;
import com.app.wanforandroid.base.BaseApp;
import com.app.wanforandroid.base.BaseFragmentPagerAdapter;
import com.app.wanforandroid.ui.fragment.HomeFragment;
import com.app.wanforandroid.ui.fragment.MyFragment;
import com.app.wanforandroid.ui.fragment.ProjectFragment;
import com.app.wanforandroid.ui.fragment.SystemFragment;
import com.app.wanforandroid.ui.fragment.WeChatFragment;
import com.app.wanforandroid.util.ArgbEvaluatorUtil;
import com.app.wanforandroid.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager_main)
    ViewPager viewpagerMain;
    @BindView(R.id.rbtn_main_home)
    RadioButton rbMainHome;
    @BindView(R.id.rbtn_main_project)
    RadioButton rbMainProject;
    @BindView(R.id.rbtn_main_wechat)
    RadioButton rbMainWeChat;
    @BindView(R.id.rbtn_main_system)
    RadioButton rbMainSystem;
    @BindView(R.id.rbtn_main_my)
    RadioButton rbMainMy;
    @BindView(R.id.radioGroup_main)
    RadioGroup radioGroupMain;

    private String[] mTitles = new String[]{"首页", "项目", "公众号", "体系", "我的"};

    private ArgbEvaluatorUtil argbEvaluatorUtil = ArgbEvaluatorUtil.get();

    @Override
    public int getLayoutResID() {
        return R.layout.app_activity_main;
    }

    @Override
    protected boolean displayHomeAsUpEnabled() {
        return false;
    }

    @Override
    public void initData() {
        setTitle(mTitles[0]);

        List<Fragment> pages = new ArrayList<>();
        pages.add(new HomeFragment());
        pages.add(new ProjectFragment());
        pages.add(new WeChatFragment());
        pages.add(new SystemFragment());
        pages.add(new MyFragment());
        viewpagerMain.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(), pages));
        viewpagerMain.setOffscreenPageLimit(2);

        argbEvaluatorUtil.addTab(rbMainHome, rbMainProject, rbMainWeChat, rbMainSystem, rbMainMy);
    }

    @Override
    public void setListener() {
        radioGroupMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_main_home:
                        setTitle(mTitles[0]);
                        viewpagerMain.setCurrentItem(0, false);
                        break;
                    case R.id.rbtn_main_project:
                        setTitle(mTitles[1]);
                        viewpagerMain.setCurrentItem(1, false);
                        break;
                    case R.id.rbtn_main_wechat:
                        setTitle(mTitles[2]);
                        viewpagerMain.setCurrentItem(2, false);
                        break;
                    case R.id.rbtn_main_system:
                        setTitle(mTitles[3]);
                        viewpagerMain.setCurrentItem(3, false);
                        break;
                    case R.id.rbtn_main_my:
                        setTitle(mTitles[4]);
                        viewpagerMain.setCurrentItem(4, false);
                        break;
                }
            }
        });


        viewpagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private final int DELAY_TIME = 100;
            private Handler handler = new Handler();

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //argbEvaluatorUtil.changeTabDrawable(position, positionOffset);
            }

            @Override
            public void onPageSelected(final int position) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setTitle(mTitles[position]);
                        //argbEvaluatorUtil.setTabSelect(position);
                    }
                }, DELAY_TIME);
                //argbEvaluatorUtil.setChecked(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 退出时间
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastUtil.showToast("再按一次退出应用");
                exitTime = System.currentTimeMillis();
            } else {
                BaseApp.getInstance().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
