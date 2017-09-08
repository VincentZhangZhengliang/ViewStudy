package com.topzrt.viewstudy;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Package_Name com.topzrt.viewstudy
 * Project_Name ViewStudy
 * Created by Vincent
 * Created on 2017/9/6
 * Desc：
 */

public class TestView extends View {

    private static final String TAG = "Vincent";
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private Path mPath;
    float[] pos = new float[2];
    float[] tan = new float[2];

    private boolean isfirst = true;

    public TestView(Context context) {
//        super(context);
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);

    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPath = new Path();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);

        mPath.moveTo(0, 0);
        mPath.quadTo(mWidth * 1.0f, 0, 0, mHeight * 1f);

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


        mPaint.setStrokeWidth(20);
        canvas.drawPoint(0, 0, mPaint);
        canvas.drawPoint(mWidth * 1f, mHeight * 1f, mPaint);
        canvas.drawPoint(0, mHeight * 1f, mPaint);
        canvas.drawPoint(mWidth * 1f, mHeight * 0.3f, mPaint);
//        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        canvas.drawLine(0, 0, mWidth * 1f, mHeight * 0.3f, mPaint);
        canvas.drawLine(0, 0, mWidth * 1f, mHeight * 0.3f, mPaint);
        canvas.drawLine(mWidth * 1f, mHeight * 0.3f, mWidth * 1f, mHeight * 1f, mPaint);
        canvas.drawLine(mWidth * 1f, mHeight * 1f, 0, mHeight * 1f, mPaint);

        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.RED);
        mPath.moveTo(0, 0);
//        mPath.quadTo(mWidth * 1.0f, 0, 0, mHeight * 1f);
        mPath.cubicTo(mWidth * 1f, mHeight * 0.3f, mWidth * 1f, mHeight * 1f, 0, mHeight * 1f);
        canvas.drawPath(mPath, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(40);
        canvas.drawCircle(pos[0], pos[1], 20, mPaint);


    }


    public void start() {
        final PathMeasure pathMeasure = new PathMeasure(mPath, false);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());

        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float value = (Float) valueAnimator.getAnimatedValue();
                pathMeasure.getPosTan(value, pos, null);
                postInvalidate();
            }
        });

        valueAnimator.start();
    }


}
