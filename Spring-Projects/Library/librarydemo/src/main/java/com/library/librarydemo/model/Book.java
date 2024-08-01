package com.library.librarydemo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name = "book")
public class Book {
    //------------- Define the fields -------------//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "image")
    @Lob
    private byte[] image;
    @Column(name = "pdf_file")
    @Lob
    private byte[] pdfFile;
    @Column(name = "creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "pdf_name")
    private String fileName;

    @Column(name = "image_name")
    private String imageName;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;


    //------------- Create constructors -------------//
    public Book(){

    }

    public Book(String title, int quantity, byte[] image, byte[] pdfFile, LocalDateTime creationDate, String fileName, String imageName) {
        this.title = title;
        this.quantity = quantity;
        this.image = image;
        this.pdfFile = pdfFile;
        this.creationDate = creationDate;
        this.fileName = fileName;
        this.imageName = imageName;
    }

    //------------- Generate getter/setter methods -------------//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    //------------- Generate toString() method -------------//


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                ", creationDate=" + creationDate +
                ", fileName='" + fileName + '\'' +
                ", imageName='" + imageName + '\'' +
                ", author=" + author.getFirstName() +
                ", shelf=" + shelf.getName() +
                '}';
    }
}
