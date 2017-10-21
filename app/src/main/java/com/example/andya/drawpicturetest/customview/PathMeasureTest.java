package com.example.andya.drawpicturetest.customview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by andya on 2017/10/19.
 */

public class PathMeasureTest extends View{

    Paint mPaint;
    Path circlePath;
    PathMeasure pathMeasure;
    float pathLength;
    Path drawPath;
    float startAndEndDis = 30;
    float startPos;
    float endPos;
    float middelPos = 0;
    ValueAnimator valueAnimatorUpLength;
    ValueAnimator valueAnimatorDownLength;

    public PathMeasureTest(Context context) {
        super(context);
        init();
    }

    public PathMeasureTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathMeasureTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        drawPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        circlePath = new Path();
        pathMeasure = new PathMeasure();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        startAnimation();

        return super.onTouchEvent(event);

    }

    public void startAnimation(){
        if(valueAnimatorUpLength != null){
            valueAnimatorUpLength.cancel();
            valueAnimatorDownLength.cancel();
        }else {
            valueAnimatorUpLength = ValueAnimator.ofFloat(0, pathLength);
            valueAnimatorUpLength.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    middelPos = (float)animation.getAnimatedValue();
                    if(middelPos <= pathLength / 2){
                        startPos = middelPos - middelPos / 4;
                        endPos = middelPos + middelPos / 4;
                    }else {
                        startPos = middelPos - (pathLength - middelPos) / 4;
                        endPos = middelPos + (pathLength - middelPos) / 4;
                    }
//                    startPos = (float)animation.getAnimatedValue();
//                    endPos = startPos + startPos * 2 / 3;
                    postInvalidate();
                }
            });
            valueAnimatorUpLength.setRepeatCount(10);
            valueAnimatorUpLength.setInterpolator(new AccelerateInterpolator());
            valueAnimatorDownLength = ValueAnimator.ofFloat(pathLength * 5 / 8,pathLength);
            valueAnimatorDownLength.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    endPos = (float)animation.getAnimatedValue();
                    startPos = endPos - ((pathLength - endPos) / 3 * 2);
                    postInvalidate();
                }
            });

//            valueAnimatorDownLength.setRepeatCount(100);                  //无限循环
            valueAnimatorUpLength.setDuration(2000);
            valueAnimatorUpLength.start();
//            valueAnimatorDownLength.setInterpolator(new AccelerateInterpolator());
        }
//        valueAnimatorDownLength.setDuration(2000);
//        valueAnimatorDownLength.start();

//        AnimatorSet animSet = new AnimatorSet();
//        animSet.play(valueAnimatorDownLength).before(valueAnimatorUpLength); //之前播放
//        animSet.setDuration(2000);
//        animSet.start();
//        animSet.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                startAnimation();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        circlePath.reset();
        drawPath.reset();
        canvas.translate(getWidth()/2,getHeight()/2);
        circlePath.addCircle(0,0,200, Path.Direction.CCW);
        pathMeasure.setPath(circlePath,true);
        pathLength = pathMeasure.getLength();
        pathMeasure.getSegment(startPos,endPos,drawPath,true);
        canvas.drawPath(drawPath,mPaint);
    }
}
