package com.zndroid.widgets.dialog.base;

import com.zndroid.widgets.wheel.wheelview.ScreenInfo;

import android.app.Activity;
import android.content.Context;

/**
 * @author luzhenyu
 * 
 *         <p>
 *         CommonDialogBase - my customdialog base class
 *         </p>
 *         <i> 方便获取content 和 屏幕宽高<dd>
 *         getHight()、getWidth()、getContext()</i>
 * */
public class CommonDialogBase {

	private Context context;
	private ScreenInfo screenInfo;

	public CommonDialogBase(Context context) {
		this.context = context;
		screenInfo = new ScreenInfo((Activity) context);
	}

	public int getHight() {
		return screenInfo.getHeight();
	}

	public int getWidth() {
		return screenInfo.getWidth();
	}

	public Context getContext() {
		return context;
	}

}
