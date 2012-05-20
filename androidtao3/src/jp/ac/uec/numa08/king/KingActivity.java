package jp.ac.uec.numa08.king;

import jp.ac.uec.numa08.core.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * 王様ゲームのActivity.ボタンが複数あるのでリスナークラスをimplementsする.スピナーは、匿名クラスを使ってコールバックの処理を設定する.
 * 
 * @author numanuma08
 * 
 */
public class KingActivity extends Activity implements OnClickListener {
	private static final String TAG = KingActivity.class.getSimpleName();
	private static final int[] BUTTON_ID = { R.id.decide_king_button,
			R.id.what_do_button };
	private static final int MAX_ATTEND = 20;
	private transient int attendNumber = 3;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle sIState) {
		super.onCreate(sIState);
		// TODO Auto-generated method stub
		setContentView(R.layout.kinig);
		// ボタンのクリックリスナーの登録
		for (int id : BUTTON_ID) {
			findViewById(id).setOnClickListener(this);
		}

		final Spinner attendSpinner = (Spinner) findViewById(R.id.attend_spinner);
		spinnerInitialize(attendSpinner);
	}

	/**
	 * Spinnerの初期設定をする
	 * 
	 * @param attendSpinner
	 *            スピナー
	 */
	private void spinnerInitialize(final Spinner attendSpinner) {
		// スピナーに表示する項目を追加する
		final ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item);
		for (int i = 3; i <= MAX_ATTEND; i++) {
			adapter.add(Integer.valueOf(i));
		}
		attendSpinner.setAdapter(adapter);

		// 項目が選択された時の動作を設定する
		attendSpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					/*
					 * 項目選択時に呼ばれるメソッド {@inheritDoc}
					 */
					@Override
					public void onItemSelected(final AdapterView<?> parent,
							final View view, final int position,
							final long selectedId) {
						Log.d(TAG, "on selected");
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

	@Override
	public void onClick(final View view) {
		// TODO Auto-generated method stub
		final KingGame game = new KingGame(attendNumber);
		String message;
		switch (view.getId()) {
		case R.id.decide_king_button:
			message = game.decideWahtDo() + "が王様です！！";
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

	private void showDialog(final String message) {
		new AlertDialog.Builder(KingActivity.this).setMessage(message).show();
	}
}
