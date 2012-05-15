package jp.ac.uec.numa08.fizzbuzz;

public class FizzBuzzChecker {
	public static final String FIZZ_BUZZ_MSG = "FizzBuzz";
	public static final String BUZZ_MSG = "Buzz";
	public static final String FIZZ_MSG = "Fizz";

	private static final int FIZZBUZZ = 15;
	private static final int FIZZ = 3;
	private static final int BUZZ = 5;

	private transient final int targetNumber;

	public FizzBuzzChecker(final int targetNumber) {
		this.targetNumber = targetNumber;
	}

	public String check() {
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
}
