package com.sankuai.dsx.platform.Base;

/**
 * Created by dsx on 2016/11/30.
 */

public interface BaseFragmentView<T extends BasePresenter> extends BaseView<T> {
    boolean isFragmentDetach();
}