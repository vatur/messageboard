package com.vkt_board.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user/{id}")
    public User get (@PathVariable Integer id) {
        return service.get(id);
    }

    @PostMapping("/user")
    public User create (@RequestBody User user) {
        return service.create(user);
    }

    @PutMapping("/user")
    public User update (@RequestBody User user) {
        return service.update (user);
    }

    @DeleteMapping("/user/{id}")
    public void delete (@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/user/list")
    public List<User> getFullList () {
        return service.getAll();
    }

}
