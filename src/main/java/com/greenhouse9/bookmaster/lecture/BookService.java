package com.greenhouse9.bookmaster.lecture;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

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

	public void upload(String filepath, String errpath) {

//		StringTokenizer token;
		BufferedReader br = null;
		try {
//			FileReader fr = new FileReader(filepath);

			String line;
			String tokenArray [];
			FileInputStream fis = new FileInputStream(filepath);
			InputStreamReader fsr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			br = new BufferedReader(fsr);

			while((line = br.readLine()) != null) {

//				token = new StringTokenizer(line, ",");
//				BookInput book = new BookInput();
//				book.setId(token.hasMoreTokens()?token.nextToken():"");
//				book.setTitle(token.hasMoreTokens()?token.nextToken():"");
//				book.setPrice(token.hasMoreTokens()?token.nextToken():"");
//				book.setNbOfPage(token.hasMoreTokens()?token.nextToken():"");

				tokenArray = line.split(",");

				if (tokenArray.length < 4) {
					continue;
				}

				BookInput book = new BookInput();
				book.setId(tokenArray[0]);
				book.setTitle(tokenArray[1]);
				book.setPrice(tokenArray[2]);
				book.setNbOfPage(tokenArray[3]);

				Book book2 = null;
				try {
					Integer.parseInt(book.getId());
					book2 = selectByPrimaryKey(Integer.parseInt(book.getId()));
				} catch (Exception e){

				}

				if (book2 == null) {
					create(book);
				} else {
					update(book);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e){

			}
		}
	}

	private SqlSession getSession() throws IOException{

		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        return session;

	}
}
