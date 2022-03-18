package com.example.library_management.repository;

import com.example.library_management.model.Loan;
import com.example.library_management.model.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Integer> {
}
