package com.sankuai.dsx.sxmeilishuo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsx on 16/10/24.
 */

public class SubTJFragment extends Fragment {

    RecyclerView mRootRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;
    boolean mNeedScroll = false;

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
        initData();
        mRootRecyclerView = (RecyclerView)view.findViewById(R.id.id_recyclerview);
        mRootRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRootRecyclerView.setAdapter(mAdapter = new HomeAdapter());

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
    }

    protected void initData()
    {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }


    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder;
            if (viewType == 100){
                holder = new MyViewHolder(LayoutInflater.from(
                        getActivity()).inflate(R.layout.jump_content, parent,
                        false),100);
            }else if (viewType == 200){
                holder = new MyViewHolder(LayoutInflater.from(
                        getActivity()).inflate(R.layout.professional_girl_item, parent,
                        false),200);
            }else {
               holder = new MyViewHolder(LayoutInflater.from(
                        getActivity()).inflate(R.layout.recycle_temp_item, parent,
                        false),300);
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            if (position > 1) {
                holder.tv.setText(mDatas.get(position));
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
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            TextView tv;
            RecyclerView mSubRecyclerView;
            public MyViewHolder(View view ,int type)
            {
                super(view);
                if (type == 100){
                    mSubRecyclerView = (RecyclerView) view.findViewById(R.id.id_sub_recyclerview);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    mSubRecyclerView.setLayoutManager(linearLayoutManager);
                    mSubRecyclerView.setAdapter(new HorRecyAdapter());
                }else if (type == 200){
                    // 绑定好多数据
                }else {
                    tv = (TextView) view.findViewById(R.id.id_num);
                }
            }

            // 嵌套一层adapter
            class HorRecyAdapter extends RecyclerView.Adapter<HorRecyAdapter.HorRecyHolder>{

                @Override
                public HorRecyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    HorRecyHolder holder = new HorRecyHolder(LayoutInflater.from(
                            getActivity()).inflate(R.layout.jump_title_item, parent,
                            false));
                    return holder;
                }

                @Override
                public void onBindViewHolder(HorRecyHolder holder, int position) {

                }

                @Override
                public int getItemCount() {
                    return 6;
                }

                class HorRecyHolder extends RecyclerView.ViewHolder{
                    ImageView mCenterImg;
                    public HorRecyHolder(View itemView) {
                        super(itemView);
                        mCenterImg = (ImageView) itemView.findViewById(R.id.center_img);
                    }
                }
            }
        }
    }
}
