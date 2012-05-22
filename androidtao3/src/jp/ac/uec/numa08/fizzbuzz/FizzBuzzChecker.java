package jp.ac.uec.numa08.fizzbuzz;

/**
 * @author numanuma08 ユーザの入力が正解かどうかを判断するクラス。
 * 
 */
public class FizzBuzzChecker {
	public static final String FIZZ_BUZZ_MSG = "FizzBuzz";
	public static final String BUZZ_MSG = "Buzz";
	public static final String FIZZ_MSG = "Fizz";

	private static final int FIZZBUZZ = 15;
	private static final int FIZZ = 3;
	private static final int BUZZ = 5;

	private transient final int targetNumber;

	/**
	 * コンストラクタ.
	 * 
	 * @param targetNumber
	 *            画面に表示されている数字.
	 * 
	 */
	public FizzBuzzChecker(final int targetNumber) {
		this.targetNumber = targetNumber;
	}

	/**
	 * 正しい答えを求める.
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
	 * @return 正解かどうか
	 */
	public boolean isCollect(final String userInput) {
		final String answer = answer();
		return answer.equals(userInput);
	}

}
