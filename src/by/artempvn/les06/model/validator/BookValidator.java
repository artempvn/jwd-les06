package by.artempvn.les06.model.validator;

import java.time.Year;
import java.util.List;

public class BookValidator {

	private static final int MIN_YEAR = 1920;
	private static final int MAX_NUMBER_PAGES = 5000;
	private static final int MIN_NUMBER_PAGES = 30;
	private static final int UNSET_ID = -1;

	public boolean isIdCorrect(long id) {
		return (id >= UNSET_ID);
	}

	public boolean isTitleCorrect(String title) {
		return (title.length() > 0);
	}

	public boolean isAuthorsCorrect(List<String> authors) {
		boolean isCorrect = true;
		if (authors.size() > 0) {
			for (String author : authors) {
				if (author.isEmpty()) {
					isCorrect = false;
				}
			}
		} else {
			isCorrect = false;
		}
		return isCorrect;
	}

	public boolean isNumberPagesCorrect(int numberPages) {
		return (numberPages >= MIN_NUMBER_PAGES
				&& numberPages <= MAX_NUMBER_PAGES);
	}

	public boolean isYearPublishingCorrect(int yearPublishing) {
		int currentYear = Year.now().getValue();
		return (yearPublishing >= MIN_YEAR && yearPublishing <= currentYear);
	}

}
