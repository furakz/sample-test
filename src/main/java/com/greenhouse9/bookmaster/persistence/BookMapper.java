package com.greenhouse9.bookmaster.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.greenhouse9.bookmaster.domain.Book;

public interface BookMapper {

	Book selectBook(Integer id);

	Book selectBook2(@Param("id") Integer id, @Param("title") String title);

	List<Book> selectAllBooks();

	void insert(@Param("record") Book record);

	int updateByPrimaryKey(Book record);
}
