package com.example.batchprocessor.batch.writer;

import com.example.batchprocessor.model.FailedTransaction;
import com.example.batchprocessor.model.SuccessfulTransaction;
import com.example.batchprocessor.model.Transaction;
import com.example.batchprocessor.repository.SuccessfulTransactionRepository;
import com.example.batchprocessor.repository.FailedTransactionRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionItemWriter implements ItemWriter<Transaction> {
    @Autowired
    private SuccessfulTransactionRepository successfulTransactionRepository;
    @Autowired
    private FailedTransactionRepository failedTransactionRepository;

    @Override
    public void write(Chunk<? extends Transaction> transactions) throws Exception {
        for (Transaction transaction : transactions) {
            if (transaction instanceof SuccessfulTransaction) {
                successfulTransactionRepository.save((SuccessfulTransaction) transaction);
            } else if (transaction instanceof FailedTransaction) {
                failedTransactionRepository.save((FailedTransaction) transaction);
            }
        }
    }
}