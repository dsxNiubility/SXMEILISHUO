package com.sankuai.dsx.sxmeilishuo;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;

/**
 * Created by dsx on 2016/11/8.
 */

public class RoundedTransformation implements Transformation {
    private final int radius;
    private final int margin; // dp

    // radius is corner radii in dp
    // margin is the board in dp
    public RoundedTransformation(final int radius, final int margin) {
        this.radius = radius;
        this.margin = margin;
    }

//    @Override
//    public Bitmap transform(final Bitmap source) {
//        final Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP,
//                Shader.TileMode.CLAMP));
//
//        Bitmap output = Bitmap.createBitmap(source.getWidth(),
//                source.getHeight(), Config.ARGB_8888);
//        Canvas canvas = new Canvas(output);
//        canvas.drawRoundRect(new RectF(margin, margin, source.getWidth()
//                - margin, source.getHeight() - margin), radius, radius, paint);
//
//        if (source != output) {
//            source.recycle();
//        }
//
//        return output;
//    }

    @Override
    public Resource transform(Resource resource, int outWidth, int outHeight) {
        final Paint paint = new Paint();

        BitmapDrawable bd = (BitmapDrawable)resource;
        Bitmap source = bd.getBitmap();

        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP));

        Bitmap output = Bitmap.createBitmap(source.getWidth(),
                source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawRoundRect(new RectF(margin, margin, source.getWidth()
                - margin, source.getHeight() - margin), radius, radius, paint);

        if (source != output) {
            source.recycle();
        }

        Drawable drawable = new BitmapDrawable(output);

        return (Resource) drawable;
    }

    @Override
    public String getId() {
        return "rounded";
    }
}
