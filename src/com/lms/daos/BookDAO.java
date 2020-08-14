package com.lms.daos;

import java.util.List;

import com.lms.models.Book;

public interface BookDAO {
	
	void createConnection();
	void addBook(Book book);
	Book getBook(int book_id);
	List<Book> getBooks();
	void updateBook(Book book);
	void deleteBook(int book_id);
	void closeConnection();
}
