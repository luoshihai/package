package com.cnsunru.common.util;

import android.graphics.Color;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * 
 * @author liufan 基本的字符串操作
 * 
 */
public final class StringUtils {

	public static boolean equals(String str, String str2) {
		return TextUtils.equals(str, str2);
	}

	/**
	 * 判断空,包括null , "" , "null"
	 * 
	 * @param target
	 * @return true if empty ,
	 */
	public static boolean isEmpty(String target) {
		return null == target || "".equals(target) || "null".equalsIgnoreCase(target);
	}

	/**
	 * 处理空串
	 * 
	 * @param target
	 *            待检验的字符串
	 * @param placeholder
	 *            如果目标串是空,则用此替代目标串
	 * @return target不空,返回target ,为空返回placeholder
	 */
	public static String ifNull(String target, String placeholder) {
		return isEmpty(target) ? placeholder : target;
	}

	public static String ifNull(String target) {
		return ifNull(target, "");
	}

	/**
	 * 将字符串数组用某符号连接起来
	 * 
	 * @param targets
	 *            目标数组 : {"A","B","C"}
	 * @param splitter
	 *            分隔符: :
	 * @return 拼接后的结果 : "A:B:C"
	 */
	public static <T> String join(T[] targets, String splitter) {
		StringBuilder temp = new StringBuilder();
		for (T dat : targets) {
			temp.append(ifNull(dat.toString())).append(splitter);
		}
		int len = temp.lastIndexOf(splitter);
		if (temp.length() == (len + splitter.length())) {
			temp.delete(len, temp.length());
		}
		return temp.toString();
	}

	public static <T> String join(List<T> targets) {
		return join(targets, ",");
	}

	public static <T> String join(List<T> targets, String splitter) {
		StringBuilder temp = new StringBuilder();
		for (T dat : targets) {
			temp.append(ifNull(dat.toString())).append(splitter);
		}
		int len = temp.lastIndexOf(splitter);
		if (temp.length() == (len + splitter.length())) {
			temp.delete(len, temp.length());
		}
		return temp.toString();
	}

	public static <T> String join(T[] targets) {
		return join(targets, ",");
	}

	/**
	 * 快速生成对象数组
	 * 
	 * @param something
	 * @return
	 */
	public static <T> T[] sample(T... something) {
		return something;
	}

	public static <T> List<T> sampleToList(T... somethings) {
		return Arrays.asList(somethings);
	}

	/**
	 * 处理null情况下的toString问题
	 * 
	 * @param obj
	 *            任意对象
	 * @param placeholder
	 *            如果任意对象为null,或者toString为任何空,则返回它
	 * @return obj.toString() 或 placeholder
	 */
	public static String toString(Object obj, String placeholder) {
		return obj == null ? placeholder : ifNull(obj.toString(), placeholder);
	}

	public static String toString(Object obj) {
		return toString(obj, "");
	}

	/**
	 * 一键uuid
	 * 
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 格式化时间
	 */

	private static String getStringWithHour(int hour) {
		if (hour >= 0 && hour <= 3) {
			return "深夜";
		}
		if (hour > 3 && hour <= 5) {
			return "凌晨";
		}
		if (hour > 5 && hour <= 12) { // 5:00 - 12:00上午
			return "上午";
		}
		if (hour > 12 && hour <= 18) { // 12:00 - 18:00 下午
			return "下午";
		}
		return "晚上";
	}

	private static boolean isSameDay(Calendar today, Calendar other) {
		if (today.get(Calendar.YEAR) != other.get(Calendar.YEAR)) return false;
		if (today.get(Calendar.MONTH) != other.get(Calendar.MONTH)) return false;
		if (today.get(Calendar.DAY_OF_MONTH) != other.get(Calendar.DAY_OF_MONTH)) return false;
		return true;
	}

	public static String parseTimeWithToday(String timeStirng) {
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parse = dateFormatter.parse(timeStirng);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(parse);

			Calendar today = Calendar.getInstance();

			if (isSameDay(today, calendar)) {
				// 同一天
				return new SimpleDateFormat(String.format("%s hh:mm", getStringWithHour(calendar.get(Calendar.HOUR_OF_DAY)))).format(parse);
			} else {
				return new SimpleDateFormat("yyyy-MM-dd").format(parse);
			}
		} catch (Exception e) {
			return timeStirng;
		}
	}

	public static String parseTimeString(String timeString) {
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parse = dateFormatter.parse(timeString);
			// 2017年3月28日下午04:44
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(parse);
			String template = String.format("yyyy年MM月dd日 %shh:mm", getStringWithHour(calendar.get(Calendar.HOUR_OF_DAY)));
			return new SimpleDateFormat(template).format(parse);
		} catch (Exception e) {
			return timeString;
		}
	}

//	public static void addUriPre(TextView txt, String content, String prefix) {
//		txt.setText(matchUrlAddPre(content, prefix));
//		txt.setAutoLinkMask(Linkify.WEB_URLS);
//		txt.setLinkTextColor(Color.parseColor("#2972AB"));
//		txt.setMovementMethod(LinkMovementMethod.getInstance());
//	}

//	public static CharSequence matchUrlAddPre(String content, String prefix) {
//		// final String pattern = "(([a-zA-z]+://)?(www\\.[\\w]+\\.[a-zA-Z]+))";
//		final String pattern = "(([a-zA-z]+://)?(w{1,3}[^.]*\\.)[^\\s]*)";
//		// final String pattern =
//		// "(([a-zA-z]+://)?(w{1,3}[^.]*\\.)[^\\s|^\u4e00-\u9fa5]*)";
//		return AndroidEmoji.ensure(content.replaceAll(pattern, prefix + "$1" + prefix));
//	}

}
