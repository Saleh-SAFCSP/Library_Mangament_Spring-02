package com.example.library_management.service;

import com.example.library_management.model.Book;
import com.example.library_management.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final BookRepository bookRepository;

    public UserService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }
}
