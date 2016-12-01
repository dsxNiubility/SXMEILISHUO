package com.sankuai.dsx.platform.Base;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by dsx on 2016/12/1.
 */

public abstract class BaseAsyncTaskLoader<D> extends AsyncTaskLoader<D> {
    public BaseAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }
}
