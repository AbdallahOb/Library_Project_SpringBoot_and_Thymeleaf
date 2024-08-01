package com.library.librarydemo.dao;

import com.library.librarydemo.model.Logging;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggingRepository extends JpaRepository<Logging, Integer> {
}
