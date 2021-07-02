package ru.orion.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.orion.library.models.Book;
import ru.orion.library.repositories.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/books")
public class MainPageController {
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/")
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

}
