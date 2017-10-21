package com.example.andya.drawpicturetest.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by andya on 2017/10/10.
 */

public class PaintView extends View{

    //        measureText(char[] text, int index, int count);
//        measureText(String text, int start, int end);
//        measureText(String text);
//        measureText(CharSequence text, int start, int end);
//        setStrokeMiter(float miter)

    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }



    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        int x =canvas.getWidth();
//
//        int y = canvas.getHeight();
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(50);
////        paint.setShadowLayer(10F, 11F,5F, Color.YELLOW);
////        paint.setTextSize(100);
//        paint.setColor(Color.rgb(255,0,0));
////        canvas.drawText("111111111",x/2,y/2,paint);
//        Path path = new Path();
//        path.lineTo(x / 2,y / 2);
//        path.lineTo(100,y / 2);
//        path.lineTo(0,0);
//        paint.setStrokeMiter(0f);
//        canvas.drawPath(path,paint);
        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLACK);

        canvas.drawColor(Color.BLUE);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);

        // x 方向上倾斜45 度
        canvas.skew(1, 0);
        mPaint.setColor(0x8800ff00);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);
    }
}
