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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResID(), container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //保证onCreate方法第一时间执行完，显示UI界面
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                initData();
                setListener();
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
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
     * 懒加载数据，只加载一次
     */
    public void onLazyLoadingData() {

    }
}
