package jp.ac.uec.numa08.termcalc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import jp.ac.uec.numa08.core.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * 期間を計算するActivity. <br>
 * シークバーを動かすと、計算をして出力する. <br>
 * シークバーのコールバックメソッドは
 * <ul>
 * <li>onStartTrack</li>
 * <li>onProgressChanged</li>
 * <li>onStopTracking</li>
 * </ul>
 * の3つがあり、 匿名クラスで記述するとややこしくなるので OnSeekBarChangedListener クラスをimplements する
 * 
 * @author numanuma08
 * 
 */
public class TermCalcActivity extends Activity implements
		OnSeekBarChangeListener {
	// private static final String TAG = TermCalcActivity.class.getSimpleName();
	/** シークバーの最大値 */
	private static final int SEEKBAR_MAX = 100;
	/** シークバーの初期値 */
	private static final int DEFAULT_SEEKBAR = SEEKBAR_MAX / 2;

	/** 期間計算を行うクラス */
	private transient CalendarCalculator calendarCalc;
	/** 片方の日付はDatePickerを利用する。 */
	private transient DatePicker fromDatePicker;
	/** DatePickerから取り出した日付などを保存するクラス。 */
	private transient Calendar fromCalendar;
	/** 結果を出力するTextView */
	private transient TextView resultTextView;
	/** 期間を出力するTextView */
	private transient TextView addDateTextView;

	/**
	 * Activity起動時に呼び出されるメソッド。<br>
	 * やること多いよ！<br>
	 * <ol>
	 * <li>DatePickerの初期化</li>
	 * <li>日付保存カレンダーの初期化</li>
	 * <li>計算クラスの初期化</li>
	 * <li>結果表示TextViewの初期化</li>
	 * <li>期間出力TextViewの初期化</li>
	 * <li>シークバーの初期化</li>
	 * </ol>
	 */
	@Override
	public void onCreate(final Bundle sIState) {
		super.onCreate(sIState);
		// TODO Auto-generated method stub
		setContentView(R.layout.terncakc);

		fromDatePicker = (DatePicker) findViewById(R.id.fromdate_picker);
		fromCalendar = Calendar.getInstance();
		calendarCalc = new CalendarCalculator(fromCalendar);

		resultTextView = (TextView) findViewById(R.id.result_text);
		addDateTextView = (TextView) findViewById(R.id.add_date_text);

		// シークバーの初期化
		initializeSeekBar();
	}

	/** 期間を0として計算をして結果を出力する。 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		viewResult(fromCalendar, 0);
	}

	/**
	 * シークバーの初期化。<br>
	 * シークバーは、中央を0として最大が50,最小が-50となる。
	 */
	private void initializeSeekBar() {
		final SeekBar termSeekbar = (SeekBar) findViewById(R.id.term_seekbar);
		termSeekbar.setMax(SEEKBAR_MAX);
		termSeekbar.setProgress(DEFAULT_SEEKBAR);
		termSeekbar.setOnSeekBarChangeListener(TermCalcActivity.this);
	}

	/**
	 * 結果を表示するメソッド。<br>
	 * SimpleDateFormatを利用して、計算結果をエレガントに出力する。<br>
	 * でも、SimpleDateFormatはエレガントじゃないんだって・・・。知らんがな。
	 * 
	 * @param calendar
	 *            計算後のカレンダークラス。　
	 * 
	 * @param addDate
	 *            増減した日付。
	 */
	private void viewResult(final Calendar calendar, final int addDate) {
		addDateTextView.setText(addDate + "日後");

		// 日付の表示形式を決める.本当は、outputFormatはフィールド変数としてstaticをつけて
		// クラスの先頭で宣言するのがいいと思う.
		final SimpleDateFormat outputFormat = new SimpleDateFormat(
				"yyyy年MM月dd日", Locale.JAPAN);
		// 出力する文字列を作る.Stringは + を用いることで文字列の連結ができるのだが
		// メモリ消費的にあんまり美味しくないので多用しない.
		// あと、Stringの連結は使いにくい.
		final StringBuilder outputString = new StringBuilder(
				outputFormat.format(calendar.getTime()));
		// 何曜日かを求める.
		final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		outputString.append(CalendarCalculator.DAY_OF_WEEK[dayOfWeek]);
		resultTextView.setText(outputString);
	}

	/**
	 * シークバーの値が変わった時に呼び出されるメソッド。<br>
	 * DatePickerとシークバーから値を読み取って計算を行う。<br>
	 * 計算はCalendarCalculatorクラス内で実装している。
	 */
	@Override
	public void onProgressChanged(final SeekBar seekBar, final int progress,
			final boolean fromUser) {
		// TODO Auto-generated method stub
		// Log.d(TAG, Integer.toString(progress));
		fromCalendar.clear();
		fromCalendar.set(fromDatePicker.getYear(), fromDatePicker.getMonth(),
				fromDatePicker.getDayOfMonth());
		calendarCalc.setCalendar(fromCalendar);
		final int addDate = progress - DEFAULT_SEEKBAR;
		viewResult(calendarCalc.addDate(addDate), addDate);
	}

	/*
	 * シークバーが動かされ始めると呼ばれるメソッド
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void onStartTrackingTouch(final SeekBar seekBar) {
		// TODO Auto-generated method stub
		// Log.d(TAG, Integer.toString(seekBar.getProgress()));
	}

	/*
	 * シークバーが動かされるのが終わると呼ばれるメソッド {@inheritDoc}
	 */
	@Override
	public void onStopTrackingTouch(final SeekBar seekBar) {
		// TODO Auto-generated method stub
		// Log.d(TAG, Integer.toString(seekBar.getProgress()));
	}
}
