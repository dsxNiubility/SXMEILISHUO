package com.sankuai.dsx.sxmeilishuo;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.DrawableBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by dsx on 16/10/18.
 */

public class HomeFragment extends Fragment {

    Banner mBanner;
    String[] images,titles;

    public static final int BANNER_HEAGHT = 525; // 轮播图高度

    private ScrollView mScrollView;
    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;
    private String[] names = {"CUPCAKE", "DONUT", "FROYO", "GINGERBREAD", "HONEYCOMB", "ICE CREAM SANDWICH", "JELLY BEAN", "KITKAT"};
    private ScrollIndicatorView scrollIndicatorView;
    private int unSelectTextColor;
    private Fragment mCurrentSubFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        images = getResources().getStringArray(R.array.url);
        titles = getResources().getStringArray(R.array.title);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBanner = (Banner)view.findViewById(R.id.top_banner);
        mScrollView = (ScrollView)view.findViewById(R.id.home_root_scrollview);
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    if (mCurrentSubFragment instanceof SubTJFragment) {
                        SubTJFragment frag = (SubTJFragment) mCurrentSubFragment;
                        if (mScrollView.getScrollY() < BANNER_HEAGHT) {
                            return false;
                        } else {
                            mScrollView.setScrollY(BANNER_HEAGHT);
                            frag.mNeedScroll = true;
                        }
                    }
                }
                return true;
            }
        });


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


        ViewPager viewPager = (ViewPager) view.findViewById(R.id.moretab_viewPager);
        scrollIndicatorView = (ScrollIndicatorView) view.findViewById(R.id.moretab_indicator);
        scrollIndicatorView.setBackgroundColor(Color.RED);
        scrollIndicatorView.setScrollBar(new DrawableBar(getContext(), R.drawable.round_border_white_selector, ScrollBar.Gravity.CENTENT_BACKGROUND) {
            @Override
            public int getHeight(int tabHeight) {
                return tabHeight - dipToPix(12);
            }

            @Override
            public int getWidth(int tabWidth) {
                return tabWidth - dipToPix(12);
            }
        });

        unSelectTextColor = Color.WHITE;
        // 设置滚动监听
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.RED, unSelectTextColor));

        viewPager.setOffscreenPageLimit(2);
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
        inflate = LayoutInflater.from(getActivity().getApplicationContext());
        indicatorViewPager.setAdapter(new MyAdapter(getActivity().getSupportFragmentManager()));

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


    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return 12;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(names[position % names.length]);
            int padding = dipToPix(10);
            textView.setPadding(padding, 0, padding, 0);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            if (position == 0){
                if (mCurrentSubFragment == null){
                    mCurrentSubFragment = new SubTJFragment();
                }
                return mCurrentSubFragment;
            }

            return new SubNSZFragment();
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_NONE;
        }

    }

    /**
     * 根据dip值转化成px值
     *
     * @param dip
     * @return
     */
    private int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return size;
    }

}
