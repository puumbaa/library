package ru.orion.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.orion.library.enums.BookStatus;
import ru.orion.library.models.Book;
import ru.orion.library.repositories.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping()
    public List<Book> getBooks(@RequestParam(required = false, name = "status")BookStatus status){
        if (status==null) return bookRepository.findAll();
        return bookRepository.findAllByStatus(status);
    }


}
