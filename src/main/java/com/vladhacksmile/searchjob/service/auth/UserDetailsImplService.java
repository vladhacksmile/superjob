package com.vladhacksmile.searchjob.service.auth;

import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImplService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByMail(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found with username: "+username));
        return UserDetailsImpl.build(user);
    }
}
