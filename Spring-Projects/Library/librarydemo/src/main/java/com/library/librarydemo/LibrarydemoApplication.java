package com.library.librarydemo;

import com.library.librarydemo.model.Address;
import com.library.librarydemo.model.Author;
import com.library.librarydemo.model.Book;
import com.library.librarydemo.model.Shelf;
import com.library.librarydemo.service.AddressService;
import com.library.librarydemo.service.AuthorService;
import com.library.librarydemo.service.BookService;
import com.library.librarydemo.service.ShelfService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;

@SpringBootApplication
public class LibrarydemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarydemoApplication.class, args);
	}


//	@Bean
//	public CommandLineRunner commandLineRunner(AddressService addressService, BookService bookService, AuthorService authorService, ShelfService shelfService){
//		return runner -> {
//
//			seedData(bookService, shelfService, authorService, addressService);
//		};
//	}
//
//	private void seedData(BookService bookService, ShelfService shelfService, AuthorService authorService, AddressService addressService) {
//		//Authors
//		Author abdallah = new Author(
//				"Abdallah",
//				"Obaidat",
//				"Abdallah.bus2001@gmail.com",
//				new Date(2001,3,6)
//		);
//
//		Author Saleem = new Author(
//				"Saleem",
//				"Obaidat",
//				"Saleem@gmail.com",
//				new Date(1957,5,11)
//		);
//		//Addresses
//		Address saleemAddress = new Address(
//				"Palestine",
//				"Alquds",
//				"Sala'a ST",
//				"A1",
//				"11111"
//		);
//		Saleem.setAddress(saleemAddress);
//
//		Address abdallahAddress = new Address(
//				"Jordan",
//				"Amman",
//				"Sebweih ST",
//				"B1",
//				"12345"
//		);
//		abdallah.setAddress(abdallahAddress);
//
//		// Shelves
//		Shelf shelf1 = new Shelf(
//				"SHELF1",
//				"Action",
//				true
//		);
//		Shelf shelf2 = new Shelf(
//				"SHELF2",
//				"Drama",
//				false
//		);
//
//
//		// Books
//		Book book1 = new Book(
//				"T1",
//				10,
//				null,
//				null
//		);
//		book1.setAuthor(Saleem);
//		book1.setShelf(shelf1);
//
//		Book book2 = new Book(
//				"T2",
//				50,
//				null,
//				null
//		);
//		book2.setAuthor(abdallah);
//		book2.setShelf(shelf2);
//
//
//		bookService.save(book1);
//		bookService.save(book2);
//
//		System.out.println("DONE!");
//	}


}
