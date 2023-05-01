package com.vladhacksmile.searchjob.service.auth;

import com.vladhacksmile.searchjob.dto.AuthRequest;
import com.vladhacksmile.searchjob.dto.RegisterRequest;
import com.vladhacksmile.searchjob.dto.JwtResponse;
import com.vladhacksmile.searchjob.dto.MessageResponse;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.repository.RoleRepository;
import com.vladhacksmile.searchjob.repository.UserRepository;
import com.vladhacksmile.searchjob.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public MessageResponse register(RegisterRequest registerRequest) {

        if(userRepository.existsByMail(registerRequest.getMail())) {
            return new MessageResponse("Auth Error, User already exist");
        } else {
            User user = new User(registerRequest.getName(), registerRequest.getSurname(), registerRequest.getPatronymic(),
                    registerRequest.getAge(), registerRequest.getNumber(), registerRequest.getMail(),
                    passwordEncoder.encode(registerRequest.getPassword()), registerRequest.getRole());
            userRepository.save(user);
            return new MessageResponse("Okay");
        }
    }

    public JwtResponse login(AuthRequest authRequest) {
        if (userRepository.existsByMail(authRequest.getMail())) {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            authRequest.getMail(),
                            authRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            User userDetails = (User) authentication.getPrincipal();
            return new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getMail());
        } else {
            return new JwtResponse("ERROR",
                    1L,
                    "ERROR");
        }
    }
}
