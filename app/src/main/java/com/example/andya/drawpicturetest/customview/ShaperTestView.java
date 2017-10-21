package com.example.andya.drawpicturetest.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by andya on 2017/10/16.
 */

public class ShaperTestView extends View{

    public ShaperTestView(Context context) {
        super(context);
    }

    public ShaperTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShaperTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        SweepGradient shape = new SweepGradient(canvas.getWidth()/2,canvas.getHeight()/2, Color.rgb(0,100,0),Color.rgb(144,238,144));
        paint.setShader(shape);
        canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,200,paint);
    }
}
