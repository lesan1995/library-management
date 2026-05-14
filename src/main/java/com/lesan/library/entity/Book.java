package com.lesan.library.entity;

import com.lesan.library.exception.BookException;
import com.lesan.library.utils.StringUtils;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "books")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 200)
    private final String title;

    @Column(nullable = false, length = 200)
    private final String author;

    private int quantity;

    private int numberBorrowed = 0;

    public Book(String title, String author, int quantity) throws BookException {
        if(StringUtils.isNullOrEmpty(title) || StringUtils.isNullOrEmpty(author))
            throw new BookException("Title and author must be not empty");
        if(quantity < 0)
            throw new BookException("Quantity must be positive");
        this.title = title.trim();
        this.author = author.trim();
        this.quantity = quantity;
    }
    public void add(int quantity) throws BookException {
        if(quantity < 0)
            throw new BookException("Quantity must be positive");
        this.quantity += quantity;
    }
    public void borrow(int amount) throws BookException{
        if(amount < 0)
            throw new BookException("Amount to take must be positive");
        if(amount > this.quantity)
            throw new BookException("Amount to take cannot be larger than current quantity of book");
        this.quantity -= amount;
        this.numberBorrowed += amount;
    }
    public void returnBook(int amount) throws BookException{
        if(amount < 0)
            throw new BookException("Amount to return must be positive");
        if(amount > this.numberBorrowed)
            throw new BookException("Amount to return cannot be larger than number borrowed of book");
        this.quantity += amount;
        this.numberBorrowed -= amount;
    }
    public boolean isBook(String title, String author){
        return this.title.equalsIgnoreCase(title.trim()) && this.author.equalsIgnoreCase(author.trim());
    }
}
