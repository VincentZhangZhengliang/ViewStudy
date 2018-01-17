package com.topzrt.viewstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vincent;
 * Created on 2018/1/17;
 * DSC:
 */

public class MyTestTextView extends View {

    private Paint mPaint;

    public MyTestTextView(Context context) {
        this(context, null);
    }

    public MyTestTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.BLUE);
        canvas.drawPoint(50, 100, mPaint);
        mPaint.setColor(Color.RED);
        String str = "ABCDEFGHIJK";
        canvas.drawText(str, 60, 100, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawText(str, 2, 5, 100, 150, mPaint);

        String str1 = "SLOOP";

        canvas.drawPosText(str1,new float[]{
                50,200,
                100,250,
                150,300,
                200,350,
                250,400
        },mPaint);


    }
}
