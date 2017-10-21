package com.example.andya.drawpicturetest.customview;


import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationSet;

/**
 * Created by andya on 2017/10/12.
 */

public class PathViewTest1 extends View{

    private int screenWidth;
    private int screenHeigth;
    private Paint mPaint;
    private Path beisaierPath;
    private float dx;
    private PointF leftPoint,rigthPoint,topPoint,bottomPoint;
    private PointF p1Point,p2Point,p4Point,p5Point,p7Point,p8Point,p10Point,p11Point;

    public PathViewTest1(Context context) {
        super(context);
        init();
    }

    public PathViewTest1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathViewTest1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(20);
        // 防抖动
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        beisaierPath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        ValueAnimator tanqiu = ValueAnimator.ofFloat(0,200);
        tanqiu.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (float)animation.getAnimatedValue();
                invalidate();
            }
        });
        tanqiu.setDuration(1000);
        ValueAnimator huitan = ValueAnimator.ofFloat(200,-100);
        huitan.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (float)animation.getAnimatedValue();
                invalidate();
            }
        });
        huitan.setDuration(1000);
        ValueAnimator huitan2 = ValueAnimator.ofFloat(-100,0);
        huitan2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (float)animation.getAnimatedValue();
                invalidate();
            }
        });
        huitan2.setDuration(500);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(huitan).after(tanqiu).before(huitan2);
        animatorSet.start();
        return true;
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
        leftPoint = new PointF(-200.0f,0.0f);
        p8Point = new PointF(-200.0f,-110.4569498f);
        p10Point = new PointF(-200.0f,110.4569498f);
        rigthPoint = new PointF(200.0f,0.0f);
        p2Point = new PointF(200.0f,110.4569498f);
        p4Point = new PointF(200.0f,-110.4569498f);
        topPoint = new PointF(0.0f,-200.0f);
        p5Point = new PointF(110.4569498f,-200.0f);
        p7Point = new PointF(-110.4569498f,-200.0f);
        bottomPoint = new PointF(0.0f,200.0f);
        p1Point = new PointF(110.4569498f,200.0f);
        p11Point = new PointF(-110.4569498f,200.0f);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(screenWidth / 2,screenHeigth / 2);
//        beisaierPath.moveTo(screenWidth / 2, 0);

        updatePath(canvas);

        canvas.drawPath(beisaierPath, mPaint);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(50);
//        canvas.drawPoint(bottomPoint.x,bottomPoint.y,paint);
//        canvas.drawPoint(p1Point.x,p1Point.y,paint);

    }


    public void updatePath(Canvas canvas){
        beisaierPath.reset();
        beisaierPath.moveTo(0,200.0f);

//        canvas.drawCircle(0,0,200,mPaint);


        beisaierPath.cubicTo(p1Point.x,p1Point.y,p2Point.x+dx,p2Point.y,rigthPoint.x+dx,rigthPoint.y);
        beisaierPath.cubicTo(p4Point.x+dx,p4Point.y,p5Point.x,p5Point.y,topPoint.x,topPoint.y);
        beisaierPath.cubicTo(p7Point.x,p7Point.y,p8Point.x,p8Point.y,leftPoint.x,leftPoint.y);
        beisaierPath.cubicTo(p10Point.x,p10Point.y,p11Point.x,p11Point.y,bottomPoint.x,bottomPoint.y);
//        beisaierPath.close();
    }


}
