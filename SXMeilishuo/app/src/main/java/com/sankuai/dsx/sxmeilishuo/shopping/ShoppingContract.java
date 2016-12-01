package com.sankuai.dsx.sxmeilishuo.shopping;

import android.content.Context;

import com.sankuai.dsx.platform.Base.BaseFragmentView;
import com.sankuai.dsx.platform.Base.BasePresenter;
import com.sankuai.dsx.sxmeilishuo.bean.ShoppingContentResponse;
import com.sankuai.dsx.sxmeilishuo.bean.ShoppingHeaderResponse;

/**
 * Created by dsx on 2016/11/30.
 */

public interface ShoppingContract {

    interface View extends BaseFragmentView<Presenter> {
        /**
         * 上部页面数据填充
         * @param data 顶部多种数据
         */
        void setTopData(ShoppingHeaderResponse data);


        /**
         * 下方子视图数据填充
         * @param data 下方子数据
         */
        void setSubRecycleViewContent(ShoppingContentResponse data);
    }

    interface Presenter extends BasePresenter {

        /**
         * 获取顶部数据
         */
        void getTopData(Context context);

        /**
         * 获取下方子页面数据
         * @param position 坐标
         */
        void getSubRecycleViewContentData(int position);
    }
}
