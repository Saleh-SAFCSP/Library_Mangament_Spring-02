package com.example.library_management.service;

import com.example.library_management.model.Author;
import com.example.library_management.model.Book;
import com.example.library_management.model.Loan;
import com.example.library_management.model.User;
import com.example.library_management.repository.AuthorRepository;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.LoanRepository;
import com.example.library_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public List<Loan> getLoans(){
        return loanRepository.findAll();
    }

    public boolean addLoan(Loan loan) {
        Optional<Book> book=bookRepository.findById(loan.getBook_id());

        if(book.isEmpty()){
             return false;
        }

        Optional<User> user=userRepository.findById(loan.getUser_id());

        if(user.isEmpty()){
            return false;
        }


        loanRepository.save(loan);

        return true;
    }

    public void returnLoan(Integer loanId) {
        Loan loan=loanRepository.getById(loanId);
        loan.setReturned(true);
        loanRepository.save(loan);
    }
}
