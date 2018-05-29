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
public class CommonCriProgressDialog extends Dialog {

	private Context context;
	private Dialog dialog;

	public CommonCriProgressDialog(Context context) {
		super(context);
		this.context = context;
	}

	public CommonCriProgressDialog builder() {
		dialog = new Dialog(context, ReflectResourceUtil.getStyleId(
				context, "CustomProgressDialog"));

		View view = LayoutInflater.from(context)
				.inflate(
						ReflectResourceUtil.getLayoutId(context,
								"zcommon_circular_progress"), null);

		dialog.setContentView(view);
		dialog.setCanceledOnTouchOutside(false);
		return this;
	}

	public void showCriProgress() {
		if (null != dialog && !dialog.isShowing())
			dialog.show();
	}

	public void closeCriProgress() {
		if (null != dialog) {
			dialog.dismiss();
			dialog = null;
		}
	}
}
