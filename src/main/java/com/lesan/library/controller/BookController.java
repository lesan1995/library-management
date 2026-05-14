package com.lesan.library.controller;

import com.lesan.library.dto.BookRequest;
import com.lesan.library.dto.BookResponse;
import com.lesan.library.exception.BookException;
import com.lesan.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest request) throws BookException {
        BookResponse response = bookService.addBook(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable UUID id) throws BookException {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("/{id}/borrow")
    public ResponseEntity<BookResponse> borrowBook(@PathVariable UUID id, @RequestParam int amount) throws BookException{
        return ResponseEntity.ok(bookService.borrowBook(id, amount));
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<BookResponse> returnBook(@PathVariable UUID id, @RequestParam int amount) throws BookException{
        return ResponseEntity.ok(bookService.returnBook(id, amount));
    }
}
