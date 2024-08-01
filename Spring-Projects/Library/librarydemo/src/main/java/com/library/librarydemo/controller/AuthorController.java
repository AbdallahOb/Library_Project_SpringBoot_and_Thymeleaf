package com.library.librarydemo.controller;

import com.library.librarydemo.model.Author;
import com.library.librarydemo.model.Book;
import com.library.librarydemo.model.Shelf;
import com.library.librarydemo.service.AddressService;
import com.library.librarydemo.service.AuthorService;
import com.library.librarydemo.wrapper.AuthorAndAddress;
import com.library.librarydemo.wrapper.BookAndFilesWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public String listAuthors(Model model){
        List<Author> authors = authorService.findAll();

        model.addAttribute("authors", authors);

        return "library/authors/list-authors";
    }

    @GetMapping("/addAuthorForm")
    public String addBookForm(Model model){
        model.addAttribute("authorAndAddress", new AuthorAndAddress());

        return "library/authors/add-author";
    }

    @PostMapping("/save")
    public String saveBook(
            @ModelAttribute("authorAndAddress") AuthorAndAddress authorAndAddress,
            BindingResult result
    ){
        if(result.hasErrors()){
            System.out.println("No Errors SUCCESS!");
        }else{
            Author tempAuthor = authorAndAddress.getAuthor();
            tempAuthor.setAddress(authorAndAddress.getAddress());
            authorService.save(tempAuthor);
        }

        return "redirect:/authors/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("authorId") int theId, Model model){
        AuthorAndAddress authorAndAddress = new AuthorAndAddress();
        authorAndAddress.setAuthor(authorService.findById(theId));
        authorAndAddress.setAddress(authorAndAddress.getAuthor().getAddress());

        model.addAttribute("authorAndAddress", authorAndAddress);


        return "library/authors/update-author";
    }

    @GetMapping("/delete")
    public String deleteAuthor(@RequestParam("authorId") int theId){
        Author tempAuthor = authorService.findById(theId);
        List<Book> books = tempAuthor.getBooks();
        for(Book book : books){
            book.setAuthor(null);
        }

        authorService.deleteById(theId);
        return "redirect:/authors/list";
    }
}
