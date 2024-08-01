package com.library.librarydemo.aspect;

import com.library.librarydemo.model.Author;
import com.library.librarydemo.model.Book;
import com.library.librarydemo.model.Logging;
import com.library.librarydemo.model.Shelf;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private LoggingService loggingService;

    @Autowired
    public LoggingAspect(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Pointcut("execution(* com.library.librarydemo.service.*.save(..))")
    private void forSaveAndUpdate(){}

    @After("forSaveAndUpdate()")
    public void additionLogging(JoinPoint jp){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        String userRole = authentication.getAuthorities().toString();
        String action = "Adding entity";

        Object entity = jp.getArgs()[0];
        if(entity instanceof Author){
            entity= (Author) entity;
        }else if(entity instanceof Book){
            entity= (Book) entity;
        }else if(entity instanceof Shelf){
            entity= (Shelf) entity;
        }
        loggingService.save(new Logging(userName, action, entity.toString(), userRole));
    }

}
