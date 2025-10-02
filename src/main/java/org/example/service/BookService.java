package org.example.service;

import org.example.dto.CreateBookRequest;
import org.example.dto.UpdateBookRequest;
import org.example.exception.BookNotFoundException;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Book create(CreateBookRequest req) {
        try {
            Book b = new Book(req.getTitle(), req.getAuthor(), req.getYear(), req.getDescription());
            return repo.save(b);
        } catch (DataAccessException ex) {
            throw ex;
        }
    }

    @Transactional(readOnly = true)
    public List<Book> listAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Book getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Transactional
    public Book update(Long id, UpdateBookRequest req) {
        Book existing = repo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        existing.setTitle(req.getTitle());
        existing.setAuthor(req.getAuthor());
        existing.setYear(req.getYear());
        existing.setDescription(req.getDescription());
        return repo.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new BookNotFoundException(id);
        repo.deleteById(id);
    }
}

