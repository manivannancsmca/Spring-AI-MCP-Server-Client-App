CREATE TABLE books (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(255)   NOT NULL,
    author        VARCHAR(255)   NOT NULL,
    isbn          VARCHAR(20)    UNIQUE,
    published_year INT,
    price         DECIMAL(10,2),
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_books_author ON books(author);