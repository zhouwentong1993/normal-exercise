package com.wentong.cache.repository;

public interface BookRepository {

    Book getByIsbn(String isbn);

    void update(String isbn);

}
