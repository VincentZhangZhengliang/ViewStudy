package com.topzrt.viewstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vincent on 2017/8/11.
 */

public class CounterView extends View implements View.OnClickListener {

    private int mCenterX;
    private int mCenterH;
    private Paint mPaint;
    private int count = 1000;
    private String mText;


    public CounterView(Context context) {
        super(context);
        init();
    }

    public CounterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CounterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
        this.setOnClickListener(this);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        mText = "" + count;

    }


    @Override
    public void onClick(View view) {
        count++;
        mText = "" + count;
        postInvalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterH = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(mCenterX, mCenterH, 100, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(30);
        //将文本的宽高赋值给矩形
        Rect rect = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), rect);
        canvas.drawText(mText, mCenterX - rect.width() / 2, mCenterH + rect.height() / 2, mPaint);
    }
}
