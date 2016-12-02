package com.sankuai.dsx.sxmeilishuo.shopping;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sankuai.dsx.sxmeilishuo.R;
import com.sankuai.dsx.sxmeilishuo.bean.ShoppingContentResponse;
import com.sankuai.dsx.sxmeilishuo.bean.ShoppingHeaderResponse;
import com.sankuai.dsx.sxmeilishuo.extension.LinearSpaceItemDecoration;

import java.util.Arrays;

/**
 * Created by dsx on 16/10/18.
 */

public class ShoppingFragment extends Fragment implements ShoppingContract.View{

    private TextView mHaowu;
    private TextView mPingpai;
    private View mTopLeft;
    private View mTopRight;
    private RecyclerView mAllRecycleView;

    private ShopAdapter mShopAdapter;

    private ShoppingPresenter mPresenter;

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
        mShopAdapter = ShopAdapter.adapter(getContext());
        mAllRecycleView.setAdapter(mShopAdapter);
        LinearLayoutManager linearManager = new LinearLayoutManager(getContext());
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAllRecycleView.setLayoutManager(linearManager);
        mAllRecycleView.addItemDecoration(new LinearSpaceItemDecoration(dipToPix(15)));
        clickTopLine(mHaowu);

        String[] cateLists = {"全部","穿搭","服装","鞋包配","美容美妆","生活方式","品牌"};
        mShopAdapter.setSubCateItems(Arrays.asList(cateLists));

        mPresenter = ShoppingPresenter.presenter(this,new ShoppingModel(getLoaderManager()));
        mPresenter.getTopData(getContext());
        mPresenter.getSubRecycleViewContentData(0);
    }

    private void clickTopLine(View view){
        mHaowu.setSelected(view == mHaowu);
        mPingpai.setSelected(view == mPingpai);
        mTopLeft.setVisibility(mHaowu.isSelected() ? View.VISIBLE : View.INVISIBLE);
        mTopRight.setVisibility(mPingpai.isSelected() ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setPresenter(@NonNull ShoppingContract.Presenter presenter) {

    }

    @Override
    public void setTopData(ShoppingHeaderResponse response) {
        if (response.getData() != null) {
            if (response.getData().getBanner() != null) {
                mShopAdapter.setBannerItems(response.getData().getBanner().getList());
            }

            if (response.getData().getMarketing() != null) {
                mShopAdapter.setMarketsItems(response.getData().getMarketing().getList());
            }

            if (response.getData().getCategory() != null) {
                mShopAdapter.setCateItems(response.getData().getCategory().getList());
            }
        }
        mShopAdapter.notifyDataSetChanged();
        if (mShopAdapter.getMarketRecyAdapter() != null) {
            mShopAdapter.getMarketRecyAdapter().notifyDataSetChanged();
        }
        if (mShopAdapter.getCategoryRecyAdapter() != null) {
            mShopAdapter.getCategoryRecyAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void setSubRecycleViewContent(ShoppingContentResponse response) {
        if (response.getData() != null) {
            mShopAdapter.setShoppingInfoItemList(response.getData().getList());
            if (mShopAdapter.getShopContentRecyAdapter() != null) {
                mShopAdapter.getShopContentRecyAdapter().notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean isFragmentDetach() {
        return false;
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
