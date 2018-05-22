package com.zndroid.widgets.dialog;

import java.util.ArrayList;
import java.util.List;

import com.zndroid.widgets.ACommonOnClickListener;
import com.zndroid.widgets.utils.ReflectResourceUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * @author luzhenyu
 *         <p>
 *         CommonSheetDialog - 底部弹出条目栏
 *         </p>
 * */
public class CommonSheetDialog {
	private Context context;
	private Dialog dialog;

	private TextView txt_title;
	private TextView txt_cancel;

	private LinearLayout lLayout_content;
	private ScrollView sLayout_content;

	private boolean showTitle = false;

	private final int LIMITED = 7;
	private List<SheetItem> sheetItemList;

	private DisplayMetrics displayMetrics;
	private int height, width;

	public CommonSheetDialog(Activity context) {
		this.context = context;
		displayMetrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		width = displayMetrics.widthPixels;
		height = displayMetrics.heightPixels;
	}

	public CommonSheetDialog builder() {
		// 获取Dialog布局
		View view = LayoutInflater.from(context).inflate(
				ReflectResourceUtil.getLayoutId(context, "zcommon_sheet_dialog"),
				null);

		// 设置Dialog最小宽度为屏幕宽度
		view.setMinimumWidth(width);

		// 获取自定义Dialog布局中的控件
		sLayout_content = (ScrollView) view.findViewById(ReflectResourceUtil
				.getId(context, "sLayout_content"));
		lLayout_content = (LinearLayout) view.findViewById(ReflectResourceUtil
				.getId(context, "lLayout_content"));
		txt_title = (TextView) view.findViewById(ReflectResourceUtil.getId(
				context, "txt_title"));
		txt_cancel = (TextView) view.findViewById(ReflectResourceUtil.getId(
				context, "txt_cancel"));
		txt_cancel.setOnClickListener(new ACommonOnClickListener() {
			@Override
			public void onNoDoubleClick(View v) {
				dialog.dismiss();
			}
		});

		// 定义Dialog布局和参数
		dialog = new Dialog(context, ReflectResourceUtil.getStyleId(context,
				"ActionSheetDialogStyle"));
		dialog.setContentView(view);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.x = 0;
		lp.y = 0;
		dialogWindow.setAttributes(lp);

		return this;
	}

	public CommonSheetDialog setTitle(String title) {
		showTitle = true;
		txt_title.setVisibility(View.VISIBLE);
		txt_title.setText(title);
		return this;
	}

	public CommonSheetDialog setCancelable(boolean cancel) {
		dialog.setCancelable(cancel);
		return this;
	}

	public CommonSheetDialog setCanceledOnTouchOutside(boolean cancel) {
		dialog.setCanceledOnTouchOutside(cancel);
		return this;
	}

	/**
	 * 
	 * @param strItem
	 *            条目名称
	 * @param color
	 *            条目字体颜色，设置null则默认蓝色
	 * @param listener
	 * @return
	 */
	public CommonSheetDialog addSheetItem(String strItem, SheetItemColor color,
                                          OnSheetItemClickListener listener) {
		if (sheetItemList == null) {
			sheetItemList = new ArrayList<SheetItem>();
		}
		sheetItemList.add(new SheetItem(strItem, color, listener));
		return this;
	}

	/** 设置条目布局 */
	private void setSheetItems() {
		if (sheetItemList == null || sheetItemList.size() <= 0) {
			return;
		}

		int size = sheetItemList.size();

		// TODO 高度控制，非最佳解决办法
		// 添加条目过多的时候控制高度
		if (size >= LIMITED) {
			LayoutParams params = (LayoutParams) sLayout_content
					.getLayoutParams();
			params.height = height / 2;
			sLayout_content.setLayoutParams(params);
		}

		// 循环添加条目
		for (int i = 1; i <= size; i++) {
			final int index = i;
			SheetItem sheetItem = sheetItemList.get(i - 1);
			String strItem = sheetItem.name;
			SheetItemColor color = sheetItem.color;
			final OnSheetItemClickListener listener = sheetItem.itemClickListener;

			TextView textView = new TextView(context);
			textView.setText(strItem);
			textView.setTextSize(18);//TODO 优化点  写死不太好吧
			textView.setGravity(Gravity.CENTER);

			// 背景图片
			if (size == 1) {
				if (showTitle) {
					textView.setBackgroundResource(ReflectResourceUtil
							.getDrawableId(context,
									"zcommon_dialog_sheet_bottom_selector"));
				} else {
					textView.setBackgroundResource(ReflectResourceUtil
							.getDrawableId(context,
									"dialog_sheet_single_selector_xmldialog_sheet_single_selector"));
				}
			} else {
				if (showTitle) {
					if (i >= 1 && i < size) {
						textView.setBackgroundResource(ReflectResourceUtil
								.getDrawableId(context,
										"zcommon_dialog_sheet_middle_selector"));
					} else {
						textView.setBackgroundResource(ReflectResourceUtil
								.getDrawableId(context,
										"zcommon_dialog_sheet_bottom_selector"));
					}
				} else {
					if (i == 1) {
						textView.setBackgroundResource(ReflectResourceUtil
								.getDrawableId(context,
										"zcommon_dialog_sheet_top_selector"));
					} else if (i < size) {
						textView.setBackgroundResource(ReflectResourceUtil
								.getDrawableId(context,
										"zcommon_dialog_sheet_middle_selector"));
					} else {
						textView.setBackgroundResource(ReflectResourceUtil
								.getDrawableId(context,
										"zcommon_dialog_sheet_bottom_selector"));
					}
				}
			}

			// 字体颜色
			if (color == null) {
				textView.setTextColor(Color.parseColor(SheetItemColor.Blue
						.getName()));
			} else {
				textView.setTextColor(Color.parseColor(color.getName()));
			}

			// 高度
			float scale = context.getResources().getDisplayMetrics().density;
			int height = (int) (45 * scale + 0.5f);
			textView.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, height));

			// 点击事件
			textView.setOnClickListener(new ACommonOnClickListener() {
				@Override
				public void onNoDoubleClick(View v) {
					listener.onClick(index);
					dialog.dismiss();
				}
			});

			lLayout_content.addView(textView);
		}
	}

	public void show() {
		setSheetItems();
		dialog.show();
	}

	public interface OnSheetItemClickListener {
		void onClick(int which);
	}

	public class SheetItem {
		String name;
		OnSheetItemClickListener itemClickListener;
		SheetItemColor color;

		public SheetItem(String name, SheetItemColor color,
				OnSheetItemClickListener itemClickListener) {
			this.name = name;
			this.color = color;
			this.itemClickListener = itemClickListener;
		}
	}

	public enum SheetItemColor {
		Blue("#037BFF"), Red("#FD4A2E");

		private String name;

		private SheetItemColor(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}