package org.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private Integer year;
    private String description;
    private Instant createdAt;

}

