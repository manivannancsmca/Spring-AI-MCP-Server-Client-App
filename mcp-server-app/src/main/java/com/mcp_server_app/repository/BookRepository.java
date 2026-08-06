package com.mcp_server_app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mcp_server_app.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    
    List<Book> findByAuthorContainingIgnoreCase(String author);
    
    Optional<Book> findByIsbn(String isbn);
}
