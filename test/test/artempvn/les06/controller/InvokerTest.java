package test.artempvn.les06.controller;

import static org.testng.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les06.controller.Invoker;
import by.artempvn.les06.exception.ControllerException;
import by.artempvn.les06.exception.DaoException;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.entity.Library;
import test.artempvn.les06.data.StaticData;

public class InvokerTest {
	Invoker invoker;
	Library library;

	@BeforeClass
	public void setUp() {
		invoker = Invoker.getInstance();
		library = Library.getInstance();
	}

	@BeforeMethod
	public void beforeMethod() throws DaoException {
		library.add(StaticData.book1);
		library.add(StaticData.book2);
		library.add(StaticData.book4);
	}

	@Test(dataProvider = "processRequestTestPositive")
	public void processRequestTestPositive(String command,
			Map<String, String> params, List<Book> expectedList)
			throws ControllerException {
		Optional<Map<String, List<Book>>> actual = invoker
				.processRequest(command, params);
		Map<String, List<Book>> expectedMap = new HashMap<>();
		expectedMap.put("Updated list", expectedList);
		Optional<Map<String, List<Book>>> expected = Optional.of(expectedMap);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] processRequestTestPositive() {
		return new Object[][] {
				{ "ADD_BOOK", StaticData.bookMap5,
						Arrays.asList(StaticData.book1, StaticData.book2,
								StaticData.book4, StaticData.book5) },
				{ "REMOVE_BOOK", StaticData.bookMap1,
						Arrays.asList(StaticData.book2, StaticData.book4) },
				{ "SORT_BY_TAG", new HashMap<String, String>() {
					{
						put("tag", "NUMBER_PAGES");
					}
				}, StaticData.sortedListNumberPages },
				{ "FIND_BY_TAG", new HashMap<String, String>() {
					{
						put("tag", "NUMBER_PAGES");
						put("value", "320");
					}
				}, Arrays.asList(StaticData.book2) } };
	}

	@Test(dataProvider = "processRequestTestNegative",
			expectedExceptions = ControllerException.class)
	public void processRequestTestNegative(String command)
			throws ControllerException {
		invoker.processRequest(command, null);
	}

	@DataProvider
	public Object[][] processRequestTestNegative() {
		return new Object[][] { { "Not command" }, { null } };
	}

	@AfterMethod
	public void afterMethod() {
		library.removeAll();
	}

	@AfterClass
	public void tierDown() {
		invoker = null;
		library = null;
	}
}
