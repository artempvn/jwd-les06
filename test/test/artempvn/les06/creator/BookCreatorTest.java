package test.artempvn.les06.creator;

import static org.testng.Assert.assertEquals;
import java.util.Optional;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les06.creator.BookCreator;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.request.BookDataRequest;
import test.artempvn.les06.data.StaticData;

public class BookCreatorTest {
	BookCreator bookCreator;

	@BeforeClass
	public void setUp() {
		bookCreator = new BookCreator();
	}

	@Test(dataProvider = "createBookTest")
	public void createBookTest(BookDataRequest bookData,
			Optional<Book> expected) {
		Optional<Book> actual = bookCreator.createBook(bookData);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] createBookTest() {
		return new Object[][] {
				{ StaticData.bookData1, Optional.of(StaticData.book1) },
				{ StaticData.bookData3, Optional.empty() },
				{ StaticData.bookData4, Optional.empty() },
				{ StaticData.bookData5, Optional.empty() },
				{ StaticData.bookData6, Optional.empty() },
				{ StaticData.bookData7, Optional.empty() }};
	}

	@AfterClass
	public void tierDown() {
		bookCreator = null;
	}
}
