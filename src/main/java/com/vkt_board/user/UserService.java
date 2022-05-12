package com.vkt_board.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User get (Integer id) {
        return userRepository.findById(id)
                             .orElse(null);
    }

    public User create (User user) {
        return userRepository.save(user);
    }

    public User update (User user) {
        return userRepository.save(user);
    }

    public void delete (Integer id) {
        userRepository.deleteById(id);
    }

    public List<User> getAll () {
        return (List<User>) userRepository.findAll();
    }

    public boolean isExist (Integer id) {
        return userRepository.existsById(id);
    }

}
