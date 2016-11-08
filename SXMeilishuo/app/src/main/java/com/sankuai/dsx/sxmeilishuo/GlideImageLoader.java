package com.sankuai.dsx.sxmeilishuo;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by dsx on 16/10/24.
 */

public class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage(final Context context, Object path, final ImageView imageView) {
        Glide.with(context).load(path).asBitmap().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCornerRadius(0);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }
}
