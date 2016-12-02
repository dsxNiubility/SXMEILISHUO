package com.sankuai.dsx.platform.Utils;

import android.content.Context;
import android.util.TypedValue;

import com.sankuai.dsx.platform.Base.BaseApplication;

/**
 * Created by dsx on 2016/12/2.
 */

public class DipUtils {

    /**
     * 根据dip值转化成px值
     *
     * @param dip 锚点
     * @return 像素
     */
    public static int dipToPix(float dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, BaseApplication.application().getResources().getDisplayMetrics());
    }
}
