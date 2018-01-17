package com.topzrt.viewstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vincent;
 * Created on 2018/1/17;
 * DSC:
 */

public class RaderView2 extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private float mR;
    private float mLength;

    private int count = 6;
    private float angle = (float) (Math.PI * 2 / count);   //60°
    private String[] titles = {"a", "b", "c", "d", "e", "f"};

    public RaderView2(Context context) {
        this(context, null);
    }

    public RaderView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2f);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mLength = (float) (Math.min(mWidth / 2, mHeight / 2) * 0.8);
        mR = mLength / (count - 1);   //每个圈之间的距离
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            float curR = mR * i;
            for (int i1 = 0; i1 < count; i1++) {
                if (i1 == 0) {
                    path.moveTo(curR, 0);
                } else {
                    float x = (float) (Math.cos(angle * i1) * curR);
                    float y = (float) (Math.sin(angle * i1) * curR);
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mPaint);
        }

        for (int i = 0; i < count; i++) {
            mPaint.setColor(Color.BLUE);
            float x = (float) (Math.cos(angle * i) * mLength);
            float y = (float) (Math.sin(angle * i) * mLength);
            canvas.drawLine(0, 0, x, y, mPaint);
        }

        //绘制文字
        mPaint.setTextSize(40);

        for (int i = 0; i < count; i++) {
            Rect rect = new Rect();
            mPaint.getTextBounds(titles[i], 0, titles[i].length(), rect);
            int width = rect.width();
            int height = rect.height();

            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            float textH = fontMetrics.descent - fontMetrics.ascent;

            float x = (float) (Math.cos(angle * i) * mLength * 1.1);
            float y = (float) (Math.sin(angle * i) * mLength * 1.1);
            canvas.drawText(titles[i], x, y, mPaint);
        }
    }
}
