package com.app.wanforandroid.base;


import android.app.Application;
import android.content.Context;

import com.app.wanforandroid.common.ActivityCallback;
import com.app.wanforandroid.common.GsonConverter;
import com.app.wanforandroid.common.InterceptorImpl;
import com.lib.http.HttpManager;

public class BaseApp {

    private Context context;

    private ActivityCallback lifeCallback;

    private BaseApp() {
    }

    public static BaseApp getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private final static BaseApp INSTANCE = new BaseApp();
    }

    public static Context getContext() {
        return getInstance().context;
    }

    public void attachBaseContext(Context base) {
        context = base;
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        lifeCallback.finishAll();
    }

    public void onCreate(Application application) {

        lifeCallback = new ActivityCallback();
        application.registerActivityLifecycleCallbacks(lifeCallback);

        initOkhttp();
    }

    private void initOkhttp() {
        HttpManager.getInstance()
                .setTimeOut(30)
                .setHttpConverter(GsonConverter.create())
                .setInterceptor(new InterceptorImpl())
                .setCertificates() //无证书可以为null
                .build();

    }

}
