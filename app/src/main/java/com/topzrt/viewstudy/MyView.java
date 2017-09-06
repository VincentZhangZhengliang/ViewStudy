package com.topzrt.viewstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Vincent;
 * Created on 2017/7/10;
 * DSC:     自定义view
 */

public class MyView extends View {

    private Paint mPaint = new Paint();


    private int mWidth;
    private int mHeight;
    private Picture mPicture;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
        recording();

    }

    private void recording() {
        Canvas canvas = mPicture.beginRecording(500, 500);
        canvas.translate(250, 250);
        canvas.drawCircle(0, 0, 100, mPaint);
        mPicture.endRecording();


    }

    private void initPaint() {
        mPicture = new Picture();
        mPaint.setColor(Color.GRAY);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawPoints(new float[]{500,500,
//                500,600,
//                500,700},mPaint);
        //画直线
//        canvas.drawLine(100,100,300,300,mPaint);

        //❀多条直线
//        canvas.drawLines(new float[]{
//                100,200,200,200,
//                100,300,200,300
//        },mPaint);

        //有选择的画线        跳过前面4个数据，然后从剩下的数据中选择12个数据
//        canvas.drawLines(new float[]{
//                50,50,400,50,
//                400,50,400,600,
//                400,600,50,600,
//                60,600,50,50
//        },4,12,mPaint);

        //画矩形
        //第一种
//        canvas.drawRect(50,50,300,300,mPaint);
        //第二种
//        Rect rect = new Rect(50,350,300,650);
//        canvas.drawRect(rect,mPaint);

        //画圆角矩形
//        RectF rectF = new RectF(100,100,800,400);
//        canvas.drawRoundRect(rectF,350,150,mPaint);
//        canvas.drawRect(rectF,mPaint);
        //画弧度
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF,0,90,true,mPaint);

        //画圆


//        RectF rectF =new RectF(100,100,500,500);
//        canvas.drawRect(rectF,mPaint);
//        mPaint.setColor(Color.WHITE);
//        canvas.drawArc(rectF,0,45,true,mPaint);
//
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF,45,90,true,mPaint);
//
//        mPaint.setColor(Color.CYAN);
//        canvas.drawArc(rectF,135,45,true,mPaint);
//
//        mPaint.setColor(Color.GREEN);
//        canvas.drawArc(rectF,180,180,true,mPaint);

//
//        canvas.translate(100, 100);
//        canvas.drawCircle(0, 0, 100, mPaint);
//
//
//        canvas.translate(200, 200);
//        canvas.drawCircle(0, 0, 100, mPaint);
        //画布旋转缩放，
//        canvas.translate(mWidth / 2, mHeight / 2);
//        RectF rectF = new RectF(-400, -400, 400, 400);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawRect(rectF, mPaint);
//        for (int i = 0; i < 50; i++) {
//            canvas.rotate(7.2f);
//            canvas.scale(0.9f, 0.9f);
//            canvas.drawRect(rectF, mPaint);
//        }

        //

//        canvas.translate(mWidth / 2, mHeight / 2);
//        canvas.drawCircle(0, 0, 400, mPaint);
//        canvas.drawCircle(0, 0, 380, mPaint);
//
//        for (int i = 0; i < 360; i += 10) {
//            canvas.drawLine(0, 380, 0, 400, mPaint);
//            canvas.rotate(10);
//        }

//        mPicture.draw(canvas);
        canvas.drawPicture(mPicture);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}
