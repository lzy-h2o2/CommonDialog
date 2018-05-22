package com.zndroid.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.zndroid.widgets.utils.ReflectResourceUtil;


/**
 * @author luzhenyu
 * 
 *         <p>
 *         CommonCriProgressDialog
 *         </p>
 *         <i> 通过 new CommonCriProgressDialog(youActivity.this).builder()创建实例<dd>
 *         	   然后就可以按照一般的Dialog使用了</i>
 * */
public class CommonCriProgressDialog extends Dialog{

	private CommonCriProgressDialog myCirProgressDialog;
	private Context context;

	public CommonCriProgressDialog(Context context) {
		super(context);
		this.context = context;
	}
	
	public CommonCriProgressDialog(Context context, int themId) {
		super(context, themId);
		this.context = context;
	}

	private CommonCriProgressDialog builder() {
		myCirProgressDialog = new CommonCriProgressDialog(context, ReflectResourceUtil.getStyleId(
				context, "CustomProgressDialog"));

		if (null == myCirProgressDialog)
			return null;

		View view = LayoutInflater.from(context)
				.inflate(
						ReflectResourceUtil.getLayoutId(context,
								"zcommon_circular_progress"), null);
		myCirProgressDialog.setContentView(view);
		myCirProgressDialog.setCanceledOnTouchOutside(false);

		return myCirProgressDialog;
	}

	public void showCriProgress() {
		if (null != myCirProgressDialog && !myCirProgressDialog.isShowing())
			myCirProgressDialog.show();
	}

	public void closeCriProgress() {
		if (null != myCirProgressDialog) {
			myCirProgressDialog.dismiss();
			myCirProgressDialog = null;
		}
	}
}
