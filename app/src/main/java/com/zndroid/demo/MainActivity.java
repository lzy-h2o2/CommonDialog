package com.zndroid.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zndroid.widgets.PositionEnum;
import com.zndroid.widgets.dialog.CommonStandardDialog;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button1(View view) {
        //默认对话框
        CommonStandardDialog dialog = new CommonStandardDialog(this);
        dialog
                .builder(PositionEnum.CENTER)//设置显示位置
                .show();

    }

    public void button11(View view) {
        //常用对话框
        CommonStandardDialog dialog = new CommonStandardDialog(this);
        dialog
                .builder(PositionEnum.CENTER)//设置显示位置
                .setTitle("Im Title")
                .setMsg("Im Message Showing")
                .setPositiveButton("ok")
                .setNegativeButton("cancel")
                .setCommonClickListener(new CommonStandardDialog.CommonOnClickListener() {
                    @Override
                    public void onOK(View v) {
                        showToast("ok clicked");
                    }

                    @Override
                    public void onCancel(View v) {
                        showToast("cancel clicked");
                    }
                })
                .show();

    }

    public void button12(View view) {
        //单个按钮的对话框
        CommonStandardDialog dialog = new CommonStandardDialog(this);
        dialog
                .builder(PositionEnum.CENTER)//设置显示位置
                .setMsg("A 'OK' button")
                .setTitle("Single Button")
                //如果  都不设置  默认一个按钮
                .setPositiveButton("OK") //or
                //.setNegativeButton("Cancel")
                .setCommonClickListener(new CommonStandardDialog.CommonOnClickListener() {
                    @Override
                    public void onOK(View v) {
                        showToast("ok clicked");
                    }

                    @Override
                    public void onCancel(View v) {
                        //根据你的设置处理回调
                    }
                })
                .show();

    }

    public void button13(final View view) {
        //带输入框的对话框
        final CommonStandardDialog dialog = new CommonStandardDialog(this);
        dialog
                .builder(PositionEnum.CENTER)//设置显示位置
                .setTitle("Has Edit Text")
                .setEditText("")//默认会提示“请输入”
                .setPositiveButton("Get Text")
                .setCommonClickListener(new CommonStandardDialog.CommonOnClickListener() {
                    @Override
                    public void onOK(View v) {
                        ((Button)view).setText(dialog.getResult());
                    }

                    @Override
                    public void onCancel(View v) {

                    }
                })
                .show();

    }

    public void button14(final View view) {
        //自定义View对话框
        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setBackgroundResource(R.drawable.ic_launcher_background);

        final CommonStandardDialog dialog = new CommonStandardDialog(this);
        dialog
                .builder(PositionEnum.CENTER)//设置显示位置
                .setTitle("Customer Dialog")
                .setPositiveButton("OK")
                .setView(imageView)
                .setCommonClickListener(new CommonStandardDialog.CommonOnClickListener() {
                    @Override
                    public void onOK(View v) {
                        showToast("OK");
                    }

                    @Override
                    public void onCancel(View v) {

                    }
                })
                .show();

    }

    public void button2(View view) {


    }

    public void button3(View view) {

    }

    public void button4(View view) {

    }

    public void button5(View view) {

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
