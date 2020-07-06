package by.artempvn.les06.entity;

import java.util.Collections;
import java.util.List;

public class Book {
	private long bookId;
	private String title;
	private List<String> authors;
	private int numberPages;
	private int yearPublishing;
	private static long id;

	public Book(String title, List<String> authors, int numberPages,
			int yearPublishing) {
		this.bookId = id;
		this.title = title;
		this.authors = authors;
		this.numberPages = numberPages;
		this.yearPublishing = yearPublishing;
		id++;
	}

	public long getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getAuthorsReadOnly() {
		return Collections.unmodifiableList(authors);
	}

	public int getNumberPages() {
		return numberPages;
	}

	public int getYearPublishing() {
		return yearPublishing;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + (int) (bookId ^ (bookId >>> 32));
		result = prime * result + numberPages;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + yearPublishing;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Book other = (Book) obj;
		if (authors == null) {
			if (other.authors != null) {
				return false;
			}
		} else if (!authors.equals(other.authors)) {
			return false;
		}
		if (bookId != other.bookId) {
			return false;
		}
		if (numberPages != other.numberPages) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (yearPublishing != other.yearPublishing) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"Book [bookId=%s, title=%s, authors=%s, numberPages=%s, yearPublishing=%s]",
				bookId, title, authors, numberPages, yearPublishing);
	}
}
