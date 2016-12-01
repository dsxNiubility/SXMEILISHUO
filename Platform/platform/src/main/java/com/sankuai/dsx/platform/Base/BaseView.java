package com.sankuai.dsx.platform.Base;

import android.support.annotation.NonNull;

/**
 * Created by dsx on 2016/11/30.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(@NonNull T presenter);
}