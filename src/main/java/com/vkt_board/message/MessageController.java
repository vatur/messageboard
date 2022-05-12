package com.vkt_board.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService service;

    @GetMapping("/message/{id}")
    public Message get (@PathVariable("id") Integer messageId) {
        return service.get(messageId);
    }

    @PostMapping("/message")
    public Message create (@RequestBody Message message, @RequestHeader Integer userId) {
        return service.create(message, userId);
    }

    @PutMapping("/message")
    public Message update (@RequestBody Message message, @RequestHeader Integer userId) {
        return service.update (message, userId);
    }

    @DeleteMapping("/message/{id}")
    public void delete (@PathVariable("id") Integer messageId, @RequestHeader Integer userId) {
        service.delete(messageId, userId);
    }

    @GetMapping("/message/list")
    public List<Message> getFullList () {
        return service.getAll();
    }

    @GetMapping("/message/toplist")
    public List<Message> getTopList (@RequestHeader Integer page, @RequestHeader Integer size) {
        return service.getTopLit(page, size).getContent();
    }

    @PostMapping("/message/vote/{id}")
    public Message vote (@PathVariable("id") Integer messageId, @RequestHeader Integer userId,  @RequestParam Boolean isLike) {
        return service.vote(messageId, userId, isLike);
    }

}
