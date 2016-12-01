package com.sankuai.dsx.sxmeilishuo.home;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.widget.Toast;

import com.sankuai.dsx.platform.Base.BaseDataCallBacks;
import com.sankuai.dsx.platform.Base.BaseLoaderCallBacks;
import com.sankuai.dsx.platform.Base.BaseModel;
import com.sankuai.dsx.platform.Base.BaseModelImplements;
import com.sankuai.dsx.sxmeilishuo.bean.BannerItem;
import com.sankuai.dsx.sxmeilishuo.bean.BannerResponse;
import com.sankuai.dsx.sxmeilishuo.bean.JumpResponse;
import com.sankuai.dsx.sxmeilishuo.bean.ProfessionalResponse;
import com.sankuai.dsx.sxmeilishuo.bean.RootListResponse;
import com.sankuai.dsx.sxmeilishuo.extension.GlideImageLoader;
import com.sankuai.dsx.sxmeilishuo.network.NetworkService;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sankuai.dsx.sxmeilishuo.network.NetworkService.MCE_MOGU_BASE_URL;
import static com.sankuai.dsx.sxmeilishuo.network.NetworkService.SOCIAL_BASE_URL;

/**
 * Created by dsx on 2016/12/1.
 */

public class HomeModel {

    public void loadBannerData(final BaseDataCallBacks<BannerResponse> goData){
        // 目测这个getService不会返回null
        Call<BannerResponse> call = getNetWorkServiceByBaseUrl(MCE_MOGU_BASE_URL).bannerItems();
        call.enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
                if (response != null && response.body() != null){
                    goData.onFinished(response.body());
                }
            }

            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {
                Log.d("", String.valueOf(t));
            }
        });
    }

    public void loadJumpGridData(final BaseDataCallBacks<JumpResponse> goData){

        Call<JumpResponse> call = getNetWorkServiceByBaseUrl(SOCIAL_BASE_URL).jumpItems();
        call.enqueue(new Callback<JumpResponse>() {
            @Override
            public void onResponse(Call<JumpResponse> call, Response<JumpResponse> response) {
                if (response != null && response.body() != null &&response.body().getData() != null){
                    goData.onFinished(response.body());
                }
            }
            @Override
            public void onFailure(Call<JumpResponse> call, Throwable t) {
                Log.d("", String.valueOf(t));
            }
        });
    }

    public void loadProfessionalData(final BaseDataCallBacks<ProfessionalResponse> goData){

        Call<ProfessionalResponse> call = getNetWorkServiceByBaseUrl(SOCIAL_BASE_URL).professionalItems();
        call.enqueue(new Callback<ProfessionalResponse>() {
            @Override
            public void onResponse(Call<ProfessionalResponse> call, Response<ProfessionalResponse> response) {
                if (response != null && response.body() != null &&response.body().getData() != null){
                    goData.onFinished(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProfessionalResponse> call, Throwable t) {
                Log.d("", String.valueOf(t));
            }
        });
    }

    public void loadMainContentData(final BaseDataCallBacks<RootListResponse> goData){

        Call<RootListResponse> call = getNetWorkServiceByBaseUrl(SOCIAL_BASE_URL).mainContentItems();
        call.enqueue(new Callback<RootListResponse>() {
            @Override
            public void onResponse(Call<RootListResponse> call, Response<RootListResponse> response) {
                if (response != null && response.body() != null &&response.body().getData() != null){
                    goData.onFinished(response.body());
                }
            }

            @Override
            public void onFailure(Call<RootListResponse> call, Throwable t) {
                Log.d("", String.valueOf(t));
            }
        });
    }


    private NetworkService getNetWorkServiceByBaseUrl(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(NetworkService.class);
    }
}
