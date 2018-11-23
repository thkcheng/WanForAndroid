package com.app.wanforandroid.base;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.app.wanforandroid.R;
import com.lib.http.HttpManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseUI {

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResID = getLayoutResID();
        if (layoutResID != 0) {
            setContentView(layoutResID);
        }
        ButterKnife.bind(this);//必须在setContentView()之后调用

        initToolBar();

        //保证onCreate方法第一时间执行完，显示UI界面
        //如果加载数据的方法直接在onCreate里执行，可能会导致UI界面不能及时显示
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initData();
                setListener();
            }
        });
    }

    /**
     * 加载数据，如请求网络，读取本地缓存等
     */
    public void loadData() {

    }

    /**
     * 绑定监听
     */
    public void setListener() {

    }

    /**
     * 初始化ToolBar
     */
    private void initToolBar() {
        setSupportActionBar(toolbar);
        setTitle("");//标题内容
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            Drawable drawable = getToolbarBackground();
            if (drawable != null) {
                ab.setBackgroundDrawable(drawable);
            }
            ab.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled());//显示返回键
        }
    }

    /**
     * Toolbar背景
     *
     * @return 背景
     */
    protected Drawable getToolbarBackground() {
        return null;
    }

    /**
     * 显示返回键
     *
     * @return true为显示左上角返回键，反之为false
     */
    protected boolean displayHomeAsUpEnabled() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//Toolbar返回键
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        //如果关闭页面，取消请求
        HttpManager.cancelTag(this);

        super.onDestroy();
    }

    /**
     * 获取当前Activity
     *
     * @return 当前Activity
     */
    protected Activity getActivity() {
        return this;
    }
}
