package com.library.librarydemo.service;
import com.library.librarydemo.model.Shelf;
import java.util.List;

public interface ShelfService {
    List<Shelf> findAll();
    Shelf findById(int theId);
    Shelf save(Shelf shelf);
    void deleteById(int theId);
}
