package com.topzrt.viewstudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.topzrt.viewstudy.R;

/**
 * Created by Vincent;
 * Created on 2018/1/25;
 * DSC:
 */

public class DashLine extends View {

    private float mDash_line_length;
    private float mDash_line_width;
    private float mDash_width;
    private int   mDash_color;

    private static final float DEFAULT_DASH_WIDTH       = 5;
    private static final float DEFAULT_DASH_LINE_WIDTH  = 10;
    private static final float DEFAULT_DASH_LINE_LENGTH = 10;
    private static final int   DEFAULT_DASH_COLOR       = Color.RED;
    private Paint mPaint;

    private float   totalHeight = 0;
    private float[] mPoints     = new float[]{0, 0, 0, totalHeight};
    private int mLineWidth;
    private int mLineLength;
    ;


    public DashLine(Context context) {
        this(context, null);
    }

    public DashLine(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DashLine);
        mDash_line_length = typedArray.getDimension(R.styleable.DashLine_dash_line_length, DEFAULT_DASH_LINE_LENGTH);
        mDash_line_width = typedArray.getDimension(R.styleable.DashLine_dash_line_width, DEFAULT_DASH_LINE_WIDTH);
        mDash_width = typedArray.getDimension(R.styleable.DashLine_dash_width, DEFAULT_DASH_WIDTH);
        mDash_color = typedArray.getColor(R.styleable.DashLine_dash_color, DEFAULT_DASH_COLOR);
        typedArray.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(mDash_line_width);
        mPaint.setColor(DEFAULT_DASH_COLOR);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mLineWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        mLineLength = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getPaddingLeft(), getPaddingTop());
        canvas.save();

        while (totalHeight < mLineLength) {
            canvas.drawLines(mPoints, mPaint);
            totalHeight = totalHeight + mDash_line_length + mDash_width;
            canvas.translate(0,mDash_line_length + mDash_width);
        }
    }
}
