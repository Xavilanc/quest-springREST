package com.wsc.springREST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wsc.springREST.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    // custom query to search a book by title or description
    List<Book> findByTitleContainingOrDescriptionContaining(String text, String textAgain);
    
}