package com.skolefun.config.jwt;

import com.skolefun.model.Role;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtTokenUserDataService {

//    private UserRepository userRepository;
//
//    @Autowired
//    public JwtTokenUserDataService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public JwtTokenUserData createFromUsername(String username) {
//        User user = userRepository.findByUsername(username);
//        return new JwtTokenUserData(user.getId(), user.getUsername(), getBrandId(user), user.getRoles());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.TEACHER);
        return new JwtTokenUserData(1L, username, roles);
    }

}
