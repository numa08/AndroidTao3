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
	private transient int targetNumber = 1;

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
		final FizzBuzzChecker checker = new FizzBuzzChecker(targetNumber);
		final String answer = checker.check();
		boolean isCollect = false;
		switch (view.getId()) {
		case R.id.fizz_button:
			isCollect = FizzBuzzChecker.FIZZ_MSG.equals(answer);
			break;
		case R.id.buzz_button:
			isCollect = FizzBuzzChecker.BUZZ_MSG.equals(answer);
			break;
		case R.id.fizz_buzz_button:
			isCollect = FizzBuzzChecker.FIZZ_BUZZ_MSG.equals(answer);
			break;
		case R.id.number_button:
			isCollect = Integer.toString(targetNumber).equals(answer);
			break;
		default:
			isCollect = false;
			break;
		}
		if (isCollect) {
			targetNumber++;
			changeText(targetNumber);
			changeButtonText(targetNumber);
		} else {
			new AlertDialog.Builder(FizzBuzzActivity.this).setMessage("違います.")
					.show();
		}
	}

	private void changeText(final int number) {
		final TextView numberText = (TextView) findViewById(R.id.number);
		numberText.setText(Integer.toString(number));
	}

	private void changeButtonText(final int number) {
		final Button numberButton = (Button) findViewById(R.id.number_button);
		numberButton.setText(Integer.toString(number));
	}

}
