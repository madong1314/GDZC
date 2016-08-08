package com.app.gdzc.net;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonUtils {
	public static <T> T json2Bean(String result, Class<T> clz) {
		Gson gson = new Gson();
		T t = gson.fromJson(result, clz);
		return t;
	}

	/**
	 * 对象转换成json字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	/**
	 * json字符串转成对象
	 * @param str
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String str, Type type) {
		Gson gson = new Gson();
		return gson.fromJson(str, type);
	}

	/**
	 * json字符串转成对象
	 * @param str
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String str, Class<T> type) {
		Gson gson = new Gson();
		return gson.fromJson(str, type);
	}
}
