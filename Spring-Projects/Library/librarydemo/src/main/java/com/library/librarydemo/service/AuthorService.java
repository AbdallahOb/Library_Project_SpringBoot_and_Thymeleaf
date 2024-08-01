package com.library.librarydemo.service;

import com.library.librarydemo.model.Address;
import com.library.librarydemo.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findById(int theId);
    Author save(Author author);
    void deleteById(int theId);


}
