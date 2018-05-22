package com.zndroid.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zndroid.widgets.ACommonOnClickListener;
import com.zndroid.widgets.PositionEnum;
import com.zndroid.widgets.dialog.base.CommonDialogBase;
import com.zndroid.widgets.utils.ReflectResourceUtil;

/**
 * @author luzhenyu
 * 
 *         <p>
 *         CommonStandardDialog - StandaradDialog include "PositiveButton" and
 *         "NegativeButton"
 *         </p>
 * 
 * */
public class CommonStandardDialog extends CommonDialogBase {

	private Button OKBtn, cancelBtn;

	private Dialog dialog;

	private LinearLayout lLayout_bg;
	private LinearLayout dialog_Group;

	private TextView txt_title;
	private TextView txt_msg;

	private ImageView dialog_marBottom;
	private ImageView img_line;

	private EditText edittxt_result;

	private boolean showTitle = false;
	private boolean showMsg = false;
	private boolean showEditText = false;
	private boolean showLayout = false;
	private boolean showPosBtn = false;
	private boolean showNegBtn = false;

	private CommonOnClickListener listener;

	public CommonStandardDialog(Context context) {
		super(context);
	}

	/**
	 * 
	 * 可以指定位置
	 * */
	public CommonStandardDialog builder(PositionEnum position) {
		// 获取Dialog布局
		View view = LayoutInflater.from(getContext()).inflate(
				ReflectResourceUtil.getLayoutId(getContext(),
						"zcommon_dialog"), null);
		// 获取Dialog里面的控件
		/**
		 * @author luzhenyu bug修复
		 * 
		 *         应该用getContext()弱引用而不是 context
		 * */
		lLayout_bg = (LinearLayout) view.findViewById(ReflectResourceUtil
				.getId(getContext(), "lLayout_bg"));

		txt_title = (TextView) view.findViewById(ReflectResourceUtil.getId(
				getContext(), "txt_title"));
		txt_title.setVisibility(View.GONE);

		txt_msg = (TextView) view.findViewById(ReflectResourceUtil.getId(
				getContext(), "txt_msg"));
		txt_msg.setVisibility(View.GONE);

		OKBtn = (Button) view.findViewById(ReflectResourceUtil.getId(
				getContext(), "btn_ok"));
		OKBtn.setVisibility(View.GONE);

		dialog_marBottom = (ImageView) view.findViewById(ReflectResourceUtil
				.getId(getContext(), "dialog_marBottom"));
		dialog_marBottom.setVisibility(View.GONE);

		edittxt_result = (EditText) view.findViewById(ReflectResourceUtil
				.getId(getContext(), "edittxt_result"));
		edittxt_result.setVisibility(View.GONE);

		img_line = (ImageView) view.findViewById(ReflectResourceUtil.getId(
				getContext(), "img_line"));
		img_line.setVisibility(View.GONE);

		dialog_Group = (LinearLayout) view.findViewById(ReflectResourceUtil
				.getId(getContext(), "dialog_Group"));
		dialog_Group.setVisibility(View.GONE);

		cancelBtn = (Button) view.findViewById(ReflectResourceUtil.getId(
				getContext(), "btn_cancel"));
		cancelBtn.setVisibility(View.GONE);
		// 设定Dialog样式
		dialog = new Dialog(getContext(), ReflectResourceUtil.getStyleId(
				getContext(), "AlertDialogStyle"));
		dialog.setContentView(view);

		Window window = dialog.getWindow();
		android.view.WindowManager.LayoutParams layoutParams = window
				.getAttributes();

		// 获取标题栏状态栏高度 以后用
		/*
		 * Rect rect = new Rect(); Window window2 =
		 * ((Activity)getContext()).getWindow();
		 * window2.getDecorView().getWindowVisibleDisplayFrame(rect);
		 */

		int gravity = 0;

		switch (position) {
			case CENTER:
				gravity = Gravity.CENTER;
				break;
			case TOP:
				gravity = Gravity.TOP;
				break;
			case BOTTOM:
				gravity = Gravity.BOTTOM;
				break;
			default:
				break;
		}

		layoutParams.gravity = gravity;
		window.setAttributes(layoutParams);

		lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams(
				(int) (getWidth() * 0.85), LayoutParams.WRAP_CONTENT));

