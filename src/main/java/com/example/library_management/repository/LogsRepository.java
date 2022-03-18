package com.example.library_management.repository;

import com.example.library_management.model.Logs;
import com.example.library_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends JpaRepository<Logs,Integer> {
}
