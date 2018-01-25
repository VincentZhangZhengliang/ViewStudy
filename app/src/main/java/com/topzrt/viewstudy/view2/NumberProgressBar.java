package com.topzrt.viewstudy.view2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vincent;
 * Created on 2018/1/18;
 * DSC:
 */

public class NumberProgressBar extends View{

    public NumberProgressBar(Context context) {
        this(context,null);
    }

    public NumberProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public NumberProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public float dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public float sp2px(float sp) {
        final float scale = getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

}
