package com.example.batchprocessor.batch.processor;

import com.example.batchprocessor.model.FailedTransaction;
import com.example.batchprocessor.model.SuccessfulTransaction;
import com.example.batchprocessor.model.Transaction;
import org.springframework.batch.item.ItemProcessor;

public class TransactionProcessor implements ItemProcessor<Transaction, Transaction> {
    @Override
    public Transaction process(Transaction transaction) throws Exception {
        if (transaction.getAmount().compareTo(0.0) > 0) {
            SuccessfulTransaction successfulTransaction = new SuccessfulTransaction();
            successfulTransaction.setAccountNumber(transaction.getAccountNumber());
            successfulTransaction.setAmount(transaction.getAmount());
            successfulTransaction.setType(transaction.getType());
            successfulTransaction.setTimestamp(transaction.getTimestamp());
            return successfulTransaction; // Valid transaction
        } else {
            FailedTransaction failedTransaction = new FailedTransaction();
            failedTransaction.setAccountNumber(transaction.getAccountNumber());
            failedTransaction.setAmount(transaction.getAmount());
            failedTransaction.setType(transaction.getType());
            failedTransaction.setTimestamp(transaction.getTimestamp());
            failedTransaction.setFailureReason("Invalid amount");
            return failedTransaction; // Invalid transaction
        }
    }
}
