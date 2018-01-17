package com.topzrt.viewstudy;

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
 * Package_Name com.topzrt.viewstudy
 * Project_Name ViewStudy
 * Created by Vincent
 * Created on 2017/9/7
 * Desc：
 */

public class PathTestView extends View {

    private int   mWidth;
    private int   mHeight;
    private Paint mPaint;
    private int   count = 6;                //数据个数
    private float angle = (float) (Math.PI * 2 / count);   //60°

    private float radius;                   //网格最大半径
    private int   centerX;                  //中心X
    private int   centerY;                  //中心Y
    private String[] titles   = {"a", "b", "c", "d", "e", "f"};
    private double[] data     = {100, 60, 60, 60, 100, 50, 10, 20}; //各维度分值
    private float    maxValue = 100;             //数据最大值
    private float mR;               //r是蜘蛛丝之间的间距
    private Paint mValuePaint;

    public PathTestView(Context context) {
        super(context);
        init();
    }

    public PathTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {


        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);


        mValuePaint = new Paint();
        mValuePaint.setColor(Color.BLUE);
        mValuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mValuePaint.setStrokeWidth(2);
        mValuePaint.setAntiAlias(true);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
        radius = Math.min(w, h) / 2 * 0.9f;
        //r是蜘蛛丝之间的间距
        mR = radius / (count - 1);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        //画蜘蛛网络
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            float r = i * mR;
            for (int i1 = 0; i1 < count; i1++) {
                if (i1 == 0) {
                    path.moveTo(r, 0);
                } else {
                    float x = (float) (r * Math.cos(angle * i1));
                    float y = (float) (r * Math.sin(angle * i1));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mPaint);
        }

        //画对角线
        for (int i = 0; i < count; i++) {
            mPaint.setColor(Color.BLACK);
            float x = (float) (radius * Math.cos(angle * i));
            float y = (float) (radius * Math.sin(angle * i));
            canvas.drawLine(0, 0, x, y, mPaint);
        }

        //画文字

        //画对角线
        for (int i = 0; i < count; i++) {
            mPaint.setColor(Color.RED);
            mPaint.setTextSize(40);
            float  x    = (float) (1.05 * radius * Math.cos(angle * i));
            float  y    = (float) (1.05 * radius * Math.sin(angle * i));
            String text = titles[i];
            Rect   rect = new Rect();
            mPaint.getTextBounds(text, 0, text.length(), rect);
            canvas.drawText(titles[i], x, y, mPaint);
        }

        //覆盖区域

        Path path1 = new Path();

        for (int i = 0; i < count; i++) {

            double percent = data[i] / maxValue;

            float x = (float) (radius * Math.cos(angle * i) * percent);
            float y = (float) (radius * Math.sin(angle * i) * percent);
            mValuePaint.setAlpha(255);
            if (i == 0) {
                path1.moveTo(radius, 0);
                canvas.drawCircle(radius, 0, 10, mValuePaint);
            } else {
                path1.lineTo(x, y);
                canvas.drawCircle(x, y, 10, mValuePaint);
            }

            mValuePaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path1, mValuePaint);

            mValuePaint.setAlpha(127);
            mValuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawPath(path1, mValuePaint);


        }


    }
}
