package com.vladhacksmile.searchjob.service;

import com.vladhacksmile.searchjob.dto.RegisterDTO;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
        if (userRepository.existsByMail(registerDTO.getMail())) {
            return ResponseEntity
                    .badRequest()
                    .body("User with this email already exists!");
        }

        User user = new User(registerDTO.getRole(), registerDTO.getName(), registerDTO.getSurname(), registerDTO.getPatronymic(),
                registerDTO.getAge(), registerDTO.getNumber(), registerDTO.getMail());

        userRepository.save(user);
        return ResponseEntity.ok("User registered!");
    }
}
