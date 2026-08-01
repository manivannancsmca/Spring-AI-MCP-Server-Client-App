package com.mcp_server_app.tool;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import com.mcp_server_app.dto.BookRequest;
import com.mcp_server_app.dto.BookResponse;
import com.mcp_server_app.exception.BookNotFoundException;
import com.mcp_server_app.service.BookService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookTools {

    private final BookService bookService;

    @Tool(name = "createBook", description = "Create a new book record with title, author, ISBN, published year, and price")
    public BookResponse createBook(
            @ToolParam(description = "Title of the book") String title,
            @ToolParam(description = "Author of the book") String author,
            @ToolParam(description = "ISBN-10 or ISBN-13, optional", required = false) String isbn,
            @ToolParam(description = "Year the book was published, optional", required = false) Integer publishedYear,
            @ToolParam(description = "Price of the book in USD, optional", required = false) Double price) {
        var request = new BookRequest(title, author, isbn, publishedYear,
                price == null ? null : java.math.BigDecimal.valueOf(price));
        return bookService.createBook(request);
    }

    @Tool(name = "getBookById", description = "Retrieve a single book by its numeric ID")
    public BookResponse getBookById(@ToolParam(description = "The book's database ID") Long id) {
        try {
            return bookService.getBookById(id);
        } catch (BookNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Tool(name = "listBooks", description = "List all books currently stored in the library")
    public List<BookResponse> listBooks() {
        return bookService.listBooks();
    }

    @Tool(name = "searchBooksByAuthor", description = "Search for books by author name (partial, case-insensitive match)")
    public List<BookResponse> searchBooksByAuthor(@ToolParam(description = "Full or partial author name") String author) {
        return bookService.searchByAuthor(author);
    }

    @Tool(name = "updateBook", description = "Update an existing book's details by ID")
    public BookResponse updateBook(
            @ToolParam(description = "ID of the book to update") Long id,
            @ToolParam(description = "New title") String title,
            @ToolParam(description = "New author") String author,
            @ToolParam(description = "New ISBN, optional", required = false) String isbn,
            @ToolParam(description = "New published year, optional", required = false) Integer publishedYear,
            @ToolParam(description = "New price, optional", required = false) Double price) {
        var request = new BookRequest(title, author, isbn, publishedYear,
                price == null ? null : java.math.BigDecimal.valueOf(price));
        try {
            return bookService.updateBook(id, request);
        } catch (BookNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Tool(name = "deleteBook", description = "Delete a book by its ID")
    public String deleteBook(@ToolParam(description = "ID of the book to delete") Long id) {
        try {
            bookService.deleteBook(id);
            return "Book with id " + id + " was deleted successfully.";
        } catch (BookNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
