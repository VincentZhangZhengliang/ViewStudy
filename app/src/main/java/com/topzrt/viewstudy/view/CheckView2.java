package com.topzrt.viewstudy.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.topzrt.viewstudy.R;

/**
 * Created by Vincent;
 * Created on 2018/1/17;
 * DSC:
 */

public class CheckView2 extends View {

    private static final int ANIM_NULL = 0;         //动画状态-没有
    private static final int ANIM_CHECK = 1;        //动画状态-开启
    private static final int ANIM_UNCHECK = 2;      //动画状态-结束

    private Bitmap mBitmap;
    private int mWidth;
    private int mHeight;
    private Paint mPaint;

    private int animCurrPage = -1;          //当前页码
    private int animMaxPage = 13;           //当前页码
    private int animDuration = 500;         //动画时长
    private int animStatus = ANIM_NULL;     //动画状态

    private boolean isCheck = false;        // 是否只选中状态

    public CheckView2(Context context) {
        this(context, null);
    }

    public CheckView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.checkmark);
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

        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, 240, mPaint);
        int sideLength = mBitmap.getHeight();
        Rect src = new Rect(animCurrPage * sideLength, 0, sideLength * (animCurrPage + 1), sideLength);
        Rect dst = new Rect(-200, -200, 200, 200);
        canvas.drawBitmap(mBitmap, src, dst, mPaint);

    }

    public void check() {
        if (animStatus != ANIM_NULL || isCheck)
            return;
        animStatus = ANIM_CHECK;
        animCurrPage = 0;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = true;
    }

    public void uncheck() {
        if (animStatus != ANIM_NULL || !isCheck)
            return;
        animStatus = ANIM_UNCHECK;
        animCurrPage = animMaxPage - 1;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = false;

    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (animCurrPage < animMaxPage && animCurrPage >= 0) {
                invalidate();
                if (animStatus == ANIM_NULL)
                    return;
                if (animStatus == ANIM_CHECK) {
                    animCurrPage++;
                } else if (animStatus == ANIM_UNCHECK) {
                    animCurrPage--;
                }
                this.sendEmptyMessageDelayed(0, animStatus / animMaxPage);
            } else {
                if (isCheck) {
                    animCurrPage = animMaxPage - 1;
                } else {
                    animCurrPage -= 1;
                }
                invalidate();
                animStatus = ANIM_NULL;
            }

        }
    };


}
