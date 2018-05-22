package com.zndroid.widgets.utils;

import android.content.Context;

import java.lang.reflect.Field;

/**
 * @author luzhenyu
 * 
 *         <p>
 *         ReflectResourceUtil - get Resource by Reflect
 *         </p>
 *         <i>通过ReflectResourceUtil.getXXX的形式<dd>
 *         只要传入所在的Activity和资源名称就可以获取资源的id<dd>
 *         可以获得的有view的id、LayoutId、StringId、getDrawableId、getStyleId</i>
 * 
 * */
public class ReflectResourceUtil {
	/**
	 * 获取资源反射值
	 * 
	 * @param context
	 * @param name
	 * @param type
	 * @return
	 */
	private static int getResourceId(Context context, String name, String type) {
		int id = 0;
		id = context.getResources().getIdentifier(name, type,
				context.getPackageName());
		return id;
	}

	public static int getId(Context context, String name) {
		return getResourceId(context, name, "id");
	}

	public static int getLayoutId(Context context, String name) {
		return getResourceId(context, name, "layout");
	}

	public static int getStringId(Context context, String name) {
		return getResourceId(context, name, "string");
	}

	public static int getDrawableId(Context context, String name) {
		return getResourceId(context, name, "drawable");
	}

	public static int getStyleId(Context context, String name) {
		return getResourceId(context, name, "style");
	}

	public static int[] getStyleableIntArray(Context context, String name) {
		try {
			Field[] fields = Class.forName(context.getPackageName() + ".R$styleable").getFields();//.与$ difference,$表示R的子类
			for (Field field : fields) {
				if (field.getName().equals(name)) {
					int ret[] = (int[]) field.get(null);
					return ret;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 遍历R类得到styleable数组资源下的子资源，1.先找到R类下的styleable子类，2.遍历styleable类获得字段值
	 *
	 * @param context
	 * @param styleableName
	 * @param styleableFieldName
	 * @return
	 */
	public static int getStyleableFieldId(Context context, String styleableName, String styleableFieldName) {
		String className = context.getPackageName() + ".R";
		String type = "styleable";
		String name = styleableName + "_" + styleableFieldName;
		try {
			Class<?> cla = Class.forName(className);
			for (Class<?> childClass : cla.getClasses()) {
				String simpleName = childClass.getSimpleName();
				if (simpleName.equals(type)) {
					for (Field field : childClass.getFields()) {
						String fieldName = field.getName();
						if (fieldName.equals(name)) {
							return (int) field.get(null);
						}
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return 0;
	}
}
