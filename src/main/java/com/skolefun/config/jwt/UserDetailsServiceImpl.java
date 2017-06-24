package com.skolefun.config.jwt;

import com.skolefun.model.Role;
import com.skolefun.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    private UserRepository userRepository;
//
//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.TEACHER);
        User user = new User(username, "password", roles);
        if (user == null) {
            return null;
        }
        String password = user.getPassword();
        Collection<? extends GrantedAuthority> authorities = user.getRoles().stream().map(Role::toSpringGrantedAuthority).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }

}
