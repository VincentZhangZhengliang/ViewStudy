package com.topzrt.viewstudy;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Package_Name com.topzrt.viewstudy
 * Project_Name ViewStudy
 * Created by Vincent
 * Created on 2017/10/31
 * Desc：
 */

public class Week extends ViewGroup {

    private Scroller mScroller;
    private int      mTouchSlop;
    private int      mChildHeight;
    private int      itemWidth;

    public Week(Context context) {
        this(context, null);
    }

    public Week(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Week(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightSpecMode == MeasureSpec.AT_MOST) {
            for (int i = 0; i < getChildCount(); i++) {
                mChildHeight = (int) (getChildAt(0).getMeasuredHeight() * 1.5);
            }
            setMeasuredDimension(widthSpecSize, mChildHeight);
        } else {
            setMeasuredDimension(widthSpecSize, heightSpecSize);
        }

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        itemWidth = getWidth() / 5; //每行显示五个
        int right,bottom,left;
    }
}
