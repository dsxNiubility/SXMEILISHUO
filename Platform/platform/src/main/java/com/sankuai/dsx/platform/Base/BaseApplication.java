package com.sankuai.dsx.platform.Base;

import android.app.Application;

/**
 * Created by dsx on 2016/12/1.
 */

public class BaseApplication extends Application {

    protected static BaseApplication mAppContext;

    public static BaseApplication application() {
        return mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
    }
}
