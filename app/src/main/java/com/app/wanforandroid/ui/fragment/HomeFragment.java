package com.app.wanforandroid.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.app.wanforandroid.R;
import com.app.wanforandroid.anim.CustomAnimation;
import com.app.wanforandroid.api.Apis;
import com.app.wanforandroid.base.BaseFragment;
import com.app.wanforandroid.image.ImageManager;
import com.app.wanforandroid.model.HomeBannerBean;
import com.app.wanforandroid.model.HomeListBean;
import com.app.wanforandroid.ui.adapter.HomeRecommendAdapter;
import com.app.wanforandroid.util.ToastUtil;
import com.lib.http.HttpManager;
import com.lib.http.callback.StringCallback;
import com.lib.http.error.ErrorModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wikikii.bannerlib.banner.IndicatorLocation;
import com.wikikii.bannerlib.banner.LoopLayout;
import com.wikikii.bannerlib.banner.LoopStyle;
import com.wikikii.bannerlib.banner.OnDefaultImageViewLoader;
import com.wikikii.bannerlib.banner.bean.BannerInfo;
import com.wikikii.bannerlib.banner.listener.OnBannerItemClickListener;
import com.wikikii.bannerlib.banner.view.BannerBgContainer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.mRefreshLayout)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.banner_bg_container)
    BannerBgContainer bannerBgContainer;
    @BindView(R.id.looplayout_banner)
    LoopLayout loopBanner;
    @BindView(R.id.recyclerview_home)
    RecyclerView mRecyclerView;

    private List<HomeListBean.DataBean.DatasBean> homebeans = new ArrayList<>();

    private HomeRecommendAdapter mAdapter;

    private int startPage = 0; //列表页码

    @Override
    public int getLayoutResID() {
        return R.layout.app_fragment_home;
    }

    @Override
    public void initData() {
        initBannerView();
        initRecyclerItem();

        requestBanner();
        requestRecommend();
    }

    @Override
    public void setListener() {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        startPage = 0;
        requestRecommend();
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        requestRecommend();
    }

    /**
     * 初始化Banner相关设置
     */
    private void initBannerView() {
        loopBanner.setLoop_ms(5000);//轮播的速度(毫秒)
        loopBanner.setLoop_duration(500);//滑动的速率(毫秒)
        loopBanner.setScaleAnimation(true);// 设置是否需要动画
        loopBanner.setLoop_style(LoopStyle.Empty);//轮播的样式-默认empty
        loopBanner.setIndicatorLocation(IndicatorLocation.Center);//指示器位置-中Center
        loopBanner.initializeData(mActivity); //初始化创建

        loopBanner.setOnBannerItemClickListener(new OnBannerItemClickListener() {
            @Override
            public void onBannerClick(int index, ArrayList<BannerInfo> banner) {
                ToastUtil.showToast(banner.get(index).title);
            }
        });
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerItem() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setNestedScrollingEnabled(false); //解决在NestedScrollView滑动粘连的问题
        mAdapter = new HomeRecommendAdapter(R.layout.app_item_home_recommend, homebeans);
        mAdapter.isFirstOnly(true);
        mAdapter.openLoadAnimation(new CustomAnimation()); //添加动画
        mRecyclerView.setAdapter(mAdapter);
    }

    private void requestBanner() {
        HttpManager.get(Apis.WAN_HOME_BANNER)
                .tag(this)
                .build()
                .enqueue(new StringCallback<HomeBannerBean>() {
                    @Override
                    public void onSuccess(HomeBannerBean response, Object... obj) {
                        loadBannerData(response.getData());
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                        ToastUtil.showToast(errorModel.getMessage());
                    }
                });
    }

    private void requestRecommend() {
        HttpManager.get(String.format(Apis.WAN_HOME_LIST,startPage))
                .tag(this)
                .build()
                .enqueue(new StringCallback<HomeListBean>() {

                    @Override
                    public void onSuccess(HomeListBean response, Object... obj) {
                        refreshRecommendList(response);
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                        ToastUtil.showToast(errorModel.getMessage());
                    }

                    @Override
                    public void onAfter(boolean success) {
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }
                });
    }

    /**
     * 装载Banner数据
     * @param data
     */
    private void loadBannerData(List<HomeBannerBean.DataBean> data) {
        ArrayList<BannerInfo> bannerInfos = new ArrayList<>();
        List<Object> bgList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            bannerInfos.add(new BannerInfo(data.get(i).getImagePath(), data.get(i).getTitle()));
            bgList.add(data.get(i).getImagePath());
        }

        loopBanner.setOnLoadImageViewListener(new OnDefaultImageViewLoader() {
            @Override
            public void onLoadImageView(ImageView imageView, Object url) {
                ImageManager.loadImage(mActivity, imageView, String.valueOf(url));
            }
        });

        loopBanner.setLoopData(bannerInfos);
        bannerBgContainer.setBannerBackBg(mActivity, bgList);
        loopBanner.setBannerBgContainer(bannerBgContainer);
        loopBanner.startLoop();
    }

    /**
     * 装载RecyclerView数据
     * @param response
     */
    public void refreshRecommendList(HomeListBean response) {
        //1、如果是第一页先清空数据 books不用做非空判断，不可能为空
        if (startPage == 0) {
            homebeans.clear();
        }
        //2、装载数据
        mAdapter.addData(response.getData().getDatas());
        //3、页码自增
        startPage++;
        //4、如果没有数据了，禁用加载更多功能
    }
}
