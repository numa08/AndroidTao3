package jp.ac.uec.numa08.core.test;

import jp.ac.uec.numa08.king.KingGame;
import junit.framework.TestCase;

public class KingGameTest extends TestCase {
	// private static final String TAG = KingGameTest.class.getSimpleName();
	KingGame game;
	private final int ATTEND_NUMBER = 20;

	protected void setUp() throws Exception {
		game = new KingGame(ATTEND_NUMBER);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testdeciedKing() {
		for (int i = 0; i < 100; i++) {
			assertFalse("0にはならないよ", game.deciedKing() == 0);
		}
	}

}
