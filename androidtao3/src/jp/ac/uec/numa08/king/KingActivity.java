package jp.ac.uec.numa08.king;

import jp.ac.uec.numa08.core.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * 王様ゲームのActivity.ボタンが複数あるのでリスナークラスをimplementsする.<br>
 * スピナーは、匿名クラスを使ってコールバックの処理を設定する.
 * 
 * @author numanuma08
 * 
 */
public class KingActivity extends Activity implements OnClickListener {
	// private static final String TAG = KingActivity.class.getSimpleName();
	/** 画面上のボタンのidを保存する配列。 */
	private static final int[] BUTTON_IDS = { R.id.decide_king_button,
			R.id.what_do_button };
	/** 最大参加人数。20人。 */
	private static final int MAX_ATTEND = 20;
	/** 最小参加者 。3人。 */
	private static final int MIN_ATTEND = 3;
	/** 選択された、参加人数。 */
	private transient int attendNumber = 3;

	/**
	 * Activity起動時に呼び出される。<br>
	 * ボタンのクリックリスナーの登録と、スピナーの初期化を行う。
	 */
	@Override
	public void onCreate(final Bundle sIState) {
		super.onCreate(sIState);
		// TODO Auto-generated method stub
		setContentView(R.layout.kinig);
		// ボタンのクリックリスナーの登録
		for (int id : BUTTON_IDS) {
			findViewById(id).setOnClickListener(this);
		}

		final Spinner attendSpinner = (Spinner) findViewById(R.id.attend_spinner);
		spinnerInitialize(attendSpinner);
	}

	/**
	 * スピナーの初期化を行うメソッド。<br>
	 * 匿名クラスを利用して、スピナーの項目選択時の動作も設定する。<br>
	 * 表示する項目は、現在の参加者の人数。
	 * 
	 * @param attendSpinner
	 *            スピナー
	 */
	private void spinnerInitialize(final Spinner attendSpinner) {
		// スピナーに表示する項目を追加する
		final ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item);
		for (int i = MIN_ATTEND; i <= MAX_ATTEND; i++) {
			adapter.add(Integer.valueOf(i));
		}
		attendSpinner.setAdapter(adapter);

		// 項目が選択された時の動作を設定する
		attendSpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					/**
					 * 項目選択時に呼ばれるメソッド。<br>
					 * 現在の参加者に、選択された項目を代入する。
					 */
					@Override
					public void onItemSelected(final AdapterView<?> parent,
							final View view, final int position,
							final long selectedId) {
						// Log.d(TAG, "on selected");
						// TODO Auto-generated method stub
						// 現在の参加者の人数を、選ばれた項目の人数とする
						attendNumber = (Integer) parent
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(final AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
	}

	/**
	 * ボタンがクリックされた時に呼ばれるメソッド。<br>
	 * 王様を決めるボタンと、ヤルことを決めるボタンで動作を分ける。<br>
	 * 
	 */
	@Override
	public void onClick(final View view) {
		// TODO Auto-generated method stub
		final KingGame game = new KingGame(attendNumber);
		String message;
		switch (view.getId()) {
		case R.id.decide_king_button:
			message = game.decideKing() + "が王様です！！";
			break;
		case R.id.what_do_button:
			message = game.decideWahtDo();
			break;
		default:
			message = "なにかがおかしいよ";
			break;
		}
		showDialog(message);
	}

	/**
	 * ダイアログを表示するメソッド。<br>
	 * 表示するだけなので、ここに分けておく。
	 * 
	 * @param message
	 *            表示するメッセージ。
	 */
	private void showDialog(final String message) {
		new AlertDialog.Builder(KingActivity.this).setMessage(message).show();
	}
}
