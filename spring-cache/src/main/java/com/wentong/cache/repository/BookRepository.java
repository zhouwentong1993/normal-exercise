package com.wentong.cache.repository;

public interface BookRepository {

    Book getByIsbn(Book book);

    void update(Book book);

}
