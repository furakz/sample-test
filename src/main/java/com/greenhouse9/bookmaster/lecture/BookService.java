package com.greenhouse9.bookmaster.lecture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import com.greenhouse9.bookmaster.domain.Book;
import com.greenhouse9.bookmaster.domain.BookHelper;
import com.greenhouse9.bookmaster.domain.BookInput;
import com.greenhouse9.bookmaster.persistence.BookMapper;

@Service
public class BookService {

	private static String resource = "mybatis-config.xml";

	private BookMapper mapper;

	public List<Book> selectAllBook() throws IOException{

        SqlSession session = getSession();

        mapper = session.getMapper(BookMapper.class);
		List<Book> bookList = mapper.selectAllBooks();
		//List<Book> bookList = null;

		session.close();

		return bookList;
	}

	public List<Book> selectByCondition(Map<String,Object> conditionMap) throws IOException{

        SqlSession session = getSession();

        mapper = session.getMapper(BookMapper.class);
		List<Book> bookList = mapper.selectByCondition(conditionMap);
		//List<Book> bookList = null;

		session.close();

		return bookList;
	}

	public Book selectByPrimaryKey(Integer id) throws IOException {

        SqlSession session = getSession();

        mapper = session.getMapper(BookMapper.class);
		Book book = mapper.selectBook(id);

		session.close();

		return book;
	}

	public void insert(Book record) throws IOException {

        SqlSession session = getSession();

        mapper = session.getMapper(BookMapper.class);

        mapper.insert(record);

        session.commit();
		session.close();
	}

	public int update(Book book) throws IOException {

        SqlSession session = getSession();

        mapper = session.getMapper(BookMapper.class);

		int ret = mapper.updateByPrimaryKey(book);

        session.commit();
		session.close();

		return ret;
	}

	public Book update(BookInput bookInput) throws IOException {

		BookHelper helper = new BookHelper();
		Book book = helper.getBook(bookInput);
		update(book);

		return book;
	}

	public Book create(BookInput bookInput) throws IOException {

		BookHelper helper = new BookHelper();
		Book book = helper.getBook(bookInput);
		insert(book);

		return book;
	}

	public void upload(String filepath) {

		try {
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);

			String line;
			StringTokenizer token;

			while((line = br.readLine()) != null) {

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private SqlSession getSession() throws IOException{

		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        return session;

	}
}
