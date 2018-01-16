package com.topzrt.viewstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.topzrt.viewstudy.PieData;

import java.util.ArrayList;

/**
 * Created by Vincent;
 * Created on 2018/1/16;
 * DSC:
 */

public class PieView2 extends View {

    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    int mWidth, mHeight;
    // 数据
    private ArrayList<PieData> mData;
    private Paint paint;

    public PieView2(Context context) {
        this(context, null);
    }

    public PieView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mData == null)
            return;
        canvas.translate(mWidth / 2, mHeight / 2);

        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);

        RectF rectF = new RectF(-r, -r, r, r);

        float currStartAngle = mStartAngle;

        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            paint.setColor(pieData.getColor());
            canvas.drawArc(rectF, currStartAngle, pieData.getAngle(), true, paint);
            currStartAngle += pieData.getAngle();
        }

    }

    public void setStartAngle(int angle) {
        this.mStartAngle = angle;
    }

    /**
     * 外部设置数据进来，
     * @param data
     */
    public void setData(ArrayList<PieData> data) {
        this.mData = data;
        initData(mData);
        invalidate();
    }

    /**
     * 对传入进来的数据处理
     * @param mData
     */
    private void initData(ArrayList<PieData> mData) {

        if (null == mData || mData.size() == 0)   // 数据有问题 直接返回
            return;

        float sumValue = 0;

        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            sumValue += pieData.getValue();
            pieData.setColor(mColors[i % mColors.length]);
        }

        float sumAngle = 0;

        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            float percentage = pieData.getValue() / sumValue;
            float angle = percentage * 360;
            pieData.setPercentage(percentage);
            pieData.setAngle(angle);
            sumAngle += angle;
        }
    }

}
