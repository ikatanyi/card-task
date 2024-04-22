package com.logicea.card.service.impl;

import com.logicea.card.exception.APIException;
import com.logicea.card.mapper.UserMapper;
import com.logicea.card.model.dto.AuthRequestDto;
import com.logicea.card.model.dto.UserRequestDto;
import com.logicea.card.model.dto.UserResponseDto;
import com.logicea.card.model.entity.UserEntity;
import com.logicea.card.repository.UserRepository;
import com.logicea.card.security.config.JwtService;
import com.logicea.card.security.config.SecurityUtils;
import com.logicea.card.service.RoleService;
import com.logicea.card.service.UserService;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper userMapper;

    private final RoleService roleService;

    private final JwtService jwtService;

    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    public UserResponseDto addNewUser(UserRequestDto userRequestDto){
        var userEntity = userMapper.mapUseRequestDtoToUserEntity(userRequestDto);

        var roleEntity= roleService.getRoleByName(userRequestDto.roles().name());

        userEntity.setRoles(Collections.singleton(roleEntity));

        userEntity.setPassword(encoder.encode(userRequestDto.password()));

        var savedUser = repository.save(userEntity);

        return userMapper.mapUserEntityToUserResponseDto(savedUser);
    }

    public String generateToken(AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.username(), authRequestDto.passWord()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequestDto.username());
        } else {
            throw new UsernameNotFoundException("invalid user request");
        }
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return repository.findByEmail(username)
            .orElseThrow(() ->  APIException.notFound("User not found by {}" + username));
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
