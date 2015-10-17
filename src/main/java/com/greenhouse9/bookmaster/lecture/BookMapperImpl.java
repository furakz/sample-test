package com.greenhouse9.bookmaster.lecture;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper{

	public Book selectBook(Integer id){
		return new Book();
	}

	public Book selectBook2(@Param("id") Integer id, @Param("title") String title){
		return new Book();
	}

	public List<Book> selectAllBooks(){
		return new ArrayList<Book>();
	}

}
