package com.topzrt.viewstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Vincent;
 * Created on 2017/7/19;
 * DSC:
 */

public class Bezier3 extends View {


    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置

    private Paint mPaint;
    private int mCenterX, mCenterY;

    private PointF mCenter = new PointF(0, 0);
    public float mCircleRadius = 80;                  // 圆的半径
    public float mDifference = mCircleRadius * C;        // 圆形的控制点与数据点的差值
    private float[] mData = new float[8];               // 顺时针记录绘制圆形的四个数据点
    private float[] mCtrl = new float[16];              // 顺时针记录绘制圆形的八个控制点
    private float mDuration = 1000;                     // 变化总时长
    private float mCurrent = 0;                         // 当前已进行时长
    private float mCount = 100;                         // 将时长总共划分多少份
    private float mPiece = mDuration / mCount;            // 每一份的时长


    public Bezier3(Context context) {
        super(context);
    }

    public Bezier3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        /*坐标轴上的点*/
        mData[0] = 0;                                   //bottom
        mData[1] = mCircleRadius;

        mData[2] = mCircleRadius;                        //right
        mData[3] = 0;

        mData[4] = 0;                                  //top
        mData[5] = -mCircleRadius;

        mData[6] = -mCircleRadius;                       //left
        mData[7] = 0;

        /* 每个坐标轴点对应的两个控制点*/
        mCtrl[0] = mData[0] + mDifference;
        mCtrl[1] = mData[1];

        mCtrl[2] = mData[2];
        mCtrl[3] = mData[3] + mDifference;

        mCtrl[4] = mData[2];
        mCtrl[5] = mData[3] - mDifference;

        mCtrl[6] = mData[4] + mDifference;
        mCtrl[7] = mData[5];

        mCtrl[8] = mData[4] - mDifference;
        mCtrl[9] = mData[5];

        mCtrl[10] = mData[6];
        mCtrl[11] = mData[7] - mDifference;

        mCtrl[12] = mData[6];
        mCtrl[13] = mData[7] + mDifference;

        mCtrl[14] = mData[0] - mDifference;
        mCtrl[15] = mData[1];

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 4;
        mCenterY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCoordinateSystem(canvas);

        canvas.translate(mCenterX, mCenterY); // 将坐标系移动到画布中央
        canvas.scale(1, -1);                 // 翻转Y轴

        drawAuxiliaryLine(canvas);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        path.moveTo(mData[0], mData[1]);

        path.cubicTo(mCtrl[0], mCtrl[1], mCtrl[2], mCtrl[3], mData[2], mData[3]);
        path.cubicTo(mCtrl[4], mCtrl[5], mCtrl[6], mCtrl[7], mData[4], mData[5]);
        path.cubicTo(mCtrl[8], mCtrl[9], mCtrl[10], mCtrl[11], mData[6], mData[7]);
        path.cubicTo(mCtrl[12], mCtrl[13], mCtrl[14], mCtrl[15], mData[0], mData[1]);

        canvas.drawPath(path, mPaint);

    }

    public void goRight(float value) {

        mData[2] += value;
        mCtrl[2] += value;
        mCtrl[4] += value;

        invalidate();

    }

    public void goMiddle(float value) {

        mData[6] -= value;
        mCtrl[10] -= value;
        mCtrl[12] -= value;
        invalidate();

    }

    public void goleft(float value) {
//        mData[2] -= value;
//        mCtrl[2] -= value;
//        mCtrl[4] -= value;
//        invalidate();

        mData[2] = mData[2] - mCircleRadius;
        mCtrl[2] = mCtrl[2] - mCircleRadius;
        mCtrl[4] = mCtrl[4] - mCircleRadius;
        invalidate();

    }

    private void drawAuxiliaryLine(Canvas canvas) {
        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);

        for (int i = 0; i < 8; i += 2) {
            canvas.drawPoint(mData[i], mData[i + 1], mPaint);
        }

        for (int i = 0; i < 16; i += 2) {
            canvas.drawPoint(mCtrl[i], mCtrl[i + 1], mPaint);
        }

        // 绘制辅助线
        mPaint.setStrokeWidth(4);

        for (int i = 2, j = 2; i < 8; i += 2, j += 4) {
            canvas.drawLine(mData[i], mData[i + 1], mCtrl[j], mCtrl[j + 1], mPaint);
            canvas.drawLine(mData[i], mData[i + 1], mCtrl[j + 2], mCtrl[j + 3], mPaint);
        }

        canvas.drawLine(mData[0], mData[1], mCtrl[0], mCtrl[1], mPaint);
        canvas.drawLine(mData[0], mData[1], mCtrl[14], mCtrl[15], mPaint);

    }

    // 绘制坐标系
    private void drawCoordinateSystem(Canvas canvas) {
        canvas.save();                      // 绘制做坐标系

        canvas.translate(mCenterX, mCenterY); // 将坐标系移动到画布中央
        canvas.scale(1, -1);                 // 翻转Y轴

        Paint fuzhuPaint = new Paint();
        fuzhuPaint.setColor(Color.RED);
        fuzhuPaint.setStrokeWidth(5);
        fuzhuPaint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(0, -2000, 0, 2000, fuzhuPaint);
        canvas.drawLine(-2000, 0, 2000, 0, fuzhuPaint);

        canvas.restore();
    }
}
