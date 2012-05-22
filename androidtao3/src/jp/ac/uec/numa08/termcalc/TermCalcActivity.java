package jp.ac.uec.numa08.termcalc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import jp.ac.uec.numa08.core.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * 期間を計算するActivity. シークバーを動かすと、計算をして出力する. シークバーのコールバックメソッドは
 * onStartTrack,onProgressChanged,onStopTrackingの3つがあり、 匿名クラスで記述するとややこしくなるので
 * OnSeekBarChangedListener クラスをimplements する
 * 
 * @author numanuma08
 * 
 */
public class TermCalcActivity extends Activity implements
		OnSeekBarChangeListener {
	private static final String TAG = TermCalcActivity.class.getSimpleName();
	private static final int SEEKBAR_MAX = 100;
	private static final int DEFAULT_SEEKBAR = SEEKBAR_MAX / 2;

	private transient CalendarCalculator calendarCalc;
	private transient DatePicker fromDatePicker;
	private transient Calendar fromCalendar;
	private transient TextView resultTextView;
	private transient TextView addDateTextView;

	/** Called when the activity is first created. */
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

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		viewResult(fromCalendar, 0);
	}

	/**
	 * シークバーの初期化
	 */
	private void initializeSeekBar() {
		final SeekBar termSeekbar = (SeekBar) findViewById(R.id.term_seekbar);
		termSeekbar.setMax(SEEKBAR_MAX);
		termSeekbar.setProgress(DEFAULT_SEEKBAR);
		termSeekbar.setOnSeekBarChangeListener(TermCalcActivity.this);
	}

	/**
	 * 結果を表示するメソッド
	 * 
	 * @param calendar
	 *            表示する結果の日付
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

	/*
	 * シークバーが動かされている最中に呼ばれるメソッド {@inheritDoc}
	 */
	@Override
	public void onProgressChanged(final SeekBar seekBar, final int progress,
			final boolean fromUser) {
		// TODO Auto-generated method stub
		Log.d(TAG, Integer.toString(progress));
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
		Log.d(TAG, Integer.toString(seekBar.getProgress()));
	}

	/*
	 * シークバーが動かされるのが終わると呼ばれるメソッド {@inheritDoc}
	 */
	@Override
	public void onStopTrackingTouch(final SeekBar seekBar) {
		// TODO Auto-generated method stub
		Log.d(TAG, Integer.toString(seekBar.getProgress()));
	}
}
