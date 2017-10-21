package com.example.andya.drawpicturetest.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by andya on 2017/10/16.
 */

public class LinearGradientViewTest extends TextView{


    private Paint mPaint;
    private Matrix matrix;
    private int mVx = 0;
    private Shader mShader;

    public LinearGradientViewTest(Context context) {
        super(context);
        init();
    }

    public LinearGradientViewTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public LinearGradientViewTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        mPaint = getPaint();
        matrix = new Matrix();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        matrix.reset();
        mPaint.setShader(mShader);
        matrix.preTranslate(mVx,0);
        mShader.setLocalMatrix(matrix);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mShader = new LinearGradient(-2 * w,0,-w,0,new int[]{getCurrentTextColor(), Color.RED,Color.YELLOW,Color.BLUE,getCurrentTextColor()},null, Shader.TileMode.CLAMP);
        mShader.setLocalMatrix(matrix);
        initAnimtor(w);
    }

    private void initAnimtor(int width) {
        ValueAnimator animator = ValueAnimator.ofInt(0, width * 3);  //我们设置value的值为0-getMeasureWidth的3 倍
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mVx = (Integer) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.setRepeatMode(ValueAnimator.RESTART);   //重新播放
        animator.setRepeatCount(-1);                    //无限循环
        animator.setDuration(2000);
        animator.start();
    }
}
