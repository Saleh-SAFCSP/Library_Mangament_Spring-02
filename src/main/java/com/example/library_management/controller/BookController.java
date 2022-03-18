package com.example.library_management.controller;

import com.example.library_management.model.Book;
import com.example.library_management.model.ResponseAPI;
import com.example.library_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api/v1")
public class BookController {


    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("books")
    public ResponseEntity getBooks(){
        return ResponseEntity.status(200).body(bookService.getBooks());
    }

    @PostMapping("books")
    public ResponseEntity addBook(@RequestBody Book book){
        bookService.addBook(book);
        return ResponseEntity.status(201).body(new
                ResponseAPI("New book create",201));
    }
}
