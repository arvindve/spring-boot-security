package com.example.security.securitydbexample.service;

import com.example.security.securitydbexample.model.CustomUserDetails;
import com.example.security.securitydbexample.model.Users;
import com.example.security.securitydbexample.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = usersRepository.findByName(userName);

        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return optionalUsers
                .map(CustomUserDetails::new).get();
    }
}
