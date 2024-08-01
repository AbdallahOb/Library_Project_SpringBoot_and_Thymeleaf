package com.library.librarydemo.controller;

import com.library.librarydemo.model.Author;
import com.library.librarydemo.model.Book;
import com.library.librarydemo.model.Shelf;
import com.library.librarydemo.service.AuthorService;
import com.library.librarydemo.service.BookService;
import com.library.librarydemo.service.ShelfService;
import com.library.librarydemo.wrapper.BookAndFilesWrapper;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService bookService;
    private AuthorService authorService;
    private ShelfService shelfService;
    private EntityManager entityManager;
    @Autowired
    public BookController(
            BookService bookService,
            ShelfService shelfService,
            AuthorService authorService,
            EntityManager entityManager)
    {
        this.bookService = bookService;
        this.authorService = authorService;
        this.shelfService = shelfService;
        this.entityManager=entityManager;
    }

    @GetMapping("/list")
    public String listBooks(Model model){
        List<Book> books= bookService.findAll();
        model.addAttribute("books", books);

        return "library/books/list-books";
    }

    @GetMapping("/addBookForm")
    public String addBookForm(Model model){
        model.addAttribute("bookAndFiles", new BookAndFilesWrapper());

        // Add authors to model attributes
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);

        // Add shelf type to model attributes
        List<Shelf> shelves = shelfService.findAll();
        model.addAttribute("shelves", shelves);

        return "library/books/book-form";
    }

    @PostMapping("/save")
    public String saveBook(
            @ModelAttribute("bookAndFiles") BookAndFilesWrapper bookAndFilesWrapper,
            BindingResult result
    ){

        if(result.hasErrors()){
            System.out.println("No Errors SUCCESS!");
        }else{
            try {
                if(bookAndFilesWrapper.getBook() != null && bookAndFilesWrapper.getBook().getId()!=0){
                    Book tempBook = bookService.findById(bookAndFilesWrapper.getBook().getId());
                    if(bookAndFilesWrapper.getPdfFile().isEmpty() && bookAndFilesWrapper.getImageFile().isEmpty()){
                        //Get the previous
                        bookAndFilesWrapper.getBook().setPdfFile(tempBook.getPdfFile());
                        bookAndFilesWrapper.getBook().setFileName(tempBook.getFileName());
                        bookAndFilesWrapper.getBook().setImage(tempBook.getImage());
                        bookAndFilesWrapper.getBook().setImageName(tempBook.getImageName());
                    }else if(bookAndFilesWrapper.getPdfFile().isEmpty() && !bookAndFilesWrapper.getImageFile().isEmpty()){
                        //Get the new image and the previous pdf
                        bookAndFilesWrapper.getBook().setPdfFile(tempBook.getPdfFile());
                        bookAndFilesWrapper.getBook().setFileName(tempBook.getFileName());
                        bookAndFilesWrapper.getBook().setImage(bookAndFilesWrapper.getImageFile().getBytes());
                        bookAndFilesWrapper.getBook().setImageName(bookAndFilesWrapper.getImageFile().getOriginalFilename());

                    }else if(!bookAndFilesWrapper.getPdfFile().isEmpty() && bookAndFilesWrapper.getImageFile().isEmpty()){
                        //Get the new pdf and the previous image then save
                        bookAndFilesWrapper.getBook().setPdfFile(bookAndFilesWrapper.getPdfFile().getBytes());
                        bookAndFilesWrapper.getBook().setFileName(bookAndFilesWrapper.getPdfFile().getOriginalFilename());
                        bookAndFilesWrapper.getBook().setImage(tempBook.getImage());
                        bookAndFilesWrapper.getBook().setImageName(tempBook.getImageName());

                    }else{
                        //get the new image and pdf then save
                        bookAndFilesWrapper.getBook().setPdfFile(bookAndFilesWrapper.getPdfFile().getBytes());
                        bookAndFilesWrapper.getBook().setImage(bookAndFilesWrapper.getImageFile().getBytes());
                        bookAndFilesWrapper.getBook().setFileName(bookAndFilesWrapper.getPdfFile().getOriginalFilename());
                        bookAndFilesWrapper.getBook().setImageName(bookAndFilesWrapper.getImageFile().getOriginalFilename());
                    }
                    entityManager.detach(tempBook);
                }else{
                    bookAndFilesWrapper.getBook().setPdfFile(bookAndFilesWrapper.getPdfFile().getBytes());
                    bookAndFilesWrapper.getBook().setImage(bookAndFilesWrapper.getImageFile().getBytes());
                    bookAndFilesWrapper.getBook().setFileName(bookAndFilesWrapper.getPdfFile().getOriginalFilename());
                    bookAndFilesWrapper.getBook().setImageName(bookAndFilesWrapper.getImageFile().getOriginalFilename());
                }
                bookService.save(bookAndFilesWrapper.getBook());


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return "redirect:/books/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theId, Model model){
        BookAndFilesWrapper bookAndFilesWrapper = new BookAndFilesWrapper();
        bookAndFilesWrapper.setBook(bookService.findById(theId));
        model.addAttribute("bookAndFiles", bookAndFilesWrapper);

        // Add authors to model attributes
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);

        // Add shelf type to model attributes
        List<Shelf> shelves = shelfService.findAll();
        model.addAttribute("shelves", shelves);
        // Send over to our form
        return "library/books/update-book";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("bookId") int theId){
        bookService.deleteById(theId);

        return "redirect:/books/list";
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
