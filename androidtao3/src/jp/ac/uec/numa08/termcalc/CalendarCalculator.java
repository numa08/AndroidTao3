package jp.ac.uec.numa08.termcalc;

import java.util.Calendar;

/**
 * 実際に日付の計算を行うクラス. プロパティのcalendarに対して計算を行うが、ゲッターとセッターを設定していあるので
 * 一々インスタンスを作らずとも利用できる.
 * 
 * @author numanuma08
 * 
 */
public class CalendarCalculator {
	// private static final String TAG =
	// CalendarCalculator.class.getSimpleName();
	public static final String[] DAY_OF_WEEK = { "日曜", "月曜", "火曜", "水曜", "木曜",
			"金曜", "土曜" };

	private Calendar calendar;

	public CalendarCalculator(final Calendar calendar) {
		this.calendar = calendar;
	}

	public Calendar addDate(final int date) {
		calendar.add(Calendar.DATE, date);
		return calendar;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(final Calendar calendar) {
		this.calendar = calendar;
	}
}
