package jp.ac.uec.numa08.fizzbuzz;

import jp.ac.uec.numa08.core.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author numanuma08 FizzBuzzゲームのためのActivity．このActivityも，ボタンがいくつかあるので、View.
 *         OnClickListenerをimplementsしている．
 * 
 */
public class FizzBuzzActivity extends Activity implements OnClickListener {
	private static final int[] _Button_Ids = { R.id.fizz_button,
			R.id.buzz_button, R.id.fizz_buzz_button, R.id.number_button };
	protected transient int targetNumber = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle sIState) {
		super.onCreate(sIState);
		// TODO Auto-generated method stub
		setContentView(R.layout.fizzbuzz);

		for (final int id : _Button_Ids) {
			findViewById(id).setOnClickListener(this);
		}
		changeText(targetNumber);
		changeButtonText(targetNumber);
	}

	@Override
	public void onClick(final View view) {
		// TODO Auto-generated method stub
		final boolean isCollect = checkAnswer(view);
		if (isCollect) {
			targetNumber++;
			changeText(targetNumber);
			changeButtonText(targetNumber);
		} else {
			new AlertDialog.Builder(FizzBuzzActivity.this).setMessage("違います.")
					.show();
		}
	}

	/**
	 * 正解かどうか判断する
	 * 
	 * @param view
	 *            押したボタン
	 * @return 正解かどうか
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
	 * 表示されている文字を変える
	 * 
	 * @param number
	 *            表示する文字
	 */
	private void changeText(final int number) {
		changeText(number, R.id.number);
	}

	protected void changeText(final int number, final int id) {
		final TextView numberText = (TextView) findViewById(id);
		numberText.setText(Integer.toString(number));
	}

	/**
	 * 数字ボタンのテキストを変える
	 * 
	 * @param number
	 *            表示する文字
	 */
	private void changeButtonText(final int number) {
		changeButtonText(number, R.id.number_button);
	}

	protected void changeButtonText(final int number, final int id) {
		final Button numberButton = (Button) findViewById(id);
		numberButton.setText(Integer.toString(number));
	}

}
