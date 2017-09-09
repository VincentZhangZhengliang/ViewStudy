package com.topzrt.viewstudy;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * Package_Name com.topzrt.viewstudy
 * Project_Name ViewStudy
 * Created by Vincent
 * Created on 2017/9/8
 * Desc：
 */

public class MySearchView extends View {


    private int     mWidth;
    private int     mHeight;
    private Paint   mPaint;
    private int     mRadius;
    private Handler mAnimatorHandler;


    private State mCurrState = State.NONE;
    private ValueAnimator                        mStartingAnimator;
    private ValueAnimator                        mSearchingAnimator;
    private ValueAnimator                        mEndingAnimator;
    private ValueAnimator.AnimatorUpdateListener mUpdateListener;
    private Animator.AnimatorListener            mAnimatorListener;
    private float                                mAnimatedValue;
    private Path                                 mSearch_path;
    private Path                                 mCircle_path;
    private PathMeasure                          mPathMeasure;
    private boolean isOver = false;
    private int     count  = 0;

    public MySearchView(Context context) {
        this(context, null);

    }

    public MySearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public MySearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        initPaint();
        initPath();
        initListener();
        initHandler();
        initAnimator();
        mCurrState = State.STARTING;
        mStartingAnimator.start();

    }

    // 默认的动效周期 2s
    private int defaultDuration = 2000;

    private void initAnimator() {

        mStartingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        mSearchingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        mEndingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);

        mStartingAnimator.addUpdateListener(mUpdateListener);
        mSearchingAnimator.addUpdateListener(mUpdateListener);
        mEndingAnimator.addUpdateListener(mUpdateListener);

        mStartingAnimator.addListener(mAnimatorListener);
        mSearchingAnimator.addListener(mAnimatorListener);
        mEndingAnimator.addListener(mAnimatorListener);

    }

    @SuppressLint("HandlerLeak")
    private void initHandler() {

        mAnimatorHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (mCurrState) {
                    case STARTING:
                        isOver = false;
                        mCurrState = State.SEARCHING;
                        mStartingAnimator.removeAllUpdateListeners();
                        mSearchingAnimator.start();
                        break;

                    case SEARCHING:
                        if (!isOver) {
                            mSearchingAnimator.start();
                            count++;
                            if (count > 1) {
                                isOver = true;
                            }
                        } else {
                            mCurrState = State.ENDING;
                            mEndingAnimator.start();
                        }
                        break;

                    case ENDING:
                        mCurrState = State.NONE;
                        break;
                }
            }
        };
    }

    private void initListener() {

        mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mAnimatedValue = (float) valueAnimator.getAnimatedValue();
                invalidate();

            }
        };

        mAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mAnimatorHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        };


    }

    private void initPath() {
        mSearch_path = new Path();
        mCircle_path = new Path();
        mPathMeasure = new PathMeasure();

        RectF rectF1 = new RectF(-50, -50, 50, 50);
        mSearch_path.addArc(rectF1, 45, 359.9f);

        RectF rectF2 = new RectF(-100, -100, 100, 100);
        mCircle_path.addArc(rectF2, 45, -359.9f);

        mPathMeasure.setPath(mCircle_path, true);

        float[] pos = new float[2];

        mPathMeasure.getPosTan(0, pos, null);

        mSearch_path.lineTo(pos[0], pos[1]);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
    }


    public enum State {

        NONE,
        STARTING,
        SEARCHING,
        ENDING

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
        mRadius = Math.min(w, h) / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSearch(canvas);
    }

    private void drawSearch(Canvas canvas) {

        mPaint.setColor(Color.WHITE);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawColor(Color.parseColor("#0082D7"));
        switch (mCurrState) {

            case NONE:
                canvas.drawPath(mSearch_path, mPaint);
                break;

            case STARTING:

                mPathMeasure.setPath(mSearch_path, false);
                Path dst = new Path();
                mPathMeasure.getSegment(mPathMeasure.getLength() * mAnimatedValue,
                        mPathMeasure.getLength(),
                        dst,
                        true);
                canvas.drawPath(dst, mPaint);
                break;

            case SEARCHING:

                mPathMeasure.setPath(mCircle_path, false);
                float stop = mPathMeasure.getLength() * mAnimatedValue;
                float start = (float) (stop - ((0.5 - Math.abs(mAnimatedValue - 0.5)) * 200f));
                Path dst2 = new Path();
                mPathMeasure.getSegment(start, stop, dst2, true);
                canvas.drawPath(dst2, mPaint);
                break;

            case ENDING:

                mPathMeasure.setPath(mSearch_path, false);

                Path dst3 = new Path();

                /*  getSegment
                *
                * 这个方法返回boolean，如果截取的长度为0则返回false，否则为true。参数意义如下
                   startD：起始距离
                    stopD：终点距离
                    dst：接收截取的path
                    startWithMoveTo：是否把截取的path，moveto到起始点。
                * */


                mPathMeasure.getSegment(mPathMeasure.getLength() - mPathMeasure.getLength() * mAnimatedValue,
                        mPathMeasure.getLength(),
                        dst3, true);


                canvas.drawPath(dst3, mPaint);

                break;
        }

    }


}
