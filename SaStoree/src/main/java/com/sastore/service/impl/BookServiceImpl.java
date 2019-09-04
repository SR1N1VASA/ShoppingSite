package com.sastore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sastore.domain.Book;
import com.sastore.repository.BookRepository;
import com.sastore.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAll() {
		List<Book> bookList = (List<Book>) bookRepository.findAll();
		List<Book> activeBookList = new ArrayList<>();
		
		for (Book book: bookList) {
			if(book.isActive()) {
				activeBookList.add(book);
			}
		}
		
		return activeBookList;
	}
	
	public Book findOne(Long id) {
		return bookRepository.findById(id).orElse(null);
	}

	public List<Book> findByCategory(String category){
		List<Book> bookList = bookRepository.findByCategory(category);
		
		List<Book> activeBookList = new ArrayList<>();
		
		for (Book book: bookList) {
			if(book.isActive()) {
				activeBookList.add(book);
			}
		}
		
		return activeBookList;
	}
	
	public List<Book> blurrySearch(String title) {
		List<Book> bookList = bookRepository.findByTitleContaining(title);
List<Book> activeBookList = new ArrayList<>();
		
		for (Book book: bookList) {
			if(book.isActive()) {
				activeBookList.add(book);
			}
		}
		
		return activeBookList;
	}

	@Override
	public List<Book> find(String category, String author, String language, String publisher, String stars,
			String format, String ourPrice1, String ourPrice2) {
		// TODO Auto-generated method stub
		List<Book> bookList = (List<Book>) bookRepository.findAll();
		List<Book> activeBookList = new ArrayList<>();
		System.out.println(category+author+language+publisher);
		int star;
		if(stars.equals("All"))
			star=0;
		else
			star = Integer.parseInt(stars);
		int Price1,Price2;
		if(ourPrice1.equals("") || ourPrice2.equals(""))
		{
			ourPrice1="All";
			Price1=0;
			ourPrice2="All";
			Price2=0;
		}
		else
		{
		Price1 = Integer.parseInt(ourPrice1);
		Price2 = Integer.parseInt(ourPrice2);
		}
		for (Book book: bookList) {
			if((book.getCategory().equals(category) || category.equals("All")) && (book.getAuthor().equals(author) || author.equals("All"))) 
					{
					if (book.getLanguage().equals(language) || language.equals("All"))
						{
						 if(book.getPublisher().equals(publisher) || publisher.equals("All"))
						 	{
							 if(book.getFormat().equals(format) || format.equals("All"))  
							 {
								 if(book.getStars()>=star || stars.equals("All")) 
								{
									if(book.getOurPrice()>=Price1 || ourPrice1.equals("All"))
									{
									if(book.getOurPrice()<=Price2 || ourPrice2.equals("All"))
										{
											activeBookList.add(book);
										}
									}
								}	
							 }		
						}
						}
					else
					{
						System.out.println(book.getLanguage());
						System.out.println("idee"+language);
					}
					}
					}
	
		return activeBookList;
	}
}
