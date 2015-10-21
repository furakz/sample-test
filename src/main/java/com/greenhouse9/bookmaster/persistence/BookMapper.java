package com.greenhouse9.bookmaster.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.greenhouse9.bookmaster.domain.Book;

public interface BookMapper {

	Book selectBook(Integer id);

	Book selectBook2(@Param("id") Integer id, @Param("title") String title);

	List<Book> selectAllBooks();

	List<Book> selectByCondition(Map<String, Object> conditionMap);

	void insert(@Param("record") Book record);

	int updateByPrimaryKey(Book record);
}
