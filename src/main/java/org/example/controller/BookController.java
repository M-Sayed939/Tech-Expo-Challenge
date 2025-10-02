package org.example.controller;

import org.example.dto.BookResponse;
import org.example.dto.CreateBookRequest;
import org.example.dto.UpdateBookRequest;
import org.example.mapper.BookMapper;
import org.example.model.Book;
import org.example.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;
    public BookController(BookService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody CreateBookRequest req) {
        Book created = service.create(req);
        BookResponse resp = BookMapper.toResponse(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> listBooks() {
        List<BookResponse> list = service.listAll().stream().map(BookMapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable("id") Long id) {
        Book b = service.getById(id);
        return ResponseEntity.ok(BookMapper.toResponse(b));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable("id") Long id,
                                                   @Valid @RequestBody UpdateBookRequest req) {
        Book updated = service.update(id, req);
        return ResponseEntity.ok(BookMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

