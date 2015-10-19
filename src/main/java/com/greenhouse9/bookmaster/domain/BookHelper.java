package com.greenhouse9.bookmaster.domain;

public class BookHelper {

	public Book getBook(BookInput bookInput) {

		Book book = null;

		try {

			Long id;
			Float price;
			Integer nbOfPage;
			if (!isEmpty(bookInput.getId())) {
				id = Long.parseLong(bookInput.getId());
			} else {
				id = null;
			}
			if (!isEmpty(bookInput.getPrice())){
				price = Float.parseFloat(bookInput.getPrice());
			} else {
				price = null;
			}
			if (!isEmpty(bookInput.getNbOfPage())) {
				nbOfPage = Integer.parseInt(bookInput.getNbOfPage());

			} else {
				nbOfPage = null;
			}

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

	private boolean isEmpty(String str){
		return (str == null || str.length() == 0);
	}
}
