package com.greenhouse9.bookmaster.lecture;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.greenhouse9.bookmaster.domain.Book;
import com.greenhouse9.bookmaster.persistence.BookMapper;

public class PreBookDAO {

	private String resource = "mybatis-config.xml";

	public static SqlSessionFactory sqlSessionFactory;

	private Book book;

	public PreBookDAO() throws IOException{

		InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();

        try {
          BookMapper mapper = session.getMapper(BookMapper.class);
          book = mapper.selectBook(101);

          book = mapper.selectBook2(101, "sum");
        } finally {
          session.close();
        }
	}

	public Book getBook(){
		return book;
	}

}
