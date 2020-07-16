package test.artempvn.les06.model.entity;

import static org.testng.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les06.exception.DaoException;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.entity.Library;
import test.artempvn.les06.data.StaticData;

public class LibraryTest {
	Library library;

	@BeforeClass
	public void setUp(){
		library=Library.getInstance();
	}
	
	@BeforeMethod
	public void beforeMethod() throws DaoException {
		library.add(StaticData.book1);
	}
	
	@Test 
	public void addTestPositive() throws DaoException {
		library.add(StaticData.book2);
		List<Book> actual=library.getBooksReadOnly();
		List<Book> expected=Arrays.asList(StaticData.book1,StaticData.book2);
		assertEquals(actual, expected, " Test failed as...");
	}
	
	@Test (dataProvider ="addTestNegative", expectedExceptions = DaoException.class )
	public void addTestNegative(Book book) throws DaoException {
		library.add(book);
	}
	
	@DataProvider
	public Object[][] addTestNegative() {
		return new Object[][] { {StaticData.book1}, {StaticData.book3} };
	}
	
	@Test 
	public void removeTestPositive() throws DaoException {
		library.remove(StaticData.book1);
		List<Book> actual=library.getBooksReadOnly();
		List<Book> expected=Collections.emptyList();
		assertEquals(actual, expected, " Test failed as...");
	}
	
	@Test (expectedExceptions = DaoException.class )
	public void removeTestNegative() throws DaoException {
		library.remove(StaticData.book2);
	}
	
	@AfterMethod
	public void afterMethod() {
		library.removeAll();
	}
	
	@AfterClass
	public void tierDown() {
		library = null;
	}
}
