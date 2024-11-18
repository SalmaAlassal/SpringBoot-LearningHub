package com.example.demo;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//@EnableBatchProcessing
public class BatchConfiguration {

    @Bean
    public CustomItemReader reader() {
        List<String> inputList = List.of("word1", "word2", "word3", "word4", "word5", "word6");
        return new CustomItemReader(inputList);
    }

    @Bean
    public CustomItemReader readerStep2() {
        List<String> inputList = List.of("word1", "word2", "word3", "word4", "word5", "word6");
        return new CustomItemReader(inputList);
    }


    @Bean
    public CustomItemProcessor processor() {
        return new CustomItemProcessor();
    }

    @Bean
    public BraceItemProcessor braceProcessor() {
        return new BraceItemProcessor();
    }

    @Bean
    public CustomItemWriter writer() {
        return new CustomItemWriter();
    }

    @Bean
    public JobNotificationListener listener() {
        return new JobNotificationListener();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<String, String>chunk(3, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step2", jobRepository)
                .<String, String>chunk(3, transactionManager)
                .reader(readerStep2())
                .processor(braceProcessor())
                .writer(writer())
                .build();
    }


    @Bean
    public Job demoJob(JobRepository jobRepository, Step step1, Step step2) {
        return new JobBuilder("demoJob", jobRepository)
                .start(step1)
                .next(step2)
                .listener(listener())
                .build();
    }
}