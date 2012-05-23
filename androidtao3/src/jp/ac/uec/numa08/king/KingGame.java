package jp.ac.uec.numa08.king;

/**
 * 王様ゲームの処理をするクラス。<br>
 * 王様を決めたり、やることを決めたりする。
 * 
 * @author numanuma08
 * 
 */
public class KingGame {
	// private static final String TAG = KingGame.class.getSimpleName();
	/** ヤルことの候補リスト。 */
	private static String[] doList = { "を褒め称える.", "に土下座する.", "に愛を誓う.",
			"のモノマネをする", "にアーンする." };
	/** 参加者の人数 */
	private final transient int ATTEND_NUMBER;

	/**
	 * コンストラクタ。<br>
	 * 現在の参加者の人数を設定する。
	 * 
	 * @param attendNnumber
	 *            現在の参加者の人数。
	 */
	public KingGame(final int attendNnumber) {
		this.ATTEND_NUMBER = attendNnumber;
	}

	/**
	 * だれが王様か決める。<br>
	 * 1～参加者の人数の間のどこかの数字がでてくる。
	 * 
	 * @return 王様の人の番号
	 * 
	 */
	public int decideKing() {
		return (int) (Math.random() * 1000.0) % ATTEND_NUMBER + 1;
	}

	/**
	 * 何をやるか決める
	 * 
	 * @return やること
	 */
	public String decideWahtDo() {
		final int firstPerson = decideKing();
		int secondPerson = firstPerson;
		while (secondPerson == firstPerson) {
			secondPerson = decideKing();
		}
		final String target = doList[(int) (Math.random() * 100.0)
				% doList.length];
		return firstPerson + "が" + secondPerson + target;
	}
}