		return this;
	}

	public CommonStandardDialog setCancelable(boolean cancel) {
		dialog.setCancelable(cancel);
		return this;
	}

	public CommonStandardDialog setTitle(String title) {
		showTitle = true;
		if ("".equals(title)) {
			txt_title.setText(ReflectResourceUtil.getStringId(getContext(),
					"zcommon_dialog_title_def"));
		} else {
			txt_title.setText(title);
		}
		return this;
	}

	public CommonStandardDialog setMsg(String msg) {
		showMsg = true;
		if ("".equals(msg)) {
			txt_msg.setText(ReflectResourceUtil.getStringId(getContext(),
					"zcommon_dialog_msg_def"));
		} else {
			txt_msg.setText(msg);
		}
		return this;
	}

	public CommonStandardDialog setEditText(String msg) {
		showEditText = true;
		if ("".equals(msg)) {
			edittxt_result.setHint(ReflectResourceUtil.getStringId(
					getContext(), "zcommon_editText_hit_def"));
		} else {
			edittxt_result.setText(msg);
		}
		return this;
	}

	public String getResult() {
		return edittxt_result.getText().toString();
	}

	public CommonStandardDialog setView(View view) {
		showLayout = true;
		if (view == null) {
			showLayout = false;
		} else
			dialog_Group.addView(view,
					LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
		return this;
	}

	public CommonStandardDialog setPositiveButton(String text) {
		showPosBtn = true;
		if ("".equals(text)) {
			OKBtn.setText(ReflectResourceUtil.getStringId(getContext(),
					"zcommon_dialog_ok_def"));
		} else {
			OKBtn.setText(text);
		}
		OKBtn.setOnClickListener(new ACommonOnClickListener() {
			@Override
			public void onNoDoubleClick(View v) {
				if (null != listener)
					listener.onOK(v);
				dialog.dismiss();
			}
		});
		return this;
	}

	public CommonStandardDialog setNegativeButton(String text) {
		showNegBtn = true;
		if ("".equals(text)) {
			cancelBtn.setText(ReflectResourceUtil.getStringId(getContext(),
					"zcommon_dialog_cancel_def"));
		} else {
			cancelBtn.setText(text);
		}
		cancelBtn.setOnClickListener(new ACommonOnClickListener() {
			@Override
			public void onNoDoubleClick(View v) {
				if (null != listener)
					listener.onCancel(v);
				dialog.dismiss();
			}
		});
		return this;
	}

	private void setLayout() {
		if (!showTitle && !showMsg) {
			txt_title.setText(ReflectResourceUtil.getStringId(getContext(),
					"zcommon_dialog_title_def"));
			txt_title.setVisibility(View.VISIBLE);
		}

		if (showTitle) {
			txt_title.setVisibility(View.VISIBLE);
		}

		if (showEditText) {
			edittxt_result.setVisibility(View.VISIBLE);
		}

		if (showMsg) {
			txt_msg.setVisibility(View.VISIBLE);
		}

		if (showLayout) {
			dialog_Group.setVisibility(View.VISIBLE);
			dialog_marBottom.setVisibility(View.GONE);
		}

		if (!showPosBtn && !showNegBtn) {
			OKBtn.setText(ReflectResourceUtil.getStringId(getContext(),
					"zcommon_dialog_ok_def"));
			OKBtn.setVisibility(View.VISIBLE);
			OKBtn.setBackgroundResource(ReflectResourceUtil.getDrawableId(
					getContext(), "zcommon_alertdialog_single_selector"));
			OKBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
		}

		if (showPosBtn && showNegBtn) {
			OKBtn.setVisibility(View.VISIBLE);
			OKBtn.setBackgroundResource(ReflectResourceUtil.getDrawableId(
					getContext(), "zcommon_dialog_right_selector"));
			cancelBtn.setVisibility(View.VISIBLE);
			cancelBtn.setBackgroundResource(ReflectResourceUtil.getDrawableId(
					getContext(), "zcommon_dialog_left_selector"));
			img_line.setVisibility(View.VISIBLE);
			dialog_marBottom.setVisibility(View.VISIBLE);
		}

		if (showPosBtn && !showNegBtn) {
			OKBtn.setVisibility(View.VISIBLE);
			OKBtn.setBackgroundResource(ReflectResourceUtil.getDrawableId(
					getContext(), "zcommon_alertdialog_single_selector"));
			dialog_marBottom.setVisibility(View.VISIBLE);
		}

		if (!showPosBtn && showNegBtn) {
			cancelBtn.setVisibility(View.VISIBLE);
			cancelBtn.setBackgroundResource(ReflectResourceUtil.getDrawableId(
					getContext(), "zcommon_alertdialog_single_selector"));
			dialog_marBottom.setVisibility(View.VISIBLE);
		}
	}

	public CommonStandardDialog setCommonClickListener(CommonOnClickListener listener) {
		this.listener = listener;
		return this;
	}

	public interface CommonOnClickListener {
		void onOK(View v);
		void onCancel(View v);
	}

	public void show() {
		setLayout();
		dialog.show();
	}
}
