package com.sankuai.dsx.sxmeilishuo.extension;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dsx on 2016/11/24.
 */

public class LinearSpaceItemDecoration extends RecyclerView.ItemDecoration{

    private int space;

    public LinearSpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(parent.getChildPosition(view) != 0)
            outRect.top = space;
    }
}
