package com.library.librarydemo.controller;

import com.library.librarydemo.model.Book;
import com.library.librarydemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private BookService bookService;
    @Autowired
    public HomeController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String listBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);

        return "library/home/list-books";
    }

    //Download Pdf
    @GetMapping("/downloadFile")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("bookId") int id) throws IOException {
        Book tempBook = bookService.findById(id);


        ByteArrayInputStream bis = new ByteArrayInputStream(tempBook.getPdfFile());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + tempBook.getFileName());
        headers.add("Content-Type", "application/pdf"); // Use application/pdf if your file is a PDF

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(bis));
    }


    // Download Image
    @GetMapping("/downloadImage")
    public ResponseEntity<InputStreamResource> downloadImage(@RequestParam("bookId") int id) throws IOException {
        Book tempBook = bookService.findById(id);

        ByteArrayInputStream bis = new ByteArrayInputStream(tempBook.getImage());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + tempBook.getImageName());
        headers.add("Content-Type", "application/octet-stream");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(bis));
    }

    //Returns the image
    @GetMapping("/image/{bookId}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable("bookId") int bookId) {
        Book tempBook = bookService.findById(bookId);
        byte[] imageBytes = tempBook.getImage();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Change this according to the image type

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
