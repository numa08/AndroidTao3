package jp.ac.uec.numa08.fizzbuzz;

import jp.ac.uec.numa08.core.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * FizzBuzzゲームのためのActivity．
 * このActivityもボタンがいくつかあるので、View.OnClickListerをimplementsしている。
 * 
 * @author numanuma08
 * 
 */
public class FizzBuzzActivity extends Activity implements OnClickListener {
	/** 画面上のボタンのIDを保存する配列。 */
	private static final int[] BUTTON_IDS = { R.id.fizz_button,
			R.id.buzz_button, R.id.fizz_buzz_button, R.id.number_button };
	/** 現在出題中の問題 */
	protected transient int targetNumber = 1;
	/** 最大の問題数 */
	protected static final int MAX_QUESTION = 5;

	/**
	 * Activity起動時に呼び出される。<br>
	 * 画面上のボタンのリスナークラスの登録、<br>
	 * 画面上に問題の表示を行う。
	 */
	@Override
	public void onCreate(final Bundle sIState) {
		super.onCreate(sIState);
		// TODO Auto-generated method stub
		setContentView(R.layout.fizzbuzz);

		for (final int id : BUTTON_IDS) {
			findViewById(id).setOnClickListener(this);
		}
		changeText(targetNumber);
		changeButtonText(targetNumber);
	}

	/**
	 * ボタンがクリックされた時に呼ばれるメソッド。<br>
	 * まず、押したボタンが正解かどうかを調べる。<br>
	 * その上で、終了条件を満たすならゲームを終了する。<br>
	 * 満たさない、ゲームを続行する。<br>
	 * 終了する時は、終了処理を呼び出す。
	 */
	@Override
	public void onClick(final View view) {
		// TODO Auto-generated method stub
		final boolean isCollect = checkAnswer(view);
		if (isCollect) {
			targetNumber++;
			if (isEnd()) {
				endGame("お疲れ様");
			} else {
				changeText(targetNumber);
				changeButtonText(targetNumber);
			}
		}
	}

	/**
	 * 終了判定.<br>
	 * 現在の問題数が規定値を超えたら、終わらせるようにする。
	 * 
	 * @return 条件を満たしたらtrue
	 */
	private boolean isEnd() {
		return targetNumber > MAX_QUESTION;
	}

	/**
	 * ゲームの終了処理.ダイアログでメッセージをだして終了する.
	 * 
	 * @param endMessage
	 *            表示するメッセージ。
	 */
	protected void endGame(final String endMessage) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				FizzBuzzActivity.this);
		builder.setMessage(endMessage);
		builder.setPositiveButton("終了", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				finish();
			}
		});
		builder.show();
	}

	/**
	 * 正解かどうか判断する。<br>
	 * ボタンのidから押されたボタンを判断し、それに応じたメッセージをFizzBuzzCheckerクラスで判断する。
	 * 
	 * @param view
	 *            押したボタン
	 * @return 正解かどうか。正解ならtrue。
	 */
	protected boolean checkAnswer(final View view) {
		final FizzBuzzChecker checker = new FizzBuzzChecker(targetNumber);
		String userInput;
		switch (view.getId()) {
		case R.id.fizz_button:
			userInput = FizzBuzzChecker.FIZZ_MSG;
			break;
		case R.id.buzz_button:
			userInput = FizzBuzzChecker.BUZZ_MSG;
			break;
		case R.id.fizz_buzz_button:
			userInput = FizzBuzzChecker.FIZZ_BUZZ_MSG;
			break;
		case R.id.number_button:
			userInput = Integer.toString(targetNumber);
			break;
		default:
			userInput = "no";
			break;
		}
		return checker.isCollect(userInput);
	}

	/**
	 * 表示されている文字を変える。<br>
	 * 
	 * 
	 * @param number
	 *            表示する文字
	 */
	private void changeText(final int number) {
		changeText(number, R.id.number);
	}

	/**
	 * 表示されている文字を変える。<br>
	 * これは、変える対象のTextViewのidを指定して呼び出す。
	 * 
	 * @param number
	 *            　表示する文字。
	 * @param viewId
	 *            対象のViewのid。
	 */
	protected void changeText(final int number, final int viewId) {
		final TextView numberText = (TextView) findViewById(viewId);
		numberText.setText(Integer.toString(number));
	}

	/**
	 * 数字ボタンの文字を変える
	 * 
	 * @param number
	 *            表示する文字
	 */
	private void changeButtonText(final int number) {
		changeButtonText(number, R.id.number_button);
	}

	/**
	 * 数字ボタンの文字を変える。<br>
	 * こちらは、変える対象のButtonのidを指定する。
	 * 
	 * @param number
	 *            表示する文字。
	 * @param viewId
	 *            対象のButtonのid。
	 */
	protected void changeButtonText(final int number, final int viewId) {
		final Button numberButton = (Button) findViewById(viewId);
		numberButton.setText(Integer.toString(number));
	}

}
