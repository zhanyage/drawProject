package com.example.andya.drawpicturetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andya.drawpicturetest.activitytest.MatrixActivityTest;
import com.example.andya.drawpicturetest.activitytest.PaintTestActivity;
import com.example.andya.drawpicturetest.activitytest.PathMeasureActivity;
import com.example.andya.drawpicturetest.activitytest.PathTestActivity1;
import com.example.andya.drawpicturetest.activitytest.ProterTestActivity;
import com.example.andya.drawpicturetest.activitytest.ShaderTestActivity1;
import com.example.andya.drawpicturetest.activitytest.ShaderTestActivity2;
import com.example.andya.drawpicturetest.activitytest.ShaperTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMainGoPorter;
    Button btnMainGoPaint;
    Button btnMainGoPath;
    Button btnMainGoShader;
    Button btnMainGoShader1;
    Button btnMainGoShader2;
    Button btnMainGoMatrix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn_go_permission);
        btn.setOnClickListener(this);
        btnMainGoPorter = (Button)findViewById(R.id.btn_main_go_porter);
        btnMainGoPorter.setOnClickListener(this);
        btnMainGoPaint = (Button)findViewById(R.id.btn_main_go_paint);
        btnMainGoPaint.setOnClickListener(this);
        btnMainGoPath = (Button)findViewById(R.id.btn_main_go_path);
        btnMainGoPath.setOnClickListener(this);
        btnMainGoShader = (Button)findViewById(R.id.btn_main_go_shader);
        btnMainGoShader.setOnClickListener(this);
        btnMainGoShader1 = (Button)findViewById(R.id.btn_main_go_shader1);
        btnMainGoShader1.setOnClickListener(this);
        btnMainGoShader2 = (Button)findViewById(R.id.btn_main_go_shader2);
        btnMainGoShader2.setOnClickListener(this);
        btnMainGoMatrix = (Button)findViewById(R.id.btn_main_go_matrix);
        btnMainGoMatrix.setOnClickListener(this);
        findViewById(R.id.btn_main_go_pathmeasure).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_go_permission:
                Intent intent = new Intent();
                intent.setClassName("com.coloros.notificationmanager","com.coloros.notificationmanager.AppDetailPreferenceActivity");
                intent.setAction("com.coloros.notificationmanager.app.detail");
                intent.putExtra("pkg_name","com.example.andya.drawpicturetest");
                intent.putExtra("app_name","易货嘀");
//                intent.putExtra("class_name","com.example.andya.drawpicturetest.MainActivity");
                startActivity(intent);
                break;
            case R.id.btn_main_go_porter:
                Intent intent1 = new Intent(this,ProterTestActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_main_go_paint:
                Intent intent2 = new Intent(this,PaintTestActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_main_go_path:
                Intent intent3 = new Intent(this,PathTestActivity1.class);
                startActivity(intent3);
                break;
            case R.id.btn_main_go_shader:
                Intent intent4 = new Intent(this, ShaperTestActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_main_go_shader1:
                Intent intent5 = new Intent(this, ShaderTestActivity1.class);
                startActivity(intent5);
                break;
            case R.id.btn_main_go_shader2:
                Intent intent6 = new Intent(this, ShaderTestActivity2.class);
                startActivity(intent6);
                break;
            case R.id.btn_main_go_matrix:
                Intent intent7 = new Intent(this, MatrixActivityTest.class);
                startActivity(intent7);
                break;
            case R.id.btn_main_go_pathmeasure:
                Intent intent8 = new Intent(this, PathMeasureActivity.class);
                startActivity(intent8);
                break;
        }
    }
}
