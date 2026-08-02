package com.mcp_server_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcp_server_app.dto.BookRequest;
import com.mcp_server_app.dto.BookResponse;
import com.mcp_server_app.entity.Book;
import com.mcp_server_app.exception.BookNotFoundException;
import com.mcp_server_app.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public BookResponse createBook(BookRequest request) {
        Book book = Book.builder()
                .title(request.title())
                .author(request.author())
                .isbn(request.isbn())
                .publishedYear(request.publishedYear())
                .price(request.price())
                .build();
        return toResponse(bookRepository.save(book));
    }

    @Transactional(readOnly = true)
    public BookResponse getBookById(Long id) {
        return bookRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<BookResponse> listBooks() {
        return bookRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<BookResponse> searchByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author)
                .stream().map(this::toResponse).toList();
    }

    @Transactional
    public BookResponse updateBook(Long id, BookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setIsbn(request.isbn());
        book.setPublishedYear(request.publishedYear());
        book.setPrice(request.price());
        return toResponse(bookRepository.save(book));
    }

    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }

    private BookResponse toResponse(Book b) {
        return new BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getIsbn(),
                b.getPublishedYear(), b.getPrice(), b.getCreatedAt(), b.getUpdatedAt());
    }
}
