package com.topzrt.viewstudy;

import android.content.Context;
import android.drm.DrmErrorEvent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.logging.Level;

/**
 * Created by Vincent on 2017/8/11.
 */

public class LockView extends View {

    private Bitmap mC;
    private Bitmap mL;
    private int mMeasuredWidth;
    private int mMeasuredHeight;
    private int mLWidth;
    private int mLHeight;
    private int mCWidth;
    private int mCHeight;
    private int mLeft;
    private int mTop;

    private int currX;
    private int currY;
    private float mDownX;
    private float mDownY;
    private int mCX;
    private int mCY;
    private int mRight;

    public LockView(Context context) {
        super(context);
        init();
    }

    public LockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //滑块
        mC = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        mL = BitmapFactory.decodeResource(getResources(), R.drawable.l);

        mCWidth = mC.getWidth();
        Log.e("Vincent", "init: mCWidth : " + mCWidth);
        mCHeight = mC.getHeight();

        mLWidth = mL.getWidth();
        Log.e("Vincent", "init: mLWidth : " + mLWidth);

        mLHeight = mL.getHeight();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mMeasuredWidth = getMeasuredWidth();        //当前布局的宽度
        mMeasuredHeight = getMeasuredHeight();

        mLeft = mMeasuredWidth / 2 - mLWidth / 2;
        mTop = mMeasuredHeight / 2 - mLHeight / 2;

        currX = mLeft;      //当前背景的left
        currY = mTop;

        mCX = currX + mCWidth / 2;  //当前滑块的中心点
        mCY = currY + mCHeight / 2;

        mRight = mMeasuredHeight / 2 + mLWidth / 2 - mCWidth;       //滑块能滑到的最右边的位置
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mL, mLeft, mTop, null);

        Log.e("Vincent", "onDraw: currX :  " + currX);
        if (currX < mLeft) {
            currX = mLeft;
        } else if (currX > mRight) {
            Log.e("Vincent", "onDraw: currX : " + currX + " ,mRight : " + mRight);
            currX = mRight;
        }
        Log.e("Vincent", "onDraw: currX++++++++++ :  " + currX);
        canvas.drawBitmap(mC, currX, mTop, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                boolean onBlock = isOnBlock();
                if (onBlock) {
                    //讲滑块的中心点滑倒当前位置
                    Toast.makeText(getContext(), "按倒了滑块", Toast.LENGTH_SHORT).show();
                    mCX = (int) mDownX;
                    currX = (int) mDownX;  //当前滑块左上角的坐标
                    
                    invalidate();
                } else {

                    Toast.makeText(getContext(), "没按倒了滑块", Toast.LENGTH_SHORT).show();
                }

                break;

            case MotionEvent.ACTION_MOVE:

                if (isOnBlock()) {
                    float moveX = event.getX();
                    float moveY = event.getY();

                    currX = (int) (moveX - mCWidth / 2);
                    currY = (int) (moveY - mCHeight / 2);
                    invalidate();
                }

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                break;
        }

        return true;
    }

    private boolean isOnBlock() {
        double sqrt = Math.sqrt((mDownX - mCX) * (mDownX - mCX) + (mDownY - mCY) * (mDownY - mCY));
        if (sqrt <= mCWidth / 2) {
            return true;
        } else {
            return false;
        }
    }
}
