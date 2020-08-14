package com.lms.daos.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.daos.BookDAO;
import com.lms.models.Book;

public class BookDAOImpl implements BookDAO {
	private static final String DRIVERNAME = "com.mysql.jdbc.Driver";
	private static final String CONURL = "jdbc:mysql://localhost:3306/libraryjavaapp";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	Connection con;

	public BookDAOImpl() {
		super();
		try {
			Class.forName(DRIVERNAME);
			System.out.println("++++ Driver Loaded +++++");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void createConnection() {
		try {
			con = DriverManager.getConnection(CONURL, USERNAME, PASSWORD);
			System.out.println("++++ Connection Created ++++");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addBook(Book book) {

		final String SQL = "insert into books values(?,?,?,?,?)";
		createConnection();
		try {
			PreparedStatement ps = con.prepareStatement(SQL); // Create statement
			ps.clearParameters(); // Clear previous statement garbage values
			ps.setInt(1, book.getBook_id());
			ps.setString(2, book.getBook_name());
			ps.setString(3, book.getBook_author());
			ps.setString(4, book.getBook_publisher());
			ps.setString(5, book.getBook_owner());

			int cnt = ps.executeUpdate();
			if (cnt != 0) {
				System.out.println("++++ Book Added ++++");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public Book getBook(int book_id) {
		final String SQL = "select * from books where book_id=?";
		Book book = null;
		createConnection();
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.clearParameters();
			ps.setInt(1, book_id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				book = new Book(rs.getInt("book_id"), rs.getString("book_name"), rs.getString("book_author"),
						rs.getString("book_publisher"), rs.getString("book_owner"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}

		return book;
	}

	@Override
	public List<Book> getBooks() {

		final String SQL = "select * from books";
		createConnection();
		ArrayList<Book> books = new ArrayList<>();

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(SQL);
			while (rs.next()) {
				books.add(new Book(rs.getInt("book_id"), rs.getString("book_name"), rs.getString("book_author"),
						rs.getString("book_publisher"), rs.getString("book_owner")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return books;
	}

	@Override
	public void updateBook(Book book) {
		
		final String SQL = "update books set book_name = ?, book_author = ?, book_publisher = ?, book_owner = ?";
		createConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, book.getBook_name());
			ps.setString(2, book.getBook_author());
			ps.setString(3, book.getBook_publisher());
			ps.setString(4, book.getBook_owner());
			
			int cnt = ps.executeUpdate();
			if(cnt!=0) {
				System.out.println("++++ Book Updated ++++");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		

	}

	@Override
	public void deleteBook(int book_id) {
		final String SQL = "delete from books where book_id = ?";
		createConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setInt(1, book_id);
			int cnt = ps.executeUpdate();
			if (cnt!=0) {
				System.out.println("++++ Book Deleted ++++");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
	}

	@Override
	public void closeConnection() {
		try {
			con.close();
			System.out.println("++++ Connection Closed ++++");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
