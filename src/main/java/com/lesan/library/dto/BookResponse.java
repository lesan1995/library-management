package com.lesan.library.dto;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private UUID id;
    private String title;
    private String author;
    private int quantity;
    private int numberBorrowed;
}
