package com.sankuai.dsx.sxmeilishuo.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.sankuai.dsx.platform.Utils.DipUtils;
import com.sankuai.dsx.sxmeilishuo.R;
import com.sankuai.dsx.sxmeilishuo.bean.JumpResponse;
import com.sankuai.dsx.sxmeilishuo.bean.ProfessionalResponse;
import com.sankuai.dsx.sxmeilishuo.bean.RootListResponse;
import com.sankuai.dsx.sxmeilishuo.extension.LinearSpaceItemDecoration;

/**
 * Created by dsx on 16/10/24.
 */

public class SubTJFragment extends Fragment implements HomeContract.SubView{

    RecyclerView mRootRecyclerView;

    boolean mNeedScroll = false;

    private HomeTJAdapter mHomeTJAdapter;

    private HomeSubPresenter mPresenter;

    private int mLastY;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sub_tuijian, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootRecyclerView = (RecyclerView)view.findViewById(R.id.id_recyclerview);
        mRootRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mHomeTJAdapter = HomeTJAdapter.tjAdapter(getContext());
        mRootRecyclerView.setAdapter(mHomeTJAdapter);
        mRootRecyclerView.addItemDecoration(new LinearSpaceItemDecoration(DipUtils.dipToPix(15)));

        mRootRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return !mNeedScroll;
            }
        });

        mRootRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if ((dy < 0) && (recyclerView.computeVerticalScrollOffset() == 0)){
                    mNeedScroll = false;
                }
            }
        });

        mPresenter = HomeSubPresenter.presenter(this,new HomeModel());
        mPresenter.start();
    }

    @Override
    public void setJumpGridData(JumpResponse data) {
        mHomeTJAdapter.setJumpItemList(data.getData());
        if (mHomeTJAdapter.getHorRecyAdapter() != null){
            mHomeTJAdapter.getHorRecyAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void setProfessionalGirlData(ProfessionalResponse data) {
        mHomeTJAdapter.setProfessionalItemList(data.getData().getList());
        if (mHomeTJAdapter != null){
            mHomeTJAdapter.notifyItemChanged(1);
        }
    }

    @Override
    public void setMainContentData(RootListResponse data) {
        mHomeTJAdapter.setMainContentItemList(data.getData().getList());
        if (mHomeTJAdapter != null){
            mHomeTJAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean isFragmentDetach() {
        return false;
    }

    @Override
    public void setPresenter(@NonNull HomeContract.SubPresenter presenter) {

    }
}
