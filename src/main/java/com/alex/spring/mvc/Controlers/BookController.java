package com.alex.spring.mvc.Controlers;


import com.alex.spring.mvc.DAO.BookDAO;
import com.alex.spring.mvc.DAO.PersonDAO;
import com.alex.spring.mvc.models.Book;

import com.alex.spring.mvc.models.Person;
import com.alex.spring.mvc.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String indexBook(Model model){
        model.addAttribute("book", bookDAO.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", bookDAO.getBook(id));

        Optional<Person> bookOwner = bookDAO.getBookOwner(id);

        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", personDAO.index());

        return "book/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book, BindingResult bindingResult){

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors())
            return "book/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.getBook(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book,BindingResult bindingResult
            , @PathVariable("id") int id){

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors())
            return "book/edit";

        bookDAO.uppdate(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson){
        System.out.println(selectedPerson);
        bookDAO.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }
}
