package com.topzrt.viewstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vincent;
 * Created on 2017/7/12;
 * DSC:
 */

public class MyTextView extends View {

    private int mWidth, mHeight;        // 宽高
    private Paint mPaint;
    private char[] mChars;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(50);

        mChars = "ABCDEFGHIJK".toCharArray();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawText(mChars, 4, 4, 0, 200, mPaint);


        canvas.drawPosText("ABCDE",
                new float[]{
                        100, 100,
                        200, 200,
                        300, 300,
                        400, 400,
                        500, 500},
                mPaint);
    }
}
