package com.greenhouse9.bookmaster.domain;

public class BookInput {

	private String id;
	private String title;
	private String price;
	private String nbOfPage;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNbOfPage() {
		return nbOfPage;
	}
	public void setNbOfPage(String nbOfPage) {
		this.nbOfPage = nbOfPage;
	}
}
