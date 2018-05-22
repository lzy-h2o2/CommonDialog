package com.zndroid.widgets;

import android.view.View;

/**
 * @author lazy
 * @create 2018/5/22 22:54
 * @desc prevent secondary clicks
 * @since
 **/
public abstract class ACommonOnClickListener implements View.OnClickListener {
    private final int INTERVAL = 300;
    private long lastTime = 0;

    @Override
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime > INTERVAL) {
            onNoDoubleClick(v);
            lastTime = currentTime;
        }
    }

    public abstract void onNoDoubleClick(View v);
}
