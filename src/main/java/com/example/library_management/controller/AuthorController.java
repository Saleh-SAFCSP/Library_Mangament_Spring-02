package com.example.library_management.controller;

import com.example.library_management.model.Author;
import com.example.library_management.model.Book;
import com.example.library_management.model.ResponseAPI;
import com.example.library_management.service.AuthorService;
import com.example.library_management.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class AuthorController {


    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("authors")
    public ResponseEntity getAuthors(){
        return ResponseEntity.status(200).body(authorService.getAuthors());
    }

    @PostMapping("authors")
    public ResponseEntity addAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
        return ResponseEntity.status(201).body(new
                ResponseAPI("New author create",201));
    }
}
