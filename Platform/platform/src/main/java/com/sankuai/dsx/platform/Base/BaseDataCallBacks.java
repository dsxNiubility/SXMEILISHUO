package com.sankuai.dsx.platform.Base;

import android.support.annotation.UiThread;

/**
 * Created by dsx on 2016/11/30.
 */

@UiThread
public interface BaseDataCallBacks<T> {

    void doBefore();

    void onFinished(T data);
}
