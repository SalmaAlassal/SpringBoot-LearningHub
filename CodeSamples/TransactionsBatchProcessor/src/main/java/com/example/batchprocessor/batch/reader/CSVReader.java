package com.example.batchprocessor.batch.reader;

import com.example.batchprocessor.model.Transaction;
import org.springframework.batch.item.file.FlatFileItemReader;

import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

public class CSVReader {

    @Value("${input.file.name}")
    private String inputFileName;

    public FlatFileItemReader<Transaction> transactionReader() {
        return new FlatFileItemReaderBuilder<Transaction>()
                .name("transactionItemReader")
                .resource(new ClassPathResource(inputFileName))
                .delimited()
                .delimiter(",")
                .names("transactionId", "accountNumber", "amount", "type", "timestamp")
                .linesToSkip(1)
                .fieldSetMapper(fieldSet -> {
                    Transaction transaction = new Transaction();
                    transaction.setId(fieldSet.readLong("transactionId"));
                    transaction.setAccountNumber(fieldSet.readString("accountNumber"));
                    transaction.setAmount(fieldSet.readDouble("amount"));
                    transaction.setType(fieldSet.readString("type"));
                    transaction.setTimestamp(fieldSet.readDate("timestamp"));
                    return transaction;
                })
                .build();
    }
}