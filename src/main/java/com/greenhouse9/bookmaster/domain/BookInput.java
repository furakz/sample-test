package com.greenhouse9.bookmaster.domain;

import org.springframework.web.multipart.MultipartFile;

public class BookInput {

	private String id;
	private String title;
	private String price;
	private String nbOfPage;
	private MultipartFile filename;

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
	public MultipartFile getFilename() {
		return filename;
	}
	public void setFilename(MultipartFile filename) {
		this.filename = filename;
	}
}
