package com.vladhacksmile.searchjob.service.auth;

import com.vladhacksmile.searchjob.dto.AuthRequest;
import com.vladhacksmile.searchjob.dto.JwtResponse;
import com.vladhacksmile.searchjob.dto.MessageResponse;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.repository.UserRepository;
import com.vladhacksmile.searchjob.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public MessageResponse register(AuthRequest authRequest) {

        if(userRepository.existsByUsername(authRequest.getUsername())) {
            return new MessageResponse("Auth Error, User already exist");
        } else {
            User user = new User(authRequest.getUsername(),
                    passwordEncoder.encode(authRequest.getPassword()));
            userRepository.save(user);
            return new MessageResponse("Okay");
        }
    }

    public JwtResponse login(AuthRequest authRequest) {
        if (userRepository.existsByUsername(authRequest.getUsername())) {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername());
        } else {
            return new JwtResponse("ERROR",
                    1L,
                    "ERROR");
        }
    }
}
