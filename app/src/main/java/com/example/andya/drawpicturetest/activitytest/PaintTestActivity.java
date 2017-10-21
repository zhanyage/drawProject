package com.example.andya.drawpicturetest.activitytest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.andya.drawpicturetest.R;

/**
 * Created by xubinbin on 2017/10/10.
 */

public class PaintTestActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_view);
    }
}
