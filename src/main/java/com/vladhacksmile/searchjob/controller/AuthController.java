package com.vladhacksmile.searchjob.controller;

import com.vladhacksmile.searchjob.dto.auth.AuthRequest;
import com.vladhacksmile.searchjob.dto.auth.RegisterRequest;
import com.vladhacksmile.searchjob.dto.MessageResponse;
import com.vladhacksmile.searchjob.dto.auth.refresh.TokenRefreshDTO;
import com.vladhacksmile.searchjob.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public MessageResponse register(@RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            return ResponseEntity.ok(userService.login(authRequest));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshDTO request) {
        return ResponseEntity.ok(userService.refreshToken(request));
    }
}
