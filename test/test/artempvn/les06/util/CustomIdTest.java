package test.artempvn.les06.util;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import by.artempvn.les06.util.CustomId;

public class CustomIdTest {
	
	@Test
	public void takeIdTest() {
		long actual = CustomId.takeId();
		long expected = 0;
		assertEquals(actual, expected, " Test failed as...");
	}
}
