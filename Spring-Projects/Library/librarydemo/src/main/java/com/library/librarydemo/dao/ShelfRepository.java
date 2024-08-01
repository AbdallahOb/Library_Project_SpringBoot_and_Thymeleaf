package com.library.librarydemo.dao;

import com.library.librarydemo.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelfRepository extends JpaRepository<Shelf, Integer> {
}
