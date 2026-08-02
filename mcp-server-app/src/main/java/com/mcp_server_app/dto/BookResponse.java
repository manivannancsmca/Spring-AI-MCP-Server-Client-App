package com.mcp_server_app.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookResponse(
        Long id,
        String title,
        String author,
        String isbn,
        Integer publishedYear,
        BigDecimal price,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
