package com.sankuai.dsx.sxmeilishuo.shopping;

import android.content.Context;

import com.sankuai.dsx.platform.Base.BaseDataCallBacks;
import com.sankuai.dsx.sxmeilishuo.bean.ShoppingContentResponse;
import com.sankuai.dsx.sxmeilishuo.bean.ShoppingHeaderResponse;

/**
 * Created by dsx on 2016/11/30.
 *
 */

public class ShoppingPresenter implements ShoppingContract.Presenter {

    private ShoppingModel mShoppingModel;
    private ShoppingContract.View mView;

    public static ShoppingPresenter presenter(ShoppingContract.View view,ShoppingModel model){
        ShoppingPresenter p = new ShoppingPresenter();
        p.mView = view;
        p.mShoppingModel = model;
        return p;
    }

    @Override
    public void getTopData(Context context) {
        mShoppingModel.loadTopData(context, new BaseDataCallBacks<ShoppingHeaderResponse>() {
            @Override
            public void doBefore() {}

            @Override
            public void onFinished(ShoppingHeaderResponse data) {
                if (mView.isFragmentDetach()){
                    return;
                }
                mView.setTopData(data);
            }
        });
    }

    @Override
    public void getSubRecycleViewContentData(int position) {
        mShoppingModel.loadSubData(position, new BaseDataCallBacks<ShoppingContentResponse>() {
            @Override
            public void doBefore() {}

            @Override
            public void onFinished(ShoppingContentResponse data) {
                if (mView.isFragmentDetach()){
                    return;
                }
                mView.setSubRecycleViewContent(data);
            }
        });
    }

    @Override
    public void start() {

    }
}
