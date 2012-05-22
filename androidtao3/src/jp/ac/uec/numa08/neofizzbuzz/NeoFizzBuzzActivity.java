package jp.ac.uec.numa08.neofizzbuzz;

import jp.ac.uec.numa08.core.R;
import jp.ac.uec.numa08.fizzbuzz.FizzBuzzActivity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.Chronometer;

/**
 * ネオFizzBuzzのActivity． その動作は、出題される数字がランダムである点を除けば
 * FizzBuzzActivityと大差ないことから、FizzBuzzActivityを継承して，
 * ソースコードの簡略化、製造コストの低減，メンテナンス性の強化を狙っている．
 * 
 * @author numanuma08
 * 
 */
public class NeoFizzBuzzActivity extends FizzBuzzActivity {
	private static final int[] BUTTON_IDS = { R.id.fizz_button,
			R.id.buzz_button, R.id.fizz_buzz_button, R.id.number_button };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle sIState) {
		super.onCreate(sIState);
		setContentView(R.layout.neo_fizzbuzz);

		// TODO Auto-generated method stub
		for (int id : BUTTON_IDS) {
			findViewById(id).setOnClickListener(this);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		final AlertDialog.Builder builder = new Builder(
				NeoFizzBuzzActivity.this);
		builder.setMessage(" スタートします");
		builder.setPositiveButton("ok", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				startFizzBuzz();
			}
		});
	}

	private void startFizzBuzz() {
		// TODO:クロノメーターをスタートする
		final Chronometer fizzBuzzChronometer = (Chronometer) findViewById(R.id.countup_chrono);
		fizzBuzzChronometer.start();
		// TODO:targetNumberを設定する
	}
}
