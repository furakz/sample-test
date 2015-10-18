package com.greenhouse9.bookmaster.domain;

public class BookHelper {

	public Book getBook(BookInput bookInput) {

		Book book = null;

		try {
			Long id = Long.parseLong(bookInput.getId());
			Float price = Float.parseFloat(bookInput.getPrice());
			Integer nbOfPage = Integer.parseInt(bookInput.getNbOfPage());

			book = new Book();

			book.setId(id);
			book.setTitle(bookInput.getTitle());
			book.setPrice(price);
			book.setNbOfPage(nbOfPage);

			return book;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
