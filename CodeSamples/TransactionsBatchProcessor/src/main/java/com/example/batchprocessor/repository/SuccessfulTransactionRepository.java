package com.example.batchprocessor.repository;

import com.example.batchprocessor.model.SuccessfulTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuccessfulTransactionRepository extends JpaRepository<SuccessfulTransaction, Long> {
}
