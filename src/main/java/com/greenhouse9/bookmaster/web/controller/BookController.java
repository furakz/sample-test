package com.greenhouse9.bookmaster.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greenhouse9.bookmaster.common.AppError;
import com.greenhouse9.bookmaster.domain.Book;
import com.greenhouse9.bookmaster.domain.BookHelper;
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

	@RequestMapping(value="edit_upload", method=GET)
	public String editUpload(Model model){

		//test
		String reg = "(\\d*)([/-])";
		String input = "2015/11-11";

		Pattern pattern = Pattern.compile(reg);
		Matcher match = pattern.matcher(input);

		while (match.find()){
			System.out.println(match.group());
			for(int i = 0; i <= match.groupCount(); i++){
				System.out.println(match.group(i));
			}
		}


		return "sample4";
	}

	@RequestMapping(value="sch_download", method=GET)
	public String schDownload(Model model){

		return "sample5";
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
	public String create(@ModelAttribute BookInput form, Model model, RedirectAttributes attributes) {

		try {
			//Book book = service.create(form);
			Book book = null;
			BookHelper bookHelper = new BookHelper();
			bookHelper.bind(form, book);

			if (bookHelper.hasErrors()) {
				String message = "error occured! <br />";

				for (AppError err:bookHelper.getErrors()){
					message = message + err.getErrorMessage() + "<br />";
				}
				attributes.addFlashAttribute("message", message);
				attributes.addFlashAttribute("book", form);
			} else {
				book = service.create(form);
			}

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

		model.addAttribute("conditionForm", map);

		return "sample1";
	}

	@RequestMapping(value="download", method=POST, produces="text/csv;charset=utf-8")
	@ResponseBody
	public String download(HttpServletRequest request, HttpServletResponse response, Model model) {

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

		StringBuilder sb = null;

		try {

			List<Book> bookList = service.selectByCondition(condMap);

			sb = new StringBuilder();

			for (Book book:bookList) {

				sb.append(book.getId()).append(",");
				sb.append(book.getTitle()).append(",");
				sb.append(book.getPrice()).append(",");
				sb.append(book.getNbOfPage());
				sb.append("\r\n");
			}

			response.setHeader("Content-Disposition", "attachment; filename=\"book.csv\"");
//			response.setHeader("Content-Type", "text/csv;charset=Windows-31J");
//			response.setContentType("text/csv;charset=utf-8");
//
//			PrintWriter pw = response.getWriter();
//			pw.print(sb.toString());

		} catch (IOException e) {

			e.printStackTrace();
		}


		return sb.toString();
	}

	/**
	 * @param form 入力フォーム
	 * @param model
	 * @return
	 */
	@RequestMapping(value="upload", method=POST)
	public String upload(@ModelAttribute BookInput form, Model model) {

		FileOutputStream fos = null;
		try {
			InputStream is  = form.getFilename().getInputStream();
//			InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));

//			int ch;
//			char character;
//			StringBuffer sb = new StringBuffer();
//
//			while ((ch = isr.read()) != -1){
//				character = (char)ch;
//				sb.append(character);
//				System.out.print(character);
//			}

			byte buffer[] = new byte[1024];
			is.reset();
			int len;
			File outFile = new File("C:\\development\\book-master\\app\\temp\\out.txt");
			if (outFile.exists() || outFile.createNewFile()) {
				fos = new FileOutputStream(outFile);
				while((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
			}

			service.upload("C:\\development\\book-master\\app\\temp\\out.txt", "C:\\development\\book-master\\app\\temp\\err.txt");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "sample4";
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
