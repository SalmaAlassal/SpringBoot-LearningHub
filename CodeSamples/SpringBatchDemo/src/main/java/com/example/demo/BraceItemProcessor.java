package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class BraceItemProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String item) throws Exception {
        String processedItem = "{" + item + "}";
        log.info("Processing the item: {}", processedItem);
        return processedItem;
    }
}