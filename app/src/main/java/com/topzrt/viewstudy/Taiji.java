package com.topzrt.viewstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Package_Name com.topzrt.viewstudy
 * Project_Name ViewStudy
 * Created by Vincent
 * Created on 2017/9/6
 * Desc：
 */

public class Taiji extends View {

    private Paint whitePaint;
    private Paint blackPaint;
    private int mCenterX;
    private int mCenterY;
    private int mRadius;
    private float degree = 0;

    public Taiji(Context context) {
        super(context);
        init();
    }

    public Taiji(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Taiji(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {

        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setColor(Color.WHITE);

        blackPaint = new Paint(whitePaint);
        blackPaint.setColor(Color.BLACK);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCenterX = w / 2;
        mCenterY = h / 2;

        mRadius = Math.min(w, h) / 2;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        canvas.translate(mCenterX, mCenterY);

        canvas.rotate(degree);

        canvas.drawArc(-mRadius, -mRadius, mRadius, mRadius,
                90, 180, true, blackPaint);
        canvas.drawArc(-mRadius, -mRadius, mRadius, mRadius,
                -90, 180, true, whitePaint);


        canvas.drawArc(-mRadius / 2, -mRadius, mRadius / 2,
                0, -90, 180, true, blackPaint);

        canvas.drawArc(-mRadius / 2, 0, mRadius / 2, mRadius,
                90, 180, true, whitePaint);

        canvas.drawArc(-mRadius / 8, -5 * mRadius / 8, mRadius / 8, -3 * mRadius / 8,
                0, 360, true, whitePaint);
        canvas.drawArc(-mRadius / 8, 3 * mRadius / 8, mRadius / 8, 5 * mRadius / 8,
                0, 360, true, blackPaint);

    }


    public void setDegree(float degree) {
        this.degree = degree;
//        invalidate();
        postInvalidate();
    }
}


