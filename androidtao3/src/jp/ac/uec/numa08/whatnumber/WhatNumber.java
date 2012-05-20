package jp.ac.uec.numa08.whatnumber;

public class WhatNumber {
	// private static final String TAG = WhatNumber.class.getSimpleName();
	private transient final int MAX_NUMBER;
	private transient int answer;

	public WhatNumber(final int maxNumber) {
		this.MAX_NUMBER = maxNumber;
	}

	/**
	 * 答えを設定する
	 */
	public void resetAnser() {
		this.answer = (int) (Math.random() * 10.0) % MAX_NUMBER + 1;
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
