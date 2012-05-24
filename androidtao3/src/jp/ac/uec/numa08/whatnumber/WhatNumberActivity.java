package jp.ac.uec.numa08.whatnumber;

import jp.ac.uec.numa08.core.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * 数当てゲームのActivity。<br>
 * スピナーの項目が選択された時のリスナークラス<br>
 * OnItemSelectedListenerをimplementsする。
 * 
 * @author numanuma08
 * 
 */
public class WhatNumberActivity extends Activity implements
		OnItemSelectedListener {
	// private static final String TAG =
	// WhatNumberActivity.class.getSimpleName();
	/** 出題されうる最大の数字。20。 */
	private static final int MAX_NUMBER = 20;
	/** 数当てゲームの処理を実装したクラス。 */
	private transient WhatNumber whatNumber;
	/** 正解を選ぶスピナー */
	private transient Spinner answerSpinner;

	/**
	 * Activity起動時に呼び出されるメソッド。<br>
	 * ゲーム処理クラスの初期化と、スピナーの初期化を行う。
	 */
	@Override
	public void onCreate(final Bundle sIState) {
		super.onCreate(sIState);
		// TODO Auto-generated method stubfalse
		setContentView(R.layout.whatnumber);
		// ゲーム処理クラスの初期化.resetAnswer()を呼び出して、解答を設定する.
		whatNumber = new WhatNumber(MAX_NUMBER);
		whatNumber.resetAnser();

		// スピナーの初期化
		initializeSpiner();
	}

	/**
	 * スピナーを初期化するメソッド。<br>
	 * スピナーの表示項目は0~20までの整数。<br>
	 * 項目が選択された際の動作もこWhatNumberActivity内に記述する。
	 * 
	 */
	private void initializeSpiner() {
		answerSpinner = (Spinner) findViewById(R.id.answer_spinner);
		final ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(
				WhatNumberActivity.this, android.R.layout.simple_spinner_item);
		for (int i = 1; i <= MAX_NUMBER; i++) {
			adapter.add(Integer.valueOf(i));
		}
		// 表示する一覧表の設定
		answerSpinner.setAdapter(adapter);

		// 項目が選択された時の設定
		answerSpinner.setOnItemSelectedListener(WhatNumberActivity.this);
	}

	/**
	 * スピナーの項目が選択された時に呼ばれるメソッド。<br>
	 * 選択された項目が正解かどうかを判断し、それぞれダイアログとして結果をだす。<br>
	 * ただ、スピナーはonResume()の後になぜかこのメソッドが呼び出される（バグ？仕様？）ために、<br>
	 * ちょっと細工をしてやる必要がある。
	 */
	@Override
	public void onItemSelected(final AdapterView<?> parent, final View view,
			final int position, final long selectedId) {
		// TODO Auto-generated method stub

		// なぜかスピナーはonResume()の後に、onItemSelected()が呼び出されてしまう.
		// とりあえず、Foucasableの初期値をfalseとし、初期起動時に反転する.
		// 暫定対策方法として・・・
		if (answerSpinner.isFocusable()) {
			// TODO Auto-generated method stub
			// 選択された項目を得る
			final int userInput = (Integer) parent.getItemAtPosition(position);
			String message;
			if (whatNumber.isCollect(userInput)) {
				message = "正解！！";
				whatNumber.resetAnser();
			} else {
				message = whatNumber.getHint(userInput);
			}
			showDialog(message);
		} else {
			answerSpinner.setFocusable(true);
		}
	}

	/**
	 * スピナー初期化のための細工をするために、<br>
	 * onStartを実装する。
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// onStart()で初期設定を書くのは,別のActivityから戻った時のことを考えてのこと
		answerSpinner.setFocusable(false);
	}

	/**
	 * ダイアログを表示する.まだ続けるなら、ダイアログを消し、もう止めるなならActivityを終わる.
	 * 
	 * @param message
	 *            ヒントのメッセージ
	 */
	private void showDialog(final String message) {
		final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
				WhatNumberActivity.this);
		dialogBuilder.setMessage(message);
		dialogBuilder.setPositiveButton("まだ続ける",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(final DialogInterface dialog,
							final int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
		dialogBuilder.setNegativeButton("もうやめる",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(final DialogInterface dialog,
							final int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						finish();
					}
				});
		dialogBuilder.show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
