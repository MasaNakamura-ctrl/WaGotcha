package com.example.api.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User getLoginUser(String userName, String password){
        User user = userRepository.findByName(userName);
        if (user != null && passwordEncoder.matches(password, user.getPassword())){
            return user;
        }
        return null;
    }
}
