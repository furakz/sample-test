package com.greenhouse9.bookmaster.lecture;

import org.apache.ibatis.annotations.Param;

public interface BookMapper {

	Book selectBook(Integer id);

	Book selectBook2(@Param("id") Integer id, @Param("title") String title);
}
