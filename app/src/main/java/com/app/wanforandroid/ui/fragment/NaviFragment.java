package com.app.wanforandroid.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.RelativeLayout;

import com.app.wanforandroid.R;
import com.app.wanforandroid.base.BaseFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

//https://www.jianshu.com/p/74c06c0554e1
//https://www.jianshu.com/p/485223349703
//https://www.jianshu.com/p/1edeff139de5?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
public class NaviFragment extends BaseFragment {

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.head_layout)
    RelativeLayout headLayout;

    @Override
    public int getLayoutResID() {
        return R.layout.app_fragment_navi;
    }

    @Override
    public void initView() {
        //把title设置到CollapsingToolbarLayout上
        setTitleToCollapsingToolbarLayout();

        //设置毛玻璃效果和沉浸状态栏
        loadBlurAndSetStatusBar();
    }


    @Override
    public void setListener() {
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

                //mToolbar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.colorPrimary), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));

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
     * 设置毛玻璃效果和沉浸状态栏
     */
    private void loadBlurAndSetStatusBar() {
        Glide.with(this).load(R.mipmap.ic_my_bg_default).bitmapTransform(new BlurTransformation(mActivity, 80, 5))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        collapsingToolbarLayout.setContentScrim(resource);
                    }
                });
    }

}

