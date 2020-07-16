package test.artempvn.les06.controller.command.impl;

import static org.testng.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les06.controller.command.impl.FindByTagCommand;
import by.artempvn.les06.exception.DaoException;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.entity.Library;
import test.artempvn.les06.data.StaticData;

public class FindByTagCommandTest {
	FindByTagCommand findByTagCommand;
	Library library;
	Map<String, String> params1;
	Map<String, String> params2;
	Map<String, String> params3;
	Map<String, String> params4;
	Map<String, String> params5;
	Map<String, String> params6;
	Map<String, String> params7;
	Map<String, String> params8;
	Map<String, String> params9;

	@BeforeClass
	public void setUp() {
		findByTagCommand = new FindByTagCommand();
		library = Library.getInstance();
		params1 = new HashMap<>();
		params1.put("tag", "ID");
		params1.put("value", "1");
		params2 = new HashMap<>();
		params2.put("tag", "TITLE");
		params2.put("value", "another");
		params3 = new HashMap<>();
		params3.put("tag", "AUTHORS");
		params3.put("value", "two");
		params4 = new HashMap<>();
		params4.put("tag", "NUMBER_PAGES");
		params4.put("value", "320");
		params5 = new HashMap<>();
		params5.put("tag", "YEAR_PUBLISHING");
		params5.put("value", "2008");
		params6 = new HashMap<>();
		params6.put("tag", "ID");
		params6.put("value", "not long");
		params7 = new HashMap<>();
		params7.put("tag", "NUMBER_PAGES");
		params7.put("value", "not int");
		params8 = new HashMap<>();
		params8.put("tag", "YEAR_PUBLISHING");
		params8.put("value", "not int");
		params9 = new HashMap<>();
		params9.put("tag", "invalid tag");
	}

	@BeforeMethod
	public void beforeMethod() throws DaoException {
		library.add(StaticData.book1);
		library.add(StaticData.book2);
		library.add(StaticData.book4);
	}

	@Test(dataProvider = "executeTest")
	public void executeTest(Map<String, String> params,
			List<Book> expectedList) {
		Map<String, List<Book>> actual = findByTagCommand.execute(params);
		Map<String, List<Book>> expected = new HashMap<>();
		expected.put("Updated list", expectedList);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] executeTest() {
		return new Object[][] { { params1, Arrays.asList(StaticData.book1) },
				{ params2, Arrays.asList(StaticData.book2) },
				{ params3, Arrays.asList(StaticData.book4) },
				{ params4, Arrays.asList(StaticData.book2) },
				{ params5, Arrays.asList(StaticData.book2) },
				{ params6, Arrays.asList() }, { params7, Arrays.asList() },
				{ params8, Arrays.asList() },
				{ params9, Arrays.asList(StaticData.book1, StaticData.book2,
						StaticData.book4) } };
	}

	@AfterMethod
	public void afterMethod() {
		library.removeAll();
	}

	@AfterClass
	public void tierDown() {
		findByTagCommand = null;
		library = null;
		params1 = null;
		params2 = null;
		params3 = null;
		params4 = null;
		params5 = null;
		params6 = null;
		params7 = null;
		params8 = null;
		params9 = null;
	}
}
