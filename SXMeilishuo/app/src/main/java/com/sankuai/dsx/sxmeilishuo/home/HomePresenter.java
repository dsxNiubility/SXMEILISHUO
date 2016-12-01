package com.sankuai.dsx.sxmeilishuo.home;

import com.sankuai.dsx.platform.Base.BaseDataCallBacks;
import com.sankuai.dsx.sxmeilishuo.bean.BannerResponse;

/**
 * Created by dsx on 2016/12/1.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeModel mHomeModel;
    private HomeContract.View mView;

    public static HomePresenter presenter(HomeContract.View view, HomeModel model){
        HomePresenter p = new HomePresenter();
        p.mView = view;
        p.mHomeModel = model;
        return p;
    }

    @Override
    public void getBannerData() {
        mHomeModel.loadBannerData(new BaseDataCallBacks<BannerResponse>() {
            @Override
            public void doBefore() {}

            @Override
            public void onFinished(BannerResponse data) {
                if (mView.isFragmentDetach()){
                    return;
                }
                mView.setBannerData(data);
            }
        });
    }

    @Override
    public void start() {

    }
}
