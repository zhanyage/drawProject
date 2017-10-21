package com.example.andya.drawpicturetest.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by andya on 2017/10/11.
 */

public class PathViewTest extends View{



    float dx = 0f;   //设置x改变的来让水动起来
    private Path beisaierPath;
    private int screenWidth;
    private int screenHeigth;
    private Paint mPaint;


    public PathViewTest(Context context) {
        super(context);
        init();
    }

    public PathViewTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathViewTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);
//        mPaint.setStyle(Paint.Style.STROKE);
        beisaierPath = new Path();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 根据触摸位置更新控制点，并提示重绘
//        control.x = event.getX();
//        control.y = event.getY();
        ValueAnimator tanqiu = ValueAnimator.ofFloat(0,1000);
        tanqiu.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (float)animation.getAnimatedValue();
                invalidate();
            }
        });
        tanqiu.setDuration(4000);
        tanqiu.start();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        updatePath();

        canvas.drawPath(beisaierPath, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            screenWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            screenHeigth = heightSize;
        } else {
            screenHeigth = 800;
        }

    }


    private void updatePath(){

        beisaierPath.reset();
        beisaierPath.moveTo(-dx,screenHeigth / 2);
        beisaierPath.cubicTo(-(screenWidth / 4 - dx),screenHeigth / 2 - 70,screenWidth / 4 * 3 - dx,screenHeigth / 2 + 70,screenWidth - dx,screenHeigth / 2);
        beisaierPath.cubicTo(screenWidth / 4 * 5 - dx,screenHeigth / 2 + 70,screenWidth * 7 / 4 - dx,screenHeigth / 2 - 70,screenWidth * 2 - dx,screenHeigth / 2);
//        beisaierPath.quadTo(screenWidth / 4 * 3 + dx + screenWidth / 2,screenHeigth / 2 - 50,screenWidth  + dx + screenWidth / 2,screenHeigth / 2);
        beisaierPath.lineTo(screenWidth * 2, screenHeigth);
        beisaierPath.lineTo(-dx, screenHeigth);
        beisaierPath.lineTo(-dx,screenHeigth / 2);
        beisaierPath.close();

    }
}
