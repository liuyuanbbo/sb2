package org.zmz.a.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.common.R;
import org.zmz.a.model.Book;
import org.zmz.a.service.BookService;
import org.zmz.a.vo.request.Demo1Request;

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
