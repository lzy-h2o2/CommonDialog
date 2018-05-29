package com.zndroid.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zndroid.widgets.PositionEnum;
import com.zndroid.widgets.dialog.CommonCriProgressDialog;
import com.zndroid.widgets.dialog.CommonSheetDialog;
import com.zndroid.widgets.dialog.CommonStandardDialog;
import com.zndroid.widgets.wheel.base.GetCityWheel;
import com.zndroid.widgets.wheel.base.GetDateWheel;
import com.zndroid.widgets.wheel.base.GetTimeWheel;

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
        //底部列表弹框
        CommonSheetDialog dialog = new CommonSheetDialog(this);
        dialog
                .builder()
                .setTitle("请选择性别")
                .addSheetItem("男", CommonSheetDialog.SheetItemColor.BlUE)
                .addSheetItem("女", CommonSheetDialog.SheetItemColor.BlUE)
                .addSheetItem("人妖", CommonSheetDialog.SheetItemColor.RED)
                .setSheetItemClickListener(new CommonSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which, CommonSheetDialog.SheetItem item) {
                        showToast(which + " " + item.getName() + "onClicked");
                    }
                })
                .show();
    }

    public void button3(View view) {
        //七彩虹进度条
        final CommonCriProgressDialog dialog = new CommonCriProgressDialog(this);
        dialog
                .builder()
                .showCriProgress();

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                showToast("OK i will close it...");
                dialog.closeCriProgress();
            }
        }, 3000);
    }

    public void button4(View view) {
        //日期选择弹框 - 可以借助自定义标准对话框作为展示载体
        CommonStandardDialog dialog = new CommonStandardDialog(this);

        final View dateWheel = new GetDateWheel(this, null).getTimeView();
        dialog
                .builder(PositionEnum.CENTER)
                .setView(dateWheel)
                .setTitle("Date Wheel")
                .setPositiveButton("ok")
                .setCommonClickListener(new CommonStandardDialog.CommonOnClickListener() {
                    @Override
                    public void onOK(View v) {
                        showToast(GetDateWheel.getTimeResult());
                    }

                    @Override
                    public void onCancel(View v) {

                    }
                })
                .show();
    }

    public void button5(View view) {
        //城市选择弹框 - 可以借助自定义标准对话框作为展示载体
        CommonStandardDialog dialog = new CommonStandardDialog(this);

        final View cityWheel = new GetCityWheel(this).getCityView();
        dialog
                .builder(PositionEnum.CENTER)
                .setView(cityWheel)
                .setTitle("City Wheel")
                .setPositiveButton("ok")
                .setCommonClickListener(new CommonStandardDialog.CommonOnClickListener() {
                    @Override
                    public void onOK(View v) {
                        showToast(GetCityWheel.getResultStr());
                    }

                    @Override
                    public void onCancel(View v) {

                    }
                })
                .show();

    }

    public void button6(View view) {
        //时间选择弹框 - 可以借助自定义标准对话框作为展示载体
        CommonStandardDialog dialog = new CommonStandardDialog(this);

        final View timeWheel = new GetTimeWheel(this, null).getTimeView();
        dialog
                .builder(PositionEnum.CENTER)
                .setView(timeWheel)
                .setTitle("Time Wheel")
                .setPositiveButton("ok")
                .setCommonClickListener(new CommonStandardDialog.CommonOnClickListener() {
                    @Override
                    public void onOK(View v) {
                        showToast(GetTimeWheel.getTimeResult());
                    }

                    @Override
                    public void onCancel(View v) {

                    }
                })
                .show();

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
