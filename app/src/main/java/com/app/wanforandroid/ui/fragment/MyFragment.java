package com.app.wanforandroid.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.wanforandroid.R;
import com.app.wanforandroid.anim.CustomAnimation;
import com.app.wanforandroid.constant.Apis;
import com.app.wanforandroid.base.BaseFragment;
import com.app.wanforandroid.constant.PreferencesName;
import com.app.wanforandroid.model.CollectBean;
import com.app.wanforandroid.result.AvoidOnResult;
import com.app.wanforandroid.ui.activity.LoginActivity;
import com.app.wanforandroid.ui.adapter.CollectAdapter;
import com.app.wanforandroid.util.SPUtil;
import com.app.wanforandroid.util.ToastUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lib.http.HttpManager;
import com.lib.http.callback.StringCallback;
import com.lib.http.error.ErrorModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

//https://www.jianshu.com/p/74c06c0554e1
//https://www.jianshu.com/p/485223349703
//https://www.jianshu.com/p/1edeff139de5?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
public class MyFragment extends BaseFragment {

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.head_layout)
    RelativeLayout headLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.imgHeaderIcon)
    ImageView imgHeaderIcon;

    @BindView(R.id.imgHeaderBg)
    ImageView imgHeaderBg;

    @BindView(R.id.tvUserName)
    TextView tvUserName;

    @BindView(R.id.recyclerview_my)
    RecyclerView mRecyclerView;

    private List<CollectBean.DataBean.DatasBean> collectbeans = new ArrayList<>();

    private CollectAdapter mAdapter;

    private int startPage = 0; //列表页码

    @Override
    public int getLayoutResID() {
        return R.layout.app_fragment_my;
    }

    @Override
    public void initView() {
        //把title设置到CollapsingToolbarLayout上
        setTitleToCollapsingToolbarLayout();

        //给RecyclerView设置适配器
        initRecyclerAdapter();

        //设置毛玻璃效果和沉浸状态栏
        loadBlurAndSetStatusBar();
    }

    @Override
    public void onLazyLoadingData() {
        super.onLazyLoadingData();

        requestCollectData();
    }

    @Override
    public void setListener() {
        imgHeaderIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 模仿RxPermissions动态申请权限的方式跳转后可回调
                new AvoidOnResult(mActivity).startForResult(LoginActivity.class, new AvoidOnResult.Callback() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {
                        loginShowCollectList();
                    }
                });
            }
        });
    }

    /**
     * 1. 动态设置ToolBar滑动透明度
     * 2. 使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，
     * 设置到Toolbar上则不会显示
     */
    private void setTitleToCollapsingToolbarLayout() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                mToolbar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.colorPrimary), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));

                if (verticalOffset <= -headLayout.getHeight() / 2) {
                    collapsingToolbarLayout.setTitle("用户中心");
                    //使用下面两个CollapsingToolbarLayout的方法设置展开透明->折叠时你想要的颜色
                    collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
                } else {
                    collapsingToolbarLayout.setTitle("");
                }
            }
        });
    }


    /**
     * 根据百分比改变颜色透明度
     */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }


    /**
     * 初始化RecyclerView
     */
    private void initRecyclerAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setNestedScrollingEnabled(false); //解决在NestedScrollView滑动粘连的问题
        mAdapter = new CollectAdapter(R.layout.app_item_home_recommend, collectbeans);
        //mAdapter.isFirstOnly(true);
        //mAdapter.openLoadAnimation(new CustomAnimation()); //添加动画
        mRecyclerView.setAdapter(mAdapter);
    }


    /**
     * 设置毛玻璃效果和沉浸状态栏
     */
    private void loadBlurAndSetStatusBar() {

        Glide.with(mActivity).load(R.mipmap.ic_my_bg_default)
                .bitmapTransform(new BlurTransformation(mActivity, 100, 5))
                .into(imgHeaderBg);

        Glide.with(this).load(R.mipmap.ic_my_bg_default).bitmapTransform(new BlurTransformation(mActivity, 80, 5))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imgHeaderBg.setBackground(resource);
                        collapsingToolbarLayout.setContentScrim(resource);
                    }
                });
    }


    private void requestCollectData() {

        HttpManager.get(String.format(Apis.WAN_COLLECT_LIST, startPage))
                .tag(this)
                .build()
                .enqueue(new StringCallback<CollectBean>() {
                    @Override
                    public void onSuccess(CollectBean response, Object... obj) {
                        refreshCollectList(response);
                        loginShowCollectList();
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                        ToastUtil.showToast(errorModel.getMessage());
                    }
                });
    }


    /**
     * 登录后显示用户数据
     */
    private void loginShowCollectList() {
        imgHeaderIcon.setImageResource(R.mipmap.icon_head_login);
        tvUserName.setVisibility(View.VISIBLE);
        tvUserName.setText(SPUtil.getString(PreferencesName.APP_USER_NAME));
    }


    /**
     * 装载RecyclerView数据
     *
     * @param response
     */
    public void refreshCollectList(CollectBean response) {
        //1、如果是第一页先清空数据 books不用做非空判断，不可能为空
        if (startPage == 0) {
            collectbeans.clear();
        }
        if (response.getData() == null) {
            ToastUtil.showToast("Cookie时效请重新登录");
            return;
        }
        //2、装载数据
        mAdapter.addData(response.getData().getDatas());
        //3、页码自增
        startPage++;
        //4、如果没有数据了，禁用加载更多功能
    }

}

