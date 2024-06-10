package com.logicea.card.service;

import com.logicea.card.model.dto.AuthRequestDto;
import com.logicea.card.model.dto.Token;
import com.logicea.card.model.dto.UserRequestDto;
import com.logicea.card.model.dto.UserResponseDto;
import com.logicea.card.model.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

public interface UserService {

  UserResponseDto addNewUser(UserRequestDto userRequestDto);

  Token generateToken(AuthRequestDto authRequestDto);

  UserEntity getAuthenticatedUser();

}
