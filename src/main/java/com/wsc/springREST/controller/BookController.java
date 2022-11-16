package com.wsc.springREST.controller;

import org.springframework.web.bind.annotation.RestController;

import com.wsc.springREST.entity.Book;
import com.wsc.springREST.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    //Search Books
    @GetMapping("/books")
    public List<Book> index(){
        return bookRepository.findAll();
    }

    //Search a Book by id
    @GetMapping("/books/{id}")
    public Book show(@PathVariable int id){
        return bookRepository.findById(id).get();
    }

    //Search a Book with a specific text
    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    //Create a new Book
    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    //Update a Book
    @PutMapping("/books/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){

        Book bookToUpdate = bookRepository.findById(id).get();
        //Modify title
        bookToUpdate.setTitle(book.getTitle());
        //Modify author
        bookToUpdate.setAuthor(book.getAuthor());
        //Modify description
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }

    //Delete a Book
    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable int id){
        bookRepository.deleteById(id);
        return true;
    }
}