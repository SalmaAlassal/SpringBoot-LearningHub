package com.example.batchprocessor.batch.job;

import com.example.batchprocessor.batch.processor.TransactionProcessor;
import com.example.batchprocessor.batch.reader.CSVReader;
import com.example.batchprocessor.batch.writer.TransactionItemWriter;
import com.example.batchprocessor.model.Transaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//@EnableBatchProcessing
public class TransactionJob {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;


    @Bean
    public CSVReader csvReader() {
        return new CSVReader();
    }

    @Bean
    public TransactionProcessor processor() {
        return new TransactionProcessor();
    }

    @Bean
    public TransactionItemWriter writer() {
        return new TransactionItemWriter();
    }


    @Bean
    public Step transactionStep() {
        return new StepBuilder("transactionStep", jobRepository)
                .<Transaction, Transaction>chunk(10, transactionManager)
                .reader(csvReader().transactionReader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job job() {
        return new JobBuilder("classifyTransactionJob", jobRepository)
                .start(transactionStep())
                .build();
    }
}