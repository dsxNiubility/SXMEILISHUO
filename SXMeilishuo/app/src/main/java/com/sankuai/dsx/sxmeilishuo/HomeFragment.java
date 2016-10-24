package com.sankuai.dsx.sxmeilishuo;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.Arrays;

/**
 * Created by dsx on 16/10/18.
 */

public class HomeFragment extends Fragment  {

    Banner mBanner;
    String[] images,titles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        images = getResources().getStringArray(R.array.url);
        titles = getResources().getStringArray(R.array.title);
        mBanner = (Banner)getActivity().findViewById(R.id.top_banner);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        images = getResources().getStringArray(R.array.url);
        titles = getResources().getStringArray(R.array.title);
        mBanner = (Banner)view.findViewById(R.id.top_banner);

        //简单使用
        mBanner.setImages(Arrays.asList(images)).setImageLoader(new GlideImageLoader()).start();

        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(3000);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.start();
        mBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity().getApplicationContext(), "点击：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPause()
    {
        super.onPause();
        System.out.println("HomeFragment--onPause");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        System.out.println("HomeFragment--onResume");
    }


    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        Log.i("--", "onStart");
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("--", "onStop");
        //结束轮播
        mBanner.stopAutoPlay();
    }

}
