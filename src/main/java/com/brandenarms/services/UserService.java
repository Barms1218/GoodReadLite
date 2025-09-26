package com.brandenarms.services;

import com.brandenarms.models.User;
import com.brandenarms.models.Book;
import com.brandenarms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

//    public Boolean removeBook(Book book) {
//
//    }
}
