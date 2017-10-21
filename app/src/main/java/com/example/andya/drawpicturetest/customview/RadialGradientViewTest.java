package com.example.andya.drawpicturetest.customview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

/**
 * Created by andya on 2017/10/16.
 */

public class RadialGradientViewTest extends Button {
    private int mX, mY;
    private ObjectAnimator mAnimator;
    private int DEFAULT_RADIUS = 50;
    private int mCurRadius = 0;
    private RadialGradient mRadialGradient;
    private Paint mPaint;

    public RadialGradientViewTest(Context context) {
        super(context);
        init();
    }

    public RadialGradientViewTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadialGradientViewTest(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (mX != event.getX() || mY != mY) {
            mX = (int) event.getX();
            mY = (int) event.getY();

            setRadius(DEFAULT_RADIUS);
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {

            if (mAnimator != null && mAnimator.isRunning()) {
                mAnimator.cancel();
            }

            if (mAnimator == null) {
                mAnimator = ObjectAnimator.ofInt(this,"radius",DEFAULT_RADIUS, getWidth());
            }

            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    setRadius(0);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            mAnimator.setDuration(4000);
            mAnimator.start();
        }

        return super.onTouchEvent(event);
    }

    public void setRadius(final int radius) {
        mCurRadius = radius;
        if (mCurRadius > 0) {
            mRadialGradient = new RadialGradient(mX, mY, mCurRadius, 0x00FFFFFF, 0xFF58FAAC, Shader.TileMode.CLAMP);
            mPaint.setShader(mRadialGradient);
        }
        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mX, mY, mCurRadius, mPaint);
    }
}
