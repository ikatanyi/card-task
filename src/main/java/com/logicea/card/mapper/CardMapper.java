package com.logicea.card.mapper;

import com.logicea.card.model.dto.CardRequestDto;
import com.logicea.card.model.dto.CardResponseDto;
import com.logicea.card.model.dto.UserRequestDto;
import com.logicea.card.model.dto.UserResponseDto;
import com.logicea.card.model.entity.CardEntity;
import com.logicea.card.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,uses = {UserMapper.class})
public interface CardMapper {

   @Mapping(target = "createdOn", expression = "java(java.time.LocalDate.now())")
   CardEntity mapCardRequestDtoToCardEntity(CardRequestDto cardRequestDto);
   CardResponseDto mapCardEntityToCardResponseDto(CardEntity cardEntity);
}
