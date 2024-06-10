package com.logicea.card.service.impl;

import com.logicea.card.mapper.UserMapper;
import com.logicea.card.model.dto.AuthRequestDto;
import com.logicea.card.model.dto.Token;
import com.logicea.card.model.dto.UserRequestDto;
import com.logicea.card.model.dto.UserResponseDto;
import com.logicea.card.model.entity.UserEntity;
import com.logicea.card.repository.UserRepository;
import com.logicea.card.security.securityOAuthConfig.SecurityUtils;
import com.logicea.card.security.securityOAuthConfig.TokenGenerator;
import com.logicea.card.service.RoleService;
import com.logicea.card.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper userMapper;

    private final RoleService roleService;

    private final PasswordEncoder encoder;

    private final DaoAuthenticationProvider daoAuthenticationProvider;


    private final TokenGenerator tokenGenerator;

    public UserResponseDto addNewUser(UserRequestDto userRequestDto){
        var userEntity = userMapper.mapUseRequestDtoToUserEntity(userRequestDto);
        var roleEntity= roleService.getRoleByName(userRequestDto.roles().name());

        userEntity.setRoles(Collections.singleton(roleEntity));
        userEntity.setPassword(encoder.encode(userRequestDto.password()));

        var savedUser = repository.save(userEntity);
        return userMapper.mapUserEntityToUserResponseDto(savedUser);
    }

    public Token generateToken(AuthRequestDto authRequestDto) {
        Authentication authentication = daoAuthenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(authRequestDto.username(), authRequestDto.passWord()));
        return tokenGenerator.createToken(authentication);
    }

    public UserEntity getAuthenticatedUser() {

        var currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if(currentUserLogin.isPresent())
            return currentUserLogin.get();
        else{
            throw new UsernameNotFoundException("invalid user request");
        }
    }

}
