package com.vladhacksmile.searchjob.service.auth;

import com.vladhacksmile.searchjob.dto.MessageResponse;
import com.vladhacksmile.searchjob.dto.auth.AuthRequest;
import com.vladhacksmile.searchjob.dto.auth.JwtResponse;
import com.vladhacksmile.searchjob.dto.auth.RegisterRequest;
import com.vladhacksmile.searchjob.dto.auth.refresh.TokenRefreshDTO;
import com.vladhacksmile.searchjob.dto.auth.refresh.TokenRefreshResponse;
import com.vladhacksmile.searchjob.entities.RefreshToken;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.repository.RefreshTokenRepository;
import com.vladhacksmile.searchjob.repository.RoleRepository;
import com.vladhacksmile.searchjob.repository.UserRepository;
import com.vladhacksmile.searchjob.security.exception.TokenIncorrectException;
import com.vladhacksmile.searchjob.security.exception.TokenRefreshException;
import com.vladhacksmile.searchjob.security.exception.UserNotAuthException;
import com.vladhacksmile.searchjob.security.jwt.JwtUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

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

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserDetailsImplService userDetailsImplService;

    @Value("${app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    public MessageResponse register(RegisterRequest registerRequest) {

        if(userRepository.existsByMail(registerRequest.getMail())) {
           throw new TokenIncorrectException("Auth Error, User already exist");
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
            RefreshToken refreshToken = createRefreshToken(userDetails.getId());
            return new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getMail(), refreshToken.getToken());
        } else {
            throw new UserNotAuthException("Пользователь не найден!");
        }
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public TokenRefreshResponse refreshToken(TokenRefreshDTO request) {
        String requestRefreshToken = request.getRefreshToken();

        return findByToken(requestRefreshToken)
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return new TokenRefreshResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not correct!"));
    }

    // HS256

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    public void auth(DelegateExecution delegateExecution) {
        String username = (String) delegateExecution.getVariable("username");
        String password = (String) delegateExecution.getVariable("password");
        JwtResponse jwtResponse = login(new AuthRequest(username, password));

        String accessToken = jwtResponse.getToken();

        delegateExecution.setVariable("accessToken", accessToken);
    }

    public User authByToken(DelegateExecution delegateExecution) throws Exception {
        String jwt = (String) delegateExecution.getVariable("accessToken");
        User userDetails;
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt);

            userDetails = userDetailsImplService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            throw new TokenIncorrectException("Token incorrect");
        }

        return userDetails;
    }
}
