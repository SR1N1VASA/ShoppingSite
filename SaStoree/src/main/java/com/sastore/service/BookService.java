package com.sastore.service;

import java.util.List;

import com.sastore.domain.Book;

public interface BookService {
	List<Book> findAll ();
	
	Book findOne(Long id);
	
	List<Book> findByCategory(String category);
	
	List<Book> blurrySearch(String title);

	List<Book> find(String category, String author, String language, String publisher, String stars, String format,
			String ourPrice1, String ourPrice2);
	
}
