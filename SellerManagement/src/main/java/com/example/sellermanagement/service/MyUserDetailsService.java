package com.example.sellermanagement.service;

import com.example.sellermanagement.entity.MyUserDetails;
import com.example.sellermanagement.entity.User;
import com.example.sellermanagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(userName);

        return user.map(MyUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("Not found " + userName));
    }
}
