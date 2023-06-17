package com.vladhacksmile.searchjob.security;

import com.vladhacksmile.searchjob.security.jwt.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                //.exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests(((auth) -> auth
//                .antMatchers("/v3/api-docs/**", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**", "/swagger-ui/**").permitAll()
//                .antMatchers("/api/auth/register").permitAll()
//                .antMatchers("/api/auth/login").permitAll()
//                .antMatchers("/api/auth/refreshtoken").permitAll()
//                .antMatchers("/api/vacancies/search/**").permitAll()
//                .antMatchers("/api/resumes/search/**").permitAll()
//                .antMatchers("/api/vacancies/response").hasAuthority(UserRole.EMPLOYER.getName())
//                .antMatchers("/api/resumes/**").hasAuthority(UserRole.EMPLOYER.getName())
//                .antMatchers("/api/vacancies/**").hasAuthority(UserRole.APPLICANT.getName())
//                .anyRequest().authenticated()
                        .anyRequest().permitAll()
                ));

//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}