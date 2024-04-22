package com.logicea.card.mapper;

import com.logicea.card.model.dto.UserRequestDto;
import com.logicea.card.model.dto.UserResponseDto;
import com.logicea.card.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,uses = {RoleMapper.class})
public interface UserMapper {

   @Mapping(target = "roles",ignore = true)
   UserEntity mapUseRequestDtoToUserEntity(UserRequestDto userRequestDto);

   UserResponseDto mapUserEntityToUserResponseDto(UserEntity userEntity);
}
