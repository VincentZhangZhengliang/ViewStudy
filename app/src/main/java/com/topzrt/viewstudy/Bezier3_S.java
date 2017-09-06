package com.topzrt.viewstudy;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vincent;
 * Created on 2017/7/20;
 * DSC:
 */

public class Bezier3_S extends View {

    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置
    private Paint mPaint;
    private float [] mData = new float[8];
    private float[] mCtrl = new float[16];
    public float mCircleRadius = 80;                  // 圆的半径
    public float mDifference = mCircleRadius * C;        // 圆形的控制点与数据点的差值

    public Bezier3_S(Context context) {
        super(context);
    }

    public Bezier3_S(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);




    }


}
