package com.example.library_management.service;

import com.example.library_management.model.Book;
import com.example.library_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }
}
