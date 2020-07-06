package by.artempvn.les06.main;

import by.artempvn.les06.entity.BookWarehouse;

public class Main {

	public static void main(String[] args) {
		BookWarehouse wareHouse = BookWarehouse.getInstance();
		System.out.println(wareHouse.getBooksReadOnly());

	}

}
