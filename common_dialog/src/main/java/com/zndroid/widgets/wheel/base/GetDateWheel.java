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
 *         GetDateWheel 获取滚动日期
 *         </p>
 *         <i> 通过 new GetCityWheel(yourActivity, String currentTime) 构造该对象<dd>
 *         如果currentTime（格式为yyyy-MM-dd）为空，则选择以当前日期<dd>
 *         通过GetCityWheel.getTimeResultStr() 可以获得选择的城市字符串<dd>
 *         如（“北京|北京市区|朝阳区”） currentTime的格式“yyyy-MM-dd”</i>
 * */
public class GetDateWheel {

	private Context context;
	private View view;
	private static WheelMain wheelMain;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private String currentTime = null;
	
	private static String result;
	
	public GetDateWheel(Context context,String currentTime) {
		this.context = context;
		if (currentTime == null) {
			currentTime = getCurrentTime();
		}else {
			this.currentTime = currentTime;
		}
		initView();
	}
	
	private String getCurrentTime() {
		String timeString;
		Calendar calendar = Calendar.getInstance();
		timeString = calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "";
		return timeString;
	}

	private void initView() {
		LayoutInflater inflater = LayoutInflater.from(context);
		final View timePickerView = inflater.inflate(ReflectResourceUtil.getLayoutId(context, "timepicker"),
				null);
		ScreenInfo screenInfo = new ScreenInfo((Activity) context);
		wheelMain = new WheelMain(timePickerView, context);
		wheelMain.screenHeight = screenInfo.getHeight();
		Calendar calendar = Calendar.getInstance();
		if (JudgeDate.isDate(currentTime, "yyyy-MM-dd")) {
			try {
				calendar.setTime(dateFormat.parse(currentTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		wheelMain.initDateTimePicker(year, month, day,0,0);
		
		view = timePickerView;
	}

	public View getTimeView() {
		return view;
	}

	public static String getTimeResult() {
		result = wheelMain.getDateTime();
		return result;
	}
	
	
}
