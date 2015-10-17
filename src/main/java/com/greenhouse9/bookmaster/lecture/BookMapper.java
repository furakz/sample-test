package com.greenhouse9.bookmaster.lecture;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BookMapper {

	Book selectBook(Integer id);

	Book selectBook2(@Param("id") Integer id, @Param("title") String title);

	List<Book> selectAllBooks();
}
