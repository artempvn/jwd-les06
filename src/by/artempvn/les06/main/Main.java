package by.artempvn.les06.main;

import java.time.Year;
import java.util.Comparator;

import by.artempvn.les06.model.dao.impl.BookListDaoImpl;
import by.artempvn.les06.model.entity.Book;
import by.artempvn.les06.model.entity.Library;
import by.artempvn.les06.model.exception.CustomException;

public class Main {

	public static void main(String[] args) throws CustomException {
//		Library wareHouse = Library.getInstance();
//		System.out.println(wareHouse.getBooksReadOnly());
//		BookListDaoImpl bd=new BookListDaoImpl();
//		System.out.println((bd.sortByNumberPages()));
//		System.out.println(wareHouse.getBooksReadOnly());
		System.out.println(Year.now().getValue());

	}

}
