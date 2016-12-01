package com.sankuai.dsx.sxmeilishuo.home;

import com.sankuai.dsx.platform.Base.BaseFragmentView;
import com.sankuai.dsx.platform.Base.BasePresenter;
import com.sankuai.dsx.sxmeilishuo.bean.BannerResponse;
import com.sankuai.dsx.sxmeilishuo.bean.JumpResponse;
import com.sankuai.dsx.sxmeilishuo.bean.ProfessionalResponse;
import com.sankuai.dsx.sxmeilishuo.bean.RootListResponse;

/**
 * Created by dsx on 2016/12/1.
 */

public class HomeContract {

    interface View extends BaseFragmentView<Presenter> {

        /**
         * 上部banner数据填充
         * @param data 轮播图数据
         */
        void setBannerData(BannerResponse data);
    }

    interface Presenter extends BasePresenter {

        /**
         * 获取banner数据
         */
        void getBannerData();
    }


    // ---------------里面有很多子页面RecycleView里的数据 用下面的v和p层获取 ------------------

    interface SubView extends BaseFragmentView<SubPresenter> {

        /**
         * 获取下面方格内的数据
         */
        void setJumpGridData(JumpResponse data);

        /**
         * 获取下面专家号的数据
         */
        void setProfessionalGirlData(ProfessionalResponse data);

        /**
         * 获取下面核心内容的数据
         */
        void setMainContentData(RootListResponse data);
    }

    interface SubPresenter extends BasePresenter {

        /**
         * 获取下面方格内的数据
         */
        void getJumpGridData();

        /**
         * 获取下面专家号的数据
         */
        void getProfessionalGirlData();

        /**
         * 获取下面核心内容的数据
         */
        void getMainContentData();
    }
}
