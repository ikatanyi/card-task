package com.logicea.card.security.securityOAuthConfig;

import com.logicea.card.service.impl.CustomUserDetailsServiceImpl;
import java.util.Collections;

import com.logicea.card.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class JWTtoUserConvertor implements Converter<Jwt, UsernamePasswordAuthenticationToken> {
    private final CustomUserDetailsServiceImpl userDetailsService;
    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
        UserDetails user = userDetailsService.loadUserByUsername(source.getSubject());
        return new UsernamePasswordAuthenticationToken(user, source, user.getAuthorities());
    }


}
