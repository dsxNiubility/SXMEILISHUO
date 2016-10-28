package com.sankuai.dsx.sxmeilishuo;

import android.annotation.TargetApi;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.Arrays;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by dsx on 16/10/18.
 */

public class HomeFragment extends Fragment {

    Banner mBanner;
    String[] images,titles;

    public static final int BANNER_HEAGHT = 200; // 轮播图高度
    public static final int VIEWPAGER_SCROLLBAR_HEIGHT = 50; // 滑动标题栏高度
    public static final int TOP_TOOLBAR_HEIGHT = 48; // 顶部导航栏高度
    public static final int TABHOST_HEIGHT = 50; // 底部tabbar的高度
    public static final int STATUSBAR_HEIGHT = 23; // 顶部电池状态栏

    private ScrollView mScrollView;
    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;
    private String[] names = {"推荐", "关注", "搭配", "美容", "逆生长", "生活", "JELLY BEAN", "KITKAT"};
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
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
                        if (mScrollView.getScrollY() < dipToPix(BANNER_HEAGHT)) {
                            return false;
                        } else {
                            mScrollView.setScrollY(dipToPix(BANNER_HEAGHT));
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

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int widthPixels= dm.widthPixels;
        int heightPixels= dm.heightPixels;
        float density = dm.density;
        float screenHeight = heightPixels / density ;
        float pageHeight = screenHeight - TABHOST_HEIGHT - TOP_TOOLBAR_HEIGHT - VIEWPAGER_SCROLLBAR_HEIGHT - STATUSBAR_HEIGHT;

        Log.d("height", String.valueOf(pageHeight));
        Log.d("height2", String.valueOf(dipToPix(pageHeight)));

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.moretab_viewPager);
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, dipToPix(pageHeight)));
        scrollIndicatorView = (ScrollIndicatorView) view.findViewById(R.id.moretab_indicator);

        ColorBar colorBar = new ColorBar(getContext(), getContext().getResources().getColor(R.color.meili_pink), 5);
        scrollIndicatorView.setScrollBar(colorBar);

        unSelectTextColor = getContext().getResources().getColor(R.color.meili_gray);

        float unSelectSize = 16;
        float selectSize = unSelectSize * 1.2f;
        scrollIndicatorView.setSplitAuto(false);
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(unSelectTextColor,unSelectTextColor).setSize(selectSize, unSelectSize));

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
            return names.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(names[position % names.length]);
            int padding = dipToPix(10);
            int witdh = getTextWidth(textView);
            textView.setWidth((int) (witdh * 1.6f) + padding );

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
            return PagerAdapter.POSITION_UNCHANGED;
        }

        private int getTextWidth(TextView textView) {
            if (textView == null) {
                return 0;
            }
            Rect bounds = new Rect();
            String text = textView.getText().toString();
            Paint paint = textView.getPaint();
            paint.getTextBounds(text, 0, text.length(), bounds);
            int width = bounds.left + bounds.width();
            return width;
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
