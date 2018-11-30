package com.app.wanforandroid.ui.activity;

import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.app.wanforandroid.R;
import com.app.wanforandroid.base.BaseActivity;
import com.app.wanforandroid.util.ToastUtil;
import com.app.wanforandroid.widget.CoolIndicatorLayout;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;

/**
 * 内容详情Act
 */
public class ContentDetailsActivity extends BaseActivity {

    @BindView(R.id.contentlayout)
    LinearLayout contentlayout;

    AgentWeb mAgentWeb;

    @Override
    public int getLayoutResID() {
        return R.layout.app_activity_details;
    }

    @Override
    public void initView() {
        setTitle(getIntent().getStringExtra("title"));

        CoolIndicatorLayout mCoolIndicatorLayout = new CoolIndicatorLayout(this);
        mAgentWeb = AgentWeb.with(this)
                //传入AgentWeb 的父控件
                .setAgentWebParent(contentlayout, new LinearLayout.LayoutParams(-1, -1))
                //自定义进度条
                .setCustomIndicator(mCoolIndicatorLayout)
                .interceptUnkownUrl()
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .createAgentWeb()
                .ready()
                .go(getIntent().getStringExtra("link_url"));
    }

    @Override
    protected boolean displayHomeAsUpEnabled() {
        return true;
    }
}
