package jp.ac.uec.numa08.core.test;

import jp.ac.uec.numa08.fizzbuzz.FizzBuzzChecker;
import junit.framework.TestCase;

public class FizzBuzzCheckerTest extends TestCase {
	private FizzBuzzChecker checker;
	private int targetNumber = 1;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public void testisCollect() {
		checker = new FizzBuzzChecker(targetNumber);
		assertTrue(checker.isCollect(Integer.toString(1)));
		assertFalse(checker.isCollect(FizzBuzzChecker.FIZZ_BUZZ_MSG));
		assertFalse(checker.isCollect(FizzBuzzChecker.FIZZ_MSG));
		assertFalse(checker.isCollect(FizzBuzzChecker.BUZZ_MSG));

		targetNumber = 3;
		checker = new FizzBuzzChecker(targetNumber);
		assertFalse(checker.isCollect(Integer.toString(targetNumber)));
		assertTrue(checker.isCollect(FizzBuzzChecker.FIZZ_MSG));
		assertFalse(checker.isCollect(FizzBuzzChecker.BUZZ_MSG));
		assertFalse(checker.isCollect(FizzBuzzChecker.FIZZ_BUZZ_MSG));

		targetNumber = 5;
		checker = new FizzBuzzChecker(targetNumber);
		assertFalse(checker.isCollect(Integer.toString(targetNumber)));
		assertFalse(checker.isCollect(FizzBuzzChecker.FIZZ_MSG));
		assertTrue(checker.isCollect(FizzBuzzChecker.BUZZ_MSG));
		assertFalse(checker.isCollect(FizzBuzzChecker.FIZZ_BUZZ_MSG));

		targetNumber = 15;
		checker = new FizzBuzzChecker(targetNumber);
		assertFalse(checker.isCollect(Integer.toString(targetNumber)));
		assertFalse(checker.isCollect(FizzBuzzChecker.FIZZ_MSG));
		assertFalse(checker.isCollect(FizzBuzzChecker.BUZZ_MSG));
		assertTrue(checker.isCollect(FizzBuzzChecker.FIZZ_BUZZ_MSG));
	}

}
