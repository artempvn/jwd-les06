package test.artempvn.les06.controller.command.impl;

import static org.testng.Assert.assertEquals;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les06.controller.command.impl.SortByTagCommand;
import by.artempvn.les06.exception.ControllerException;
import by.artempvn.les06.exception.DaoException;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.entity.Library;
import test.artempvn.les06.data.StaticData;

public class SortByTagCommandTest {
	SortByTagCommand sortByTagCommand;
	Library library;
	Map<String, String> params1;
	Map<String, String> params2;
	Map<String, String> params3;
	Map<String, String> params4;
	Map<String, String> params5;
	Map<String, String> params6;

	@BeforeClass
	public void setUp() {
		sortByTagCommand = new SortByTagCommand();
		library = Library.getInstance();
		params1 = new HashMap<>();
		params1.put("tag", "ID");
		params2 = new HashMap<>();
		params2.put("tag", "TITLE");
		params3 = new HashMap<>();
		params3.put("tag", "AUTHORS");
		params4 = new HashMap<>();
		params4.put("tag", "NUMBER_PAGES");
		params5 = new HashMap<>();
		params5.put("tag", "YEAR_PUBLISHING");
		params6 = new HashMap<>();
		params6.put("tag", "Incorrect");
	}

	@BeforeMethod
	public void beforeMethod() throws DaoException {
		library.add(StaticData.book1);
		library.add(StaticData.book2);
		library.add(StaticData.book4);
	}

	@Test(dataProvider = "executeTestPositive")
	public void executeTestPositive(Map<String, String> params,
			List<Book> expectedList) throws ControllerException {
		Map<String, List<Book>> actual = sortByTagCommand.execute(params);
		Map<String, List<Book>> expected = new HashMap<>();
		expected.put("Updated list", expectedList);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] executeTestPositive() {
		return new Object[][] { { params1, StaticData.sortedListId },
				{ params2, StaticData.sortedListTitle },
				{ params3, StaticData.sortedListAuthors },
				{ params4, StaticData.sortedListNumberPages },
				{ params5, StaticData.sortedListYearPublishing }, };
	}

	@Test(expectedExceptions = ControllerException.class)
	public void executeTestNegative() throws ControllerException {
		sortByTagCommand.execute(params6);
	}

	@AfterMethod
	public void afterMethod() {
		library.removeAll();
	}

	@AfterClass
	public void tierDown() {
		sortByTagCommand = null;
		library = null;
		params1 = null;
		params2 = null;
		params3 = null;
		params4 = null;
		params5 = null;
		params6 = null;
	}
}
