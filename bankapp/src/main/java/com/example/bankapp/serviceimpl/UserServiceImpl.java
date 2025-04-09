package com.example.bankapp.serviceimpl;

import com.example.bankapp.model.User;
import com.example.bankapp.repository.UserRepository;
import com.example.bankapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User register(User user) {
        //TODO: mã hóa mật khẩu sau
        return userRepository.save(user);
    }
}
