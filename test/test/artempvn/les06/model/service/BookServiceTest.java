package test.artempvn.les06.model.service;

import static org.testng.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les06.exception.DaoException;
import by.artempvn.les06.exception.ServiceException;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.entity.Library;
import by.artempvn.les06.model.service.BookService;
import by.artempvn.les06.request.BookDataRequest;
import test.artempvn.les06.data.StaticData;

public class BookServiceTest {
	BookService bookService;
	Library library;

	@BeforeClass
	public void setUp() {
		bookService = BookService.getInstance();
		library = Library.getInstance();
	}

	@BeforeMethod
	public void beforeMethod() throws DaoException {
		library.add(StaticData.book1);
		library.add(StaticData.book2);
		library.add(StaticData.book4);
	}

	@Test
	public void addTestPositive() throws ServiceException {
		bookService.add(StaticData.bookData2);
		List<Book> actual = bookService.takeAllBooks();
		List<Book> expected = Arrays.asList(StaticData.book1, StaticData.book2,
				StaticData.book4, StaticData.book5);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "addTestNegative", expectedExceptions = ServiceException.class)
	public void addTestNegative(BookDataRequest bookData)
			throws ServiceException {
		bookService.add(bookData);
	}

	@DataProvider
	public Object[][] addTestNegative() {
		return new Object[][] { { StaticData.bookData3 },
				{ StaticData.bookData1 } };
	}

	@Test
	public void removeTestPositive() throws ServiceException {
		bookService.remove(StaticData.bookData1);
		List<Book> actual = bookService.takeAllBooks();
		List<Book> expected = Arrays.asList(StaticData.book2, StaticData.book4);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "removeTestNegative", 
			expectedExceptions = ServiceException.class)
	public void removeTestNegative(BookDataRequest bookData)
			throws ServiceException {
		bookService.remove(bookData);
	}

	@DataProvider
	public Object[][] removeTestNegative() {
		return new Object[][] { { StaticData.bookData3 },
				{ StaticData.bookData2 } };
	}

	@Test(dataProvider = "findByIdTest", dataProviderClass = StaticData.class)
	public void findByIdTest(long id, List<Book> expected) {
		List<Book> actual = bookService.findById(id);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByTitleTest",
			dataProviderClass = StaticData.class)
	public void findByTitleTest(String title, List<Book> expected) {
		List<Book> actual = bookService.findByTitle(title);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByAuthorTest",
			dataProviderClass = StaticData.class)
	public void findByAuthorTest(String author, List<Book> expected) {
		List<Book> actual = bookService.findByAuthor(author);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByNumberPagesTest",
			dataProviderClass = StaticData.class)
	public void findByNumberPagesTest(int numberPages, List<Book> expected) {
		List<Book> actual = bookService.findByNumberPages(numberPages);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByYearPublishingTest",
			dataProviderClass = StaticData.class)
	public void findByYearPublishingTest(int yearPublishing,
			List<Book> expected) {
		List<Book> actual = bookService.findByYearPublishing(yearPublishing);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test
	public void sortByIdTest() {
		List<Book> actual = bookService.sortById();
		List<Book> expected = StaticData.sortedListId;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test
	public void sortByTitleTest() {
		List<Book> actual = bookService.sortByTitle();
		List<Book> expected = StaticData.sortedListTitle;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test
	public void sortByAuthorTest() {
		List<Book> actual = bookService.sortByAuthor();
		List<Book> expected = StaticData.sortedListAuthors;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test
	public void sortByNumberPagesTest() {
		List<Book> actual = bookService.sortByNumberPages();
		List<Book> expected = StaticData.sortedListNumberPages;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test
	public void sortByYearPublishingTest() {
		List<Book> actual = bookService.sortByYearPublishing();
		List<Book> expected = StaticData.sortedListYearPublishing;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test
	public void takeAllBooksTest() {
		List<Book> actual = bookService.takeAllBooks();
		List<Book> expected = Arrays.asList(StaticData.book1, StaticData.book2,
				StaticData.book4);
		assertEquals(actual, expected, " Test failed as...");
	}

	@AfterMethod
	public void afterMethod() {
		library.removeAll();
	}

	@AfterClass
	public void tierDown() {
		bookService = null;
		library = null;
	}
}
