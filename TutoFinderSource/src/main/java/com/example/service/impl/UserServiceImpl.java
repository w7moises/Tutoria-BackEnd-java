package com.example.service.impl;

import com.example.entity.UserSignIn;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${tutoserver.default-rol}")
    private Long DEFAULT_ROL;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSignIn user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Usuario no existe", username));
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach( role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public Page<UserSignIn> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public UserSignIn registerUser(UserSignIn user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserSignIn userNew=userRepository.save(user);
        userRepository.register(userNew.getIdUser(),DEFAULT_ROL);
        return userNew;
    }
}
