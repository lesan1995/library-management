package com.lesan.library.utils;

import com.lesan.library.dto.BookResponse;
import com.lesan.library.entity.Book;

public class BookMapper {
    public static BookResponse convertToResponse(Book book){
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getQuantity(),
                book.getNumberBorrowed()
        );
    }
}
