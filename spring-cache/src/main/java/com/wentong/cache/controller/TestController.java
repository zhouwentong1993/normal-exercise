package com.wentong.cache.controller;

import com.wentong.cache.repository.Book;
import com.wentong.cache.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private BookRepository repository;

    @GetMapping("get/{isbn}")
    public void getInfo(@PathVariable String isbn) {
        for (int i = 0; i < 2; i++) {
            Book book = repository.getByIsbn(new Book((long) i, isbn, "title"));
            System.out.println(book);
        }
    }

    @GetMapping("update/{isbn}")
    public void update(@PathVariable String isbn) {
        repository.update(new Book(1L, isbn, "title"));
    }

}
