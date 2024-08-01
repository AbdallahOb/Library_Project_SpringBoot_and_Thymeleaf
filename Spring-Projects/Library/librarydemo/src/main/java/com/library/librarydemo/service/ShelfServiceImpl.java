package com.library.librarydemo.service;

import com.library.librarydemo.dao.ShelfRepository;
import com.library.librarydemo.model.Book;
import com.library.librarydemo.model.Shelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelfServiceImpl implements ShelfService{
    private ShelfRepository shelfRepository;
    @Autowired
    public ShelfServiceImpl(ShelfRepository shelfRepository){
        this.shelfRepository= shelfRepository;
    }


    @Override
    public List<Shelf> findAll() {
        return shelfRepository.findAll();
    }

    @Override
    public Shelf findById(int theId) {
        Optional<Shelf> result = shelfRepository.findById(theId);
        Shelf shelf=null;
        if(result.isPresent()){
            shelf = result.get();
        }else{
            throw new RuntimeException("Did not find shelf id - "+theId);
        }
        return shelf;
    }

    @Override
    public Shelf save(Shelf shelf) {
        return shelfRepository.save(shelf);
    }

    @Override
    public void deleteById(int theId) {
        shelfRepository.deleteById(theId);
    }
}
