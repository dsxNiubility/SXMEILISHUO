package com.sankuai.dsx.sxmeilishuo;

import com.sankuai.dsx.sxmeilishuo.bean.BannerResponse;
import com.sankuai.dsx.sxmeilishuo.bean.JumpResponse;
import com.sankuai.dsx.sxmeilishuo.bean.ProfessionalResponse;
import com.sankuai.dsx.sxmeilishuo.bean.RootListResponse;
import com.sankuai.dsx.sxmeilishuo.bean.ShoppingContentResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dsx on 2016/10/28.
 */

public interface NetworkService {
    public static final String MCE_MOGU_BASE_URL = "http://mce.mogucdn.com";
    public static final String SOCIAL_BASE_URL = "http://social-api.meilishuo.com";

    @GET("/ajax/multiget/3?pids=5956")
    Call<BannerResponse> bannerItems();

    @GET("/2.0/posting/recommend_tag?tag_rec_pos=recommend_list") // 不自己拼参数了 他们URL这样写死也能每天得新数据
    Call<JumpResponse> jumpItems();

    @GET("/2.0/posting/expert_users?offset=0&show=home&limit=10")
    Call<ProfessionalResponse> professionalItems();

    @GET("/2.0/posting/recommend_list?limit=15") // 核心展示内容
    Call<RootListResponse> mainContentItems();

    @GET("file:///android_asset/test.json") // 购物页展示内容
    Call<ShoppingContentResponse> shoppingContentItems();
}