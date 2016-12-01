package com.sankuai.dsx.platform.Base;

import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

/**
 * Created by dsx on 2016/11/30.
 */

public abstract class BaseLoaderCallBacks<D> implements LoaderManager.LoaderCallbacks<D> {

    // TODO 异常处理以后再封装一层

    @WorkerThread
    private D loadDataInBackground(Bundle args){
        return loadData(args);
    };

    protected abstract D loadData(Bundle args);

    /**
     * UI操作
     */
    @UiThread
    protected void doBefore() {

    }

    @Override
    public Loader<D> onCreateLoader(int id, final Bundle args) {
        //这里给他实现了 子类就省事了。
        doBefore();
        return new BaseAsyncTaskLoader<D>(BaseApplication.application()) {
            @Override
            public D loadInBackground() {
                return loadDataInBackground(args);
            }
        };
    }

    @Override
    public void onLoaderReset(Loader<D> loader) {
        //这里给他实现了 子类就省事了。
    }
}
