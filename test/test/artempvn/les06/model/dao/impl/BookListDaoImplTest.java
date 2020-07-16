package test.artempvn.les06.model.dao.impl;

import static org.testng.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import by.artempvn.les06.exception.DaoException;
import by.artempvn.les06.model.dao.impl.BookListDaoImpl;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.entity.Library;
import test.artempvn.les06.data.StaticData;

public class BookListDaoImplTest {
	BookListDaoImpl bookListDaoImpl;
	Library library;

	@BeforeClass
	public void setUp() {
		bookListDaoImpl = BookListDaoImpl.getInstance();
		library = Library.getInstance();
	}

	@BeforeMethod
	public void beforeMethod() throws DaoException {
		library.add(StaticData.book1);
		library.add(StaticData.book2);
		library.add(StaticData.book4);
	}

	@Test
	public void addTestPositive() throws DaoException {
		bookListDaoImpl.add(StaticData.book5);
		List<Book> actual = bookListDaoImpl.takeAllBooks();
		List<Book> expected = Arrays.asList(StaticData.book1, StaticData.book2,
				StaticData.book4, StaticData.book5);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = DaoException.class)
	public void addTestNegative() throws DaoException {
		bookListDaoImpl.add(StaticData.book1);
	}

	@Test
	public void removeTestPositive() throws DaoException {
		bookListDaoImpl.remove(StaticData.book1);
		List<Book> actual = bookListDaoImpl.takeAllBooks();
		List<Book> expected = Arrays.asList(StaticData.book2, StaticData.book4);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = DaoException.class)
	public void removeTestNegative() throws DaoException {
		bookListDaoImpl.remove(StaticData.book5);
	}

	@Test(dataProvider = "findByIdTest", dataProviderClass = StaticData.class)
	public void findByIdTest(long id, List<Book> expected) throws DaoException {
		List<Book> actual = bookListDaoImpl.findById(id);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByTitleTest", dataProviderClass = StaticData.class)
	public void findByTitleTest(String title, List<Book> expected)
			throws DaoException {
		List<Book> actual = bookListDaoImpl.findByTitle(title);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByAuthorTest", dataProviderClass = StaticData.class)
	public void findByAuthorTest(String author, List<Book> expected)
			throws DaoException {
		List<Book> actual = bookListDaoImpl.findByAuthor(author);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByNumberPagesTest", dataProviderClass = StaticData.class)
	public void findByNumberPagesTest(int numberPages, List<Book> expected)
			throws DaoException {
		List<Book> actual = bookListDaoImpl.findByNumberPages(numberPages);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findByYearPublishingTest", dataProviderClass = StaticData.class)
	public void findByYearPublishingTest(int yearPublishing,
			List<Book> expected) throws DaoException {
		List<Book> actual = bookListDaoImpl
				.findByYearPublishing(yearPublishing);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test
	public void sortByIdTest() {
		List<Book> actual = bookListDaoImpl.sortById();
		List<Book> expected = StaticData.sortedListId;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test
	public void sortByTitleTest() {
		List<Book> actual = bookListDaoImpl.sortByTitle();
		List<Book> expected = StaticData.sortedListTitle;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test
	public void sortByAuthorTest() {
		List<Book> actual = bookListDaoImpl.sortByAuthor();
		List<Book> expected = StaticData.sortedListAuthors;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test
	public void sortByNumberPagesTest() {
		List<Book> actual = bookListDaoImpl.sortByNumberPages();
		List<Book> expected = StaticData.sortedListNumberPages;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test
	public void sortByYearPublishingTest() {
		List<Book> actual = bookListDaoImpl.sortByYearPublishing();
		List<Book> expected = StaticData.sortedListYearPublishing;
		assertEquals(actual, expected, " Test failed as...");
	}

	@AfterMethod
	public void afterMethod() {
		library.removeAll();
	}

	@AfterClass
	public void tierDown() {
		bookListDaoImpl = null;
		library = null;
	}
}
