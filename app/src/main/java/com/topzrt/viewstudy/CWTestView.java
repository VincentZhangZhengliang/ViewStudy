package com.topzrt.viewstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Package_Name com.topzrt.viewstudy
 * Project_Name ViewStudy
 * Created by Vincent
 * Created on 2017/9/7
 * Desc：
 */

public class CWTestView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public CWTestView(Context context) {
        this(context, null);
//        super(context);
    }

    public CWTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
//        super(context, attrs);
    }


    public CWTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

    }

    private void init() {

        mPaint = new Paint();

        mPaint.setColor(Color.BLACK);

        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setAntiAlias(true);

        mPaint.setStrokeWidth(2);


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.translate(mWidth / 2, mHeight / 2);

        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addCircle(0, 0, 200, Path.Direction.CW);

        path2.addRect(0, -200, 200, 200, Path.Direction.CCW);
//
        path3.addCircle(0, -100, 100, Path.Direction.CW);
//
        path4.addCircle(0, 100, 100, Path.Direction.CW);
//
        path1.op(path2, Path.Op.DIFFERENCE);

        path1.op(path3, Path.Op.UNION);
//
        path1.op(path4, Path.Op.DIFFERENCE);

        canvas.drawPath(path1, mPaint);

    }
}
