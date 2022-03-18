package com.example.library_management.service;

import com.example.library_management.model.Author;
import com.example.library_management.model.Book;
import com.example.library_management.repository.AuthorRepository;
import com.example.library_management.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }
}
