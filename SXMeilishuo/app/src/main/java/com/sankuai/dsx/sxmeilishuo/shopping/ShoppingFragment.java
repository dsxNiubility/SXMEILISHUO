package com.sankuai.dsx.sxmeilishuo.shopping;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.sankuai.dsx.sxmeilishuo.R;
import com.sankuai.dsx.sxmeilishuo.bean.BannerItem;
import com.sankuai.dsx.sxmeilishuo.bean.ShoppingContentResponse;
import com.sankuai.dsx.sxmeilishuo.bean.ShoppingHeaderResponse;
import com.sankuai.dsx.sxmeilishuo.bean.ShoppingInfoItem;
import com.sankuai.dsx.sxmeilishuo.bean.TwittersInfoItem;
import com.sankuai.dsx.sxmeilishuo.shopping.ShoppingFragment.ShopAdapter.MyViewHolder.MarketRecyAdapter;
import com.sankuai.dsx.sxmeilishuo.shopping.ShoppingFragment.ShopAdapter.MyViewHolder.CategoryRecyAdapter;
import com.sankuai.dsx.sxmeilishuo.shopping.ShoppingFragment.ShopAdapter.MyViewHolder.ShopContentRecyAdapter;
import com.sankuai.dsx.sxmeilishuo.shopping.ShoppingFragment.ShopAdapter.MyViewHolder.ShopContentRecyAdapter.ShopContentRecyHolder.ShopTwittersAdapter;
import com.sankuai.dsx.sxmeilishuo.shopping.ShoppingFragment.ShopAdapter.MyViewHolder.SubCateRecyAdapter;
import com.sankuai.dsx.sxmeilishuo.extension.GlideImageLoader;
import com.sankuai.dsx.sxmeilishuo.extension.LinearSpaceItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dsx on 16/10/18.
 */

public class ShoppingFragment extends Fragment {

    private TextView mHaowu;
    private TextView mPingpai;
    private View mTopLeft;
    private View mTopRight;
    private RecyclerView mAllRecycleView;


    private List<BannerItem> mBannerItems;
    private List<BannerItem> mMarketsItems;
    private List<BannerItem> mCateItems;
    private List<ShoppingInfoItem> mShoppingInfoItemList;
    private List<String> mSubCateItems;

    private ShopAdapter mShopAdapter;
    private MarketRecyAdapter mMarketRecyAdapter;
    private CategoryRecyAdapter mCategoryRecyAdapter;
    private ShopContentRecyAdapter mShopContentRecyAdapter;
    private SubCateRecyAdapter mSubCateRecyAdapter;
    private ShopTwittersAdapter mShopTwittersAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopping, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHaowu = (TextView) view.findViewById(R.id.shop_top_haowu);
        mPingpai = (TextView) view.findViewById(R.id.shop_top_pingpai);
        mTopLeft = view.findViewById(R.id.top_line_left);
        mTopRight = view.findViewById(R.id.top_line_right);
        mAllRecycleView = (RecyclerView) view.findViewById(R.id.id_shop_recyclerview);
        mShopAdapter = new ShopAdapter();
        mAllRecycleView.setAdapter(mShopAdapter);
        LinearLayoutManager linearManager = new LinearLayoutManager(getContext());
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAllRecycleView.setLayoutManager(linearManager);
        mAllRecycleView.addItemDecoration(new LinearSpaceItemDecoration(dipToPix(15)));
        clickTopLine(mHaowu);

        String[] cateLists = {"全部","穿搭","服装","鞋包配","美容美妆","生活方式","品牌"};
        mSubCateItems = Arrays.asList(cateLists);

