package jp.ac.uec.numa08.neofizzbuzz;

import java.util.ArrayList;
import java.util.List;

import jp.ac.uec.numa08.core.R;
import jp.ac.uec.numa08.fizzbuzz.FizzBuzzActivity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

/**
 * ネオFizzBuzzのActivity． その動作は、出題される数字がランダムである点を除けば<br>
 * FizzBuzzActivityと大差ないことから、FizzBuzzActivityを継承して，<br>
 * ソースコードの簡略化、製造コストの低減，メンテナンス性の強化を狙っている．<br>
 * 
 * @author numanuma08
 * 
 */
public class NeoFizzBuzzActivity extends FizzBuzzActivity {
	/** ボタンのID */
	private static final int[] BUTTON_IDS = { R.id.fizz_button,
			R.id.buzz_button, R.id.fizz_buzz_button, R.id.number_button };

	/** 問題の総数 */
	private static final int MAX_QUESTION = 20;

	/** 出題される数字の最大値＋１ */
	private static final int MAX_NUMBER = 100 + 1;

	/** 今、何問目？ */
	private transient int targetIndex = 0;

	/** 出題される数字のリスト */
	protected transient List<Integer> targetNumberList;

	/** 回答時間を計測するクロノメータ */
	private transient Chronometer coutUpChrono;

	/**
	 * Activity生成時に呼び出される。<br>
	 * FizzBuzzActivity.javaのonCreateで初期化処理はだいたい終わる.<br>
	 * レイアウトの最指定とボタンのリスナークラスの登録を行なっておく。<br>
	 */
	@Override
	public void onCreate(final Bundle sIState) {
		super.onCreate(sIState);
		setContentView(R.layout.neo_fizzbuzz);
		// TODO Auto-generated method stub
		for (final int id : BUTTON_IDS) {
			findViewById(id).setOnClickListener(this);
		}
	}

	/**
	 * onResumeが呼ばれたタイミングで、ユーザはゲームを開始できる.<br>
	 * スタートをするダイアログの表示。 <br>
	 * ユーザがゲームをスタートしたら,<br>
	 * <ol>
	 * <li>ダイアログを消す</li>
	 * <li>ゲームの開始処理を呼び出す</li>
	 * </ol>
	 * を行う。
	 * 
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		targetNumberList = new ArrayList<Integer>();
		final AlertDialog.Builder builder = new Builder(
				NeoFizzBuzzActivity.this);
		builder.setMessage(" スタートします");
		builder.setPositiveButton("ok", new OnClickListener() {

			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				startFizzBuzz();
			}
		});
		builder.show();
	}

	/**
	 * ゲームの開始処理。
	 * <ol>
	 * <li>クロノメータの初期化</li>
	 * <li>問題リストの生成</li>
	 * <li>第１問目の出力</li>
	 * </ol>
	 */
	private void startFizzBuzz() {
		// TODO:クロノメーターをスタートする
		coutUpChrono = (Chronometer) findViewById(R.id.countup_chrono);
		coutUpChrono.start();
		// TODO:targetNumberを設定する
		initializeTargetNumberList();
		targetNumber = targetNumberList.get(targetIndex);
		// TODO:画面上に出題されている番号を表示する
		changeText(targetNumber, R.id.number);
		changeButtonText(targetNumber, R.id.number_button);
	}

	/**
	 * 問題リストの生成。<br>
	 * 重複のない０～１００までの整数を問題個数分作る。　
	 */
	protected void initializeTargetNumberList() {
		int num;
		for (int i = 0; i < MAX_QUESTION; i++) {
			do {
				num = (int) (Math.random() * 10000.0) % MAX_NUMBER;
			} while (targetNumberList.indexOf(num) != -1);
			targetNumberList.add(num);
		}
	}

	/**
	 * 画面上のボタンがクリックされたときに呼び出される。<br>
	 * 正解かどうかをチェックし、正解ならば問題数を進める。すでに、全て解き終わっている時は、<br>
	 * 終了処理を呼び出す。<br>
	 * まだ、問題が残っているなら問題を画面に出力し、番号ボタンの数字も変える。<br>
	 * 不正解の答えをしたら、トーストでメッセージを出す。
	 */
	@Override
	public void onClick(final View view) {
		// TODO Auto-generated method stub
		final boolean isCollect = checkAnswer(view);
		if (isCollect) {
			// TODO:終了判定
			targetIndex++;
			if (isEnd()) {
				endGame();
			} else {
				// TODO:targetNumberの設定
				targetNumber = targetNumberList.get(targetIndex);
				// TODO:画面とボタンの文字列を変える
				changeText(targetNumber, R.id.number);
				changeButtonText(targetNumber, R.id.number_button);
			}
		} else {
			Toast.makeText(NeoFizzBuzzActivity.this, "違います", Toast.LENGTH_SHORT)
					.show();
		}
	}

	/**
	 * 終了判定.20問以上といたら終わり
	 * 
	 * @return 終わっていたらtrue
	 */
	private boolean isEnd() {
		return targetIndex >= targetNumberList.size();
	}

	/**
	 * 終了処理。クロノメータを止め、ダイアログを出してActivityを閉じる。
	 */
	private void endGame() {
		coutUpChrono.stop();
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				NeoFizzBuzzActivity.this);
		builder.setMessage("おつかれさん。クリアタイムは" + coutUpChrono.getText().toString()
				+ "だったよ。");
		builder.setPositiveButton("おつ～", new OnClickListener() {

			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		builder.show();
	}

}
