package jp.ac.uec.numa08.neofizzbuzz;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import jp.ac.uec.numa08.core.R;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;

public class NeoFizzBuzzActivityTest extends
		ActivityInstrumentationTestCase2<NeoFizzBuzzActivity> {
	private static final String TAG = NeoFizzBuzzActivityTest.class
			.getSimpleName();

	public NeoFizzBuzzActivityTest() {
		super("jp.ac.uec.numa08.neofizzbuzz", NeoFizzBuzzActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		fizzButton = (Button) getActivity().findViewById(R.id.fizz_button);
		buzzButton = (Button) getActivity().findViewById(R.id.buzz_button);
		fizzBuzzButton = (Button) getActivity().findViewById(
				R.id.fizz_buzz_button);
		numberButton = (Button) getActivity().findViewById(R.id.number_button);
	}

	public void testInitializeTargetNumberList() {
		getActivity().initializeTargetNumberList();
		assertThat(getActivity().targetNumberList.size(), is(equalTo(20)));
		for (int targetNumber : getActivity().targetNumberList) {
			Log.d(TAG, Integer.toString(targetNumber));
		}
	}
}
