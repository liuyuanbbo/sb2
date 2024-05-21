package org.zmz.security.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zmz.security.model.Book;
import org.zmz.security.service.BookService;
import org.zmz.security.vo.request.Demo1Request;
import org.zmz.security.vo.response.R;

@RestController
@Slf4j
public class DemoController {

    ObjectMapper objectMapper;
    BookService bookService;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/demo1")
    public R<?> demo1(@RequestBody Demo1Request demoRequest) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(demoRequest);
        log.info("{}", json);
        return R.ok(demoRequest);
    }

    @GetMapping("/demo2/{id}")
    public R<?> demo2(@PathVariable Long id) {
        Book book = bookService.getFromCacheOrDb(id);
        return R.ok(book);
    }

    @GetMapping("/c2")
    public String c2(String ip) {
        log.debug("c-2");
        return ip;
    }

}
