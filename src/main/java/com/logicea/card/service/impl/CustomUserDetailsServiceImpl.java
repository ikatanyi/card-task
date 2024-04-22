package com.logicea.card.service.impl;

import com.logicea.card.mapper.UserMapper;
import com.logicea.card.model.dto.AuthRequestDto;
import com.logicea.card.model.dto.UserRequestDto;
import com.logicea.card.model.dto.UserResponseDto;
import com.logicea.card.repository.UserRepository;
import com.logicea.card.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
    }
}
