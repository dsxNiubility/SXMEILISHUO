package com.sankuai.dsx.sxmeilishuo;

import com.sankuai.dsx.sxmeilishuo.bean.BannerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dsx on 2016/10/28.
 */

public interface NetworkService {
    public static final String API_URL = "http://mce.mogucdn.com";

    @GET("/ajax/multiget/3?pids=5956")
    Call<BannerResponse> bannerItems();
}