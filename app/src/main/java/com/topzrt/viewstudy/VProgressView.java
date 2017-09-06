package com.topzrt.viewstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vincent on 2017/8/11.
 */

public class VProgressView extends View {

    private int mCenterX;
    private int mCenterY;
    private Paint mPaint;
    private String mText = "";
    private int max;
    private int progress;

    public VProgressView(Context context) {
        super(context);
        init();
    }

    public VProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCenterX = w / 2;
        mCenterY = h / 2;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
        mPaint.setColor(Color.RED);
        canvas.drawCircle(mCenterX, mCenterY, mCenterX, mPaint);

        //画弧线
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(10);
        RectF rectF = new RectF(mCenterX - mCenterX, mCenterY - mCenterX, mCenterX + mCenterX, mCenterY + mCenterX);
        canvas.drawArc(rectF, 0, 360 * progress / max, false, mPaint);

        //画文本
        mText = 100*progress/max + "%";
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(0);
        mPaint.setTextSize(40);
        Rect rect = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), rect);
        canvas.drawText(mText, mCenterX - rect.width() / 2, mCenterY - rect.height() / 2, mPaint);

    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        postInvalidate();
    }

}
