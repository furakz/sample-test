package com.greenhouse9.bookmaster.lecture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.greenhouse9.bookmaster.domain.Book;

public class JdbcTest {

	public static void main(String[] args){
		Connection conn=null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String strConn = "jdbc:mysql://localhost/biblio"
					+ "?user=bibdev&password=bibdev"
					+ "&useUnicode=true&characterEncoding=utf-8";

			conn = DriverManager.getConnection(strConn);
			stmt = conn.createStatement();

			//String strSql = "SELECT * FROM m_author";
			String strSql2 = "SELECT * FROM book";
			rs=stmt.executeQuery(strSql2);

			while(rs.next()){
				//System.out.println(rs.getInt("id") + ": " + rs.getString("author_name"));
				System.out.println(rs.getInt("id") + ": " + rs.getString("title"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}



		System.out.println("Test Success!");

		Book book = new Book();
		book.setTitle("夏への扉");
		book.setPrice(12.5F);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("book-master");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		em.persist(book);
		tx.commit();

		em.close();
		emf.close();

	}
}
