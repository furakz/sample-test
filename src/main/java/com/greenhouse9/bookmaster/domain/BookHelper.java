package com.greenhouse9.bookmaster.domain;

import java.util.ArrayList;
import java.util.List;

import com.greenhouse9.bookmaster.common.AppError;

public class BookHelper {

	private List<AppError> errorList = new ArrayList<AppError>();

	public List<AppError> getErrors () {
		return errorList;
	}

	public void bind(BookInput bookInput, Book book) {

		if (book == null){
			book = new Book();
		}

		boolean isValid = true;
		AppError err;

		errorList.clear();

		//id
		Long id = null;
		if (isEmpty(bookInput.getId())) {
			isValid = false;
			err = new AppError();
			err.setErrorMessage("id is empty");
			errorList.add(err);
		}
		if(isValid){

			try {
				id = Long.parseLong(bookInput.getId());
			} catch(Exception e) {
				isValid = false;
				err = new AppError();
				err.setErrorMessage("id is not number format");
				errorList.add(err);
			}
		}
		if (isValid){
			book.setId(id);
		}

		//title
		isValid = true;
		String title = "";
		if (isEmpty(bookInput.getTitle())) {
			isValid = false;
			err = new AppError();
			err.setErrorMessage("title is empty");
			errorList.add(err);
		}
		if(isValid){

			title = bookInput.getTitle();
			if (title.length() > 20) {
				isValid = false;
				err = new AppError();
				err.setErrorMessage("title is too long (max 20)");
				errorList.add(err);
			}
		}
		if(isValid){
			book.setTitle(title);
		}

		//price (prec 9, scale 2)
		isValid = true;
		Float price = null;
		if (!isEmpty(bookInput.getPrice())) {
			if (!bookInput.getPrice().matches("^\\d{1,7}([\\.]\\d{1,2})?$")){
				isValid = false;
				err = new AppError();
				err.setErrorMessage("price is not float");
				errorList.add(err);
			};
			if(isValid){

				try {
					price = Float.parseFloat(bookInput.getPrice());
				} catch (Exception e) {
					isValid = false;
					err = new AppError();
					err.setErrorMessage("price is not float");
					errorList.add(err);
				}
			}
		}
		if(isValid){
			book.setPrice(price);
		}

		//page [0 , 999999]
		isValid = true;
		Integer nbOfPage = null;
		if (!isEmpty(bookInput.getNbOfPage())) {

				try {
					nbOfPage = Integer.parseInt(bookInput.getNbOfPage());
					if (nbOfPage < 0) {
						isValid = false;
						err = new AppError();
						err.setErrorMessage("nbOfPage is negative");
						errorList.add(err);
					}
					if (nbOfPage > 999999) {
						isValid = false;
						err = new AppError();
						err.setErrorMessage("nbOfPage is too big (max 999999)");
						errorList.add(err);
					}
				} catch (Exception e) {
					isValid = false;
					err = new AppError();
					err.setErrorMessage("nbOfPage is not Integer");
					errorList.add(err);
				}
		}
		if(isValid){
			book.setNbOfPage(nbOfPage);
		}

}

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

	public Boolean hasErrors(){
		return !errorList.isEmpty();
	}

	public Boolean noErrors(){
		return errorList.isEmpty();
	}

	private boolean isEmpty(String str){
		return (str == null || str.length() == 0);
	}
}
