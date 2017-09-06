package com.topzrt.viewstudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vincent;
 * Created on 2017/7/13;
 * DSC:
 */

public class PathView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;


    private float currValue = 0;

    private float[] pos;
    private float[] tan;

    private Bitmap mBitmap;
    private Matrix mMatrix;

    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
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
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);

        pos = new float[2];
        tan = new float[2];

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inSampleSize = 2;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arrow1, options);
        mMatrix = new Matrix();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);

        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);
        PathMeasure measure = new PathMeasure(path, false);

        currValue += 0.005;
        if (currValue >= 1) {
            currValue = 0;
        }

        measure.getPosTan(measure.getLength() * currValue, pos, tan);

        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);

//        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);

        measure.getMatrix(measure.getLength() * currValue, mMatrix, PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);
        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);

        canvas.drawPath(path, mPaint);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
        invalidate();

    }
}