        requestForTopData();
        requestForSubData();
    }

    private void clickTopLine(View view){
        mHaowu.setSelected(view == mHaowu);
        mPingpai.setSelected(view == mPingpai);
        mTopLeft.setVisibility(mHaowu.isSelected() ? View.VISIBLE : View.INVISIBLE);
        mTopRight.setVisibility(mPingpai.isSelected() ? View.VISIBLE : View.INVISIBLE);
    }


    private void requestForTopData(){
        // TODO 等会下面通了后 改成异步
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = getContext().getAssets().open("shopping_top_data.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gsonn = new Gson();
        ShoppingHeaderResponse response = gsonn.fromJson(sb.toString(),ShoppingHeaderResponse.class);
        if (response.getData() != null) {
            if (response.getData().getBanner() != null) {
                mBannerItems = response.getData().getBanner().getList();
            }

            if (response.getData().getMarketing() != null) {
                mMarketsItems = response.getData().getMarketing().getList();
            }

            if (response.getData().getCategory() != null) {
                mCateItems = response.getData().getCategory().getList();
            }
        }
        mShopAdapter.notifyDataSetChanged();
    }

    private void requestForSubData(){
        // TODO 等会下面通了后 改成异步
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = getContext().getAssets().open("shopping_sub_one.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gsonn = new Gson();
        ShoppingContentResponse response = gsonn.fromJson(sb.toString(),ShoppingContentResponse.class);
        if (response.getData() != null) {
            mShoppingInfoItemList = response.getData().getList();
        }
//        mShopContentRecyAdapter.notifyDataSetChanged();
    }

    class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder>
    {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder;
            if (viewType == 100){
                holder = new MyViewHolder(LayoutInflater.from(
                        getActivity()).inflate(R.layout.shopping_header_item, parent,
                        false),100);
            }else if (viewType == 200){
                holder = new MyViewHolder(LayoutInflater.from(
                        getActivity()).inflate(R.layout.shopping_cate_item, parent,
                        false),200);
            }else {
                holder = new MyViewHolder(LayoutInflater.from(
                        getActivity()).inflate(R.layout.shopping_sub_recy_item, parent,
                        false),300);
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position)
        {
            if (position == 0){

                List<String> bannerImgsUrl = new ArrayList<>();
                for (BannerItem item : mBannerItems){
                    bannerImgsUrl.add(item.getImage());
                }
                //简单使用
                holder.mBanner.setImages(bannerImgsUrl).setImageLoader(new GlideImageLoader()).start();

                //设置banner样式
                holder.mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
                holder.mBanner.isAutoPlay(true);
                holder.mBanner.setDelayTime(3000);
                holder.mBanner.setIndicatorGravity(BannerConfig.CENTER);
                holder.mBanner.start();
                holder.mBanner.setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(getActivity().getApplicationContext(), "点击：" + position, Toast.LENGTH_SHORT).show();
                    }
                });

            }else if (position == 1) {

            }else if (position > 1){

            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0)return 100;
            if (position == 1)return 200;
            return super.getItemViewType(position);
        }

        @Override
        public int getItemCount()
        {
            return 3;
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            // 第一格
            Banner mBanner;
            RecyclerView mMarketRecyclerView;

            // 第二格
            RecyclerView mCategoryRecycleView;

            // 第三格
            RecyclerView mSubCateScrollView;
            RecyclerView mShopRecycleVIew;

            public MyViewHolder(View view ,int type)
            {
                super(view);
                if (type == 100){
                    mBanner = (Banner) view.findViewById(R.id.top_banner);

                    mMarketRecyclerView = (RecyclerView) view.findViewById(R.id.id_market_recyclerview);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    mMarketRecyclerView.setLayoutManager(linearLayoutManager);
                    mMarketRecyAdapter = new MarketRecyAdapter();
                    mMarketRecyclerView.setAdapter(mMarketRecyAdapter);

                }else if (type == 200){
                    mCategoryRecycleView = (RecyclerView) view.findViewById(R.id.id_category_recycleview);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
                    gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                    mCategoryRecycleView.setLayoutManager(gridLayoutManager);
                    mCategoryRecyAdapter = new CategoryRecyAdapter();
                    mCategoryRecycleView.setAdapter(mCategoryRecyAdapter);

                }else {
                    mSubCateScrollView = (RecyclerView) view.findViewById(R.id.id_cate_scrollView);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    mSubCateScrollView.setLayoutManager(linearLayoutManager);
                    mSubCateRecyAdapter = new SubCateRecyAdapter();
                    mSubCateScrollView.setAdapter(mSubCateRecyAdapter);

                    mShopRecycleVIew = (RecyclerView) view.findViewById(R.id.id_content_recyclerview);
                    LinearLayoutManager linearManager = new LinearLayoutManager(getContext());
                    linearManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mShopRecycleVIew.setLayoutManager(linearManager);
                    mShopContentRecyAdapter = new ShopContentRecyAdapter();
                    mShopRecycleVIew.setAdapter(mShopContentRecyAdapter);
                }
            }


            class MarketRecyAdapter extends RecyclerView.Adapter<MarketRecyAdapter.MarketRecyHolder>{

                @Override
                public MarketRecyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MarketRecyHolder(LayoutInflater.from(
                            getActivity()).inflate(R.layout.shopping_market_item, parent,
                            false));
                }

                @Override
                public void onBindViewHolder(final MarketRecyHolder holder, int position) {
                    if (position > mMarketsItems.size() - 1) return;
                    Glide.with(getContext()).load(mMarketsItems.get(position).getImage())
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)//这一行是为了解决 RGB_565转换ARGB_8888 图片会出现浅绿的bug
                            .into(holder.mCenterImg);
                }

                @Override
                public int getItemCount() {
                    return mMarketsItems == null ? 0 : mMarketsItems.size();
                }

                class MarketRecyHolder extends RecyclerView.ViewHolder{
                    ImageView mCenterImg;

                    public MarketRecyHolder(View itemView) {
                        super(itemView);
                        mCenterImg = (ImageView) itemView.findViewById(R.id.shop_marketing_center);
                    }
                }
            }

            class CategoryRecyAdapter extends RecyclerView.Adapter<CategoryRecyAdapter.CategoryRecyHolder>{

                @Override
                public CategoryRecyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new CategoryRecyHolder(LayoutInflater.from(
                            getActivity()).inflate(R.layout.shopping_cate_img_item, parent,
                            false));
                }

                @Override
                public void onBindViewHolder(final CategoryRecyHolder holder, int position) {
                    if (position > mCateItems.size() - 1) return;
                    Glide.with(getContext()).load(mCateItems.get(position).getImage())
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .into(holder.mCenterImg);
                }

                @Override
                public int getItemCount() {
                    return mCateItems == null ? 0 : mCateItems.size();
                }

                class CategoryRecyHolder extends RecyclerView.ViewHolder{
                    ImageView mCenterImg;

                    public CategoryRecyHolder(View itemView) {
                        super(itemView);
                        mCenterImg = (ImageView) itemView.findViewById(R.id.shop_cate_center);
                    }
                }
            }

            class SubCateRecyAdapter extends RecyclerView.Adapter<SubCateRecyAdapter.SubCateRecyHolder>{

                @Override
                public SubCateRecyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new SubCateRecyHolder(LayoutInflater.from(
                            getActivity()).inflate(R.layout.shopping_sub_cate_item, parent,
                            false));
                }

                @Override
                public void onBindViewHolder(final SubCateRecyHolder holder, int position) {
                    if (position > mSubCateItems.size() - 1) return;
                    holder.mTitleView.setText(mSubCateItems.get(position));

                    if (position == 0){
                        holder.mTitleView.setSelected(true);
                    }
                }

                @Override
                public int getItemCount() {
                    return mSubCateItems == null ? 0 : mSubCateItems.size();
                }

                class SubCateRecyHolder extends RecyclerView.ViewHolder{
                    TextView mTitleView;

                    public SubCateRecyHolder(View itemView) {
                        super(itemView);
                        mTitleView = (TextView) itemView.findViewById(R.id.shop_sub_cate_name);
                    }
                }
            }

            class ShopContentRecyAdapter extends RecyclerView.Adapter<ShopContentRecyAdapter.ShopContentRecyHolder>{

                @Override
                public ShopContentRecyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new ShopContentRecyHolder(LayoutInflater.from(
                            getActivity()).inflate(R.layout.shopping_content_item, parent,
                            false));
                }

                @Override
                public void onBindViewHolder(final ShopContentRecyHolder holder, int position) {
                    if (position > mShoppingInfoItemList.size() - 1) return;
                    holder.mUserName.setText(mShoppingInfoItemList.get(position).getUinfo().getNickname());
                    holder.mShopTitle.setText(mShoppingInfoItemList.get(position).getTitle());
                    holder.mShopDesc.setText(mShoppingInfoItemList.get(position).getSubtitle());

                    Glide.with(getContext()).load(mShoppingInfoItemList.get(position).getUinfo().getAvatar()).asBitmap().into(new BitmapImageViewTarget(holder.mUserIcon){
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            holder.mUserIcon.setImageDrawable(circularBitmapDrawable);
                        }
                    });
                    Glide.with(getContext()).load(mShoppingInfoItemList.get(position).getImage().getPic_url()).into(holder.mCenterImg);

                    holder.mTwittersInfoItems = mShoppingInfoItemList.get(position).getTwitters();

                    int a = 0;
                }

                @Override
                public int getItemCount() {
                    return mShoppingInfoItemList == null ? 0 : mShoppingInfoItemList.size();
                }

                class ShopContentRecyHolder extends RecyclerView.ViewHolder{
                    ImageView mUserIcon;
                    TextView mUserName;
                    ImageView mCenterImg;
                    TextView mShopTitle;
                    TextView mShopDesc;
                    RecyclerView mTwitterRecy;

                    List<TwittersInfoItem> mTwittersInfoItems;

                    public ShopContentRecyHolder(View itemView) {
                        super(itemView);
                        mCenterImg = (ImageView) itemView.findViewById(R.id.shop_content_img);
                        mUserIcon = (ImageView) itemView.findViewById(R.id.shop_item_user_icon);
                        mUserName = (TextView) itemView.findViewById(R.id.shop_item_username);
                        mShopTitle = (TextView) itemView.findViewById(R.id.shop_item_title);
                        mShopDesc = (TextView) itemView.findViewById(R.id.shop_item_desc);
                        mTwitterRecy = (RecyclerView) itemView.findViewById(R.id.id_twitters_recy);

                        mTwitterRecy = (RecyclerView) itemView.findViewById(R.id.id_twitters_recy);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        mTwitterRecy.setLayoutManager(linearLayoutManager);
                        mShopTwittersAdapter = new ShopTwittersAdapter();
                        mTwitterRecy.setAdapter(mShopTwittersAdapter);
                    }


                    class ShopTwittersAdapter extends RecyclerView.Adapter<ShopTwittersAdapter.ShopTwittersRecyHolder>{

                        @Override
                        public ShopTwittersRecyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                            return new ShopTwittersRecyHolder(LayoutInflater.from(
                                    getActivity()).inflate(R.layout.shopping_twitters_item, parent,
                                    false));
                        }

                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onBindViewHolder(final ShopTwittersRecyHolder holder, int position) {
                            if (position > mTwittersInfoItems.size() - 1) return;
                            holder.mTwitterTitle.setText(mTwittersInfoItems.get(position).getGoods_title());
                            holder.mPrice.setText("￥ " + mTwittersInfoItems.get(position).getOrigin_price());
                            Glide.with(getContext()).load(mTwittersInfoItems.get(position).getShow_pic()).into(holder.mCenterImg);
                        }

                        @Override
                        public int getItemCount() {
                            return mTwittersInfoItems == null ? 0 : mTwittersInfoItems.size() - 1;
                        }

                        class ShopTwittersRecyHolder extends RecyclerView.ViewHolder{
                            TextView mTwitterTitle;
                            TextView mPrice;
                            ImageView mCenterImg;

                            public ShopTwittersRecyHolder(View itemView) {
                                super(itemView);
                                mTwitterTitle = (TextView) itemView.findViewById(R.id.shop_twitter_title);
                                mPrice = (TextView) itemView.findViewById(R.id.shop_twitter_price);
                                mCenterImg = (ImageView) itemView.findViewById(R.id.shop_twitters_center);
                            }
                        }
                    }

                }
            }
        }
    }

    /**
     * 根据dip值转化成px值
     *
     * @param dip 锚点
     * @return 像素
     */
    private int dipToPix(float dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }
}
