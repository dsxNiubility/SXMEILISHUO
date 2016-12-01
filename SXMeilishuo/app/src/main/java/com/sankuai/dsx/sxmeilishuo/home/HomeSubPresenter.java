package com.sankuai.dsx.sxmeilishuo.home;

import com.sankuai.dsx.platform.Base.BaseDataCallBacks;
import com.sankuai.dsx.sxmeilishuo.bean.BannerResponse;
import com.sankuai.dsx.sxmeilishuo.bean.JumpResponse;
import com.sankuai.dsx.sxmeilishuo.bean.ProfessionalResponse;
import com.sankuai.dsx.sxmeilishuo.bean.RootListResponse;

/**
 * Created by dsx on 2016/12/1.
 */

public class HomeSubPresenter implements HomeContract.SubPresenter {

    private HomeModel mHomeModel;
    private HomeContract.SubView mView;

    public static HomeSubPresenter presenter(HomeContract.SubView view, HomeModel model){
        HomeSubPresenter p = new HomeSubPresenter();
        p.mView = view;
        p.mHomeModel = model;
        return p;
    }

    @Override
    public void getJumpGridData() {
        mHomeModel.loadJumpGridData(new BaseDataCallBacks<JumpResponse>() {
            @Override
            public void doBefore() {}

            @Override
            public void onFinished(JumpResponse data) {
                if (mView.isFragmentDetach()){
                    return;
                }
                mView.setJumpGridData(data);
            }
        });
    }

    @Override
    public void getProfessionalGirlData() {
        mHomeModel.loadProfessionalData(new BaseDataCallBacks<ProfessionalResponse>() {
            @Override
            public void doBefore() {}

            @Override
            public void onFinished(ProfessionalResponse data) {
                if (mView.isFragmentDetach()){
                    return;
                }
                mView.setProfessionalGirlData(data);
            }
        });
    }

    @Override
    public void getMainContentData() {
        mHomeModel.loadMainContentData(new BaseDataCallBacks<RootListResponse>() {
            @Override
            public void doBefore() {}

            @Override
            public void onFinished(RootListResponse data) {
                if (mView.isFragmentDetach()){
                    return;
                }
                mView.setMainContentData(data);
            }
        });
    }

    @Override
    public void start() {
        getJumpGridData();
        getProfessionalGirlData();
        getMainContentData();
    }
}
