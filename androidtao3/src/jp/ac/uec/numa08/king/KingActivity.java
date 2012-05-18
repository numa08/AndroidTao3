package jp.ac.uec.numa08.king;

import jp.ac.uec.numa08.core.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 王様ゲームのActivity.ボタンが複数あるのでリスナークラスをimplementsする.スピナーは、匿名クラスを使ってコールバックの処理を設定する.
 * 
 * @author numanuma08
 * 
 */
public class KingActivity extends Activity implements OnClickListener {
	// private static final String TAG = KingActivity.class.getSimpleName();
	private static final int[] BUTTON_ID = { R.id.decide_king_button,
			R.id.what_do_button };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle sIState) {
		super.onCreate(sIState);
		// TODO Auto-generated method stub
		setContentView(R.layout.kinig);
		for (int id : BUTTON_ID) {
			findViewById(id).setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
