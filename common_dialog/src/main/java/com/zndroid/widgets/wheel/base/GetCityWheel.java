package com.zndroid.widgets.wheel.base;

import com.zndroid.widgets.utils.ReflectResourceUtil;
import com.zndroid.widgets.wheel.wheelcity.AddressData;
import com.zndroid.widgets.wheel.wheelcity.OnWheelChangedListener;
import com.zndroid.widgets.wheel.wheelcity.WheelView;
import com.zndroid.widgets.wheel.wheelcity.adapters.AbstractWheelTextAdapter;
import com.zndroid.widgets.wheel.wheelcity.adapters.ArrayWheelAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author luzhenyu
 *         <p>
 *         GetCityWheel 省-市-地区 三级联动
 *         </p>
 *         <i> 通过 new GetCityWheel(yourActivity) 构造该对象<dd>
 *         通过GetCityWheel.getResultStr() 可以获得选择的城市字符串<dd>
 *         如（“北京|北京市区|朝阳区”） </i>
 * */
public class GetCityWheel {

	private Context context;
	private View view;

	private static String cityTxt = "";
	
	public GetCityWheel(Context context) {
		this.context = context;
		initView();
	}

	private void initView() {
		View contentView = LayoutInflater.from(context).inflate(
				ReflectResourceUtil.getLayoutId(context,
						"zcommon_wheelcity_cities_layout"), null);
		final WheelView country = (WheelView) contentView
				.findViewById(ReflectResourceUtil.getId(context,
						"wheelcity_country"));
		country.setVisibleItems(3);
		country.setViewAdapter(new CountryAdapter(context));

		final String cities[][] = AddressData.CITIES;
		final String ccities[][][] = AddressData.COUNTIES;
		final WheelView city = (WheelView) contentView
				.findViewById(ReflectResourceUtil.getId(context,
						"wheelcity_city"));
		city.setVisibleItems(0);

		final WheelView ccity = (WheelView) contentView
				.findViewById(ReflectResourceUtil.getId(context,
						"wheelcity_ccity"));
		ccity.setVisibleItems(0);

		country.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				updateCities(city, cities, newValue);
				cityTxt = AddressData.PROVINCES[country.getCurrentItem()]
						+ " | "
						+ AddressData.CITIES[country.getCurrentItem()][city
								.getCurrentItem()]
						+ " | "
						+ AddressData.COUNTIES[country.getCurrentItem()][city
								.getCurrentItem()][ccity.getCurrentItem()];
			}
		});

		city.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				updatecCities(ccity, ccities, country.getCurrentItem(),
						newValue);
				cityTxt = AddressData.PROVINCES[country.getCurrentItem()]
						+ " | "
						+ AddressData.CITIES[country.getCurrentItem()][city
								.getCurrentItem()]
						+ " | "
						+ AddressData.COUNTIES[country.getCurrentItem()][city
								.getCurrentItem()][ccity.getCurrentItem()];
			}
		});

		ccity.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				cityTxt = AddressData.PROVINCES[country.getCurrentItem()]
						+ " | "
						+ AddressData.CITIES[country.getCurrentItem()][city
								.getCurrentItem()]
						+ " | "
						+ AddressData.COUNTIES[country.getCurrentItem()][city
								.getCurrentItem()][ccity.getCurrentItem()];
			}
		});

		country.setCurrentItem(1);
		city.setCurrentItem(1);
		ccity.setCurrentItem(1);

		view = contentView;

	}

	/**
	 * Updates the city wheel
	 */
	private void updateCities(WheelView city, String cities[][], int index) {
		ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<String>(
				context, cities[index]);
		adapter.setTextSize(18);
		city.setViewAdapter(adapter);
		city.setCurrentItem(0);
	}

	/**
	 * Updates the ccity wheel
	 */
	private void updatecCities(WheelView city, String ccities[][][], int index,
			int index2) {
		ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<String>(
				context, ccities[index][index2]);
		adapter.setTextSize(18);
		city.setViewAdapter(adapter);
		city.setCurrentItem(0);
	}

	/**
	 * Adapter for countries
	 */
	private class CountryAdapter extends AbstractWheelTextAdapter {
		// Countries names
		private String countries[] = AddressData.PROVINCES;

		/**
		 * Constructor
		 */
		protected CountryAdapter(Context context) {
			super(context, ReflectResourceUtil.getLayoutId(context,
					"zcommon_wheelcity_country_layout"), NO_RESOURCE);
			setItemTextResource(ReflectResourceUtil.getId(context,
					"wheelcity_country_name"));
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return countries.length;
		}

		@Override
		protected CharSequence getItemText(int index) {
			return countries[index];
		}
	}

	public View getCityView() {
		return view;
	}

	public static String getResultStr() {
		return cityTxt;

	}
}
