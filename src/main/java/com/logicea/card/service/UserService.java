package com.logicea.card.service;

import com.logicea.card.model.dto.AuthRequestDto;
import com.logicea.card.model.dto.UserRequestDto;
import com.logicea.card.model.dto.UserResponseDto;
import com.logicea.card.model.entity.UserEntity;

public interface UserService {

  UserResponseDto addNewUser(UserRequestDto userRequestDto);

  String generateToken(AuthRequestDto authRequestDto);

  UserEntity getUserByUsername(String username);

  UserEntity getAuthenticatedUser();

}
