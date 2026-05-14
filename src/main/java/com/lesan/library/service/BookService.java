package com.lesan.library.service;

import com.lesan.library.dto.BookRequest;
import com.lesan.library.dto.BookResponse;
import com.lesan.library.entity.Book;
import com.lesan.library.exception.BookException;
import com.lesan.library.repository.BookRepository;
import com.lesan.library.utils.BookMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BookResponse addBook(BookRequest request) throws BookException {
        Book book = new Book(request.getTitle(), request.getAuthor(), request.getQuantity());
        Book savedBook = bookRepository.save(book);
        return BookMapper.convertToResponse(savedBook);
    }

    public List<BookResponse> getAllBooks(){
        return bookRepository.findAll().stream()
                .map(BookMapper::convertToResponse)
                .toList();
    }

    public BookResponse getBookById(UUID id) throws BookException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookException("Book not found with id: " + id.toString()));
        return BookMapper.convertToResponse(book);
    }

    @Transactional
    public BookResponse borrowBook(UUID id, int amount) throws BookException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookException("Book not found with id: " + id.toString()));
        book.borrow(amount);
        Book savedBook = bookRepository.save(book);
        return BookMapper.convertToResponse(savedBook);
    }

    @Transactional
    public BookResponse returnBook(UUID id, int amount) throws BookException{
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookException("Book not found with id: " + id.toString()));
        book.returnBook(amount);
        Book savedBook = bookRepository.save(book);
        return BookMapper.convertToResponse(savedBook);
    }
}
