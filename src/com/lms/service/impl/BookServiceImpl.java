package com.lms.service.impl;

import java.util.List;

import com.lms.daos.impl.BookDAOImpl;
import com.lms.models.Book;
import com.lms.service.BookService;

public class BookServiceImpl implements BookService {

	private BookDAOImpl bookDao;
	
	
	public BookServiceImpl() {
		bookDao = new BookDAOImpl();
	}

	@Override
	public void add(Book book) {
		bookDao.addBook(book);
	}

	@Override
	public Book get(int book_id) {
		return bookDao.getBook(book_id);
	}

	@Override
	public List<Book> getBooks() {
		return bookDao.getBooks();
	}

	@Override
	public void update(Book book) {
		bookDao.updateBook(book);
	}

	@Override
	public void remove(int book_id) {	
		bookDao.deleteBook(book_id);
	}

}
