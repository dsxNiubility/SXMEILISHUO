package com.sankuai.dsx.sxmeilishuo.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sankuai.dsx.sxmeilishuo.network.NetworkService;
import com.sankuai.dsx.sxmeilishuo.R;
import com.sankuai.dsx.sxmeilishuo.bean.JumpResponse;
import com.sankuai.dsx.sxmeilishuo.bean.JumpResponse.JumpItem;
import com.sankuai.dsx.sxmeilishuo.bean.ProfessionalResponse;
import com.sankuai.dsx.sxmeilishuo.bean.RootListResponse;
import com.sankuai.dsx.sxmeilishuo.bean.RootListResponse.RootListData.RootListItem;
import com.sankuai.dsx.sxmeilishuo.bean.UserInfoItem;
import com.sankuai.dsx.sxmeilishuo.extension.LinearSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sankuai.dsx.sxmeilishuo.network.NetworkService.SOCIAL_BASE_URL;

/**
 * Created by dsx on 16/10/24.
 */

public class SubTJFragment extends Fragment {

    RecyclerView mRootRecyclerView;
    private List<String> mDatas;

    private List<JumpItem> mJumpItemList;
    private List<UserInfoItem> mProfessionalItemList;
    private List<RootListItem> mMainContentItemList;

    boolean mNeedScroll = false;

    private HomeAdapter mHomeAdapter;
    private HomeAdapter.MyViewHolder.HorRecyAdapter mHorRecyAdapter;

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
        mHomeAdapter = new HomeAdapter();
        mRootRecyclerView.setAdapter(mHomeAdapter);
        mRootRecyclerView.addItemDecoration(new LinearSpaceItemDecoration(dipToPix(15)));

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

