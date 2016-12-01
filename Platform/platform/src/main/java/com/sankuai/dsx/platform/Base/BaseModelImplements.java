package com.sankuai.dsx.platform.Base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;

/**
 * Created by dsx on 2016/11/30.
 */

public class BaseModelImplements implements BaseModel {
    protected final LoaderManager manager;

    public BaseModelImplements(LoaderManager manager) {
        this.manager = manager;
    }

    public <T> void startLoader(@NonNull LoaderManager.LoaderCallbacks<T> callbacks) {
        manager.restartLoader(callbacks.hashCode(), null, callbacks);
    }

    public <T> void startLoader(@NonNull Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<T> callbacks) {
        manager.restartLoader(callbacks.hashCode(), bundle, callbacks);
    }

    public void destroyLoader(LoaderManager.LoaderCallbacks loaderCallbacks) {
        manager.destroyLoader(loaderCallbacks.hashCode());
    }
}
