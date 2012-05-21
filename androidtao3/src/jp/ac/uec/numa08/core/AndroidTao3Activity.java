package jp.ac.uec.numa08.core;

import jp.ac.uec.numa08.fizzbuzz.FizzBuzzActivity;
import jp.ac.uec.numa08.king.KingActivity;
import jp.ac.uec.numa08.termcalc.TermCalcActivity;
import jp.ac.uec.numa08.whatnumber.WhatNumberActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author numanuma08 最初に起動するActivity．様々なアプリへのボタンを用意している． View.OnClickListener
 *         をimplementsすることで各ボタンのリスナークラスをこのクラスに設定している．
 *         ボタンの数が多い場合などは、匿名クラスは利用せずに特定のクラスをリスナークラスとしたほうが コードが見やすくなると思う.
 * 
 */
public class AndroidTao3Activity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	private static final int[] _ButtonIds = { R.id.first_app, R.id.second_app,
			R.id.therd_app, R.id.fourth_app, R.id.fifth_app };

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * onCreateメソッド内では、ボタンの初期化を行う．
	 * ボタンのidは予め配列に入れてあるし，全てのボタンのリスナークラスはこのクラスであるため，ループ処理を利用して短い行で実現できる．`
	 */
	@Override
	public void onCreate(final Bundle sIState) {
		super.onCreate(sIState);
		setContentView(R.layout.main);

		for (int id : _ButtonIds) {
			findViewById(id).setOnClickListener(AndroidTao3Activity.this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 * ボタンがクリックされたら呼び出されるメソッド．
	 * 引数のViewは，getIdメソッドで押されたボタンのidを取得することができる．idはint型であるため
	 * ，switch分を利用した条件分岐が可能である.この様にして，ボタンごとに異なる動作を行わせることができる．
	 */
	@Override
	public void onClick(final View view) {
		// TODO Auto-generated method stub
		Class<?> targetClass;
		switch (view.getId()) {
		case R.id.first_app:
			targetClass = FizzBuzzActivity.class;
			break;
		case R.id.second_app:
			targetClass = KingActivity.class;
			break;
		case R.id.therd_app:
		case R.id.fourth_app:
			targetClass = WhatNumberActivity.class;
			break;
		case R.id.fifth_app:
			targetClass = TermCalcActivity.class;
			break;
		default:
			targetClass = AndroidTao3Activity.class;
			break;
		}
		final Intent intent = new Intent(AndroidTao3Activity.this, targetClass);
		startActivity(intent);
	}
}