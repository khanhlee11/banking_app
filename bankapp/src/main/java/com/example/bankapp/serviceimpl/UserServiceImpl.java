package com.example.bankapp.serviceimpl;

import com.example.bankapp.model.User;
import com.example.bankapp.repository.UserRepository;
import com.example.bankapp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        //TODO: mã hóa mật khẩu sau
        return userRepository.save(user);
    }
}
