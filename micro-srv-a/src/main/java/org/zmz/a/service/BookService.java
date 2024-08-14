package org.zmz.a.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.zmz.a.model.Book;
import org.zmz.a.repo.BookCacheRepo;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class BookService {

    BookCacheRepo bookCacheRepo;

    @Autowired
    public void setBookRepo(BookCacheRepo bookCacheRepo) {
        this.bookCacheRepo = bookCacheRepo;
    }

    private static final List<Book> BOOKS = List.of(
            new Book(1L, "java", BigDecimal.valueOf(9.99), List.of("1", "11")),
            new Book(2L, "python", BigDecimal.valueOf(19.99), List.of("12", "22")),
            new Book(3L, "go", BigDecimal.valueOf(29.99), List.of("3", "33")),
            new Book(4L, "rust", BigDecimal.valueOf(39.99), List.of("4", "44")),
            new Book(5L, "linux", BigDecimal.valueOf(49.99), List.of("5", "555")),
            new Book(6L, "typescript", BigDecimal.valueOf(59.99), List.of("6", "666666"))
    );

    @Cacheable(value = "book", key = "#id")
    public Book getFromCacheOrDb(Long id) {
        Book optionalBook = bookCacheRepo.findById(id);
        if (optionalBook != null) {
            log.info("命中缓存 直接从缓存中返回: {}", optionalBook);
            return optionalBook;
        }
        log.info("没有命中缓存 查询DB返回");
        return BOOKS.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }


}
