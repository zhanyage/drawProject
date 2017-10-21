package com.example.andya.drawpicturetest.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.andya.drawpicturetest.R;

/**
 * Created by andya on 2017/10/19.
 */

public class MetrixTest extends View {

    private Bitmap mBitmap;             // 要绘制的图片
    private Matrix mPolyMatrix;         // 测试setPolyToPoly用的Matrix
    private float yuanWidth;
    private float changeWidth;
    private float yuanUnitWidth;
    private float changeUnitWidth;
    private Matrix[] matrix = new Matrix[4];


    public MetrixTest(Context context) {
        super(context);
        initBitmapAndMatrix();
    }

    public MetrixTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBitmapAndMatrix();
    }

    public MetrixTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBitmapAndMatrix();
    }


    private void initBitmapAndMatrix() {
        mBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_yindao_three1);
        float[] src1 = new float[8];
        float[] dst1 = new float[8];
        yuanWidth = mBitmap.getWidth();
        changeWidth = yuanWidth * 0.8f;
        yuanUnitWidth = yuanWidth / 4;
        changeUnitWidth = changeWidth / 4;

        int depth = (int) Math.sqrt(yuanUnitWidth * yuanUnitWidth
                - changeUnitWidth * changeUnitWidth)/2;
        for(int i = 0;i < 4;i++){
            matrix[i] = new Matrix();
        }

        for(int i = 0;i < 4;i++){
            src1[0] = i * yuanUnitWidth;
            src1[1] = 0;
            src1[2] = src1[0] + yuanUnitWidth;
            src1[3] = 0;
            src1[4] = src1[2];
            src1[5] = mBitmap.getHeight();
            src1[6] = src1[0];
            src1[7] = src1[5];

            boolean isOddNumber = i % 2 == 0;

            dst1[0] = i * changeUnitWidth;
            dst1[1] = isOddNumber ? 0 : depth;
            dst1[2] = dst1[0] + changeUnitWidth;
            dst1[3] = isOddNumber ? depth : 0;
            dst1[4] = dst1[2];
            dst1[5] = isOddNumber ? mBitmap.getHeight() - depth : mBitmap
                    .getHeight();
            dst1[6] = dst1[0];
            dst1[7] = isOddNumber ? mBitmap.getHeight() : mBitmap.getHeight()
                    - depth;
            matrix[i].setPolyToPoly(src1, 0, dst1, 0, src1.length >> 1);

        }
//
//        float[] src = {0, 0,                                    // 左上
//                mBitmap.getWidth(), 400,                          // 右上
//                mBitmap.getWidth(), mBitmap.getHeight()-500,        // 右下
//                0, mBitmap.getHeight()};                        // 左下
//
//        float[] dst = {0, 0,                                    // 左上
//                mBitmap.getWidth(), 400,                       // 右上
//                mBitmap.getWidth(), mBitmap.getHeight() - 200,  // 右下
//                0, mBitmap.getHeight()};                        // 左下

        // 核心要点
        // src.length >> 1 为位移运算 相当于处以2

        // 此处为了更好的显示对图片进行了等比缩放和平移(图片本身有点大)
//        mPolyMatrix.postScale(0.26f, 0.26f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.scale(0.3f,0.3f);
        for(int i = 0;i < 4;i++){
            canvas.save();

            canvas.concat(matrix[i]);

            canvas.clipRect(yuanUnitWidth * i, 0, yuanUnitWidth * i + yuanUnitWidth,
                    mBitmap.getHeight());
            canvas.drawBitmap(mBitmap,0,0,null);
            canvas.restore();
        }


        // 根据Matrix绘制一个变换后的图片
//        canvas.drawBitmap(mBitmap, mPolyMatrix, null);
    }
}
