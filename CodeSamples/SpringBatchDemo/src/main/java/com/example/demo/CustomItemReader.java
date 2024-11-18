package com.example.demo;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomItemReader implements ItemReader<String> {
    private Iterator<String> inputList;

    public CustomItemReader(List<String> inputList) {
        this.inputList = inputList.iterator();
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (inputList.hasNext()) {
            var item = inputList.next();
            log.info("Reading the next item from the list which is: {}", item);
            return item;
        }
        return null;
    }
}