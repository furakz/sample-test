package com.greenhouse9.bookmaster.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenhouse9.bookmaster.domain.Book;
import com.greenhouse9.bookmaster.domain.BookInput;
import com.greenhouse9.bookmaster.lecture.BookService;

@Controller
@RequestMapping(value="book")
public class BookController {

	@Autowired
	private BookService service;

	@RequestMapping(value="select", method=GET)
	public String sample(Model model) {

		Book book = new Book();

		try {
			book.setTitle("GitHub INSERT");
			book.setPrice(151F);
			book.setNbOfPage(100);
			//service.insert(book);

			book.setId(1L);
			book.setTitle("GitHub UPDATE");
			book.setPrice(150F);
			book.setNbOfPage(120);
			//service.update(book);

			List<Book> bookList = service.selectAllBook();
			System.out.println("PRICE: " + bookList.get(0).getPrice());
			model.addAttribute("bookList", bookList);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "sample1";
	}

	@RequestMapping(value="select/{id}", method=GET)
	public String selectById(@PathVariable Integer id, Model model) {

		try {
			Book book = service.selectByPrimaryKey(id);
			model.addAttribute("book", book);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "sample2";
	}

	@RequestMapping(value="edit_new", method=GET)
	public String editNew(Model model) {

		return "sample3";
	}

	@RequestMapping(value="update", method=POST)
	public String update(@ModelAttribute BookInput form, Model model) {

		System.out.println("TEST: " + form.getPrice());

		try {
			Book book = service.update(form);
			model.addAttribute("book", book);

		} catch (Exception e) {
			e.printStackTrace();

			model.addAttribute("book", form);
			return "sample2";
		}

		return "redirect:select/" + form.getId();
	}

	@RequestMapping(value="create", method=POST)
	public String create(@ModelAttribute BookInput form, Model model) {

		try {
			Book book = service.create(form);
			model.addAttribute("book", book);

		} catch (Exception e) {
			e.printStackTrace();

			model.addAttribute("book", form);
			return "sample3";
		}

		return "redirect:edit_new";
	}

	@RequestMapping(value="search", method=POST)
	public String search(HttpServletRequest request, Model model) {

		Map<String,String[]> map = request.getParameterMap();
		Map<String, Object> condMap = new HashMap<String,Object>();

		for(String key: map.keySet()){
			if (map.get(key).length == 1 && map.get(key)[0].length() > 0) {
				if(key.equals("price")){
					condMap.put(key, Float.parseFloat(map.get(key)[0]));
				} else if (key.equals("nbOfPage")) {
					condMap.put(key, Integer.parseInt(map.get(key)[0]));
				} else {
					condMap.put(key, map.get(key)[0]);
				}
			} else if (map.get(key).length > 1 && containsNonEmpty(map.get(key))) {
				condMap.put(key, Arrays.<String>asList(map.get(key)));
			}
		}

		for(String key: condMap.keySet()){
			System.out.println("key: " + key + " value: " + condMap.get(key));
		}
		try {

			List<Book> bookList = service.selectByCondition(condMap);
			model.addAttribute("bookList", bookList);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return "sample1";
	}

	private boolean containsNonEmpty(String [] array) {
		for (String str: array){
			if (str != null && str.length() >0){
				return true;
			}
		}
		return false;
	}
}
