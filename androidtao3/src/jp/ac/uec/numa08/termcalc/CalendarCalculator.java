package jp.ac.uec.numa08.termcalc;

import java.util.Calendar;

/**
 * 実際に日付の計算を行うクラス。<br>
 * シークバーの移動をする度何度も呼ばれるため、ゲッターとセッターを利用して<br>
 * インスタンスを一々作らなくても利用できるようにする。
 * 
 * @author numanuma08
 * 
 */
public class CalendarCalculator {
	// private static final String TAG =
	// CalendarCalculator.class.getSimpleName();
	/** 曜日の配列。Calendarクラスは便利だけど、日本語の曜日を扱えないため、自分で実装する必用がある。 */
	public static final String[] DAY_OF_WEEK = { "日曜", "月曜", "火曜", "水曜", "木曜",
			"金曜", "土曜" };

	/** 計算用のカレンダークラス。 */
	private Calendar calendar;

	/**
	 * コンストラクタ。<br>
	 * DatePickerで表示されている日付のデータをCalendarインスタンスとして設定する。
	 * 
	 * @param calendar
	 *            DatePickerで表示されている日付。
	 */
	public CalendarCalculator(final Calendar calendar) {
		this.calendar = calendar;
	}

	/**
	 * 指定の日付を足し合わせるメソッド。<br>
	 * もちろん、負の値を足すこともできるみたい。
	 * 
	 * @param date
	 *            足し合わせる日数。
	 * @return 足しあわせた後の日付データ。
	 */
	public Calendar addDate(final int date) {
		calendar.add(Calendar.DATE, date);
		return calendar;
	}

	/**
	 * 日付データを得る。
	 * 
	 * @return 日付データ。
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * 日付データを再設定する。
	 * 
	 * @param calendar
	 *            日付データ
	 */
	public void setCalendar(final Calendar calendar) {
		this.calendar = calendar;
	}
}
