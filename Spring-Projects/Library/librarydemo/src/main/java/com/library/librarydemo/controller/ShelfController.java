package com.library.librarydemo.controller;

import com.library.librarydemo.model.Author;
import com.library.librarydemo.model.Book;
import com.library.librarydemo.model.Shelf;
import com.library.librarydemo.service.ShelfService;
import com.library.librarydemo.wrapper.AuthorAndAddress;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("shelves")
public class ShelfController {
    private ShelfService shelfService;

    public ShelfController(ShelfService shelfService) {
        this.shelfService = shelfService;
    }

    @GetMapping("/list")
    public String listAuthors(Model model){
        List<Shelf> shelves = shelfService.findAll();

        model.addAttribute("shelves", shelves);


        return "library/shelves/list-shelves";
    }

    @GetMapping("/addShelfForm")
    public String addBookForm(Model model){
        model.addAttribute("shelf", new Shelf());

        return "library/shelves/add-shelf";
    }

    @PostMapping("/save")
    public String saveShelf(
            @ModelAttribute("shelf") Shelf shelf,
            BindingResult result
    ){
        if(result.hasErrors()){
            System.out.println("No Errors SUCCESS!");
        }else{
            shelfService.save(shelf);
        }

        return "redirect:/shelves/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("shelfId") int theId, Model model){


        model.addAttribute("shelf", shelfService.findById(theId));


        return "library/shelves/update-shelf";
    }

    @GetMapping("/delete")
    public String deleteShelf(@RequestParam("shelfId") int theId){
        shelfService.deleteById(theId);
        return "redirect:/shelves/list";
    }

}
