package com.example.jwt.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // ...
}

