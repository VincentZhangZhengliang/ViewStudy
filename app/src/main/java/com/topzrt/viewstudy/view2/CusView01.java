package com.topzrt.viewstudy.view2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vincent;
 * Created on 2018/1/18;
 * DSC:
 */

public class CusView01 extends View implements Runnable {

    private int mWidth;
    private int mHeight;

    private int radiu = 10;
    private Paint mPaint;

    public CusView01(Context context) {
        this(context, null);
    }

    public CusView01(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inti();
    }

    private void inti() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2f);
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
        canvas.drawCircle(mWidth / 2, mHeight / 2, radiu, mPaint);
    }

    @Override
    public void run() {
        while (true) {
            if (radiu <= 200) {
                radiu += 10;
                postInvalidate();
            } else {
                radiu = 0;
            }
            SystemClock.sleep(100);
        }
    }
}
