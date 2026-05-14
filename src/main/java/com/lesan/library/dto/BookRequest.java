package com.lesan.library.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private String title;
    private String author;
    private int quantity;
}
