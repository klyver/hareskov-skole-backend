package com.skolefun.config.jwt;

import com.skolefun.model.User;
import com.skolefun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenUserDataService {

    private UserRepository userRepository;

    @Autowired
    public JwtTokenUserDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public JwtTokenUserData createFromUsername(String username) {
        User user = userRepository.findByUsername(username);
        return new JwtTokenUserData(user.getId(), user.getUsername(), user.getRoles());
    }

}
