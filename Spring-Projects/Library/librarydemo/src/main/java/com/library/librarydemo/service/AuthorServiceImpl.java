package com.library.librarydemo.service;

import com.library.librarydemo.dao.AddressRepository;
import com.library.librarydemo.dao.AuthorRepository;
import com.library.librarydemo.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{
    private AuthorRepository authorRepository;
    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository=authorRepository;
    }
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(int theId) {
        Optional<Author> result= authorRepository.findById(theId);
        Author author=null;
        if(result.isPresent()){
            author= result.get();
        }else{
            throw new RuntimeException("Did not find author id - "+theId);
        }
        return author;
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteById(int theId) {
        authorRepository.deleteById(theId);
    }


}
