package com.brandenarms.services;

import com.brandenarms.dtos.UserResponseDTO;
import com.brandenarms.models.User;
import com.brandenarms.models.Book;
import com.brandenarms.repositories.BookRepository;
import com.brandenarms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.bcrypt.BcryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public UserService(BookRepository bookRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public UserResponseDTO registerUser(String username, String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User newUser = new User();

        newUser.setUsername(username);

        String hashedPassword = passwordEncoder.encode(password);
        newUser.setPasswordHash(hashedPassword);

        saveUser(newUser);

        return new UserResponseDTO(username);
    }

    public boolean loginUser(String username, String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User existingUser = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found."));

        if (passwordEncoder.encode(password) == existingUser.getPasswordHash()) {
            return true;
        }

        return false;
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long userId) {
        User user;

        user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist."));

        userRepository.delete(user);
    }
}
