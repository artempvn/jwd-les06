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
import org.testng.annotations.Test;
import by.artempvn.les06.controller.command.impl.RemoveBookCommand;
import by.artempvn.les06.exception.ControllerException;
import by.artempvn.les06.exception.DaoException;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.entity.Library;
import test.artempvn.les06.data.StaticData;

public class RemoveBookCommandTest {
	RemoveBookCommand removeBookCommand;
	Library library;

	@BeforeClass
	public void setUp() {
		removeBookCommand = new RemoveBookCommand();
		library = Library.getInstance();
	}

	@BeforeMethod
	public void beforeMethod() throws DaoException {
		library.add(StaticData.book1);
		library.add(StaticData.book2);
		library.add(StaticData.book4);
	}

	@Test
	public void executeTestPositive() throws ControllerException {
		Map<String, List<Book>> actual = removeBookCommand
				.execute(StaticData.bookMap1);
		Map<String, List<Book>> expected = new HashMap<>();
		expected.put("Updated list",
				Arrays.asList(StaticData.book2, StaticData.book4));
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ControllerException.class)
	public void executeTestNegative() throws ControllerException {
		removeBookCommand.execute(StaticData.bookMap5);
	}

	@AfterMethod
	public void afterMethod() {
		library.removeAll();
	}

	@AfterClass
	public void tierDown() {
		removeBookCommand = null;
		library = null;
	}
}
