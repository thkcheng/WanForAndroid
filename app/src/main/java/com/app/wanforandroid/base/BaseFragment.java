package com.app.wanforandroid.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.wanforandroid.R;
import com.app.wanforandroid.widget.LoadingDataLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment implements IBaseUI {

    protected final String TAG = getClass().getSimpleName();

    /**
     * 使用mActivity代替getActivity()，保证Fragment即使在onDetach后，仍持有Activity的引用<p>
     * 有引起内存泄露的风险，但相比空指针应用闪退，这种做法更“安全”
     */
    protected Activity mActivity;

    /**
     * 网络请求各种状态显示容器
     * <p>Required view 'view_loading_container' with ID 2131427348 for field 'mLoadingDataLayout' was not found. If this view is optional add '@Nullable' (fields) or '@Optional' (methods) annotation.
     */
    @Nullable
    @BindView(R.id.view_loading_container)
    protected LoadingDataLayout mLoadingDataLayout;

    /**
     * 是否允许懒加载
     */
    private boolean allowLazyLoading = true;
    /**
     * Fragment视图是否已初始化完成
     */
    private boolean isViewCreated = false;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isViewCreated = true;
        View view = inflater.inflate(getLayoutResID(), container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLoadingDataLayout();

        //保证onCreate方法第一时间执行完，显示UI界面
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                initView();
                setListener();
                //Fragment初始化时setUserVisibleHint方法会先于onCreateView执行
                prepareLazyLoading(getUserVisibleHint(), isViewCreated);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        prepareLazyLoading(isVisibleToUser, isViewCreated);
    }


    /**
     * 预备懒加载
     *
     * @param isVisibleToUser Fragment用户可见
     * @param isViewCreated   Fragment视图已初始化完成
     */
    private void prepareLazyLoading(boolean isVisibleToUser, boolean isViewCreated) {
        if (!allowLazyLoading) return;

        if (isVisibleToUser && isViewCreated) {
            allowLazyLoading = false;//保证onLazyLoadingData（）只调用一次
            onLazyLoadingData();
        }
    }

    /**
     * 懒加载数据，只加载一次
     */
    public void onLazyLoadingData() {

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

    private void initLoadingDataLayout() {
        if (mLoadingDataLayout != null) {
            showLoading();
            mLoadingDataLayout.setRetryListener(new LoadingDataLayout.OnRetryListener() {
                @Override
                public void onRetry() {
                    loadData();
                }
            });
        }
    }

    public void showLoading() {
        showLoadingStatus(LoadingDataLayout.STATUS_LOADING);
    }

    public void hideLoading(boolean success) {
        if (success) {
            showLoadingStatus(LoadingDataLayout.STATUS_SUCCESS);
        } else {
            showLoadingStatus(LoadingDataLayout.STATUS_ERROR);
        }
    }

    /**
     * 展示网络请求各种状态
     *
     * @param status 网络请求状态
     */
    protected void showLoadingStatus(int status) {
        if (mLoadingDataLayout != null && !mLoadingDataLayout.isSuccess()) {
            mLoadingDataLayout.setStatus(status);
        }
    }

}
