package org.example.mapper;

import org.example.dto.BookResponse;
import org.example.model.Book;

public class BookMapper {
    public static BookResponse toResponse(Book b) {
        BookResponse res = new BookResponse();
        res.setId(b.getId());
        res.setTitle(b.getTitle());
        res.setAuthor(b.getAuthor());
        res.setYear(b.getYear());
        res.setDescription(b.getDescription());
        res.setCreatedAt(b.getCreatedAt());
        return res;
    }
}

