package com.lesan.library.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    @NotBlank(message = "Title cannot be empty")
    @Size(max = 200, message = "Title cannot be longer than 200 characters")
    private String title;

    @NotBlank(message = "Author cannot be empty")
    @Size(max = 100, message = "Author cannot be longer than 200 characters")
    private String author;

    @Min(value = 1, message = "Quantity cannot be small than 1")
    @Max(value = 50, message = "Quantity cannot be larger than 50")
    private int quantity;
}
