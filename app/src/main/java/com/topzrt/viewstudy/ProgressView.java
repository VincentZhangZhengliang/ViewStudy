package com.topzrt.viewstudy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Map;


/**
 * Created by Vincent on 2017/8/11.
 */

public class ProgressView extends View {

    private Paint mPaint;
    private int mCenterX;
    private int mCenterY;
    private int radius;

    private int progress = 0;

    public ProgressView(Context context) {
        super(context);
        init();
    }


    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(40);
        radius = 100;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        canvas.drawCircle(100, 100, 100, mPaint);

//        RectF rectF = new RectF(mCenterX - radius, mCenterY - radius, mCenterX + radius, mCenterY + radius);
        RectF rectF = new RectF(0, 0, 200, 200);

        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(10);

        canvas.drawArc(rectF, 0, progress * 360 / 100, true, mPaint);

        String text = progress + "%";
        Rect bounds = new Rect();
        mPaint.setStrokeWidth(0);
        mPaint.setColor(Color.BLACK);
        mPaint.getTextBounds(text, 0, text.length(), bounds);
        canvas.drawText(text, 100 - bounds.width() / 2, 100 + bounds.height() / 2, mPaint);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }


    public void start() {

        progress = 0;
        mHandler.sendEmptyMessageDelayed(0, 100);

    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {

                if (progress < 100) {
                    progress++;
                    invalidate();
                    sendEmptyMessageDelayed(0, 100);
                }
            }
        }
    };
}
