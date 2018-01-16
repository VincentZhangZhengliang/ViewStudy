package com.topzrt.viewstudy;

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
 * DSC:
 */

public class BasicShapeView extends View {

    private int centerX;
    private int centerY;
    private Paint paint;

    public BasicShapeView(Context context) {
        this(context, null);
    }

    public BasicShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BasicShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {

        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10f);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将原点移动到中间
        canvas.translate(centerX, centerY);

        paint.setColor(Color.RED);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(0, 0, paint);

        //画点
        paint.setColor(Color.BLUE);
        canvas.drawPoint(50, 50, paint);

        //画多点
        paint.setColor(Color.BLACK);
        float[] points = new float[]{50, 100, 50, 150, 50, 200};
        canvas.drawPoints(points, paint);

        //画坐标
        paint.setColor(Color.RED);
        canvas.drawLine(-50, 0, 400, 0, paint);
        canvas.drawLine(400, 0, 375, -25, paint);
        canvas.drawLine(400, 0, 375, 25, paint);

        canvas.drawLine(0, -50, 0, 400, paint);
        canvas.drawLine(0, 400, 25, 375, paint);
        canvas.drawLine(0, 400, -25, 375, paint);

        //画矩形
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        paint.setAntiAlias(true);
        RectF rectF = new RectF(100, 50, 200, 100);
        canvas.drawRoundRect(rectF, 10f, 10f, paint);

        //画椭圆
        RectF rectFOval = new RectF(100, 150, 200, 200);
        paint.setColor(Color.BLACK);
        canvas.drawRect(rectFOval, paint);
        paint.setColor(Color.RED);
        canvas.drawOval(rectFOval, paint);

        //画圆
        canvas.drawCircle(150, 300, 50, paint);

        //画圆弧
        RectF rectFArc = new RectF(250, 50, 350, 100);
        canvas.drawRect(rectFArc,paint);
        canvas.drawArc(rectFArc,0,90,false,paint);



    }

}
