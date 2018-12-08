package com.app.wanforandroid.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.wanforandroid.base.BaseActivity;
import com.app.wanforandroid.R;
import com.app.wanforandroid.base.BaseApp;
import com.app.wanforandroid.base.BaseFragmentPagerAdapter;
import com.app.wanforandroid.constant.PreferencesName;
import com.app.wanforandroid.result.AvoidOnResult;
import com.app.wanforandroid.ui.fragment.HomeFragment;
import com.app.wanforandroid.ui.fragment.NaviFragment;
import com.app.wanforandroid.ui.fragment.ProjectFragment;
import com.app.wanforandroid.ui.fragment.SystemFragment;
import com.app.wanforandroid.ui.fragment.WeChatFragment;
import com.app.wanforandroid.util.ArgbEvaluatorUtil;
import com.app.wanforandroid.util.SPUtil;
import com.app.wanforandroid.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
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
    @BindView(R.id.rbtn_main_navi)
    RadioButton rbMainNavi;
    @BindView(R.id.radioGroup_main)
    RadioGroup radioGroupMain;

    //NavigationView的子布局
    ImageView imgUserIcon;
    LinearLayout llUserLayout;
    TextView tvUserName;

    private String[] mTitles = new String[]{"首页", "项目", "公众号", "体系", "导航"};

    private ArgbEvaluatorUtil argbEvaluatorUtil = ArgbEvaluatorUtil.get();

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean displayHomeAsUpEnabled() {
        return false;
    }

    @Override
    public void initView() {
        setTitle(mTitles[0]);

        //初始化NavigationView的Header布局
        View nvheaderView = navigationView.getHeaderView(0);
        imgUserIcon = nvheaderView.findViewById(R.id.imgUserIcon);
        llUserLayout = nvheaderView.findViewById(R.id.llUserLayout);
        tvUserName = nvheaderView.findViewById(R.id.tvUserName);

        //装载Fragment
        final List<Fragment> pages = new ArrayList<>();
        pages.add(new HomeFragment());
        pages.add(new ProjectFragment());
        pages.add(new WeChatFragment());
        pages.add(new SystemFragment());
        pages.add(new NaviFragment());
        viewpagerMain.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(), pages));
        viewpagerMain.setOffscreenPageLimit(5);

        //绑定Tab和TabDrable
        argbEvaluatorUtil.addTab(rbMainHome, rbMainProject, rbMainWeChat, rbMainSystem, rbMainNavi);
        argbEvaluatorUtil.addTabDrawable(R.mipmap.icon_home_bootom_tab,R.mipmap.icon_tixi_bootom_tab, R.mipmap.icon_tixi_bootom_tab,R.mipmap.icon_tixi_bootom_tab,R.mipmap.icon_mine_bootom_tab);

        //初始化DrawerLayout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void setListener() {

        imgUserIcon.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);

        radioGroupMain.setOnCheckedChangeListener((group, checkedId) -> {
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
                case R.id.rbtn_main_navi:
                    setTitle(mTitles[4]);
                    viewpagerMain.setCurrentItem(4, false);
                    break;
            }
        });

        viewpagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private final int DELAY_TIME = 100;
            private Handler handler = new Handler();

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                argbEvaluatorUtil.changeTabDrawable(position, positionOffset);
            }

            @Override
            public void onPageSelected(final int position) {
                handler.postDelayed(() -> {
                    setTitle(mTitles[position]);
                    argbEvaluatorUtil.setTabSelect(position);
                }, DELAY_TIME);

                argbEvaluatorUtil.setChecked(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        // 模仿RxPermissions动态申请权限的方式跳转后可回调
        new AvoidOnResult(this).startForResult(LoginActivity.class, new AvoidOnResult.Callback() {
            @Override
            public void onActivityResult(int resultCode, Intent data) {
                imgUserIcon.setImageResource(R.mipmap.icon_head_login);
                llUserLayout.setVisibility(View.VISIBLE);
                tvUserName.setText(SPUtil.getString(PreferencesName.APP_USER_NAME));
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(getActivity(), CollectListActivity.class));
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
