package com.library.librarydemo.service;

import com.library.librarydemo.model.Address;
import com.library.librarydemo.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(int theId);
    Book save(Book book);
    void deleteById(int theId);


}
