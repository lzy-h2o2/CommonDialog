package com.zndroid.widgets.wheel.base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.zndroid.widgets.utils.ReflectResourceUtil;
import com.zndroid.widgets.wheel.wheelview.JudgeDate;
import com.zndroid.widgets.wheel.wheelview.ScreenInfo;
import com.zndroid.widgets.wheel.wheelview.WheelMain;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author luzhenyu
 *         <p>
 *         GetTimeWheel 获取滚动时间
 *         </p>
 *         <i> 通过 new GetCityWheel(yourActivity) 构造该对象<dd>
 *         通过GetCityWheel.getResultStr() 可以获得选择的城市字符串<dd>
 *         如（“北京|北京市区|朝阳区”） currentTime的格式“yyyy-MM-dd”</i>
 * */
public class GetTimeWheel {

	private Context context;
	private View view;
	private static WheelMain wheelMain;
	private DateFormat dateFormat = new SimpleDateFormat("hh:mm");
	private String currentTime = null;

	private static String result;

	public GetTimeWheel(Context context, String currentTime) {
		this.context = context;
		if (currentTime == null) {
			currentTime = getCurrentTime();
		}else {
			this.currentTime = currentTime;
		}
		initView(currentTime);
	}
	/**
	 *  // TODO  不是24小时制，如果是下午会出现显示问题
	 * */
	private String getCurrentTime() {
		String timeString;
		Calendar calendar = Calendar.getInstance();
		timeString = calendar.get(Calendar.HOUR_OF_DAY)
				+ ":"
				+ calendar.get(Calendar.MINUTE) + "";
		return timeString;
	}

	private void initView(String currentTime) {
		LayoutInflater inflater = LayoutInflater.from(context);
		final View timepickerview = inflater.inflate(ReflectResourceUtil.getLayoutId(context, "zcommon_timepicker"),
				null);
		ScreenInfo screenInfo = new ScreenInfo((Activity) context);
		wheelMain = new WheelMain(timepickerview, context, true, true);
		wheelMain.screenHeight = screenInfo.getHeight();
		Calendar calendar = Calendar.getInstance();
		if (JudgeDate.isDate(currentTime, "hh:mm")) {
			try {
				calendar.setTime(dateFormat.parse(currentTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		int h = Integer.parseInt(currentTime.split(":")[0]);
		int m = Integer.parseInt(currentTime.split(":")[1]);

		wheelMain.initTimePicker(h, m);

		view = timepickerview;
	}

	public View getTimeView() {
		return view;
	}

	public static String getTimeResult() {
		result = wheelMain.getTime();
		return result;
	}


}
