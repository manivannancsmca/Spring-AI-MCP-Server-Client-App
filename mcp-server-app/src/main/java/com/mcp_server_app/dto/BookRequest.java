package com.mcp_server_app.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record BookRequest(
        @NotBlank(message = "Title must not be blank") String title,
        @NotBlank(message = "Author must not be blank") String author,
        @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "Invalid ISBN") String isbn,
        @Min(value = 1450, message = "Year seems invalid") Integer publishedYear,
        @DecimalMin(value = "0.0", inclusive = true) BigDecimal price
) {}
