package com.example.api.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // ToDo:登録APIにてハッシュ化の実装
    public User postLoginUser(String userName, String password){
        User user = userRepository.findByName(userName);
        if (user != null && password.equals(user.getPassword())){
            return user;
        }
        return null;
    }
}
