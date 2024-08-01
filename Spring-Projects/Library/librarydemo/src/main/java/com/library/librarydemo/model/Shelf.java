package com.library.librarydemo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shelf")
public class Shelf {
    //------------- Define the fields -------------//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "active")
    private boolean status;
    @Column(name = "creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();

    @OneToMany(mappedBy = "shelf", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Book> books;

    //------------- Create constructors -------------//
    public Shelf(){

    }
    public Shelf(String name, String type, boolean status) {
        this.name = name;
        this.type = type;
        this.status = status;
    }


    //------------- Generate getter/setter methods -------------//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

//------------- Generate toString() method -------------//

    @Override
    public String toString() {
        return "Shelf{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", isActive=" + status +
                ", creationDate=" + creationDate +
                ", books=" + books +
                '}';
    }


    //------------- Add convenience methods for bi-directional relationship -------------//
    public void add(Book book){
        if(books == null){
            books = new ArrayList<>();
        }
        books.add(book);
        book.setShelf(this);
    }

    public int numberOfBooks(){
        return books==null?0:books.size();
    }

}