        mJumpItemList = new ArrayList<>();
        mProfessionalItemList = new ArrayList<>();
        requestForJumpData();
        requestForProfessionalData();
        requestForMainContentData();
    }

    private void requestForJumpData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SOCIAL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetworkService networkService = retrofit.create(NetworkService.class);
        Call<JumpResponse> call = networkService.jumpItems();

        call.enqueue(new Callback<JumpResponse>() {
            @Override
            public void onResponse(Call<JumpResponse> call, Response<JumpResponse> response) {
                if (response != null && response.body() != null &&response.body().getData() != null){
                    mJumpItemList = response.body().getData();
                    if (mHorRecyAdapter != null){
                        mHorRecyAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<JumpResponse> call, Throwable t) {
                Log.d("", String.valueOf(t));
            }
        });
    }

    private void requestForProfessionalData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SOCIAL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetworkService networkService = retrofit.create(NetworkService.class);
        Call<ProfessionalResponse> call = networkService.professionalItems();

        call.enqueue(new Callback<ProfessionalResponse>() {
            @Override
            public void onResponse(Call<ProfessionalResponse> call, Response<ProfessionalResponse> response) {
                if (response != null && response.body() != null &&response.body().getData() != null){
                    mProfessionalItemList = response.body().getData().getList();
                    if (mHomeAdapter != null){
                        mHomeAdapter.notifyItemChanged(1);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfessionalResponse> call, Throwable t) {
                Log.d("", String.valueOf(t));
            }
        });
    }

    private void requestForMainContentData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SOCIAL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetworkService networkService = retrofit.create(NetworkService.class);
        Call<RootListResponse> call = networkService.mainContentItems();

        call.enqueue(new Callback<RootListResponse>() {
            @Override
            public void onResponse(Call<RootListResponse> call, Response<RootListResponse> response) {
                if (response != null && response.body() != null &&response.body().getData() != null){
                    mMainContentItemList = response.body().getData().getList();
                    if (mHomeAdapter != null){
                        mHomeAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<RootListResponse> call, Throwable t) {
                Log.d("", String.valueOf(t));
            }
        });
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
                        getActivity()).inflate(R.layout.main_content_item, parent,
                        false),300);
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position)
        {
            if (position == 1){
                if (mProfessionalItemList == null || mProfessionalItemList.size() < 4)return;


                holder.txt_one.setText(mProfessionalItemList.get(0).getNickname());
                holder.desc_one.setText(mProfessionalItemList.get(0).getAbout_me());

                holder.txt_two.setText(mProfessionalItemList.get(1).getNickname());
                holder.desc_two.setText(mProfessionalItemList.get(1).getAbout_me());

                holder.txt_three.setText(mProfessionalItemList.get(2).getNickname());
                holder.desc_three.setText(mProfessionalItemList.get(2).getAbout_me());

                holder.txt_four.setText(mProfessionalItemList.get(3).getNickname());
                holder.desc_four.setText(mProfessionalItemList.get(3).getAbout_me());


                Glide.with(getContext()).load(mProfessionalItemList.get(0).getAvatar_a()).asBitmap().into(new BitmapImageViewTarget(holder.img_one){
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        holder.img_one.setImageDrawable(circularBitmapDrawable);
                    }
                });

                Glide.with(getContext()).load(mProfessionalItemList.get(1).getAvatar_a()).asBitmap().into(new BitmapImageViewTarget(holder.img_two){
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        holder.img_two.setImageDrawable(circularBitmapDrawable);
                    }
                });

                Glide.with(getContext()).load(mProfessionalItemList.get(2).getAvatar_a()).asBitmap().into(new BitmapImageViewTarget(holder.img_three){
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        holder.img_three.setImageDrawable(circularBitmapDrawable);
                    }
                });

                Glide.with(getContext()).load(mProfessionalItemList.get(3).getAvatar_a()).asBitmap().into(new BitmapImageViewTarget(holder.img_four){
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        holder.img_four.setImageDrawable(circularBitmapDrawable);
                    }
                });


            }else if (position > 1) {
                if (mMainContentItemList == null || mMainContentItemList.size() < 1)return;


                    WindowManager manager = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
                    DisplayMetrics dm=new DisplayMetrics();
                    manager.getDefaultDisplay().getMetrics(dm);
                    int center_width = dm.widthPixels;
                    int center_height = 0;
                    if (mMainContentItemList.get(position-2).getPinfo().getFeed_pic_url().length() > 0 ) {
                        if (mMainContentItemList.get(position-2).getPinfo().getFeed_pic_width() > 0) {
                            center_height = mMainContentItemList.get(position - 2).getPinfo().getFeed_pic_height() /
                                    mMainContentItemList.get(position - 2).getPinfo().getFeed_pic_width() * center_width;
                        }else {
                            center_height = 500;
                        }
                    }else {
                        center_height = mMainContentItemList.get(position - 2).getPinfo().getPost_cover_height() /
                                mMainContentItemList.get(position - 2).getPinfo().getPost_cover_width() * center_width;
                    }
                    ViewGroup.LayoutParams para = holder.img_bigCenter.getLayoutParams();
                    para.height = center_height;

//                    Log.d("aabbb", mMainContentItemList.get(position - 2).getPinfo().getFeed_pic_width() + "分隔" +mMainContentItemList.get(position - 2).getPinfo().getFeed_pic_height());
//                    Log.d("aabbc", para.width + "分隔" +para.height);

                    holder.img_bigCenter.setLayoutParams(para);

                Glide.with(getContext()).load(mMainContentItemList.get(position-2).getUinfo().getAvatar_a()).asBitmap().into(new BitmapImageViewTarget(holder.img_icon){
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        holder.img_icon.setImageDrawable(circularBitmapDrawable);
                    }
                });

                Glide.with(getContext()).load(mMainContentItemList.get(position-2).getUinfo().getIdentity_img()).asBitmap().into(new BitmapImageViewTarget(holder.img_vip){
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        holder.img_vip.setImageDrawable(circularBitmapDrawable);
                    }
                });

                String centerImgUrl = mMainContentItemList.get(position-2).getPinfo().getFeed_pic_url().length() > 0 ?
                        mMainContentItemList.get(position-2).getPinfo().getFeed_pic_url() :
                        mMainContentItemList.get(position-2).getPinfo().getPost_cover();
                Glide.with(getContext()).load(centerImgUrl).into(holder.img_bigCenter);

                holder.tv_title.setText(mMainContentItemList.get(position-2).getUinfo().getNickname());
                holder.tv_desc.setText(mMainContentItemList.get(position-2).getUinfo().getAbout_me());
                holder.tv_comment.setText(mMainContentItemList.get(position-2).getPinfo().getPost_desc());
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
            return mMainContentItemList == null ? 2 : mMainContentItemList.size() + 2;
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            TextView tv;
            RecyclerView mSubRecyclerView;

            // 第二个item有一组数据
            ImageView img_one;
            TextView txt_one;
            TextView desc_one;

            ImageView img_two;
            TextView txt_two;
            TextView desc_two;

            ImageView img_three;
            TextView txt_three;
            TextView desc_three;

            ImageView img_four;
            TextView txt_four;
            TextView desc_four;

            // 主要界面的item
            ImageView img_icon;
            ImageView img_vip;
            ImageView img_bigCenter;
            TextView tv_title;
            TextView tv_desc;
            TextView tv_time_ago;
            TextView tv_comment;

            public MyViewHolder(View view ,int type)
            {
                super(view);
                if (type == 100){
                    mSubRecyclerView = (RecyclerView) view.findViewById(R.id.id_sub_recyclerview);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    mSubRecyclerView.setLayoutManager(linearLayoutManager);
                    mHorRecyAdapter = new HorRecyAdapter();
                    mSubRecyclerView.setAdapter(mHorRecyAdapter);
                }else if (type == 200){
                    // 绑定好多数据
                    img_one = (ImageView) view.findViewById(R.id.img_one);
                    txt_one = (TextView)view.findViewById(R.id.title_one);
                    desc_one = (TextView)view.findViewById(R.id.desc_one);

                    img_two = (ImageView) view.findViewById(R.id.img_two);
                    txt_two = (TextView)view.findViewById(R.id.title_two);
                    desc_two = (TextView)view.findViewById(R.id.desc_two);

                    img_three = (ImageView) view.findViewById(R.id.img_three);
                    txt_three = (TextView)view.findViewById(R.id.title_three);
                    desc_three = (TextView)view.findViewById(R.id.desc_three);

                    img_four = (ImageView) view.findViewById(R.id.img_four);
                    txt_four = (TextView)view.findViewById(R.id.title_four);
                    desc_four = (TextView)view.findViewById(R.id.desc_four);

                }else {
                    img_icon = (ImageView) view.findViewById(R.id.main_item_icon);
                    img_vip = (ImageView) view.findViewById(R.id.main_item_vip);
                    img_bigCenter = (ImageView) view.findViewById(R.id.main_center_img);
                    tv_title = (TextView)view.findViewById(R.id.main_item_title);
                    tv_time_ago = (TextView)view.findViewById(R.id.main_item_time);
                    tv_desc = (TextView)view.findViewById(R.id.main_item_desc);
                    tv_comment = (TextView)view.findViewById(R.id.main_item_comment);
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
                public void onBindViewHolder(final HorRecyHolder holder, int position) {
                    if (position > mJumpItemList.size() - 1) return;
                    Glide.with(getContext()).load(mJumpItemList.get(position).getPic_url()).asBitmap().into(new BitmapImageViewTarget(holder.mCenterImg){
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                            circularBitmapDrawable.setCornerRadius(40);
                            holder.mCenterImg.setImageDrawable(circularBitmapDrawable);
                        }
                    });
                }

                @Override
                public int getItemCount() {
                    return mJumpItemList.size();
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
