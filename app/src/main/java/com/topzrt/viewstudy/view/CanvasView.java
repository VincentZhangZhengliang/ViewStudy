package com.topzrt.viewstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vincent;
 * Created on 2018/1/16;
 * DSC:  画布基本操作
 */

public class CanvasView extends View {

    int mWidth, mHeight;
    private Paint paint;

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
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
        //绘制坐标
        drawCoordinates(canvas);
        //绘制矩形
        drawRec(canvas);


    }

    private void drawRec(Canvas canvas) {
        RectF rectF = new RectF(0, -200, 200, 0);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5f);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rectF, paint);

//        paint.setColor(Color.BLUE);
//        canvas.scale(-0.5f, 0.5f);
//        canvas.drawRect(rectF, paint);

        //先移动后缩放
//        paint.setColor(Color.BLUE);
//        canvas.scale(-0.5f, -0.5f,200,0);
//        canvas.drawRect(rectF, paint);
//        canvas.rotate(90);
//        paint.setColor(Color.BLUE);
//        canvas.drawRect(rectF, paint);
//
//        canvas.rotate(90);
//        paint.setColor(Color.GREEN);
//        canvas.drawRect(rectF, paint);
//
//        canvas.drawLine(0, 0, 100, 100, paint);
//        paint.setColor(Color.RED);
//        paint.setStrokeWidth(10f);
//        canvas.drawPoint(100, 100, paint);

//        canvas.drawCircle(0, 0, 400, paint);
//        canvas.drawCircle(0, 0, 380, paint);
//
//        for (int i = 0; i < 360; i += 10) {
//            canvas.drawLine(380, 0, 400, 0, paint);
//            canvas.rotate(10);
//        }


        canvas.rotate(90);
        paint.setColor(Color.GREEN);
        canvas.drawRect(rectF, paint);



    }

    private void drawCoordinates(Canvas canvas) {
        paint.setColor(Color.RED);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(-500, 0, 500, 0, paint);
        canvas.drawLine(0, -700, 0, 700, paint);
        canvas.drawLine(500, 0, 475, -25, paint);
        canvas.drawLine(500, 0, 475, 25, paint);
        canvas.drawLine(0, 700, 25, 675, paint);
        canvas.drawLine(0, 700, -25, 675, paint);
    }
}
