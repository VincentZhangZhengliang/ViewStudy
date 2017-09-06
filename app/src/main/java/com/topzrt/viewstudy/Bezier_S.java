package com.topzrt.viewstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Vincent;
 * Created on 2017/7/20;
 * DSC:
 */

public class Bezier_S extends View {

    private int centerX, centerY;

    private Paint mPaint;

    private PointF start, end, control;

    public Bezier_S(Context context) {
        super(context);
    }

    public Bezier_S(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control = new PointF(0, 0);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        //初始化点的位置

        start.x = centerX + 200;
        start.y = centerY;

        end.x = centerX - 200;
        end.y = centerY;

        control.x = centerX;
        control.y = centerY - 200;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(30);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control.x, control.y, mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(5);
        canvas.drawLine(start.x, start.y, control.x, control.y, mPaint);
        canvas.drawLine(end.x, end.y, control.x, control.y, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(start.x, start.y);

        path.quadTo(control.x, control.y, end.x, end.y);

        canvas.drawPath(path, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        control.x = (int) event.getX();
        control.y = (int) event.getY();
        invalidate();
        return true;

    }
}
