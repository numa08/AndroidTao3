package jp.ac.uec.numa08.king;

public class KingGame {
	// private static final String TAG = KingGame.class.getSimpleName();
	private static String[] doList = { "を褒め称える.", "に土下座する.", "に愛を誓う.",
			"のモノマネをする", "にアーンする." };
	private final transient int ATTEND_NUMBER;

	public KingGame(final int attendNnumber) {
		this.ATTEND_NUMBER = attendNnumber;
	}

	/**
	 * だれが王様か決める
	 * 
	 * @return 王様の人の番号
	 * 
	 */
	public int decideKing() {
		return (int) (Math.random() * 10.0) % ATTEND_NUMBER + 1;
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
		final String target = doList[(int) (Math.random() * 10.0)
				% doList.length];
		return firstPerson + "が" + secondPerson + target;
	}
}
