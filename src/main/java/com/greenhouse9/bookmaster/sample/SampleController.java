package com.greenhouse9.bookmaster.sample;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenhouse9.bookmaster.lecture.Book;
import com.greenhouse9.bookmaster.lecture.PreBookDAO;

@Controller
public class SampleController {

	@RequestMapping(value="sample", method=GET)
	public String sample(Model model) {

		Book book = new Book();
		book.setTitle("The door to summer");
		book.setPrice(12.5F);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("book-master");
		EntityManager em = emf.createEntityManager();

		em.persist(book);

		em.close();
		emf.close();

		try {
			PreBookDAO dao = new PreBookDAO();
			Book book2 = dao.getBook();
			model.addAttribute("book", book2);
			model.addAttribute("title", book2.getTitle());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "sample1";
	}

}
