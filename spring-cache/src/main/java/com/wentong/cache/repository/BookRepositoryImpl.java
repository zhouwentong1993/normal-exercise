package com.wentong.cache.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BookRepositoryImpl implements BookRepository {
    @Override
    @Cacheable(value = "books",key = "#isbn")
    public Book getByIsbn(String isbn) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Book(1L,isbn,"newTitle");
    }

    @Override
    @CacheEvict(value = "books",key = "#isbn")
    public void update(String isbn) {

    }

}
