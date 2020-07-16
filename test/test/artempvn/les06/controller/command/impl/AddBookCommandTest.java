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
import by.artempvn.les06.controller.command.impl.AddBookCommand;
import by.artempvn.les06.exception.ControllerException;
import by.artempvn.les06.exception.DaoException;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.entity.Library;
import test.artempvn.les06.data.StaticData;

public class AddBookCommandTest {
	AddBookCommand addBookCommand;
	Library library;

	@BeforeClass
	public void setUp(){
		addBookCommand=new AddBookCommand();
		library=Library.getInstance();
	}
	
	@BeforeMethod
	public void beforeMethod() throws DaoException {
		library.add(StaticData.book1);
		library.add(StaticData.book2);
		library.add(StaticData.book4);
	}
	
	@Test 
	public void executeTestPositive() throws ControllerException {
		Map<String, List<Book>> actual=addBookCommand.execute(StaticData.bookMap5);
		Map<String, List<Book>> expected=new HashMap<>();
		expected.put("Updated list",Arrays.asList(StaticData.book1,StaticData.book2,StaticData.book4,StaticData.book5));
		assertEquals(actual, expected, " Test failed as...");
	}
	
	@Test (expectedExceptions = ControllerException.class )
	public void executeTestNegative() throws ControllerException  {
		addBookCommand.execute(StaticData.bookMap1);
	}
	
	@AfterMethod
	public void afterMethod() {
		library.removeAll();
	}
	
	@AfterClass
	public void tierDown() {
		addBookCommand=null;
		library = null;
	}
}
