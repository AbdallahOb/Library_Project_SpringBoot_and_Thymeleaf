package com.library.librarydemo.wrapper;

import com.library.librarydemo.model.Book;
import org.springframework.web.multipart.MultipartFile;

public class BookAndFilesWrapper {
    private MultipartFile pdfFile;
    private MultipartFile imageFile;
    private Book book;

    public MultipartFile getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(MultipartFile pdfFile) {
        this.pdfFile = pdfFile;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
