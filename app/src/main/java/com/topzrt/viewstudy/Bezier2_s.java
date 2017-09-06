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

public class Bezier2_s extends View {

    private Paint mPaint;

    private int centerX, centerY;

    private PointF start, end, ctrl1, ctrl2;

    private boolean mode = false;

    public Bezier2_s(Context context) {
        super(context);
    }

    public Bezier2_s(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        ctrl1 = new PointF(0, 0);
        ctrl2 = new PointF(0, 0);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(ctrl1.x, ctrl1.y, mPaint);
        canvas.drawPoint(ctrl2.x, ctrl2.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);

        mPaint.setStrokeWidth(5);
        canvas.drawLine(start.x, start.y, ctrl1.x, ctrl1.y, mPaint);
        canvas.drawLine(ctrl1.x, ctrl1.y, ctrl2.x, ctrl2.y, mPaint);
        canvas.drawLine(ctrl2.x, ctrl2.y, end.x, end.y, mPaint);

        mPaint.setColor(Color.RED);

        Path path = new Path();
        path.moveTo(start.x, start.y);

        path.cubicTo(ctrl1.x, ctrl1.y, ctrl2.x, ctrl2.y, end.x, end.y);

        canvas.drawPath(path, mPaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = w / 2;
        centerY = h / 2;

        start.x = centerX - 200;
        start.y = centerY;

        ctrl1.x = centerX;
        ctrl1.y = centerY - 100;

        ctrl2.x = centerX;
        ctrl2.y = centerY - 100;

        end.x = centerX + 200;
        end.y = centerY;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (mode) {
            ctrl1.x = event.getX();
            ctrl1.y = event.getY();
        } else {
            ctrl2.x = event.getX();
            ctrl2.y = event.getY();
        }
        invalidate();
        return true;
    }
}
