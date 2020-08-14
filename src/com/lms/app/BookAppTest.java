package com.lms.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lms.models.Book;
import com.lms.service.impl.BookServiceImpl;

public class BookAppTest {

	public static void main(String[] args) {
		BookServiceImpl bookService = new BookServiceImpl();
		Scanner sc = new Scanner(System.in);
		int choice = 0, input = 0;
		int book_id = 0;
		String book_name = null, book_author = null, book_publisher = null, book_owner = null;
		while (true) {
			System.out.print(
					"\n\nSelect Your Option:\n1. View Books\n2. Add Book\n3. Get Book Details\n4. Update Book\n5. Delete Book\n0. Exit\nYour Choice :: ");
			choice = Integer.parseInt(sc.nextLine());
			if (choice == 0)
				break;
			switch (choice) {
			case 1:
				List<Book> books = bookService.getBooks();
				System.out.println(String.format("|%-5s", "Sr.No") + String.format("|%-20s", "Book Name")
						+ String.format("|%-20s", "Book Author") + String.format("|%-20s", "Book Publisher")
						+ String.format("|%-20s", "Book Owner"));
				for (Book book : books) {
					System.out.println(
							String.format("|%-5d", book.getBook_id()) + String.format("|%-20s", book.getBook_name())
									+ String.format("|%-20s", book.getBook_author())
									+ String.format("|%-20s", book.getBook_publisher())
									+ String.format("|%-20s", book.getBook_owner()));
				}
				break;
			case 2:
				System.out.print("Enter Book Id :: ");
				book_id = Integer.parseInt(sc.nextLine());
				System.out.print("Enter Book Name :: ");
				book_name = sc.next();
				System.out.print("Enter Book Author :: ");
				book_author = sc.next();
				sc.nextLine();
				System.out.print("Enter Book Publisher :: ");
				book_publisher = sc.next();
				sc.nextLine();
				System.out.print("Enter Book Owner :: ");
				book_owner = sc.next();
				sc.nextLine();
				bookService.add(new Book(book_id, book_name, book_author, book_publisher, book_owner));
				break;

			case 3:
				System.out.print("Enter Book Id :: ");
				book_id = sc.nextInt();
				Book book = bookService.get(book_id);
				System.out.println(String.format("|%-5s", "Sr.No") + String.format("|%-20s", "Book Name")
						+ String.format("|%-20s", "Book Author") + String.format("|%-20s", "Book Publisher")
						+ String.format("|%-20s", "Book Owner"));
				System.out.println(String.format("|%-5d", book.getBook_id())
						+ String.format("|%-20s", book.getBook_name()) + String.format("|%-20s", book.getBook_author())
						+ String.format("|%-20s", book.getBook_publisher())
						+ String.format("|%-20s", book.getBook_owner()));
				break;
				
			case 4:
				System.out.print("Enter Book Id :: ");
				book_id = sc.nextInt();
				System.out.print("Enter Book Name :: ");
				book_name = sc.nextLine();
				System.out.print("Enter Book Author :: ");
				book_author = sc.nextLine();
				System.out.print("Enter Book Publisher :: ");
				book_publisher = sc.nextLine();
				System.out.print("Enter Book Owner :: ");
				book_owner = sc.nextLine();
				bookService.update(new Book(book_id, book_name, book_author, book_publisher, book_owner));
				break;
			
			case 5:
				System.out.print("Enter Book Id :: ");
				book_id = sc.nextInt();
				bookService.remove(book_id);
				break;
			}
		}

	}

}
