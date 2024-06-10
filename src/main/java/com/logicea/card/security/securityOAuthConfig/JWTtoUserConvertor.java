package com.logicea.card.security.securityOAuthConfig;

import java.util.Collections;

import com.logicea.card.model.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;



@Component
public class JWTtoUserConvertor implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
        UserEntity user = new UserEntity();
        user.setId(Integer.parseInt(source.getSubject()));
        return new UsernamePasswordAuthenticationToken(user, source, Collections.EMPTY_LIST);
    }


}
