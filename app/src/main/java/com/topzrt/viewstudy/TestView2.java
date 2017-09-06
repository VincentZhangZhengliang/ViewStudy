package com.topzrt.viewstudy;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Package_Name com.topzrt.viewstudy
 * Project_Name ViewStudy
 * Created by Vincent
 * Created on 2017/9/6
 * Desc：
 */

public class TestView2 extends View {

    private static final String TAG = "Vincent";
    private String mName;
    private int mColor;

    public TestView2(Context context) {
//        super(context);
        this(context, null);
    }

    public TestView2(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);

    }

    public TestView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TestView2);
        //获取 TestView2 中的name属性
        mName = ta.getString(R.styleable.TestView2_name);
        //获取 TestView2 中的mColor
        mColor = ta.getColor(R.styleable.TestView2_mColor, Color.BLACK);
        //回收ta，防止内存泄漏
        ta.recycle();


        for (int i = 0; i < attrs.getAttributeCount(); i++) {

            String name = attrs.getAttributeName(i);
            String value = attrs.getAttributeValue(i);
            Log.e(TAG, "TestView2: name : " + name + " , value : " + value);

        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(mColor);
    }
}
