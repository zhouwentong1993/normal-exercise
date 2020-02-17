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
        for (int i = 0; i < 3; i++) {
            Book book = repository.getByIsbn(isbn + i);
            System.out.println(book);
        }
    }

}
