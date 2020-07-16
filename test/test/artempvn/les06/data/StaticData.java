package test.artempvn.les06.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.request.BookDataRequest;

public class StaticData {

	public static final Book book1 = new Book(1, "title",
			Arrays.asList("author one"), 310, 1998);
	public static final Book book2 = new Book(2, "another title",
			Arrays.asList("anonim"), 320, 2008);
	public static final Book book3 = new Book(1, "some title",
			Arrays.asList("author one", "author two"), 120, 1994);
	public static final Book book4 = new Book(3, "some title",
			Arrays.asList("author one", "author two"), 120, 1994);
	public static final Book book5 = new Book(4, "some title",
			Arrays.asList("author one", "author two"), 120, 1994);
	public static final BookDataRequest bookData1 = new BookDataRequest(1,
			"title", Arrays.asList("author one"), 310, 1998);
	public static final BookDataRequest bookData2 = new BookDataRequest(4,
			"some title", Arrays.asList("author one", "author two"), 120, 1994);
	public static final BookDataRequest bookData3 = new BookDataRequest(5,
			"some title", Arrays.asList("author one", "author two"), 120, 2030);
	public static final BookDataRequest bookData4 = new BookDataRequest(-10,
			"some title", Arrays.asList("author one", "author two"), 120, 1994);
	public static final BookDataRequest bookData5 = new BookDataRequest(5, "",
			Arrays.asList("author one", "author two"), 120, 2030);
	public static final BookDataRequest bookData6 = new BookDataRequest(5,
			"some title", Arrays.asList("123"), 120, 2030);
	public static final BookDataRequest bookData7 = new BookDataRequest(5,
			"some title", Arrays.asList("author one", "author two"), -10, 2030);
	public static final Map<String, String> bookMap1 = new HashMap<>();
	public static final Map<String, String> bookMap5 = new HashMap<>();
	public static final Map<String, String> mapTestFalse1 = new HashMap<>();
	public static final Map<String, String> mapTestFalse2 = new HashMap<>();
	public static final Map<String, String> mapTestFalse3 = new HashMap<>();
	public static final List<Book> sortedListId = Arrays
			.asList(StaticData.book1, StaticData.book2, StaticData.book4);
	public static final List<Book> sortedListTitle = Arrays
			.asList(StaticData.book2, StaticData.book4, StaticData.book1);
	public static final List<Book> sortedListAuthors = Arrays
			.asList(StaticData.book2, StaticData.book1, StaticData.book4);
	public static final List<Book> sortedListNumberPages = Arrays
			.asList(StaticData.book4, StaticData.book1, StaticData.book2);
	public static final List<Book> sortedListYearPublishing = Arrays
			.asList(StaticData.book4, StaticData.book1, StaticData.book2);

	static {
		bookMap1.put("ID", "1");
		bookMap1.put("TITLE", "title");
		bookMap1.put("AUTHORS", "author one");
		bookMap1.put("NUMBER_PAGES", "310");
		bookMap1.put("YEAR_PUBLISHING", "1998");
		bookMap5.put("ID", "4");
		bookMap5.put("TITLE", "some title");
		bookMap5.put("AUTHORS", "author one/author two");
		bookMap5.put("NUMBER_PAGES", "120");
		bookMap5.put("YEAR_PUBLISHING", "1994");
		mapTestFalse1.put("ID", "Word");
		mapTestFalse1.put("TITLE", "title");
		mapTestFalse1.put("AUTHORS", "author one/author two");
		mapTestFalse1.put("NUMBER_PAGES", "320");
		mapTestFalse1.put("YEAR_PUBLISHING", "1998");
		mapTestFalse2.put("ID", "31");
		mapTestFalse2.put("TITLE", "title");
		mapTestFalse2.put("AUTHORS", "author one/author two");
		mapTestFalse2.put("NUMBER_PAGES", "word");
		mapTestFalse2.put("YEAR_PUBLISHING", "1998");
		mapTestFalse3.put("ID", "31");
		mapTestFalse3.put("TITLE", "title");
		mapTestFalse3.put("AUTHORS", "author one/author two");
		mapTestFalse3.put("NUMBER_PAGES", "320");
		mapTestFalse3.put("YEAR_PUBLISHING", "word");
	}

	@DataProvider
	public Object[][] findByIdTest() {
		return new Object[][] { { 1, Arrays.asList(StaticData.book1) },
				{ 5, Arrays.asList() } };
	}

	@DataProvider
	public Object[][] findByTitleTest() {
		return new Object[][] { { "another", Arrays.asList(StaticData.book2) },
				{ "unfound", Arrays.asList() } };
	}

	@DataProvider
	public Object[][] findByAuthorTest() {
		return new Object[][] { { "two", Arrays.asList(StaticData.book4) },
				{ "unfound", Arrays.asList() } };
	}

	@DataProvider
	public Object[][] findByNumberPagesTest() {
		return new Object[][] { { 320, Arrays.asList(StaticData.book2) },
				{ 35, Arrays.asList() } };
	}

	@DataProvider
	public Object[][] findByYearPublishingTest() {
		return new Object[][] { { 2008, Arrays.asList(StaticData.book2) },
				{ 2000, Arrays.asList() } };
	}
}
