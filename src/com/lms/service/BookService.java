package com.lms.service;

import java.util.List;

import com.lms.models.Book;

public interface BookService {
	void add(Book book);
	Book get(int book_id);
	List<Book> getBooks();
	void update(Book book);
	void remove(int book_id);
}
