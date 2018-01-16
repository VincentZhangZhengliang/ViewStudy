package com.topzrt.viewstudy.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.topzrt.viewstudy.R;

/**
 * Created by Vincent;
 * Created on 2018/1/16;
 * DSC:
 */

public class PicTextView extends View {

    private Paint paint;
    private Picture picture = new Picture();
    private int width;
    private int height;
    private Bitmap bitmap;

    public PicTextView(Context context) {
        this(context, null);
    }

    public PicTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        recording();
        initPaint();
        initBitmap();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    private void initBitmap() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timg);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    private void recording() {
        Canvas canvas = picture.beginRecording(500, 500);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.translate(250, 250);
        canvas.drawCircle(0, 0, 100, paint);
        picture.endRecording();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        PictureDrawable drawable = new PictureDrawable(picture);
//        drawable.setBounds(-150, 0, 100, picture.getHeight());
//        drawable.draw(canvas);
//        canvas.drawBitmap(bitmap, new Matrix(), paint);

        Rect src = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Rect dst = new Rect(0, 0, 200, 200);
        canvas.drawBitmap(bitmap, src, dst, paint);


    }
}
