package jp.ac.uec.numa08.fizzbuzz;

/**
 * ユーザの入力が正解かどうかを判断するクラス。
 * 
 * @author numanuma08
 * 
 */
public class FizzBuzzChecker {
	/** FizzBuzz */
	public static final String FIZZ_BUZZ_MSG = "FizzBuzz";
	/** Buzz */
	public static final String BUZZ_MSG = "Buzz";
	/** Fizz */
	public static final String FIZZ_MSG = "Fizz";

	/** 割り切れたらFizzBuzz */
	private static final int FIZZBUZZ = 15;
	/** 割り切れたらFizz */
	private static final int FIZZ = 3;
	/** 割り切れたらBuzz */
	private static final int BUZZ = 5;

	/** 現在出題中の数字 */
	private transient final int targetNumber;

	/**
	 * コンストラクタ。<br>
	 * 現在出題中の数字の初期化のみを行う。
	 * 
	 * @param targetNumber
	 *            現在出題中の数字。
	 * 
	 */
	public FizzBuzzChecker(final int targetNumber) {
		this.targetNumber = targetNumber;
	}

	/**
	 * 現在出題中の答えに対して、正しい解答を求める。
	 * 
	 * @return 正しい答え.Fizz,Buzz,FizzBuzz,数字のままのいずれか.
	 */
	private String answer() {
		String message;
		if (targetNumber % FIZZBUZZ == 0) {
			message = FizzBuzzChecker.FIZZ_BUZZ_MSG;
		} else if (targetNumber % FIZZ == 0) {
			message = FizzBuzzChecker.FIZZ_MSG;
		} else if (targetNumber % BUZZ == 0) {
			message = FizzBuzzChecker.BUZZ_MSG;
		} else {
			message = Integer.toString(targetNumber);
		}
		return message;
	}

	/**
	 * ユーザの入力が正解かどうかを求める
	 * 
	 * @param userInput
	 *            ユーザの入力
	 * @return 正解ならtrue。
	 */
	public boolean isCollect(final String userInput) {
		final String answer = answer();
		return answer.equals(userInput);
	}

}
