package com.library.librarydemo.aspect;


import com.library.librarydemo.dao.LoggingRepository;
import com.library.librarydemo.model.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggingServiceImpl implements LoggingService {
    private LoggingRepository loggingRepository;

    @Autowired
    public LoggingServiceImpl(LoggingRepository loggingRepository) {
        this.loggingRepository = loggingRepository;
    }


    @Override
    public Logging save(Logging logging) {
        return loggingRepository.save(logging);
    }
}
