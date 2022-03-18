package com.example.library_management.controller;

import com.example.library_management.model.Book;
import com.example.library_management.model.Loan;
import com.example.library_management.model.ResponseAPI;
import com.example.library_management.service.BookService;
import com.example.library_management.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class LoanController {


    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("loans")
    public ResponseEntity getLoans(){
        return ResponseEntity.status(200).body(loanService.getLoans());
    }

    @PostMapping("create-loan")
    public ResponseEntity addLoan(@RequestBody Loan loan){
        boolean isLoanAdded= loanService.addLoan(loan);

        if(!isLoanAdded){
            return ResponseEntity.status(400).body(new
                    ResponseAPI("Book or user is not present ",400));
        }
        return ResponseEntity.status(201).body(new
                ResponseAPI("New loan created",201));
    }

    @PostMapping("return-loan/{loanId}")
    public ResponseEntity returnLoan(@PathVariable Integer loanId){
        loanService.returnLoan(loanId);

        return ResponseEntity.status(201).body(new
                ResponseAPI("Loan returned",201));
    }
}
