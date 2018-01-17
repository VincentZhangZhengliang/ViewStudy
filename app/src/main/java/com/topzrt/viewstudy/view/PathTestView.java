package com.topzrt.viewstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vincent;
 * Created on 2018/1/17;
 * DSC:
 */

public class PathTestView extends View {

    private int mWidth;
    private int mHeight;
    private Paint mPaint;

    public PathTestView(Context context) {
        this(context, null);
    }

    public PathTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);

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

        //绘制坐标系
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5f);
        canvas.translate(50, 50);
        canvas.drawLine(-50, 0, 1000, 0, mPaint);
        canvas.drawLine(0, -50, 0, 1500, mPaint);
        canvas.drawLine(1000, 0, 975, 25, mPaint);
        canvas.drawLine(1000, 0, 975, -25, mPaint);
        canvas.drawLine(0, 1500, 25, 1475, mPaint);
        canvas.drawLine(0, 1500, -25, 1475, mPaint);
//        demo1(canvas);
//        demo2(canvas);
//        demo3(canvas);

//        demo4(canvas);

        Path path = new Path();
        path.lineTo(100,100);
        RectF rectF = new RectF(0,0,300,300);
        path.arcTo(rectF,0,90,false);
        canvas.drawPath(path,mPaint);

    }

    private void demo4(Canvas canvas) {
        Path path = new Path();
        path.lineTo(150,100);
        RectF rectF = new RectF(0,100,300,300);
        path.addArc(rectF,180,90);
        canvas.drawPath(path,mPaint);
    }

    private void demo3(Canvas canvas) {
        Path path = new Path();
        Path src = new Path();

        path.addRect(200, 200, 400, 400, Path.Direction.CW);
        src.addCircle(300, 200, 50, Path.Direction.CW);
        path.addPath(src);
        canvas.drawPath(path,mPaint);
    }

    /**
     * addXXX
     *
     * @param canvas
     */
    private void demo2(Canvas canvas) {
        Path path = new Path();
        path.addRect(300, 300, 600, 600, Path.Direction.CW);
        canvas.drawPath(path, mPaint);
    }

    /**
     * moveTo ，LineTo， setLastPoint
     *
     * @param canvas
     */
    private void demo1(Canvas canvas) {
        mPaint.setColor(Color.BLUE);
        Path path = new Path();
        path.lineTo(200, 200);
        path.setLastPoint(300, 300);
        path.lineTo(200, 0);
        path.close();
        canvas.drawPath(path, mPaint);
    }
}
