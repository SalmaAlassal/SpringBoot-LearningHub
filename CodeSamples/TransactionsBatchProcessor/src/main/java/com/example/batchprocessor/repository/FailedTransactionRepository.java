package com.example.batchprocessor.repository;

import com.example.batchprocessor.model.FailedTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedTransactionRepository extends JpaRepository<FailedTransaction, Long> {
}
