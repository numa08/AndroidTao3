package jp.ac.uec.numa08.whatnumber;

/**
 * 数当てゲームのゲーム系処理を実装するクラス。<Br>
 * 出題する問題を設定したり,ユーザの入力が正しいか間違っているかを判断するなど。
 * 
 * @author numanuma08
 * 
 */
public class WhatNumber {
	// private static final String TAG = WhatNumber.class.getSimpleName();
	/** 出題されうる最大の数字 */
	private transient final int MAX_NUMBER;
	/** 正解の数字。 */
	private transient int answer;

	/**
	 * コンストラクタ。<br>
	 * MAX_NUMBERを指定するのみ。
	 * 
	 * @param maxNumber
	 */
	public WhatNumber(final int maxNumber) {
		this.MAX_NUMBER = maxNumber;
	}

	/**
	 * 答えを設定する。<br>
	 * 1～MAX_NUMBERのどこかの数字が出てくる。
	 * 
	 */
	public void resetAnser() {
		this.answer = (int) (Math.random() * 1000.0) % MAX_NUMBER + 1;
	}

	/**
	 * 答えを得る
	 * 
	 * @return 答え
	 * 
	 */
	public int getAnswer() {
		return this.answer;
	}

	/**
	 * 正解かどうかチェックする
	 * 
	 * @param userInput
	 *            ユーザの入力
	 * @return 正解かどうか
	 */
	public boolean isCollect(final int userInput) {
		return getAnswer() == userInput;
	}

	/**
	 * ヒントを教える
	 * 
	 * @param userInput
	 *            ユーザの入力　
	 * @return ヒント
	 */
	public String getHint(final int userInput) {
		String hint;
		if (getAnswer() > userInput) {
			hint = "もっと大きいよ";
		} else {
			hint = "もっと小さいよ";
		}
		return hint;
	}
}
