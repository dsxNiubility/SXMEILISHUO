package com.sankuai.dsx.sxmeilishuo.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sankuai.dsx.sxmeilishuo.R;
import com.sankuai.dsx.sxmeilishuo.bean.JumpResponse;
import com.sankuai.dsx.sxmeilishuo.bean.RootListResponse;
import com.sankuai.dsx.sxmeilishuo.bean.UserInfoItem;

import java.util.List;

/**
 * Created by dsx on 2016/12/2.
 * 
 */

public class HomeTJAdapter extends RecyclerView.Adapter<HomeTJAdapter.MyViewHolder> {

    private List<JumpResponse.JumpItem> mJumpItemList;
    private List<UserInfoItem> mProfessionalItemList;
    private List<RootListResponse.RootListData.RootListItem> mMainContentItemList;

    private HomeTJAdapter.MyViewHolder.HorRecyAdapter mHorRecyAdapter;

    private Context mContext;
    
    public static HomeTJAdapter tjAdapter(Context context){
        HomeTJAdapter tjAdapter = new HomeTJAdapter();
        tjAdapter.mContext = context;
        return tjAdapter;
    }

    public void setJumpItemList(List<JumpResponse.JumpItem> jumpItemList) {
        mJumpItemList = jumpItemList;
    }

    public void setProfessionalItemList(List<UserInfoItem> professionalItemList) {
        mProfessionalItemList = professionalItemList;
    }

    public void setMainContentItemList(List<RootListResponse.RootListData.RootListItem> mainContentItemList) {
        mMainContentItemList = mainContentItemList;
    }

    public MyViewHolder.HorRecyAdapter getHorRecyAdapter() {
        return mHorRecyAdapter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder;
        if (viewType == 100){
            holder = new MyViewHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.jump_content, parent,
                    false),100);
        }else if (viewType == 200){
            holder = new MyViewHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.professional_girl_item, parent,
                    false),200);
        }else {
            holder = new MyViewHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.main_content_item, parent,
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


            Glide.with(mContext).load(mProfessionalItemList.get(0).getAvatar_a()).asBitmap().into(new BitmapImageViewTarget(holder.img_one){
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.img_one.setImageDrawable(circularBitmapDrawable);
                }
            });

            Glide.with(mContext).load(mProfessionalItemList.get(1).getAvatar_a()).asBitmap().into(new BitmapImageViewTarget(holder.img_two){
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.img_two.setImageDrawable(circularBitmapDrawable);
                }
            });

            Glide.with(mContext).load(mProfessionalItemList.get(2).getAvatar_a()).asBitmap().into(new BitmapImageViewTarget(holder.img_three){
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.img_three.setImageDrawable(circularBitmapDrawable);
                }
            });

            Glide.with(mContext).load(mProfessionalItemList.get(3).getAvatar_a()).asBitmap().into(new BitmapImageViewTarget(holder.img_four){
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.img_four.setImageDrawable(circularBitmapDrawable);
                }
            });


        }else if (position > 1) {
            if (mMainContentItemList == null || mMainContentItemList.size() < 1)return;


            WindowManager manager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
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

            Glide.with(mContext).load(mMainContentItemList.get(position-2).getUinfo().getAvatar_a()).asBitmap().into(new BitmapImageViewTarget(holder.img_icon){
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.img_icon.setImageDrawable(circularBitmapDrawable);
                }
            });

            Glide.with(mContext).load(mMainContentItemList.get(position-2).getUinfo().getIdentity_img()).asBitmap().into(new BitmapImageViewTarget(holder.img_vip){
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.img_vip.setImageDrawable(circularBitmapDrawable);
                }
            });

            String centerImgUrl = mMainContentItemList.get(position-2).getPinfo().getFeed_pic_url().length() > 0 ?
                    mMainContentItemList.get(position-2).getPinfo().getFeed_pic_url() :
                    mMainContentItemList.get(position-2).getPinfo().getPost_cover();
            Glide.with(mContext).load(centerImgUrl).into(holder.img_bigCenter);

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

        public MyViewHolder(View view , int type)
        {
            super(view);
            if (type == 100){
                mSubRecyclerView = (RecyclerView) view.findViewById(R.id.id_sub_recyclerview);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
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
                        mContext).inflate(R.layout.jump_title_item, parent,
                        false));
                return holder;
            }

            @Override
            public void onBindViewHolder(final HorRecyHolder holder, int position) {
                if (position > mJumpItemList.size() - 1) return;
                Glide.with(mContext).load(mJumpItemList.get(position).getPic_url()).asBitmap().into(new BitmapImageViewTarget(holder.mCenterImg){
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(40);
                        holder.mCenterImg.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }

            @Override
            public int getItemCount() {
                return mJumpItemList == null ? 0 : mJumpItemList.size();
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
