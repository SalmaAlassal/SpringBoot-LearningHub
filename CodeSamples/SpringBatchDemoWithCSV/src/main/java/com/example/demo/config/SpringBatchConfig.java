package com.example.demo.config;

import com.example.demo.batch.DBWriter;
import com.example.demo.batch.Processor;
import com.example.demo.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SpringBatchConfig {
    @Value("${input.file.name}")
    private String inputFileName;

    @Bean
    public FlatFileItemReader<User> reader() {
        return new FlatFileItemReaderBuilder<User>()
                .name("userItemReader")
                .resource(new ClassPathResource(inputFileName))
                .delimited()
                .delimiter(",")
                .names("id", "name", "dept", "salary")
                .linesToSkip(1)
                .fieldSetMapper(fieldSet -> {
                    User user = new User();
                    user.setId(fieldSet.readInt("id"));
                    user.setName(fieldSet.readString("name"));
                    user.setDept(fieldSet.readString("dept"));
                    user.setSalary(fieldSet.readDouble("salary"));
                    return user;
                })
                .build();
    }

    @Bean
    public Processor processor() {
        return new Processor();
    }

    @Bean
    public ItemWriter<User> writer() {
        return new DBWriter();
    }

    // Chunk-Oriented Step
    /**
     * JobRepository is used to allow the step to persist its execution data.
     * PlatformTransactionManager is used to manage the transaction(commit or rollback) of the step.
     */
    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        // The StepBuilder is used to create a step
        return new StepBuilder("step1", jobRepository) // The name of the step is step1.
                .<User, User>chunk(2, transactionManager) // Process 2 records at a time
                .reader(reader()) // Reads data in each chunk using the reader() method
                .processor(processor()) // Processes the data in each chunk using the processor() method
                .writer(writer()) // Writes the data in each chunk using the writer() method
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("demoJob", jobRepository)
                .start(step(jobRepository, transactionManager))
                .build();
    }
}